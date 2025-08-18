package com.atf.test.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class DriverHooks {

    @Lazy
    private WebDriver driver;

    @Before("@ui")
    public void setup() {
        // Already initialized in @Bean
        driver.manage().window().maximize();
    }

    @After("@ui")
    public void teardown() {
        driver.quit();
    }
}
