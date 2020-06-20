@smoke @regression
Feature: Pay Bills verification

  Background:
    Given I am on the Login page
    Then I login with valid credentials
    And I navigate to Pay Bills page


  Scenario: Pay Bills title
    Given  Pay Bills page should have the title "Zero - Pay Bills"
    And I complete a successful Pay operation
    Then The payment was successfully submitted. alert should be displayed

  Scenario: Negative
    When I try to make a payment without entering the amount or date
    Then Please fill out this field. alert should be displayed


  Scenario: Alphabetical input is not accepted
    When I enter date as an alphabetical value
    Then The date input should contain only numeric value

