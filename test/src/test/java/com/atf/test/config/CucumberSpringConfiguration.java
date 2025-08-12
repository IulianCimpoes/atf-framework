package com.atf.test.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = com.atf.test.config.TestApplication.class)
public class CucumberSpringConfiguration {
}
