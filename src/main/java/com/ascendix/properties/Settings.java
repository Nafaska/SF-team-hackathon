package com.ascendix.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class Settings {
    public static final String SETTINGS_FILE_NAME = "settings.config";

    public static String getSettingValueByName(String settingName) {
        Properties prop = new Properties();
        try {
            String fileName = SETTINGS_FILE_NAME;
            ClassLoader classLoader = Settings.class.getClassLoader();

            URL res = Objects.requireNonNull(classLoader.getResource(fileName),
                    "Can't find configuration file  " + SETTINGS_FILE_NAME);
            InputStream is = new FileInputStream(res.getFile());
            prop.load(is);

            return prop.getProperty(settingName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
