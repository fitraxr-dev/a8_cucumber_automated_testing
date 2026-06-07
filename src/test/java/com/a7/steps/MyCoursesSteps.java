package com.a7.steps;

import com.a7.config.WebDriverConfig;
import com.a7.action.LoginAction;
import com.a7.action.DashboardAction;
import com.a7.action.MyCoursesAction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MyCoursesSteps {
    private WebDriver driver;
    private LoginAction loginAction;
    private DashboardAction dashboardAction;
    private MyCoursesAction myCoursesAction;

    @Given("User telah terautentikasi")
    public void user_telah_terautentikasi() {
        driver = WebDriverConfig.initDriver();
        driver.get(WebDriverConfig.getBaseUrl());
        
        loginAction = new LoginAction(driver);
        loginAction.enterUsername("fitra.pelajar@example.com");
        loginAction.enterPassword("Fitra.pelajar123");
        loginAction.clickLogin();
        
        dashboardAction = new DashboardAction(driver);
        assertTrue("User harus terautentikasi dan dashboard berhasil dimuat", dashboardAction.isDashboardLoaded());
    }

    @And("User telah mendaftar course dan course belum ada progress sama sekali")
    public void user_telah_mendaftar_course_dan_course_belum_ada_progress_sama_sekali() {
        System.out.println("Precondition: Data test course participant dengan progress 0% siap di database.");
    }

    @When("User mengklik tombol Kursus Saya")
    public void user_mengklik_tombol_kursus_saya() {
        dashboardAction.clickMyCourses();
    }

    @Then("User diarahkan ke halaman \"Kursus Saya\"")
    public void user_diarahkan_ke_halaman_kursus_saya() {
        myCoursesAction = new MyCoursesAction(driver);
        myCoursesAction.verifyOnMyCoursesPage();
    }

    @And("Halaman Kursus Saya tab Dalam Progress menampilkan daftar kursus yang sedang dalam progress termasuk 0%")
    public void halaman_kursus_saya_tab_dalam_progress_menampilkan_daftar_kursus_yang_sedang_dalam_progress_termasuk_0() {
        myCoursesAction.clickInProgressTab();
        
        String progressVue = myCoursesAction.getCourseProgress("Belajar Vue");
        String progressPostgres = myCoursesAction.getCourseProgress("PostGreSql");
        
        System.out.println("Progress 'Belajar Vue': " + progressVue);
        System.out.println("Progress 'PostGreSql': " + progressPostgres);
        
        assertNotNull("Kursus 'Belajar Vue' harus ditemukan di tab Dalam Progress", progressVue);
        assertEquals("Kursus 'Belajar Vue' harus memiliki progress 0%", "0%", progressVue);
        
        assertNotNull("Kursus 'PostGreSql' harus ditemukan di tab Dalam Progress", progressPostgres);
        assertEquals("Kursus 'PostGreSql' harus memiliki progress 0%", "0%", progressPostgres);
        
        WebDriverConfig.quitDriver();
    }
}
