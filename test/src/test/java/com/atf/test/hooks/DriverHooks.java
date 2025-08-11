package com.atf.test.hooks;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverHooks {

    @Autowired
    private WebDriver driver;

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}