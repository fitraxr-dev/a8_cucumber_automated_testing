package com.a7.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    @FindBy(xpath = "//a[contains(@class, 'nav-link') and contains(text(), 'Fitra Pelajar')]")
    private WebElement accountDropdown;

    @FindBy(xpath = "//button[contains(@class, 'dropdown-button') and text()='Keluar']")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[contains(@class, 'nav-link') and text()='Kursus Saya']")
    private WebElement myCoursesNavbarLink;

    public DashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getAccountDropdown() {
        return accountDropdown;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public WebElement getMyCoursesNavbarLink() {
        return myCoursesNavbarLink;
    }
}
