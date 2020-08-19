package com.katalon.sideload;

import com.katalon.sideload.utils.ConsoleLogger;
import com.microsoft.appcenter.appium.Factory;
import org.junit.*;
import org.junit.rules.TestWatcher;

import java.util.HashMap;
import java.util.logging.Logger;

public class SideloadTest {

    private static final Logger LOGGER = Logger.getLogger(SideloadTest.class.getName());

    private static final String API_KEY = "<api_key>";

    private static final String KATALON_VERSION = "7.2.7-beta";

    private static final String KATALON_PROJECT_PACKAGE_FILE = "KatalonDemoProject.zip";

    private static final String KATALON_PROJECT_PATH = "KatalonDemoProject";

    private static final String KATALON_EXECUTE_ARGS = String.format("-retry=0 -testSuitePath=\"Test Suites/Regression Tests\" -executionProfile=default -browserType=Chrome -apiKey=\"%s\"", API_KEY);

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sideload() {
        String version = SideloadUtils.getenv("KATALON_VERSION", KATALON_VERSION);
        String projectPackageFile = SideloadUtils.getenv("KATALON_PROJECT_PACKAGE_FILE", KATALON_PROJECT_PACKAGE_FILE);
        String projectPath = SideloadUtils.getenv("KATALON_PROJECT_PATH", KATALON_PROJECT_PATH);
        String executeArgs = SideloadUtils.getenv("KATALON_EXECUTE_ARGS", KATALON_EXECUTE_ARGS);

        com.katalon.utils.Logger consoleLogger = new ConsoleLogger(LOGGER);
        boolean result = SideloadUtils.executeKatalon(
                projectPackageFile,
                consoleLogger,
                version,
                null,
                projectPath,
                executeArgs,
                null,
                null,
                new HashMap<>(System.getenv()));
        if (!result) {
            Assert.fail("Failed to execute Katalon");
        }
    }
}
