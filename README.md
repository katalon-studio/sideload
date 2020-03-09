# sideload
Proof of Concept (POC) of executing katalon test on Appium

## Setup
* Create new Android/iOS app on AppCenter
* Open app
* Create new test run with any devices that is compatible with the app's OS and follow the instruction

## Prerequisites
* Maven
* Node.js, version 6.3 or later
  * Install the appcenter-cli NPM package: `npm install -g appcenter-cli`


## Running tests
From your test project workspace:
1. Build and Package all your dependencies using Maven
```sh
mvn -DskipTests -P prepare-for-upload package
```

2. Upload and schedule tests
```sh
appcenter test run appium --app <app_name> --devices <device_id/device_name> --app-path <path_to_app_file> --test-series "master" --locale "en_US" --build-dir target/upload
```
