package com.a7.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.a7.types.MyCourseProgress;

public class MyCoursePage {

    @FindBy(css = "li.nav-item:nth-child(2) > a:nth-child(1)")
    private WebElement kursusSayaButton;

    @FindBy(css = "#completed-tab")
    private WebElement selesaiTabButton;

    @FindBy(css = "#inprogress-tab")
    private WebElement dalamProgressTabButton;

    @FindBy(css = ".card-body-mycourse")
    private List<WebElement> courseProgressListRaw;

    private List<MyCourseProgress> courseProgressList = new ArrayList<>();

    public MyCoursePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        courseProgressList = parseCourseProgressList();
    }

    public WebElement getKursusSayaButton() {
        return kursusSayaButton;
    }

    public WebElement getDalamProgressTabButton() {
        return dalamProgressTabButton;
    }

    public boolean isDalamProgressTabActive() {
        return dalamProgressTabButton.getAttribute("class").contains("active");
    }

    public WebElement getSelesaiTabButton() {
        return selesaiTabButton;
    }

    public boolean isSelesaiTabActive() {
        return selesaiTabButton.getAttribute("class").contains("active");
    }

    public List<MyCourseProgress> getCourseProgressList() {
        return courseProgressList;
    }

    public List<MyCourseProgress> parseCourseProgressList() {
        List<MyCourseProgress> resultList = new ArrayList<>();

        // PERBAIKAN 3: Loop sekarang valid karena courseProgressListRaw adalah List
        for (WebElement courseProgress : this.courseProgressListRaw) {
            String courseName = courseProgress.findElement(By.cssSelector(".card-title")).getText().trim();
            String lecturerName = courseProgress.findElement(By.cssSelector(".card-text")).getText().trim();
            String percentage = courseProgress.findElement(By.cssSelector(".progress-percentage")).getText().trim();

            resultList.add(new MyCourseProgress(courseName, lecturerName, percentage));
        }

        return resultList;
    }

}