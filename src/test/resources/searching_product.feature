Feature: Searching product
  Scenario: User inputs criteria and product is found
    Given User opens page
    And User accepts cookies
    And User inputs query="lodówka side side lodówki" and clicks Search button
    When User reads all data of products
    Then Data is saved
