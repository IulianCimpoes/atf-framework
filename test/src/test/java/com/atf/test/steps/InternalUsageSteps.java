package com.atf.test.steps;

import com.atf.test.context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class InternalUsageSteps {

    private final ScenarioContext scenarioContext;

    @Given("I save {string} in the Scenario Context")
    public void iSaveInTheScenarioContext(String value) {
        scenarioContext.put("testValue", value);
    }

    @Then("Retrieved value from Scenario Context is {string}")
    public void retrievedValueFromScenarioContextIs(String expectedValue) {
        String actualValue = scenarioContext.get("testValue");
        assertEquals(expectedValue, actualValue, "Value mismatch in Scenario Context");
    }
}
