package com.katalon.sideload;

import com.katalon.sideload.utils.ConsoleLogger;
import com.microsoft.appcenter.appium.Factory;
import org.junit.*;
import org.junit.rules.TestWatcher;

import java.util.HashMap;

import com.katalon.utils.Logger;

public class SideloadTest {
    /**
     * Your Katalon API Key
     */
    private static final String API_KEY = "<api_key>";

    /**
     * Katalon version which will be used to run the test
     */
    private static final String KATALON_VERSION = ""; // Leave it blank to always use the latest version

    /**
     * The package file under the "src/test/resources" folder
     */
    private static final String KATALON_PROJECT_PACKAGE_FILE = "KatalonDemoProject.zip";

    /**
     * Path to the katalon project inside the package file.
     * If not specified it will use the same name with the package file.
     * (In this case, it is: KatalonDemoProject)
     */
    private static final String KATALON_PROJECT_PATH = "";

    /**
     * Katalon arguments
     * @apiNote Remember to always set "browserType" to "Chrome". This will prevent Katalon from inject inappropriate configurations in execution.
     * @apiNote Besides, you do not need to include project path in the argument list.
     */
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

        boolean result = SideloadUtils.executeKatalon(
                projectPackageFile,
                version,
                null, // ksLocation
                projectPath,
                executeArgs,
                null, // x11Display
                null, // xvfbConfiguration
                new HashMap<>(System.getenv()));
        if (!result) {
            Assert.fail("Failed to execute Katalon");
        }
    }
}
