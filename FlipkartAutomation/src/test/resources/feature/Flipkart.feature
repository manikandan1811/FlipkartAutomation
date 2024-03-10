@Flipkart
Feature: Verify the flipkart order summary

  @FlipkartAddCart
  Scenario: To validate the prodcut is added to the cart
    Given Launch the url "https://www.flipkart.com"
    When Enter "laptop" in a search text
    And Select the "2" product
    And Check deliver pincode as "628251"
    And click on Buy now button
    And Enter the login credentials "9566978090"
    And Enter shipping details
      | Name        | Manikandan      |
      | MobileNo    |      9566978090 |
      | Pincode     |          628251 |
      | Locality    | Sravanampatti   |
      | Address     | 1/3 Indra Nagar |
      | AddressType | HOME            |
    And click on continue button
    And Select payment options
    Then Verify Order Summary
