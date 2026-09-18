package tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class BaseTest {

    protected AndroidDriver driver;

    @AfterEach
    public void captureOnFailure(TestInfo testInfo) {
        if (driver != null) {
            try {
                byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String name = testInfo.getDisplayName().replaceAll("[^a-zA-Z0-9-_]", "_");
                Files.createDirectories(Paths.get("target/screenshots"));
                Files.write(Paths.get("target/screenshots/" + name + ".png"), png);
            } catch (Exception ignored) {
            }
        }
    }
}