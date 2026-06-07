package com.a7.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(css = "input.form-control:nth-child(1)")
    private WebElement usernameField;

    @FindBy(css = "input.form-control:nth-child(2)")
    private WebElement passwordField;

    @FindBy(css = ".btn-danger")
    private WebElement loginButton;

    @FindBy(css = "#swal2-html-container")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }
}
