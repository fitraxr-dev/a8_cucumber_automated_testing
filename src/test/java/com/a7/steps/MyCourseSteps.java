package com.a7.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertNotNull;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.a7.action.LoginAction;
import com.a7.action.MyCourseAction;
import com.a7.config.WebDriverConfig;
import com.a7.context.TestContext;
import com.a7.context.pages.LoginPageContext;
import com.a7.context.pages.MyCoursePageContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyCourseSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    
    private LoginAction loginAction;
    private MyCourseAction myCourseAction;

    public MyCourseSteps(TestContext testContext, LoginPageContext loginPageContext, MyCoursePageContext myCoursePageContext) {
        this.driver = testContext.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("User telah login dengan kredesial yang valid")
    public void user_telah_login_dengan_kredesial_yang_valid() {
        driver.get(WebDriverConfig.getBaseUrl());

        loginAction.enterUsername("fitra.pelajar@example.com");
        loginAction.enterPassword("Fitra.pelajar123");
        loginAction.clickLogin();
    }

    @Given("User berada di halaman Dashboard")
    public void user_berada_di_halaman_Dashboard() {
        wait.until(ExpectedConditions.urlToBe(WebDriverConfig.getBaseUrl() + "dashboard-pelajar"));
        assertEquals(WebDriverConfig.getBaseUrl() + "dashboard-pelajar", driver.getCurrentUrl());
    }

    @When("User klik tombol kursus saya")
    public void user_klik_tombol_kursus_saya() {
        myCourseAction = new MyCourseAction(driver);
        myCourseAction.clickKursusSaya();
    }

    @Then("User diarahkan ke halaman kursus saya tab Dalam Progress")
    public void user_diarahkan_ke_halaman_kursus_saya_tab_Dalam_Progress() {
        assertEquals(WebDriverConfig.getBaseUrl() + "my-courses", driver.getCurrentUrl());
        assertTrue(myCourseAction.isDalamProgressTabActive());
    }

    @Then("Halaman menampilkan daftar kursus yang sedang berlangsung")
    public void halaman_menampilkan_daftar_kursus_yang_sedang_berlangsung() {
        assertNotNull(myCourseAction.getCourseProgressList());
    }
}
