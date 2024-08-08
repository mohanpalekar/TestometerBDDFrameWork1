#Author: your.email@your.domain.com

Feature: Login page scenarios

@BDD
Scenario: Verify that the login is successful
Given I click My Account
And I enter username as test1
And I enter password as test2
And I click on login button
Then I see dashboard page
