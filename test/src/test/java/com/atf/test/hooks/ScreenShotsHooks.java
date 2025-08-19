package com.atf.test.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.ObjectProvider;

@Slf4j
public class ScreenShotsHooks {

    private final ObjectProvider<WebDriver> driverProvider;

    public ScreenShotsHooks(ObjectProvider<WebDriver> driverProvider) {
        this.driverProvider = driverProvider;
    }

    @After(value = "@ui", order = 0)
    public void snapOnFailure(Scenario s) {
        if (!s.isFailed()) return;

        WebDriver proxy = driverProvider.getIfAvailable();
        if (proxy == null) return;

        Object target = proxy;
        try {
            if (AopUtils.isAopProxy(proxy) && proxy instanceof Advised adv) {
                target = adv.getTargetSource().getTarget(); // unwrap
            }
        } catch (Exception ignored) {
        }

        if (target instanceof TakesScreenshot ts) {
            s.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
        } else {
            log.warn("Target driver does not implement TakesScreenshot: {}", target.getClass());
        }
    }
}
