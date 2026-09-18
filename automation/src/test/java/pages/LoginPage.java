package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    private static final By TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"login_title_first\")");
    private static final By PHONE_INPUT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"login_input\")");
    private static final By PASSWORD_INPUT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"password_input\")");
    private static final By LOGIN_BUTTON = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"login_button\")");
    private static final By CREATE_ACCOUNT_BUTTON = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"create_account_button\")");
    private static final By FORGOT_PASSWORD_BUTTON = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"forgot_password_button\")");

    private static final By PHONE_ERROR = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"login_input_error_message\")");
    private static final By PASSWORD_ERROR = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"password_input_error_message\")");

    private static final By DIALOG_MESSAGE = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"android:id/message\")");
    private static final By OK_BUTTON = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"android:id/button1\")");

    private static final Duration QUICK = Duration.ofSeconds(10);
    private static final Duration DIALOG = Duration.ofSeconds(5);

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    private boolean isVisibleQuick(By by, Duration timeout) {
        try {
            new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoaded()               { return isVisibleQuick(TITLE, QUICK); }
    public boolean hasTitle()               { return isVisibleQuick(TITLE, QUICK); }
    public boolean hasPhoneInput()          { return isVisibleQuick(PHONE_INPUT, QUICK); }
    public boolean hasPasswordInput()       { return isVisibleQuick(PASSWORD_INPUT, QUICK); }
    public boolean hasLoginButton()         { return isVisibleQuick(LOGIN_BUTTON, QUICK); }
    public boolean hasCreateAccountButton() { return isVisibleQuick(CREATE_ACCOUNT_BUTTON, QUICK); }
    public boolean hasForgotPasswordButton(){ return isVisibleQuick(FORGOT_PASSWORD_BUTTON, QUICK); }

    public boolean isLoginButtonEnabled()   { return waitVisible(LOGIN_BUTTON).isEnabled(); }
    public boolean isCreateAccountEnabled() { return waitVisible(CREATE_ACCOUNT_BUTTON).isEnabled(); }

    public LoginPage enterPhone(String phone) {
        type(PHONE_INPUT, phone);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(PASSWORD_INPUT, password);
        return this;
    }

    public LoginPage clickLogin() {
        click(LOGIN_BUTTON);
        return this;
    }

    public LoginPage clickCreateAccount() {
        click(CREATE_ACCOUNT_BUTTON);
        return this;
    }

    public LoginPage clickForgotPassword() {
        click(FORGOT_PASSWORD_BUTTON);
        return this;
    }

    public boolean hasError() {
        return hasPhoneError() || hasPasswordError() || isDialogVisible();
    }

    public boolean hasPhoneError()   { return isVisibleQuick(PHONE_ERROR, QUICK); }
    public boolean hasPasswordError(){ return isVisibleQuick(PASSWORD_ERROR, QUICK); }
    public boolean isDialogVisible() { return isVisibleQuick(DIALOG_MESSAGE, QUICK); }

    public boolean waitForPhoneError()   { return isVisibleQuick(PHONE_ERROR, DIALOG); }
    public boolean waitForPasswordError(){ return isVisibleQuick(PASSWORD_ERROR, DIALOG); }
    public boolean waitForErrorDialog()  { return isVisibleQuick(DIALOG_MESSAGE, DIALOG); }

    public String getPhoneErrorText() {
        return hasPhoneError() ? driver.findElement(PHONE_ERROR).getText() : "";
    }

    public String getPasswordErrorText() {
        return hasPasswordError() ? driver.findElement(PASSWORD_ERROR).getText() : "";
    }

    public String getDialogText() {
        return isDialogVisible() ? driver.findElement(DIALOG_MESSAGE).getText() : "";
    }

    public LoginPage clickOkIfVisible() {
        if (isVisibleQuick(OK_BUTTON, QUICK)) {
            driver.findElement(OK_BUTTON).click();
        }
        return this;
    }

    public String getTitle() {
        return waitVisible(TITLE).getText();
    }
}