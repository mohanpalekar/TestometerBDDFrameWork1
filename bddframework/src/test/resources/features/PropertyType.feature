@Regression @Sanity @Test231
Feature: Browse By Property Types
  @Test2
  Scenario: Verify that user is able to see Browse By Property Types
    Given I am on the landing page
    And I close the popup when I see it
    Then I see Browse By Property Types Hotels
    Then I see Browse By Property Types Apartments
    Then I see Browse By Property Types Resorts2
    Then I see Browse By Property Types Villas

  @Test3 
  Scenario Outline: Verify that user is able to see Browse By Property Types
    Given I am on the landing page
    And I close the popup when I see it
    Then I see Browse By Property Types <propertyName>
    
  Examples:
  
  |propertyName|
  |Hotels|
  |Apartments|
  |Resorts2|
  |Villas|  
    
