package tests;

import config.DriverFactory;
import data.Credentials;
import data.TestData;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MoldcellTest extends BaseTest {

    private static AndroidDriver driver;

    @BeforeAll
    public static void setUpAll() {
        driver = DriverFactory.getDriver();
    }

    @AfterAll
    public static void tearDownAll() {
        DriverFactory.quit();
    }

    @BeforeEach
    public void ensureLoginScreen() {
        assertTrue(new LoginPage(driver).isLoaded(),
            "Login screen did not load before test");
    }

    @Test
    @Order(1)
    @DisplayName("Positive: login screen loads")
    public void loginScreenLoads() {
        assertTrue(new LoginPage(driver).isLoaded(),
            "Login screen did not load");
    }

    @Test
    @Order(2)
    @DisplayName("Positive: login screen contains key elements")
    public void loginScreenHasKeyElements() {
        LoginPage loginPage = new LoginPage(driver);

        assertAll(
            () -> assertTrue(loginPage.hasTitle(), "Title is not displayed"),
            () -> assertTrue(loginPage.hasPhoneInput(), "Phone input is not displayed"),
            () -> assertTrue(loginPage.hasPasswordInput(), "Password input is not displayed"),
            () -> assertTrue(loginPage.hasLoginButton(), "Login button is not displayed"),
            () -> assertTrue(loginPage.hasCreateAccountButton(), "Register button is not displayed"),
            () -> assertTrue(loginPage.hasForgotPasswordButton(), "Forgot password button is not displayed")
        );
    }

    @Test
    @Order(3)
    @DisplayName("Positive: login button is enabled")
    public void loginButtonIsEnabled() {
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginButtonEnabled(), "Login button is not enabled");
        assertTrue(loginPage.isCreateAccountEnabled(), "Create account button is not enabled");
    }

    @Test
    @Order(4)
    @DisplayName("Negative: empty phone shows inline error")
    public void emptyPhoneShowsInlineError() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPhone("");
        loginPage.enterPassword(Credentials.INVALID_PASSWORD);
        loginPage.clickLogin();

        assertTrue(loginPage.waitForPhoneError(),
            "Inline phone error was not shown for empty phone");

        String actual = loginPage.getPhoneErrorText();
        String expected = TestData.PHONE_ERROR_TEXT;
        assertTrue(actual.toLowerCase().contains(expected.toLowerCase()),
            "Phone error text mismatch. Expected substring: '" + expected +
            "', actual: '" + actual + "'");
    }

    @Test
    @Order(5)
    @DisplayName("Negative: empty password shows inline error")
    public void emptyPasswordShowsInlineError() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPhone(TestData.VALID_PHONE_MIN);
        loginPage.enterPassword("");
        loginPage.clickLogin();

        assertTrue(loginPage.waitForPasswordError(),
            "Inline password error was not shown for empty password");

        String actual = loginPage.getPasswordErrorText();
        String expected = TestData.PASSWORD_ERROR_TEXT;
        assertTrue(actual.toLowerCase().contains(expected.toLowerCase()),
            "Password error text mismatch. Expected substring: '" + expected +
            "', actual: '" + actual + "'");
    }

    @Test
    @Order(6)
    @DisplayName("Negative: empty phone + empty password shows both inline errors")
    public void bothFieldsEmptyShowBothErrors() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPhone("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        assertAll(
            () -> assertTrue(loginPage.waitForPhoneError(),
                "Phone error was not shown"),
            () -> assertTrue(loginPage.waitForPasswordError(),
                "Password error was not shown")
        );
    }

    @Test
    @Order(7)
    @DisplayName("Negative: valid phone + wrong password shows dialog error")
    public void wrongPasswordShowsDialogError() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPhone(TestData.VALID_PHONE_MIN);
        loginPage.enterPassword(Credentials.INVALID_PASSWORD);
        loginPage.clickLogin();

        assertTrue(loginPage.waitForErrorDialog(),
            "Dialog was not shown for valid phone + wrong password");

        String actual = loginPage.getDialogText();
        String expected = TestData.DIALOG_ERROR_TEXT;
        assertTrue(actual.toLowerCase().contains(expected.toLowerCase()),
            "Dialog text mismatch. Expected substring: '" + expected +
            "', actual: '" + actual + "'");

        loginPage.clickOkIfVisible();
        assertTrue(loginPage.isLoaded(),
            "After closing dialog the login screen should be visible");
    }
}