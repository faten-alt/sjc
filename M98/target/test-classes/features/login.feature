Feature: Login Feature

  Scenario: Valid login
    Given user is on login page
    When user enters username "28888600066" and password "testpass"
    Then user should be logged in successfully

  Scenario: Submit valid request
    Given user is on login page
    When user enters username "28888600066" and password "testpass"
    Then user should be logged in successfully
    When press on MyServices
    Then navigated to the EightyNine Service
    And submit the service with pleasure