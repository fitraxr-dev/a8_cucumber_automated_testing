package com.a7.action;

import com.a7.pages.MyCoursesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyCoursesAction {
    private WebDriver driver;
    private MyCoursesPage myCoursesPage;

    public MyCoursesAction(WebDriver driver) {
        this.driver = driver;
        this.myCoursesPage = new MyCoursesPage(driver);
    }

    public void verifyOnMyCoursesPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("my-courses"));
    }

    public void clickInProgressTab() {
        myCoursesPage.getInProgressTab().click();
    }

    public String getCourseProgress(String courseName) {
        try {
            String xpath = String.format("//h6[text()='%s']/following-sibling::div//span", courseName);
            WebElement progressElement = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return progressElement.getText().trim();
        } catch (Exception e) {
            return null;
        }
    }
}
