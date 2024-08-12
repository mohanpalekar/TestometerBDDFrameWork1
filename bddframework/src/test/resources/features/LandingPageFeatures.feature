
@Regression @Sanity
Feature: Verify landing Page Features

   Scenario: Verify that main features is the site are displayed to the user
    Given I am on the landing page
    Then I see the features called Stays
    Then I see the features called Flights
    Then I see the features called Flights+Hotel
    Then I see the features called Car rentals
    Then I see the features called Attractions
    Then I see the features called Airport taxis
    
    
    Scenario Outline: Verify that main features is the site are displayed to the user
    Given I am on the landing page
    Then I see the features called <featureName>
    
    Examples:
    |featureName|
    |Stays|
    |Flights|
    |Flights+Hotel|
    |Car rentals|
    |Attractions|
    |Airport taxis|
    
    
    Scenario: Verify that main features is the site are displayed to the user
    Given I am on the landing page
    Then I see the features called Stays
    
    Scenario: Verify that main features is the site are displayed to the user
    Given I am on the landing page
    Then I see the features called Flights
    
    
    Scenario: Verify that main features is the site are displayed to the user
    Given I am on the landing page
    Then I see the features called Flights+Hotel
    
    
    Scenario: Verify that main features is the site are displayed to the user
    Given I am on the landing page
    Then I see the features called Car rentals
    
    Scenario: Verify that main features is the site are displayed to the user
    Given I am on the landing page
    Then I see the features called Attractions
    
    Scenario: Verify that main features is the site are displayed to the user
    Given I am on the landing page
    Then I see the features called Airport taxis

