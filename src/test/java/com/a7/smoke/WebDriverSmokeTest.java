package com.a7.smoke;

import com.a7.config.WebDriverConfig;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WebDriverSmokeTest {

    @Test
    public void shouldStartWebDriverAndOpenPage() {
        WebDriver driver = null;

        try {
            driver = WebDriverConfig.initDriver();
            driver.get("https://example.com");

            assertNotNull(driver);
            assertTrue(driver.getTitle().contains("Example Domain"));
        } finally {
            WebDriverConfig.quitDriver();
        }
    }
}