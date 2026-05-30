package com.a7.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue = "com.a7.steps",
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)
public class RunCucumberTest {
}