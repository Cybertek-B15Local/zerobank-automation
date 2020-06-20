@smoke
@regression
Feature: Automating login functionality

  Background:
    Given I am on the Login page

  Scenario: As an authorized user I should be able to login
    And I login with username "username" and password "password"
    Then the Account Summary page should be displayed

  Scenario: As an unauthorized user I should NOT be able to login
    When I login with username "wrong" and password "wrong"
    Then Error message Login and/or password are wrong. should be displayed.
    And I login with username "" and password ""
    Then Error message Login and/or password are wrong. should be displayed.

