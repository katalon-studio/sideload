package com.katalon.sideload.utils;

import com.katalon.utils.Logger;
import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

class FileUtils {

    static void downloadAndExtract(
            Logger logger, String fileUrl, File targetDir)
            throws IOException, InterruptedException {

        LogUtils.info(logger, "Downloading Katalon Studio from " + fileUrl + ". It may take a few minutes.");

        URL url = new URL(fileUrl);

        try (InputStream inputStream = url.openStream()) {
            Path temporaryFile = Files.createTempFile("Katalon-", "");
            Files.copy(
                    inputStream,
                    temporaryFile,
                    StandardCopyOption.REPLACE_EXISTING);

            LogUtils.info(logger, "Extract " + temporaryFile.toString() + " to " + targetDir);
            if (fileUrl.contains(".zip")) {
                Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.ZIP);
                archiver.extract(temporaryFile.toFile(), targetDir);
            } else if (fileUrl.contains(".tar.gz")) {
                // jarchivelib had bug
                String command = "tar -xzf \"" + temporaryFile.toAbsolutePath() + "\"";
                OsUtils.runCommand(
                        logger,
                        command,
                        targetDir.toPath(),
                        null,
                        null,
                        null);
            } else {
                throw new IllegalStateException();
            }
        }
    }

}
