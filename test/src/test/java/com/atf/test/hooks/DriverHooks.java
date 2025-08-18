package com.atf.test.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class DriverHooks {

    @Autowired
    private ObjectProvider<WebDriver> driverProvider;

    @Before("@ui")
    public void setup() {
        log.info("Init actions for @ui scenarios");
        // Already initialized in @Bean
        driverProvider.getObject().manage().window().maximize();
    }

    @After("@ui")
    public void teardown() {
        WebDriver driver = driverProvider.getIfAvailable();
        if (driver != null) {
            log.info("Closing WebDriver after @ui scenario");
            try {
                driver.quit();
            } catch (Exception e) {
                log.error("Error while closing WebDriver: {}", e.getMessage());
            }
        } else {
            log.warn("WebDriver was not initialized or already closed.");
        }

    }
}
