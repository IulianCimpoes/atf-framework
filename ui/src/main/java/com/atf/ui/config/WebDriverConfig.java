package com.atf.ui.config;

import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebDriverConfig {

    @Value("${ui.headless:true}")
    private boolean headless;

    @Bean(destroyMethod = "quit")
    @ScenarioScope
    public WebDriver webDriver() {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1280,900");
        } else {
            options.addArguments("--start-maximized");
        }
        return new ChromeDriver(options);
    }
}
