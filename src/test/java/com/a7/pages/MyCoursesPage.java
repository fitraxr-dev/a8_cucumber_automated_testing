package com.a7.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyCoursesPage {

    @FindBy(id = "inprogress-tab")
    private WebElement inProgressTab;

    public MyCoursesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getInProgressTab() {
        return inProgressTab;
    }
}
