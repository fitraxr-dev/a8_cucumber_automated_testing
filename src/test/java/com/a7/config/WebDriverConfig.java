package com.a7.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.InputStream;
import java.util.Properties;

public class WebDriverConfig {
    private static WebDriver driver;
    private static Properties properties;

    public static void loadProperties() {
        properties = new Properties();
        try (InputStream input = WebDriverConfig.class
                .getClassLoader()
                .getResourceAsStream("web-driver.properties")) {
            if (input == null) {
                throw new RuntimeException("web-driver.properties tidak ditemukan");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Gagal membaca file properties", e);
        }
    }

    public static WebDriver initDriver() {
        if (properties == null) {
            loadProperties();
        }

        String browser = properties.getProperty("browser");

        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            String browserBinary = properties.getProperty("browserBinary");
            if (browserBinary != null && !browserBinary.isBlank()) {
                options.setBinary(browserBinary);
            }
            driver = new ChromeDriver(options);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            String browserBinary = properties.getProperty("browserBinary");
            if (browserBinary != null && !browserBinary.isBlank()) {
                options.setBinary(browserBinary);
            }
            driver = new FirefoxDriver(options);
        } else {
            throw new RuntimeException("Browser tidak didukung: " + browser);
        }

        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicitWait", "10"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }
}
