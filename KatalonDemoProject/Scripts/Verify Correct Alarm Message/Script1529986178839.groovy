import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.internal.PathUtil as PathUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import org.openqa.selenium.remote.DesiredCapabilities
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType

String remoteServerUrl = System.getenv('XTC_SERVICE_ENDPOINT_APPIUM') + 'wd/hub'

DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, 'S10 Plus')
capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, 'android')
capabilities.setCapability('appActivity', 'com.hmh.api.ApiDemos')
capabilities.setCapability('appPackage', 'com.hmh.api')
capabilities.setCapability('appWaitActivity', '*')
capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AppiumDriverManager.UIAUTOMATOR2)
capabilities.setCapability('autoGrantPermissions', true)

AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL(remoteServerUrl), capabilities)
AppiumDriverManager.setDriver(driver)

// --------------------------------------------------------------------


Mobile.comment('Story: Verify correct alarm message')

Mobile.comment('Given that user has started an application')

'Get full directory\'s path of android application'
//def appPath = PathUtil.relativeToAbsolutePath(GlobalVariable.G_AndroidApp, RunConfiguration.getProjectDir())

//Mobile.startApplication(appPath, false)

Mobile.comment('And he navigates the application to Activity form')

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Application/android.widget.TextView - App'), 10)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Application/App/android.widget.TextView-Activity'), 10)

Mobile.takeScreenshot()

Mobile.comment('When he taps on the Custom Dialog button')

Mobile.tap(findTestObject('Application/App/Activity/android.widget.TextView-Custom Dialog'), 10)

Mobile.takeScreenshot()

'Get displayed message on the dialog'
def message = Mobile.getText(findTestObject('Application/App/Activity/Custom Dialog/android.widget.TextViewCustomDialog'), 
    10)

Mobile.comment('Then the correct dialog message should be displayed')

Mobile.verifyEqual(message, 'Example of how you can use a custom Theme.Dialog theme to make an activity that looks like a customized dialog, here with an ugly frame.')

Mobile.closeApplication()


