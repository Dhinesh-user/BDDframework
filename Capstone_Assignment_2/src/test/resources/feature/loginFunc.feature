@custlogin
Feature: To test the login functionality

  @login
  Scenario: Login functionality
    Given user is on login page
    When user is on customer login
    And selects the customer name
    Then account details should be displayed

  @deposit
  Scenario Outline: deposit functionality
    Given user is on deposit page
    When user gives <deposit amount> and clicks on deposit button
    Then amount should be deposited succesfully

    Examples: 
      | deposit amount |
      |            300 |
      |              0 |
      |           -200 |
      |         500000 |
      |          -3000 |

  @withdrawal
  Scenario Outline: withdrawal functionality
    Given user is on withdrawal page
    When user gives <withdrawal amount> and clicks on withdrawal button
    Then amount should be withdrawn succesfully

    Examples: 
      | withdrawal amount |
      |              3000 |
      |                 0 |
      |              -200 |
      |               500 |
      |             -3000 |

  @transactions
  Scenario: Transactions functionality
    When user clicks on transactions button
    Then user should be able to see transactions

  @logout
  Scenario: Logout functionality
    When user clicks on logout button
    Then user should get logged out