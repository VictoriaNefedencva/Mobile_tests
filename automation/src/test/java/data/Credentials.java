package data;

import config.ConfigReader;

public final class Credentials {

    private Credentials() {}

    // Invalid credentials for negative tests
    public static final String INVALID_PASSWORD = ConfigReader.get("invalid.password");

    // Add valid credentials here when they become available:
    // public static final String VALID_PHONE = ConfigReader.get("valid.phone");
    // public static final String VALID_PASSWORD = ConfigReader.get("valid.password");
}