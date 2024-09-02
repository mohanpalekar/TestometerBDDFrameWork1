 @Regression  @Sanity @Test231
Feature: Invalid Password
 
 
  Scenario: Verify that invalid passwords are identified
    Given I am on the landing page
    And I close the popup when I see it
    And I click Login
    And I enter username as mohanpalekar02@gmail.com
    And I enter password as 123456
    And I click on login button
    Then I verify that invalid password error message is shown to the user

