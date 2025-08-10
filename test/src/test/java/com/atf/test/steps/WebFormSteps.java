package com.atf.test.steps;

import com.atf.ui.pages.WebFormPage;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.slf4j.MDC;

public class WebFormSteps {

    private final WebFormPage page = new WebFormPage();

    @Given("I open the web form page")
    public void i_open_the_web_form_page() {
        MDC.put("testId", "web-form-feature");
        page.open();
    }

    @When("I enter {string}")
    public void i_enter(String text) {
        page.enterText(text);
    }

    @Then("the text field should contain {string}")
    public void the_text_field_should_contain(String expected) {
        String actual = page.getEnteredText();
        Assertions.assertEquals(expected, actual, "Text field value mismatch");
        MDC.clear();
    }
}
