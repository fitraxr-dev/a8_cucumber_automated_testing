package com.a7.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.time.Duration;
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
            driver = createChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            driver = createFirefoxDriver();
        } else {
            throw new RuntimeException("Browser tidak didukung: " + browser);
        }

        if (!isHeadless()) {
            driver.manage().window().maximize();
        }

        return driver;
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();

        if (isHeadless()) {
            options.addArguments("--headless");
        }

        String browserBinary = properties.getProperty("browserBinary");
        if (browserBinary != null && !browserBinary.isBlank()) {
            options.setBinary(browserBinary);
        }

        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();

        // 1. Konsumsi property headless untuk Firefox
        if (isHeadless()) {
            options.addArguments("-headless");
        }

        // 2. Konsumsi property browserBinary
        String browserBinary = properties.getProperty("browserBinary");
        if (browserBinary != null && !browserBinary.isBlank()) {
            options.setBinary(browserBinary);
        }

        return new FirefoxDriver(options);
    }

    public static WebDriverWait initDriverWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(properties.getProperty("timeout"))));
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
        // Diperbaiki: menghapus spasi di "baseUrl"
        return properties.getProperty("baseUrl");
    }

    private static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }

}