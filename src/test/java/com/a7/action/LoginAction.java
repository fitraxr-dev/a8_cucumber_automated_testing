package com.a7.action;

import com.a7.pages.LoginPage;
import com.a7.pages.DashboardPage;
import org.openqa.selenium.WebDriver;

public class LoginAction {
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginAction(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void enterUsername(String username) {
        loginPage.getUsernameField().clear();
        loginPage.getUsernameField().sendKeys(username);
    }

    public void enterPassword(String password) {
        loginPage.getPasswordField().clear();
        loginPage.getPasswordField().sendKeys(password);
    }

    public DashboardPage clickLogin() {
        loginPage.getLoginButton().click();
        return new DashboardPage(driver);
    }
}
