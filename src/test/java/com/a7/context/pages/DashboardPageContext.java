package com.a7.context.pages;

import org.openqa.selenium.WebDriver;

import com.a7.action.DashboardAction;
import com.a7.context.TestContext;

public class DashboardPageContext {
    private WebDriver driver;
    private DashboardAction dashboardAction;

    public DashboardPageContext(TestContext testContext) {
        this.driver = testContext.getDriver();
        this.dashboardAction = new DashboardAction(driver);
    }

    public DashboardAction getDashboardAction() {
        return dashboardAction;
    }
}
