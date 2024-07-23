@managerLogin
Feature: To test the Manager login functionality

  @addCustomer
  Scenario Outline: To test the Add Customer fucntionality by logging in as manager
    Given user is on login page
    And user is on manager login page
    When user clicks on add customer button
    And user gives <firstName> <lastName> <zipcode> as input
    Then user should be able to add

    Examples: 
      | firstName | lastName | zipcode |
      | Anna      | Marie    |   12000 |

  @openAccount
  Scenario: To test the open account functionality after adding new customer
    Given user is on Open Account page
    When user gives customer name and currency details
    Then account should be opened

  @customerDelete
  Scenario: To test the delete functionality after adding new customer
    Given user is on customers page
    When user gives customer name
    Then account should be deleted