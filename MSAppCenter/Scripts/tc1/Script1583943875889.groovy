import org.openqa.selenium.OutputType as OutputType
import org.openqa.selenium.TakesScreenshot as TakesScreenshot
import org.openqa.selenium.remote.DesiredCapabilities as DesiredCapabilities

import com.kms.katalon.core.appium.driver.AppiumDriverManager as AppiumDriverManager
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

import io.appium.java_client.MobileElement as MobileElement
import io.appium.java_client.android.AndroidDriver as AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType as MobileCapabilityType

URL url = new URL(System.getenv('XTC_SERVICE_ENDPOINT_APPIUM') + 'wd/hub')

DesiredCapabilities capabilities = new DesiredCapabilities()

capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, 'S10 Plus')

capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, 'android')

capabilities.setCapability('appActivity', 'com.hmh.api.ApiDemos')

capabilities.setCapability('appPackage', 'com.hmh.api')

capabilities.setCapability('appWaitActivity', '*')

capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AppiumDriverManager.UIAUTOMATOR2)

capabilities.setCapability('autoGrantPermissions', true)

System.out.println('Create Android Webdriver...')

AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(url, capabilities)

System.out.println('Create Android Webdriver successful.')

takeScreenshot(driver, 'start-app')

List<MobileElement> btns = driver.findElementsByXPath("//*[@class = 'android.widget.TextView']")

MobileElement btn = btns.get(0)

btn.click()

try {
    Thread.sleep(3000)
}
catch (InterruptedException ex) {
} 

takeScreenshot(driver, 'btn-clicked')

driver.quit()

def takeScreenshot(def driver, String name) {
    def srcFile = ((driver) as TakesScreenshot).getScreenshotAs(OutputType.FILE)

    def targetFile = new File(((RunConfiguration.getReportFolder() + '/') + name) + '.jpg')

    targetFile << srcFile.bytes

    System.out.println('Take screenshot ' + targetFile)
}