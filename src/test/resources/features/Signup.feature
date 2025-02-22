Feature: Account Signup Functionality

  Scenario: Verify all mandatory fields are present
    Given I am on the "signup" page
    Then I should see all required fields
    Then I close the page

  Scenario: Verify error messages for empty fields
    Given I am on the "signup" page
    When I click on Create Account
    Then I click on Create Account
    Then I should see error messages for required fields
    Then I close the page

  Scenario: Verify valid email format is required
    Given I am on the "signup" page
    When I enter "John" as first name
    And I enter "Doe" as last name
    And I enter "invalidEmail" as email
    And I enter "Test@1234" as password
    And I confirm "Test@1234" as password
    And I click on Create Account
    Then I should see an error message "Please enter a valid email address (Ex: johndoe@domain.com)."
    Then I close the page

  Scenario: Verify password strength validation
    Given I am on the "signup" page
    When I enter "John" as first name
    And I enter "Doe" as last name
    And I enter a unique email
    And I enter "123456" as password
    And I click on Create Account
    Then I should see password strength as "Weak"
    Then I close the page

  Scenario: Verify passwords must match
    Given I am on the "signup" page
    When I enter "John" as first name
    And I enter "Doe" as last name
    And I enter a unique email
    And I enter "Test@1234" as password
    And I confirm "Test@5678" as password
    And I click on Create Account
    Then I should see a password error message "Please enter the same value again"
    Then I close the page

  Scenario: Verify successful account creation
    Given I am on the "signup" page
    When I enter "John" as first name
    And I enter "Doe" as last name
    And I enter a unique email
    And I enter "Test@1234" as password
    And I confirm "Test@1234" as password
    And I click on Create Account
    Then I should be redirected to the account dashboard

  Scenario: Verify error for already registered email
    Given I am on the "signup" page
    When I enter "John" as first name
    And I enter "Doe" as last name
    And I enter an existing email
    And I enter "Test@1234" as password
    And I confirm "Test@1234" as password
    And I click on Create Account
    Then I should see an error alert "There is already an account with this email address"
    Then I close the page

  Scenario: Verify SQL Injection is prevented
    Given I am on the "signup" page
    When I enter SQL injection in email field
    And I click on Create Account
    Then I should see an error message "Please enter a valid email address (Ex: johndoe@domain.com)."
    Then I close the page

  Scenario: Verify XSS attack is prevented
    Given I am on the "signup" page
    When I enter XSS script in email field
    And I click on Create Account
    Then I should see an error message "Please enter a valid email address (Ex: johndoe@domain.com)."
    Then I close the page
    
  Scenario: Verify signIn
    Given I am on the "signin" page
    When I enter "test1740202774374@test.com" as signIn email
    And I enter "Test@1234" as signIn pass
    And I click signIn button
    Then I should be redirected to the Home page
