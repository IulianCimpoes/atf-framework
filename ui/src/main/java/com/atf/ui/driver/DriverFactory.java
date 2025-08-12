package com.atf.ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
public class DriverFactory {

    public WebDriver createDriver() {
        return  WebDriverManager.chromedriver().create();
    }
}