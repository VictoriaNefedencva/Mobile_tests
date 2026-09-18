# my moldcell - QA Test Assignment

## Environment
- Windows 11
- Java 17 (Temurin)
- Maven 3.9+
- Appium 3.4.2
- Android SDK
- Device: Xiaomi, Android 14 (API 34), ID: WCBA89PZNBQ4VCIN
- App: my moldcell 1.43.1 (md.moldcell.selfservice)

## Running the automated tests

### 1. Start Appium
    appium

### 2. Verify device is connected
    adb devices

### 3. Run tests
    cd automation
    mvn clean test

## Configuration

All environment and test data is externalized:

- automation/src/test/resources/config.properties - Appium URL, device, app package/activity, timeouts
- automation/src/test/resources/testdata.properties - phone numbers, passwords, expected error texts

Support/QA can change test data without editing code.

### Override via environment variables
    APPIUM_URL=http://127.0.0.1:4723
    DEVICE_NAME=WCBA89PZNBQ4VCIN
    APP_PACKAGE=md.moldcell.selfservice
    APP_ACTIVITY=.screens.login.LoginActivity

### Override via system properties
    mvn clean test -Ddevice.name=ANOTHER_DEVICE

Priority: system property > env variable > config.properties > testdata.properties

## Automated scenarios (7 tests)

Positive:
1. Login screen loads.
2. Login screen contains key elements (title, inputs, buttons).
3. Login button is enabled.

Negative:
4. Empty phone shows inline error.
5. Empty password shows inline error.
6. Both fields empty show both inline errors.
7. Valid phone + wrong password shows error dialog; OK button closes it.

## Locators

All `resource-id` values in the app are defined without a package prefix.
XPath `//*[@resource-id='...']` does not match Compose elements reliably
(false negatives), so `AppiumBy.androidUIAutomator` with `UiSelector` is used:

    AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"login_input\")")
    AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"login_input_error_message\")")

This is stable against Jetpack Compose UI where XPath tends to be flaky.

## Project structure
    moldcell-tests/
    |-- README.md
    |-- .gitignore
    |-- manual/
    |   |-- checkList.md
    |   |-- bugs.md
    |   |-- summary.md
    |   -- evidence/
    |       -- Bug_001.png
    -- automation/
        |-- pom.xml
        -- src/test/
            |-- java/
            |   |-- config/ConfigReader.java
            |   |-- config/DriverFactory.java
            |   |-- data/TestData.java
            |   |-- data/Credentials.java
            |   |-- pages/BasePage.java
            |   |-- pages/LoginPage.java
            |   |-- tests/BaseTest.java
            |   -- tests/MoldcellTest.java
            -- resources/
                |-- config.properties
                -- testdata.properties

## Limitations
- Tests launch the app automatically on session start (`appium:forceAppLaunch`).
- `noReset=true`: app data (login state) is preserved between runs. For a full
  reset, set `appium:noReset=false` in `DriverFactory`.
- Automated tests cover only the unauthenticated part of the app (login screen).
- No real payments or plan changes were performed.
- Personal phone numbers are masked in reports and logs.