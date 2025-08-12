package com.atf.ui.config;

import com.atf.ui.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

    private final DriverFactory driverFactory;

    public WebDriverConfig(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    @Bean
    public WebDriver webDriver() {
        return driverFactory.createDriver();
    }
}
