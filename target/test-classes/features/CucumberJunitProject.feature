
Feature: CucumberJunitProject
 
  Scenario: REG 001
Given User is on Homepage
Then  User hovers on SignInOption
And User clicks on SignInButton
When User clicks on CreateNewAccountButton


 Scenario: REG 002
Given User is on Homepage
Then  User hovers on SignInOption
And User clicks on SignInButton
When User clicks on CreateNewAccountButton
Then User fills all mandatory field
When User clicks on ContinueButton