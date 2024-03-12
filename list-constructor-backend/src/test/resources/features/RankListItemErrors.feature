Feature: Rank List Item API Expected Errors

  Scenario: Try to Create a Rank List Item without List Id
    Given I have no list id
    When I create a rank list item with that list id and the following details
      | name | description | rank |
      |      |             |      |
    Then I should get a bad request error with the following message and message details
      | message                                        | messageDetails | requestDetails |
      | List id can't be null when creating list items |                |                |

  Scenario: Try to Create a Rank List Item with an Invalid List Id
    Given lists exist with the following details
      | name           | description                                         | type |
      | Test List 01CE | Test list 01 for rank list item creation error test | RANK |
      | Test List 02CE | Test list 02 for rank list item creation error test | RANK |
    And I get a randomly generated list id
    When I create a rank list item with that list id and the following details
      | name | description | rank |
      |      |             |      |
    Then I should get a resource not found by list id error
    And I should purge the test lists from the database

  Scenario: Try to Create a Rank List Item without Necessary Data
    Given a list exists with the following details and I get its id
      | name           | description                                         | type |
      | Test List 03CE | Test list 03 for rank list item creation error test | RANK |
    When I create a rank list item with that list id and the following details
      | name | description | rank |
      |      |             |      |
    Then I should get a bad request error with the following message and message details
      | message         | messageDetails         | requestDetails |
      | Invalid Payload | name: must not be null |                |
    And I should purge the test lists from the database

  Scenario: Try to Create Multiple Rank List Items without List Id
    Given I have no list id
    When I create multiple rank list items with that list id and the following details
      | name | description | rank |
      |      |             |      |
      |      |             |      |
      |      |             |      |
    Then I should get a bad request error with the following message and message details
      | message                                        | messageDetails | requestDetails |
      | List id can't be null when creating list items |                |                |

  Scenario: Try to Create Multiple Rank List Items with an Invalid List Id
    Given lists exist with the following details
      | name           | description                                         | type |
      | Test List 04CE | Test list 04 for rank list item creation error test | RANK |
      | Test List 05CE | Test list 05 for rank list item creation error test | RANK |
    And I get a randomly generated list id
    When I create multiple rank list items with that list id and the following details
      | name | description | rank |
      |      |             |      |
      |      |             |      |
      |      |             |      |
    Then I should get a resource not found by list id error
    And I should purge the test lists from the database

  Scenario: Try to Create Multiple Rank List Items without Necessary Data
    Given a list exists with the following details and I get its id
      | name           | description                                         | type |
      | Test List 06CE | Test list 06 for rank list item creation error test | RANK |
    When I create multiple rank list items with that list id and the following details
      | name | description | rank |
      |      |             |      |
      |      |             |      |
      |      |             |      |
    Then I should get a bad request error with the following message and message details
      | message         | messageDetails         | requestDetails |
      | Invalid Payload | name: must not be null |                |
    And I should purge the test lists from the database

  Scenario: Try to Get a Rank List Item with an Invalid Id
    Given a list exists with the following details and I get its id
      | name          | description                                  | type |
      | Test List 01R | Test list 01 for rank list item reading test | RANK |
    And rank list items exist with that list id and the following details
      | name                     | description                                                  | rank |
      | Test Rank List Item 01RE | Test rank list item 01 for rank list item reading error test | 1    |
      | Test Rank List Item 02RE | Test rank list item 02 for rank list item reading error test | 2    |
    And I get a randomly generated id
    When I get the rank list item with that id
    Then I should get a resource not found by id error
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database

  Scenario: Try to Get Rank List Items with an Invalid List Id
    Given a list exists with the following details and I get its id
      | name          | description                                  | type |
      | Test List 01R | Test list 01 for rank list item reading test | RANK |
    And rank list items exist with that list id and the following details
      | name                     | description                                                  | rank |
      | Test Rank List Item 01RE | Test rank list item 01 for rank list item reading error test | 7    |
      | Test Rank List Item 02RE | Test rank list item 02 for rank list item reading error test | 4    |
    And I get a randomly generated list id
    When I get all rank list items with that list id
    Then I should get a resource not found by list id error
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database

  Scenario: Try to Update a Rank List Item with an Invalid Id
    Given a list exists with the following details and I get its id
      | name           | description                                       | type |
      | Test List 01UE | Test list 01 for rank list item update error test | RANK |
    And rank list items exist with that list id and the following details
      | name                     | description                                                 | rank |
      | Test Rank List Item 01UE | Test rank list item 01 for rank list item update error test | 99   |
      | Test Rank List Item 02UE | Test rank list item 02 for rank list item update error test | 42   |
    And I get a randomly generated id
    When I update the rank list item with that id and the following details
      | name                           | description                                                  | rank |
      | Updated Test Rank List Item 02 | Updated test rank list item 02 for rank list item error test | 73   |
    Then I should get a resource not found by id error
    And rank list items should exist with the following details
      | name                     | description                                                 | rank |
      | Test Rank List Item 01UE | Test rank list item 01 for rank list item update error test | 99   |
      | Test Rank List Item 02UE | Test rank list item 02 for rank list item update error test | 42   |
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database

  Scenario: Try to Delete a Rank List Item with an Invalid Id
    Given a list exists with the following details and I get its id
      | name           | description                                         | type |
      | Test List 01DE | Test list 01 for rank list item deletion error test | RANK |
    And rank list items exist with that list id and the following details
      | name                     | description                                                   | rank |
      | Test Rank List Item 01DE | Test rank list item 01 for rank list item deletion error test | 0    |
      | Test Rank List Item 02DE | Test rank list item 02 for rank list item deletion error test | 1    |
    And I get a randomly generated id
    When I delete the rank list item with that id
    Then I should get a resource not found by id error
    And rank list items should exist with the following details
      | name                     | description                                                   | rank |
      | Test Rank List Item 01DE | Test rank list item 01 for rank list item deletion error test | 0    |
      | Test Rank List Item 02DE | Test rank list item 02 for rank list item deletion error test | 1    |
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database