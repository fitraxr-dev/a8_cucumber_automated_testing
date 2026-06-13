package com.a7.action;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.a7.pages.MyCoursePage;
import com.a7.types.MyCourseProgress;

public class MyCourseAction {
    private WebDriver driver;
    private MyCoursePage myCoursePage;

    public MyCourseAction(WebDriver driver) {
        this.driver = driver;
        this.myCoursePage = new MyCoursePage(driver);
    }

    public void waitForLoadingToDisappear() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='Loading...']")));
        } catch (Exception e) {
            // Ignore if loading indicator is not found or already gone
        }
    }

    private WebElement waitForElement(WebElement element) {
        waitForLoadingToDisappear();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("DEBUG - Current URL: " + driver.getCurrentUrl());
            System.out.println("DEBUG - Page Source: " + driver.getPageSource());
            throw e;
        }
    }

    public void clickKursusSaya() {
        waitForElement(myCoursePage.getKursusSayaButton()).click();
    }

    public void clickSelesaiTab() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.urlContains("my-courses"));
        waitForElement(myCoursePage.getSelesaiTabButton()).click();
    }

    public void clickDalamProgressTab() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.urlContains("my-courses"));
        waitForElement(myCoursePage.getDalamProgressTabButton()).click();
    }

    public boolean isDalamProgressTabActive() {
        return waitForElement(myCoursePage.getDalamProgressTabButton()).getAttribute("class").contains("active");
    }

    public boolean isSelesaiTabActive() {
        return waitForElement(myCoursePage.getSelesaiTabButton()).getAttribute("class").contains("active");
    }

    public List<MyCourseProgress> getCourseProgressList() {
        return myCoursePage.getCourseProgressList();
    }

    public List<MyCourseProgress> getCompletedCourseList() {
        return myCoursePage.getCompletedCourseList();
    }

}