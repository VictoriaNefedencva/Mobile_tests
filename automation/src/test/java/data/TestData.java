package data;

import config.ConfigReader;

public final class TestData {

    private TestData() {}

    public static final String VALID_PHONE_MIN   = ConfigReader.get("valid.phone.min");

    public static final String PHONE_ERROR_TEXT    = ConfigReader.get("phone.error.text");
    public static final String PASSWORD_ERROR_TEXT = ConfigReader.get("password.error.text");
    public static final String DIALOG_ERROR_TEXT   = ConfigReader.get("dialog.error.text");
}