Feature: How Old Are you check
  This file check 
  
Background: user is online
Given User navigates to site HowOldAreYou

@Last
Scenario: User Submit without Name

When user is in How old are you homepage
And user fetch testData for "SubmitWithoutName"
And user fill date
And user Submits form
Then user should be getting name error

Scenario: User Submit without Date

When user is in How old are you homepage
And user fetch testData for "SubmitWithoutDate"
And user fill name
And user Submits form
Then user should be getting date error

@Last
Scenario: User Born in 1990

When user is in How old are you homepage
And user fetch testData for "UserBornIn1990"
And user fill user details
And user Submits form
Then user should be directed to Results Page
And users name should be dislayed correctly
And users age should be calculated correctly

