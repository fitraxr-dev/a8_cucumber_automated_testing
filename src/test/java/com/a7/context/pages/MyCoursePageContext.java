package com.a7.context.pages;

import org.openqa.selenium.WebDriver;

import com.a7.action.MyCourseAction;
import com.a7.context.TestContext;

public class MyCoursePageContext {
    private WebDriver driver;
    private MyCourseAction myCourseAction;

    public MyCoursePageContext(TestContext testContext) {
        this.driver = testContext.getDriver();
    }

    public MyCourseAction getMyCourseAction() {
        if (myCourseAction == null) {
            myCourseAction = new MyCourseAction(driver);
        }
        return myCourseAction;
    }

}
