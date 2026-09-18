package config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            try {
                UiAutomator2Options options = new UiAutomator2Options();
                options.setPlatformName(ConfigReader.get("platform.name"));
                options.setDeviceName(ConfigReader.get("device.name"));
                options.setAutomationName(ConfigReader.get("automation.name"));
                options.setAppPackage(ConfigReader.get("app.package"));
                options.setAppActivity(ConfigReader.get("app.activity"));
                options.setNoReset(true);
                options.setCapability("appium:forceAppLaunch", true);
                options.setCapability("appium:shouldTerminateApp", true);
                options.setSkipDeviceInitialization(true);
                options.setIgnoreHiddenApiPolicyError(true);
                options.setAutoGrantPermissions(false);
                options.setNewCommandTimeout(
                    Duration.ofSeconds(ConfigReader.getInt("timeout.new.command", 120)));

                driver = new AndroidDriver(
                    new URL(ConfigReader.get("appium.url")), options);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create driver", e);
            }
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}