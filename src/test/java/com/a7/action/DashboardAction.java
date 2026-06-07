package com.a7.action;

import com.a7.pages.DashboardPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardAction {
    private WebDriver driver;
    private DashboardPage dashboardPage;

    public DashboardAction(WebDriver driver) {
        this.driver = driver;
        this.dashboardPage = new DashboardPage(driver);
    }

    public boolean isDashboardLoaded() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("dashboard-pelajar"),
                    ExpectedConditions.urlContains("dashboard")
                ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickAccountDropdown() {
        dashboardPage.getAccountDropdown().click();
    }

    public void clickKeluar() {
        dashboardPage.getLogoutButton().click();
    }

    public void clickMyCourses() {
        dashboardPage.getMyCoursesNavbarLink().click();
    }
}
