# sideload

**sideload** is a Proof of Concept (POC) built for executing Katalon Studio tests on App Center Test. [App Center Test](https://docs.microsoft.com/en-us/appcenter/test-cloud/) only supports running tests using supported frameworks such as [Appium tests written in Java with JUnit](https://docs.microsoft.com/en-gb/appcenter/test-cloud/preparing-for-upload/appium); hence, Katalon users cannot execute their tests on App Center Test directly.

By using **sideload** to package Katalon projects in JUnit format, you can execute your Katalon test scripts with devices provided on App Center Test. This tutorial shows you how to configure App Center Test, Katalon projects and **sideload** to execute the usage sample, which is **KatalonDemoProject**. Also, at the end of this tutorial, we provide some configurations that you can try to execute your Katalon projects on App Center Test.

## Prerequisites

You need

* An active [Katalon Runtime Engine](https://docs.katalon.com/katalon-studio/docs/license.html#katalon-runtime-engine) license
* A [Katalon API Key](https://docs.katalon.com/katalon-analytics/docs/ka-api-key.html)

Your machine should

* Install [Apache Maven](https://maven.apache.org/download.cgi) version 3.3.9 or later
* Install [NodeJS](https://nodejs.org/es/blog/release/) version 6.3 or later
* Install and log into [App Center CLI](https://docs.microsoft.com/en-us/appcenter/cli/#installation)

## Executing KatalonDemoProject

This section guides you on how to upload **KatalonDemoProject** packaged in JUnit by **sideload** and start a test run in App Center Test.

1. Clone or download **sideload** [here](https://github.com/katalon-studio/sideload).
2. Open the workspace of the usage example project by following this path `src/test/resources/KatalonDemoProject.zip`.

On App Center Test, [create and start a new test run](https://docs.microsoft.com/en-us/appcenter/test-cloud/starting-a-test-run#new-test-run).

* Device: Android 7.1.1 or prior. Ex: Motorola Nexus 6
* Test framework: Appium

To run tests, from the **KatalonDemoProject** workspace, copy and paste the commands generated automatically in the **Submit** screen of the **New test run** dialog

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

You can view [test reports](https://docs.microsoft.com/en-us/appcenter/test-cloud/test-reports) on App Center Test.

## Executing your Katalon test suites

If you'd like to try running your Katalon projects with App Center Test, please make sure you have properly configured your Katalon project and make updates in **sideload**.

### Update your Katalon Studio Project

Before executing any test, you need to create an Appium Driver instance manually and set it as the currently used instance in Katalon Studio.

Open your project in Katalon Studio and put the following snippet at the beginning of your test script or in the `Before Test Case` listener. Additionally, remember to change the [desired capabilities](http://appium.io/docs/en/writing-running-appium/caps/) corresponding to your app.

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

> Since you have created a custom Appium driver, you need to comment out, or remove all the `Mobile.startApplication(...)` or `Mobile.startExistingApplication(...)` in your current test cases.

### Update **sideload**

You need to package your Katalon project into a `zip` file and place it in the resources folder `src/test/resources`.

Open `src/test/java/com.katalon.sideload/SideloadTest.java`, find the `sideload` test section and change the following variables as your context:

  - `katalonProjectPackageFile`: Your package file<br>
    - (e.g. `"KatalonDemoProject.zip"`)
  - `projectPath`: Katalon project's folder name inside the `zip` package<br>
    - (e.g. `"KatalonDemoProject"`)
  - `executeArgs`: The arguments part of your Katalon run command<br>
    - (e.g. `"-retry=0 -testSuitePath="Test Suites/Regression Tests" -executionProfile="default" -browserType="Chrome" -apiKey="f9074412-f2b0-49a4-b6ef-b0e50f9b59d8""`)
    > Please note that the `-browserType` argument must be set to `"Chrome"`.

Then create a new test run in App Center Test and execute the tests.

## Companion products

### Katalon TestOps

[Katalon TestOps](https://analytics.katalon.com) is a web-based application that provides dynamic perspectives and an insightful look at your automation testing data. You can leverage your automation testing data by transforming and visualizing your data; analyzing test results; seamlessly integrating with such tools as Katalon Studio and Jira; maximizing the testing capacity with remote execution.

* Read our [documentation](https://docs.katalon.com/katalon-analytics/docs/overview.html).
* Ask a question on [Forum](https://forum.katalon.com/categories/katalon-analytics).
* Request a new feature on [GitHub](CONTRIBUTING.md).
* Vote for [Popular Feature Requests](https://github.com/katalon-analytics/katalon-analytics/issues?q=is%3Aopen+is%3Aissue+label%3Afeature-request+sort%3Areactions-%2B1-desc).
* File a bug in [GitHub Issues](https://github.com/katalon-analytics/katalon-analytics/issues).

### Katalon Studio
[Katalon Studio](https://www.katalon.com) is a free and complete automation testing solution for Web, Mobile, and API testing with modern methodologies (Data-Driven Testing, TDD/BDD, Page Object Model, etc.) as well as advanced integration (JIRA, qTest, Slack, CI, Katalon TestOps, etc.). Learn more about [Katalon Studio features](https://www.katalon.com/features/).
