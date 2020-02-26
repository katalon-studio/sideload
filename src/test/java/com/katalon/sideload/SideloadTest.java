package com.katalon.sideload;

import com.microsoft.appcenter.appium.Factory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

import java.util.logging.Logger;

public class SideloadTest {

    public static final String TEST_PROJECT_PATH = "MSAppCenter.zip";

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
    public void sideload() {
        com.katalon.utils.Logger consoleLogger = new com.katalon.sideload.utils.ConsoleLogger(LOGGER);
        String version = "7.2.4";
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
}
