package com.atf.ui.pages;

import com.atf.ui.driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Autowired
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void type(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected String getValue(By locator) {
        return driver.findElement(locator).getAttribute("value");
    }

    protected String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
