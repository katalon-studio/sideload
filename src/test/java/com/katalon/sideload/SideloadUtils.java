package com.katalon.sideload;

import com.katalon.sideload.utils.KatalonUtils;
import com.katalon.utils.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.file.Paths;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SideloadUtils {
    public static boolean executeKatalon(String testProjectPackage,
                                         @Nullable Logger logger,
                                         String version,
                                         String ksLocation,
                                         @Nullable String projectPath,
                                         String executeArgs,
                                         @Nullable String x11Display,
                                         @Nullable String xvfbConfiguration,
                                         Map<String, String> environmentVariablesMap) {
        InputStream is = SideloadUtils.class.getClassLoader().getResourceAsStream(testProjectPackage);
        String dirPath = "tmp";
        File dir = Paths.get(dirPath).toAbsolutePath().toFile();
        dir.mkdirs();

        if (is == null) {
            return false;
        }

        try {
            File tmp = File.createTempFile("test-", ".zip", dir);
            tmp.deleteOnExit();

            System.out.println(tmp);
            FileUtils.copyToFile(is, tmp);
            unzip(tmp, dir);

            String args = "-retry=0 -testSuitePath=\"Test Suites/ts1\" -executionProfile=\"default\" -browserType=\"Chrome\" -apiKey=\"f907ee68-f2b0-49a4-b6ef-b0e50f9b59d8\"";

            if (projectPath != null) {
                projectPath = Paths.get(dirPath, projectPath).toAbsolutePath().toString();
            }
            environmentVariablesMap.put("KATALON_HOME", dir.getAbsolutePath());
            environmentVariablesMap.put("ECLIPSE_SANDBOX", "1.11");
            return KatalonUtils.executeKatalon(logger, version, ksLocation, projectPath, args, x11Display, xvfbConfiguration, environmentVariablesMap, dirPath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void unzip(File zipFile, File destDir) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        for (ZipEntry zipEntry = zis.getNextEntry(); zipEntry != null; zipEntry = zis.getNextEntry()) {
            File newFile = newFile(destDir, zipEntry);

            if (zipEntry.isDirectory()) {
                newFile.mkdirs();
            } else {
                FileOutputStream fos = new FileOutputStream(newFile);
                IOUtils.copy(zis, fos);
                fos.close();
            }
        }

        zis.closeEntry();
        zis.close();
    }

    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        // handle Zip Slip vulnerability
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
