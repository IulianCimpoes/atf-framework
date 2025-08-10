Feature: Web Form interaction

  Scenario: Submit a simple text input
    Given I open the web form page
    When I enter "Test from Cucumber"
    Then the text field should contain "Test from Cucumber"