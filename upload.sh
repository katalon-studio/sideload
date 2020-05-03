#!/bin/bash

appcenter test run appium --app <app_name> \
  --devices <device_id/device_name> \
  --app-path <path_to_app_file> \
  --test-series "master" \
  --locale "en_US" \
  --build-dir target/upload \
  --test-parameter "test_env=KATALON_VERSION=<katalon_version>" \
  --test-parameter "test_env=KATALON_PROJECT_PACKAGE_FILE=<katalon_project_package_file>" \
  --test-parameter "test_env=KATALON_PROJECT_PATH=<katalon_project_path>" \
  --test-parameter "test_env=KATALON_EXECUTE_ARGS=<katalon_execute_args>"

# appcenter test run appium --app "katalon/demo-app" \
#  --devices "katalon/nexus" \
#  --app-path "apps/APIDemos.apk" \
#  --test-series "master" \
#  --locale "en_US" \
#  --build-dir target/upload \
#  --test-parameter "test_env=KATALON_EXECUTE_ARGS=-retry=0 -testSuitePath=""Test Suites/Regression Tests"" -executionProfile=default -browserType=Chrome -apiKey=""f9074412-f2b0-49a4-b6ef-b0e50f9b59d8"""
