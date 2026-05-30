package com.a7.steps;

import com.a7.config.WebDriverConfig;
import com.a7.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("User berada di halaman login")
    public void user_berada_di_halaman_login() {
        driver = WebDriverConfig.initDriver();
        driver.get(WebDriverConfig.getBaseUrl());
        loginPage = new LoginPage(driver);
    }

    @When("User memasukkan email valid: {string}")
    public void user_memasukkan_email_valid(String email) {
        loginPage.enterUsername(email);
    }

    @When("User memasukkan password valid: {string}")
    public void user_memasukkan_password_valid(String password) {
        loginPage.enterPassword(password);
    }

    @Then("User berhasil login dan diarahkan ke halaman dashboard")
    public void user_berhasil_login_dan_diarahkan_ke_halaman_dashboard() {
        loginPage.clickLogin();

        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.or(
                ExpectedConditions.urlContains("dashboard-pelajar"),
                ExpectedConditions.urlContains("dashboard")
            ));

        String current = driver.getCurrentUrl();
        System.out.println("Current URL after login: " + current);
        assertTrue("Expected dashboard in URL but was: " + current,
                current.contains("dashboard-pelajar") || current.contains("dashboard"));

        WebDriverConfig.quitDriver();
    }
}