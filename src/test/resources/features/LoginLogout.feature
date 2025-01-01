Feature: User Session Management

  Scenario: Login and Logout Functionality
    Given the User is on the login page
    When the User logs in with valid credentials
    Then the User should see the dashboard
    When the User logs out
    Then the User should return to the login page
