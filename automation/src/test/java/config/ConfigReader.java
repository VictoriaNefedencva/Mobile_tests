package config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigReader {

    private static final Properties CONFIG = load("config.properties");
    private static final Properties TESTDATA = load("testdata.properties");

    private static Properties load(String fileName) {
        Properties props = new Properties();
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new RuntimeException("File not found in resources: " + fileName);
            }
            props.load(new InputStreamReader(is, StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + fileName, e);
        }
        return props;
    }

    public static String get(String key) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isEmpty()) return sys;

        String envKey = key.toUpperCase().replace('.', '_');
        String env = System.getenv(envKey);
        if (env != null && !env.isEmpty()) return env;

        String cfg = CONFIG.getProperty(key);
        if (cfg != null) return cfg;

        String td = TESTDATA.getProperty(key);
        if (td != null) return td;

        return null;
    }

    public static int getInt(String key, int defaultValue) {
        String v = get(key);
        return (v == null || v.isEmpty()) ? defaultValue : Integer.parseInt(v);
    }
}