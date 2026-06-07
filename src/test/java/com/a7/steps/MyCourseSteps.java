package com.a7.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.openqa.selenium.WebDriver;

import com.a7.action.LoginAction;
import com.a7.action.MyCourseAction;
import com.a7.config.WebDriverConfig;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyCourseSteps {
    private static WebDriver driver;
    private static LoginAction loginAction;
    private static MyCourseAction myCourseAction;

    @Given("User telah login dengan kredesial yang valid")
    public void user_telah_login_dengan_kredesial_yang_valid() {
        driver = WebDriverConfig.initDriver();
        driver.get(WebDriverConfig.getBaseUrl());
        loginAction = new LoginAction(driver);

        loginAction.enterUsername("fitra.pelajar@example.com");
        loginAction.enterPassword("Fitra.pelajar123");
        loginAction.clickLogin();
    }

    @Given("User berada di halaman Dashboard")
    public void user_berada_di_halaman_Dashboard() {
        WebDriverConfig.driverWait(3000);

        assertEquals(WebDriverConfig.getBaseUrl() + "dashboard-pelajar", driver.getCurrentUrl());
    }

    @When("User klik tombol kursus saya")
    public void user_klik_tombol_kursus_saya() {
        myCourseAction = new MyCourseAction(driver);
        myCourseAction.clickKursusSaya();
    }

    @Then("User diarahkan ke halaman kursus saya tab Dalam Progress")
    public void user_diarahkan_ke_halaman_kursus_saya_tab_Dalam_Progress() {
        WebDriverConfig.driverWait(10000);

        assertEquals(WebDriverConfig.getBaseUrl() + "my-courses", driver.getCurrentUrl());
        assertTrue(myCourseAction.isDalamProgressTabActive());
    }

    @Then("Halaman menampilkan daftar kursus yang sedang berlangsung")
    public void halaman_menampilkan_daftar_kursus_yang_sedang_berlangsung() {
        WebDriverConfig.driverWait(10000);

        assertNotNull(myCourseAction.getCourseProgressList());

        WebDriverConfig.quitDriver();
    }
}
