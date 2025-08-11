package com.atf.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebFormPage extends BasePage {

    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    private final By textInput     = By.name("my-text");
    private final By passwordInput = By.name("my-password");
    private final By submitButton  = By.tagName("button");

    @Autowired
    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public void enterText(String text) {
        type(textInput, text);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void submitForm() {
        click(submitButton);
    }

    public String getEnteredText() {
        return getValue(textInput);
    }
}
