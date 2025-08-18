@ui
Feature: Web Form interaction 02

  Scenario: Submit a simple text input 02
    Given I open the web form page
    When I enter "Test from Cucumber"
    Then the text field should contain "Test from Cucumber"