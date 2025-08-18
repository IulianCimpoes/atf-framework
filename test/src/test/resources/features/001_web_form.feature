@ui
Feature: Web Form interaction 01

  Scenario: Submit a simple text input 01
    Given I open the web form page
    When I enter "Test from Cucumber"
    Then the text field should contain "Test from Cucumber"