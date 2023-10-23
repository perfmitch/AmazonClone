Feature: Amazon login,search, add to cart

  @Login
  Scenario: User is able to login into Amazon
    Given I am on Amazon homepage
    And I click on Log in
    Then I enter the username
      | username                       |
      | ERROR |
    And I click on continue
    Then I enter the password
      | password       |
      | NOTHING |
    And I click on Stay Logged In
    Then I click on Sign In
    And I store the cookies in auth.json

  @Search
  Scenario: User is able to search for a product and add it to cart
    Given I am logged in on Amazon
    Then I search for the following products
      | product        |
      | iphone charger |
    And I click on the Nth product
      | Nth product |
      | 5           |
    Then I am on the product page
    Then I select the quantity
      | quantity |
      | 8        |
    And I click on add to cart

  @Checkout
  Scenario: User is able to checkout
    Given I am on the cart page
    When I click on Proceed to Checkout
    Then I change the address
    And I click on add a new address
    Then I fill out the address form with the details
      | name      | phone      | address1         | address2    | city      | state     | zipcode |
      | John Wick | 5044583993 | 538 Jefferson St | "" | Lafayette | LA | 70501   |
    And I save the information to use the address
    Then I click add a new card
    And I fill out the card form with the details
      | card number      | name      | expiration month | expiration year | security code |
      | 4254188570240562 | john wick | 09               | 2027            | 264           |
    Then  I save the information to use this payment method






