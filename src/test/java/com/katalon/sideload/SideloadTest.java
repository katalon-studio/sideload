package com.katalon.sideload;

import com.microsoft.appcenter.appium.Factory;
import org.junit.*;
import org.junit.rules.TestWatcher;

import java.util.HashMap;
import java.util.logging.Logger;

public class SideloadTest {

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
        String katalonProjectPackageFile = "MSAppCenter.zip";
        String projectPath = "MSAppCenter";
        String executeArgs = "-retry=0 -testSuitePath=\"Test Suites/Test Suite 01\" -executionProfile=\"default\" -browserType=\"Chrome\" -apiKey=\"f9074412-f2b0-49a4-b6ef-b0e50f9b59d8\"";

        String katalonVersion = "7.2.7";
        com.katalon.utils.Logger consoleLogger = new com.katalon.sideload.utils.ConsoleLogger(LOGGER);
        boolean result = SideloadUtils.executeKatalon(
                katalonProjectPackageFile,
                consoleLogger,
                katalonVersion,
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
