package com.atf.test.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;

@RequiredArgsConstructor
public class DriverHooks {

    private final WebDriver driver;

    @Before
    public void setup() {
        // Already initialized in @Bean
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
