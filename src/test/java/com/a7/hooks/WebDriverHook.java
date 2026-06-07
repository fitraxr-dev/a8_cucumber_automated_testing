package com.a7.hooks;

import com.a7.config.WebDriverConfig;
import com.a7.context.TestContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class WebDriverHook {
    private TestContext testContext;
    
    public WebDriverHook(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setUp() {
        WebDriverConfig.initDriver();
        testContext.setDriver(WebDriverConfig.getDriver());
    }

    @After
    public void tearDown() {
        testContext.quitDriver();
    }

}
