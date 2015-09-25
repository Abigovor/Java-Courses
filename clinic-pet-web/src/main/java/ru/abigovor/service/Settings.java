package ru.abigovor.service;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private static final Settings INSTANCE = new Settings();

    private final Properties properties = new Properties();

    private Settings() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings getInstance() {
        return INSTANCE;
    }

    public String value(String key) {
        return this.properties.getProperty(key);
    }
}
