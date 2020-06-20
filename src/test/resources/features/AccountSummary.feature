@smoke
Feature: Smoke test

  Background:
    Given I am on the Login page
    Then I login with valid credentials

  Scenario: Account summary
    Given the Account Summary page should be displayed
    And Account summary page should have the title "Zero - Account Summary"
    Then Account summary page should have the following account types: Cash Accounts, Investment Accounts, Credit Accounts, Loan Accounts
    And Credit Accounts table must have columns Account, Credit Card and Balance

