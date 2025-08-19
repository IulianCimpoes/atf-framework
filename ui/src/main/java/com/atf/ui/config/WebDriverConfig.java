package com.atf.ui.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;


@Configuration
@Slf4j
public class WebDriverConfig {

    @Value("${ui.headless:true}")
    private boolean headless;

    @Bean(destroyMethod = "quit")
    @Scope(value = "cucumber-glue",
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Lazy
    public WebDriver webDriver() {
        WebDriverManager.chromedriver().setup();
        log.info("Chrome driver has been initialized");
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
