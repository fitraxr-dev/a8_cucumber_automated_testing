package com.a7.context.pages;

import org.openqa.selenium.WebDriver;

import com.a7.action.LoginAction;
import com.a7.context.TestContext;

public class LoginPageContext {
    private WebDriver driver;
    private LoginAction loginAction;

    public LoginPageContext(TestContext testContext) {
        this.driver = testContext.getDriver();
    }

    public LoginAction getLoginAction() {
        if (loginAction == null) {
            loginAction = new LoginAction(driver);
        }
        return loginAction;
    }
}
