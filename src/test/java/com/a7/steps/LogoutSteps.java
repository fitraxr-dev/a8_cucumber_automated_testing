package com.a7.steps;

import com.a7.config.WebDriverConfig;
import com.a7.action.LoginAction;
import com.a7.action.DashboardAction;
import com.a7.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class LogoutSteps {
    private WebDriver driver;
    private LoginAction loginAction;
    private DashboardAction dashboardAction;
    private LoginPage loginPage;

    @Given("User sudah login sebagai Pelajar dan sesi aktif")
    public void user_sudah_login_sebagai_pelajar_dan_sesi_aktif() {
        driver = WebDriverConfig.initDriver();
        driver.get(WebDriverConfig.getBaseUrl());
        
        loginAction = new LoginAction(driver);
        loginAction.enterUsername("fitra.pelajar@example.com");
        loginAction.enterPassword("Fitra.pelajar123");
        loginAction.clickLogin();
        
        dashboardAction = new DashboardAction(driver);
        assertTrue("Precondition: Dashboard gagal termuat saat login", dashboardAction.isDashboardLoaded());
    }

    @When("User mengklik dropdown Nama Akun pada navbar")
    public void user_mengklik_dropdown_nama_akun_pada_navbar() {
        dashboardAction.clickAccountDropdown();
    }

    @And("User mengklik sub-menu Keluar")
    public void user_mengklik_sub_menu_keluar() {
        dashboardAction.clickKeluar();
    }

    @Then("User berhasil logout dan diarahkan ke halaman login")
    public void user_berhasil_logout_dan_diarahkan_ke_halaman_login() {
        // Menunggu hingga kembali ke halaman login (base URL)
        String expectedUrl = WebDriverConfig.getBaseUrl();
        String expectedUrlAlt = expectedUrl.endsWith("/") ? expectedUrl.substring(0, expectedUrl.length() - 1) : expectedUrl;

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.or(
                    ExpectedConditions.urlToBe(expectedUrl),
                    ExpectedConditions.urlToBe(expectedUrlAlt)
                ));
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after logout: " + currentUrl);
        
        String cleanCurrent = currentUrl.endsWith("/") ? currentUrl.substring(0, currentUrl.length() - 1) : currentUrl;
        String cleanExpected = expectedUrl.endsWith("/") ? expectedUrl.substring(0, expectedUrl.length() - 1) : expectedUrl;
        assertEquals("Harus diarahkan kembali ke halaman login", cleanExpected, cleanCurrent);
        
        // Memastikan form login ditampilkan kembali
        loginPage = new LoginPage(driver);
        assertTrue("Form login username field harus terlihat kembali setelah logout", 
                loginPage.getUsernameField().isDisplayed());
    }

    @And("User tidak dapat mengakses halaman yang membutuhkan autentikasi")
    public void user_tidak_dapat_mengakses_halaman_yang_membutuhkan_autentikasi() {
        // Mencoba mengakses halaman dashboard secara langsung setelah logout
        String baseUrl = WebDriverConfig.getBaseUrl();
        String dashboardUrl = baseUrl.endsWith("/") ? baseUrl + "dashboard-pelajar" : baseUrl + "/dashboard-pelajar";
        
        driver.get(dashboardUrl);
        
        // Memastikan dialihkan kembali ke login page karena tidak terautentikasi
        String expectedUrl = WebDriverConfig.getBaseUrl();
        String expectedUrlAlt = expectedUrl.endsWith("/") ? expectedUrl.substring(0, expectedUrl.length() - 1) : expectedUrl;
        
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.or(
                    ExpectedConditions.urlToBe(expectedUrl),
                    ExpectedConditions.urlToBe(expectedUrlAlt)
                ));
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after trying to access dashboard unauthenticated: " + currentUrl);
        
        String cleanCurrent = currentUrl.endsWith("/") ? currentUrl.substring(0, currentUrl.length() - 1) : currentUrl;
        String cleanExpected = expectedUrl.endsWith("/") ? expectedUrl.substring(0, expectedUrl.length() - 1) : expectedUrl;
        assertEquals("Akses tanpa autentikasi harus dialihkan ke halaman login", cleanExpected, cleanCurrent);
        
        WebDriverConfig.quitDriver();
    }
}
