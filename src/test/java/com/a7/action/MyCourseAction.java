package com.a7.action;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.a7.pages.MyCoursePage;
import com.a7.types.MyCourseProgress;

public class MyCourseAction {
    private WebDriverWait wait;
    private WebDriver driver;
    private MyCoursePage myCoursePage;

    public MyCourseAction(WebDriver driver) {
        this.driver = driver;
        this.myCoursePage = new MyCoursePage(driver);
    }

    private WebElement waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickKursusSaya() {
        waitForElement(myCoursePage.getKursusSayaButton()).click();
    }

    public void clickSelesaiTab() {
        waitForElement(myCoursePage.getSelesaiTabButton()).click();
    }

    public void clickDalamProgressTab() {
        waitForElement(myCoursePage.getDalamProgressTabButton()).click();
    }

    public boolean isDalamProgressTabActive() {
        return waitForElement(myCoursePage.getDalamProgressTabButton()).getAttribute("class").contains("active");
    }

    public List<MyCourseProgress> getCourseProgressList() {
        return myCoursePage.getCourseProgressList();
    }

}