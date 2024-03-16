Feature: Rank List Item API CRUD Operations

  Scenario: Create a Rank List Item
    Given no rank list item exists with name "Test Rank List Item 01C"
    And a list exists with the following details and I get its id
      | name          | description                                   | type |
      | Test List 01C | Test list 01 for rank list item creation test | RANK |
    And the list is currently of size 0
    When I create a rank list item with that list id and the following details
      | name                    | description                                             | rank |
      | Test Rank List Item 01C | Test rank list item 01 for rank list item creation test | 1    |
    Then I should get no error
    And I should get a rank list item with the following details
      | name                    | description                                             | rank |
      | Test Rank List Item 01C | Test rank list item 01 for rank list item creation test | 1    |
    And the list is currently of size 1
    And rank list items should exist with the following details
      | name                    | description                                             | rank |
      | Test Rank List Item 01C | Test rank list item 01 for rank list item creation test | 1    |
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database

  Scenario: Create Multiple Rank List Items
    Given no rank list items exist with names "Test Rank List Item 01C"
    And a list exists with the following details and I get its id
      | name          | description                                   | type |
      | Test List 02C | Test list 02 for rank list item creation test | RANK |
    And the list is currently of size 0
    When I create multiple rank list items with that list id and the following details
      | name                    | description                                             | rank |
      | Test Rank List Item 02C | Test rank list item 02 for rank list item creation test | 1    |
      | Test Rank List Item 03C | Test rank list item 03 for rank list item creation test | 2    |
      | Test Rank List Item 04C | Test rank list item 04 for rank list item creation test | 3    |
      | Test Rank List Item 05C | Test rank list item 05 for rank list item creation test | 4    |
    Then I should get no error
    And I should get rank list items with the following details
      | name                    | description                                             | rank |
      | Test Rank List Item 02C | Test rank list item 02 for rank list item creation test | 1    |
      | Test Rank List Item 03C | Test rank list item 03 for rank list item creation test | 2    |
      | Test Rank List Item 04C | Test rank list item 04 for rank list item creation test | 3    |
      | Test Rank List Item 05C | Test rank list item 05 for rank list item creation test | 4    |
    And the list is currently of size 4
    And rank list items should exist with the following details
      | name                    | description                                             | rank |
      | Test Rank List Item 02C | Test rank list item 02 for rank list item creation test | 1    |
      | Test Rank List Item 03C | Test rank list item 03 for rank list item creation test | 2    |
      | Test Rank List Item 04C | Test rank list item 04 for rank list item creation test | 3    |
      | Test Rank List Item 05C | Test rank list item 05 for rank list item creation test | 4    |
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database

  Scenario: Get a Rank List Item by Id
    Given a list exists with the following details and I get its id
      | name          | description                                  | type |
      | Test List 01R | Test list 01 for rank list item reading test | RANK |
    And rank list items exist with that list id and the following details
      | name                    | description                                            | rank |
      | Test Rank List Item 01R | Test rank list item 01 for rank list item reading test | 1    |
      | Test Rank List Item 02R | Test rank list item 02 for rank list item reading test | 3    |
      | Test Rank List Item 03R | Test rank list item 03 for rank list item reading test | 4    |
      | Test Rank List Item 04R | Test rank list item 04 for rank list item reading test | 2    |
    And I get the id of rank list item "Test Rank List Item 04R"
    When I get the rank list item with that id
    Then I should get no error
    And I should get a rank list item with the following details
      | name                    | description                                            | rank |
      | Test Rank List Item 04R | Test rank list item 04 for rank list item reading test | 2    |
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database

  Scenario: Get Rank List Items by List Id
    Given a list exists with the following details and I get its id
      | name          | description                                  | type |
      | Test List 02R | Test list 02 for rank list item reading test | RANK |
    And rank list items exist with that list id and the following details
      | name                    | description                                            | rank |
      | Test Rank List Item 05R | Test rank list item 05 for rank list item reading test | 1    |
      | Test Rank List Item 06R | Test rank list item 06 for rank list item reading test | 3    |
      | Test Rank List Item 07R | Test rank list item 07 for rank list item reading test | 2    |
    And a list exists with the following details and I get its id
      | name          | description                                  | type |
      | Test List 03R | Test list 03 for rank list item reading test | RANK |
    And rank list items exist with that list id and the following details
      | name                    | description                                            | rank |
      | Test Rank List Item 08R | Test rank list item 08 for rank list item reading test | 1    |
      | Test Rank List Item 09R | Test rank list item 09 for rank list item reading test | 2    |
      | Test Rank List Item 10R | Test rank list item 10 for rank list item reading test | 3    |
    When I get all rank list items with that list id
    Then I should get no error
    And I should only get rank list items with the following details
      | name                    | description                                            | rank |
      | Test Rank List Item 08R | Test rank list item 08 for rank list item reading test | 1    |
      | Test Rank List Item 09R | Test rank list item 09 for rank list item reading test | 2    |
      | Test Rank List Item 10R | Test rank list item 10 for rank list item reading test | 3    |
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database

  Scenario: Get all Rank List Items
    Given a list exists with the following details and I get its id
      | name          | description                                  | type |
      | Test List 04R | Test list 04 for rank list item reading test | RANK |
    And rank list items exist with that list id and the following details
      | name                    | description                                            | rank |
      | Test Rank List Item 11R | Test rank list item 11 for rank list item reading test | 2    |
      | Test Rank List Item 12R | Test rank list item 12 for rank list item reading test | 1    |
      | Test Rank List Item 13R | Test rank list item 13 for rank list item reading test | 4    |
      | Test Rank List Item 14R | Test rank list item 14 for rank list item reading test | 3    |
    When I get all rank list items
    Then I should get no error
    And I should get rank list items with the following details
      | name                    | description                                            | rank |
      | Test Rank List Item 11R | Test rank list item 11 for rank list item reading test | 2    |
      | Test Rank List Item 12R | Test rank list item 12 for rank list item reading test | 1    |
      | Test Rank List Item 13R | Test rank list item 13 for rank list item reading test | 4    |
      | Test Rank List Item 14R | Test rank list item 14 for rank list item reading test | 3    |
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database

  Scenario: Update a Rank List Item by Id
    Given a list exists with the following details and I get its id
      | name          | description                                 | type |
      | Test List 01U | Test list 01 for rank list item update test | RANK |
    And rank list items exist with that list id and the following details
      | name                    | description                                           | rank |
      | Test Rank List Item 01U | Test rank list item 01 for rank list item update test | 2    |
      | Test Rank List Item 02U | Test rank list item 02 for rank list item update test | 4    |
      | Test Rank List Item 03U | Test rank list item 03 for rank list item update test | 1    |
      | Test Rank List Item 04U | Test rank list item 04 for rank list item update test | 3    |
    And I get the id of rank list item "Test Rank List Item 02U"
    When I update the rank list item with that id and the following details
      | name                           | description                                            | rank |
      | Updated Test Rank List Item 02 | Updated test rank list item 02 for rank list item test | 5    |
    Then I should get no error
    And I should get a rank list item with the following details
      | name                           | description                                            | rank |
      | Updated Test Rank List Item 02 | Updated test rank list item 02 for rank list item test | 5    |
    And rank list items should exist with the following details
      | name                           | description                                            | rank |
      | Test Rank List Item 01U        | Test rank list item 01 for rank list item update test  | 2    |
      | Updated Test Rank List Item 02 | Updated test rank list item 02 for rank list item test | 5    |
      | Test Rank List Item 03U        | Test rank list item 03 for rank list item update test  | 1    |
      | Test Rank List Item 04U        | Test rank list item 04 for rank list item update test  | 3    |
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database

  Scenario: Delete a Rank List Item by Id
    Given a list exists with the following details and I get its id
      | name          | description                                   | type |
      | Test List 01D | Test list 01 for rank list item deletion test | RANK |
    And rank list items exist with that list id and the following details
      | name                    | description                                             | rank |
      | Test Rank List Item 01D | Test rank list item 01 for rank list item deletion test | 2    |
      | Test Rank List Item 02D | Test rank list item 02 for rank list item deletion test | 1    |
      | Test Rank List Item 03D | Test rank list item 03 for rank list item deletion test | 3    |
      | Test Rank List Item 04D | Test rank list item 04 for rank list item deletion test | 4    |
    And the list is currently of size 4
    And I get the id of rank list item "Test Rank List Item 03D"
    When I delete the rank list item with that id
    Then I should get no error
    And the list is currently of size 3
    And rank list items should exist with the following details
      | name                    | description                                             | rank |
      | Test Rank List Item 01D | Test rank list item 01 for rank list item deletion test | 2    |
      | Test Rank List Item 02D | Test rank list item 02 for rank list item deletion test | 1    |
      | Test Rank List Item 04D | Test rank list item 04 for rank list item deletion test | 4    |
    And no rank list item should exist with name "Test List 03D"
    And I should purge the test rank list items from the database
    And I should purge the test lists from the database
