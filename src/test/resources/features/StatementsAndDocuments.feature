@regression
Feature: Statements and Documents details
  Background:
    Given the user is logged in
    And I am on the Online Statements page


  Scenario Outline: Recent statements per year
    When the user selects the Recent Statements Year <year>
    Then <count> statements should be displayed for that <year>


    Examples:
  | year | count |
  | 2009 | 2     |
  | 2010 | 2     |
  | 2011 | 2     |
  | 2012 | 1     |

  Scenario Outline: Download statements
    And the user selects the Recent Statements Year <year>
    When the user clicks on statement "<statement>"
    Then the downloaded file name should contain "<name>"
    And the file type should be pdf
    Examples:
      | year | statement               | name     |
      | 2009 | Statement 31/11/09(57K) | 31-11-09 |
      | 2010 | Statement 01/12/10(57K) | 01-12-10 |
      | 2011 | Statement 05/12/11(57K) | 05-12-11 |
      | 2012 | Statement 01/10/12(57K) | 01-10-12 |