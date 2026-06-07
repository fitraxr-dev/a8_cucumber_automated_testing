package com.a7.steps;

import com.a7.config.WebDriverConfig;
import com.a7.context.TestContext;
import com.a7.action.LoginAction;
import com.a7.action.DashboardAction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginSteps {
    private WebDriver driver;
    private LoginAction loginAction;
    private DashboardAction dashboardAction;

    public LoginSteps(TestContext testContext) {
        this.driver = testContext.getDriver();
    }

    @Given("User berada di halaman login")
    public void user_berada_di_halaman_login() {
        driver = WebDriverConfig.initDriver();
        driver.get(WebDriverConfig.getBaseUrl());
        loginAction = new LoginAction(driver);
    }

    @When("User memasukkan email valid: {string}")
    public void user_memasukkan_email_valid(String email) {
        loginAction.enterUsername(email);
    }

    @When("User memasukkan password valid: {string}")
    public void user_memasukkan_password_valid(String password) {
        loginAction.enterPassword(password);
    }

    @When("User langsung klik login")
    @When("User klik tombol login")
    public void user_klik_tombol_login() {
        loginAction.clickLogin();
    }

    @Then("User berhasil login dan diarahkan ke halaman dashboard")
    public void user_berhasil_login_dan_diarahkan_ke_halaman_dashboard() {
        dashboardAction = new DashboardAction(driver);

        boolean isLoaded = dashboardAction.isDashboardLoaded();
        String current = dashboardAction.getCurrentUrl();
        System.out.println("Current URL after login: " + current);

        assertTrue("Expected dashboard in URL but was: " + current, isLoaded);

        WebDriverConfig.quitDriver();
    }

    @Then("Sistem menampilkan pesan {string}")
    public void sistem_menampilkan_pesan(String expectedMessage) {
        String actualMessage = loginAction.getErrorMessage();
        assertEquals(expectedMessage, actualMessage);

        WebDriverConfig.quitDriver();
    }
}