import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.remote.DesiredCapabilities

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.internal.PathUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType

import com.kms.katalon.core.appium.driver.AppiumDriverManager

/**
 * Start Application has been moved to Test Listener.
 */

//if (GlobalVariable.isRunLocal) {
//	def appPath = PathUtil.relativeToAbsolutePath(GlobalVariable.G_AndroidApp, RunConfiguration.getProjectDir())
//	Mobile.startApplication(appPath, false)
//} else {
//	String remoteServerUrl = System.getenv('XTC_SERVICE_ENDPOINT_APPIUM') + 'wd/hub'
//	
//	DesiredCapabilities capabilities = new DesiredCapabilities();
//	capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, 'S10 Plus')
//	capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, 'android')
//	capabilities.setCapability('appActivity', 'com.hmh.api.ApiDemos')
//	capabilities.setCapability('appPackage', 'com.hmh.api')
//	capabilities.setCapability('appWaitActivity', '*')
//	capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AppiumDriverManager.UIAUTOMATOR2)
//	capabilities.setCapability('autoGrantPermissions', true)
//	
//	AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL(remoteServerUrl), capabilities)
//	AppiumDriverManager.setDriver(driver)
//}

