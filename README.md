# sideload

**sideload** is a Proof of Concept (POC) for executing Katalon tests on App Center.

## Prerequisites

* An active Katalon Studio Enterprise license
* [Katalon API Key](https://docs.katalon.com/katalon-analytics/docs/ka-api-key.html)
* **Maven** - version `3.3.9` or later
* **NodeJS** - version `6.3` or later
* **App Center CLI** (Install through **npm** with `npm install -g appcenter-cli`, and then login with `appcenter login`)

## Set-up

### On App Center

* Create a new **App** on App Center
* Open the newly created app
* In **Test**, create a new **Test run** with any device that is compatible with your app's OS

### In your Katalon Project

Before executing any test, you need to create an Appium Driver instance manually and set it as the currently used instance in Katalon Studio.

To do that, put the following snippet at the beginning of your test case or in the `Before Test Case` listener. Additionally, remember to change the desired capabilities corresponding to your app.

```groovy
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
```

Since you have created a custom Appium driver. You will also need to comment out, or remove all the `Mobile.startApplication(...)` or `Mobile.startExistingApplication(...)` in your current test cases.

### Update in sideload repository

* Package your Katalon Project into a `zip` file and place it in the resources folder `src/test/resources`
* Open `src/test/java/com.katalon.sideload/SideloadTest.java`, find the `sideload` test section and change the following variables as your context:
  - `katalonProjectPackageFile`: Your package file<br>
    - (e.g. `"MSAppCenter.zip"`)
  - `projectPath`: Katalon project's folder name inside the `zip` package<br>
    - (e.g. `"MSAppCenter"`)
  - `executeArgs`: The arguments part of your Katalon run command<br>
    - (e.g. `"-retry=0 -testSuitePath="Test Suites/Test Suite 01" -executionProfile="default" -browserType="Chrome" -apiKey="f9074412-f2b0-49a4-b6ef-b0e50f9b59d8""`)
    > Please be noted that the `-browserType` argument must be set to `"Chrome"`.

## Running tests

From your test project workspace:

1. Build and Package all your dependencies using Maven

    ```shell script
    $ mvn -DskipTests -P prepare-for-upload package
    ```

2. Upload and schedule tests

    ```shell script
    $ appcenter test run appium --app <app_name> --devices <device_id/device_name> --app-path <path_to_app_file> --test-series "master" --locale "en_US" --build-dir target/upload
    ```

   * `<app_name>`: Your App Name on App Center
   * `<device_id/device_name>`: Device ID or Device Name on App Center (You could find it under the tab `Test > Device sets`)
   * `<path_to_app_file>`: The App to upload to App Center

    Usage Example:

    ```shell script
    $ appcenter test run appium --app "katalon/demo-app" --devices "katalon/nexus" --app-path "apps/APIDemos.apk" --test-series "master" --locale "en_US" --build-dir target/upload
    ```

> In step 3 in the **New test run** dialog of App Center, you can copy the above commands for running tests.

## Usage example

If you'd like to execute the usage example, which is the **MSAppCenter** project provided in **sideload**, please zip the **MSAppCenter** project folder and replace the existing one in `src/test/resources`. We recommend using devices with Android 7.1.1 to ensure the compatibility between the demo App and device.
