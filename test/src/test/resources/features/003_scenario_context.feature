Feature: Scenario context interaction 03

  Scenario: Save and retrieve from Scenario Context
    Given I save "Test from Cucumber" in the Scenario Context
    Then Retrieved value from Scenario Context is "Test from Cucumber"

