package com.katalon.sideload;

import com.katalon.sideload.utils.LogUtils;
import com.katalon.sideload.utils.OsUtils;
import com.microsoft.appcenter.appium.Factory;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestWatcher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.logging.Logger;

public class SideloadTest {

    public static final String TEST_PROJECT_PATH = "MSAppCenter.zip";

    public static final String TEST_BINARY = "com.example.helloWorld.product-macosx.cocoa.x86_64.tar.gz";

    private final static Logger LOGGER = Logger.getLogger(SideloadTest.class.getName());

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Ignore
    public void print() {
        System.out.println("====== SYSTEM PROPERTIES ======");
        System.getProperties().forEach((key, value) -> {
            System.out.println(key + "=" + value);
        });

        System.out.println("====== SYSTEM ENVIRONMENT ======");
        System.getenv().entrySet().forEach((entry) -> {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        });
        System.out.println("==============================");
    }

    @Test
    @Ignore
    public void executeEclipseBinary() throws IOException, InterruptedException {
        com.katalon.utils.Logger logger = new com.katalon.sideload.utils.ConsoleLogger(LOGGER);
        String dirPath = "tmp";
        File dir = Paths.get(dirPath).toAbsolutePath().toFile();
        dir.mkdirs();

        extractBinary(logger, TEST_BINARY, dirPath);

        File bin = Paths.get(dirPath, "Headless Example.app", "Contents", "MacOS", "eclipse").toFile();
        executeBinary(logger, bin, "", dir);
    }

    @Test
    public void sideload() {
        com.katalon.utils.Logger consoleLogger = new com.katalon.sideload.utils.ConsoleLogger(LOGGER);
        String version = "7.2.7";
        String projectPath = "MSAppCenter";
        String executeArgs = "-retry=0 -testSuitePath=\"Test Suites/ts1\" -executionProfile=\"default\" -browserType=\"Chrome\" -apiKey=\"f907ee68-f2b0-49a4-b6ef-b0e50f9b59d8\"";
        boolean result = SideloadUtils.executeKatalon(TEST_PROJECT_PATH,
                consoleLogger,
                version,
                null,
                projectPath,
                executeArgs,
                null,
                null,
                System.getenv());
    }

    private void extractBinary(com.katalon.utils.Logger logger, String filePath, String targetDir) throws IOException, InterruptedException {
        File dir = Paths.get(targetDir).toAbsolutePath().toFile();

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);
        if (is == null) {
            LogUtils.info(logger, MessageFormat.format("{0} NOT EXISTS.", filePath));
        }
        File tmp = File.createTempFile("archive-", filePath, dir);
        tmp.deleteOnExit();

        FileUtils.copyToFile(is, tmp);
        String extractCommand = MessageFormat.format("tar -xzf \"{0}\"", tmp.getAbsolutePath());
        OsUtils.runCommand(
                logger,
                extractCommand,
                dir.toPath(),
                null,
                null,
                null);
        LogUtils.info(logger, MessageFormat.format("Extract {0} to {1}.", tmp.toString(), targetDir));
    }

    private void executeBinary(com.katalon.utils.Logger logger, File binary, String execArgs, File dir) throws IOException, InterruptedException {
        if (binary.exists()) {
            binary.setExecutable(true);
            OsUtils.runCommand(logger, MessageFormat.format("\"{0}\" {1}", binary.getAbsolutePath(), execArgs), dir.toPath(), null, null, null);
        } else {
            LogUtils.info(logger, MessageFormat.format("{0} NOT EXISTS.", binary.toString()));
        }
    }
}
