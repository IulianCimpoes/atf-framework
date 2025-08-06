package com.atf.ui.tests;

import com.atf.ui.driver.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@Slf4j
public abstract class BaseTest {


    @BeforeEach
    public void setup() {
        log.info(">>> BaseTest.setup() CALLED");
        DriverFactory.initDriver();
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
