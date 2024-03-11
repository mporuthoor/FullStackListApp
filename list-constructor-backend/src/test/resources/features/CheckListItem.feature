Feature: Check List Item API CRUD Operations

  Scenario: Create a Check List Item
    Given no check list item exists with name "Test Check List Item 01C"
    And a list exists with the following details and I get its id
      | name          | description                                    | type  |
      | Test List 01C | Test list 01 for check list item creation test | CHECK |
    And the list is currently of size 0
    When I create a check list item with that list id and the following details
      | name                     | description                                               | checked |
      | Test Check List Item 01C | Test check list item 01 for check list item creation test | true    |
    Then I should get no error
    And I should get a check list item with the following details
      | name                     | description                                               | checked |
      | Test Check List Item 01C | Test check list item 01 for check list item creation test | true    |
    And the list is currently of size 1
    And check list items should exist with the following details
      | name                     | description                                               | checked |
      | Test Check List Item 01C | Test check list item 01 for check list item creation test | true    |
    And I should purge the test check list items from the database
    And I should purge the test lists from the database

  Scenario: Create Multiple Check List Items
    Given no check list items exist with names "Test Check List Item 01C"
    And a list exists with the following details and I get its id
      | name          | description                                    | type  |
      | Test List 02C | Test list 02 for check list item creation test | CHECK |
    And the list is currently of size 0
    When I create multiple check list items with that list id and the following details
      | name                     | description                                               | checked |
      | Test Check List Item 02C | Test check list item 02 for check list item creation test | true    |
      | Test Check List Item 03C | Test check list item 03 for check list item creation test | false   |
      | Test Check List Item 04C | Test check list item 04 for check list item creation test | false   |
      | Test Check List Item 05C | Test check list item 05 for check list item creation test | true    |
    Then I should get no error
    And I should get check list items with the following details
      | name                     | description                                               | checked |
      | Test Check List Item 02C | Test check list item 02 for check list item creation test | true    |
      | Test Check List Item 03C | Test check list item 03 for check list item creation test | false   |
      | Test Check List Item 04C | Test check list item 04 for check list item creation test | false   |
      | Test Check List Item 05C | Test check list item 05 for check list item creation test | true    |
    And the list is currently of size 4
    And check list items should exist with the following details
      | name                     | description                                               | checked |
      | Test Check List Item 02C | Test check list item 02 for check list item creation test | true    |
      | Test Check List Item 03C | Test check list item 03 for check list item creation test | false   |
      | Test Check List Item 04C | Test check list item 04 for check list item creation test | false   |
      | Test Check List Item 05C | Test check list item 05 for check list item creation test | true    |
    And I should purge the test check list items from the database
    And I should purge the test lists from the database

  Scenario: Get a Check List Item by Id
    Given a list exists with the following details and I get its id
      | name          | description                                   | type  |
      | Test List 01R | Test list 01 for check list item reading test | CHECK |
    And check list items exist with that list id and the following details
      | name                     | description                                              | checked |
      | Test Check List Item 01R | Test check list item 01 for check list item reading test | true    |
      | Test Check List Item 02R | Test check list item 02 for check list item reading test | true    |
      | Test Check List Item 03R | Test check list item 03 for check list item reading test | false   |
      | Test Check List Item 04R | Test check list item 04 for check list item reading test | false   |
    And I get the id of check list item "Test Check List Item 04R"
    When I get the check list item with that id
    Then I should get no error
    And I should get a check list item with the following details
      | name                     | description                                              | checked |
      | Test Check List Item 04R | Test check list item 04 for check list item reading test | false   |
    And I should purge the test check list items from the database
    And I should purge the test lists from the database

  Scenario: Get Check List Items by List Id
    Given a list exists with the following details and I get its id
      | name          | description                                   | type  |
      | Test List 02R | Test list 02 for check list item reading test | CHECK |
    And check list items exist with that list id and the following details
      | name                     | description                                              | checked |
      | Test Check List Item 05R | Test check list item 05 for check list item reading test | true    |
      | Test Check List Item 06R | Test check list item 06 for check list item reading test | true    |
      | Test Check List Item 07R | Test check list item 07 for check list item reading test | false   |
    And a list exists with the following details and I get its id
      | name          | description                                   | type  |
      | Test List 03R | Test list 03 for check list item reading test | CHECK |
    And check list items exist with that list id and the following details
      | name                     | description                                              | checked |
      | Test Check List Item 08R | Test check list item 08 for check list item reading test | true    |
      | Test Check List Item 09R | Test check list item 09 for check list item reading test | false   |
      | Test Check List Item 10R | Test check list item 10 for check list item reading test | false   |
    When I get all check list items with that list id
    Then I should get no error
    And I should only get check list items with the following details
      | name                     | description                                              | checked |
      | Test Check List Item 08R | Test check list item 08 for check list item reading test | true    |
      | Test Check List Item 09R | Test check list item 09 for check list item reading test | false   |
      | Test Check List Item 10R | Test check list item 10 for check list item reading test | false   |
    And I should purge the test check list items from the database
    And I should purge the test lists from the database

  Scenario: Get all Check List Items
    Given a list exists with the following details and I get its id
      | name          | description                                   | type  |
      | Test List 04R | Test list 04 for check list item reading test | CHECK |
    And check list items exist with that list id and the following details
      | name                     | description                                              | checked |
      | Test Check List Item 11R | Test check list item 11 for check list item reading test | false   |
      | Test Check List Item 12R | Test check list item 12 for check list item reading test | true    |
      | Test Check List Item 13R | Test check list item 13 for check list item reading test | false   |
      | Test Check List Item 14R | Test check list item 14 for check list item reading test | true    |
    When I get all check list items
    Then I should get no error
    And I should get check list items with the following details
      | name                     | description                                              | checked |
      | Test Check List Item 11R | Test check list item 11 for check list item reading test | false   |
      | Test Check List Item 12R | Test check list item 12 for check list item reading test | true    |
      | Test Check List Item 13R | Test check list item 13 for check list item reading test | false   |
      | Test Check List Item 14R | Test check list item 14 for check list item reading test | true    |
    And I should purge the test check list items from the database
    And I should purge the test lists from the database

  Scenario: Update a Check List Item by Id
    Given a list exists with the following details and I get its id
      | name          | description                                  | type  |
      | Test List 01U | Test list 01 for check list item update test | CHECK |
    And check list items exist with that list id and the following details
      | name                     | description                                             | checked |
      | Test Check List Item 01U | Test check list item 01 for check list item update test | false   |
      | Test Check List Item 02U | Test check list item 02 for check list item update test | false   |
      | Test Check List Item 03U | Test check list item 03 for check list item update test | true    |
      | Test Check List Item 04U | Test check list item 04 for check list item update test | true    |
    And I get the id of check list item "Test Check List Item 02U"
    When I update the check list item with that id and the following details
      | name                            | description                                              | checked |
      | Updated Test Check List Item 02 | Updated test check list item 02 for check list item test | true    |
    Then I should get no error
    And I should get a check list item with the following details
      | name                            | description                                              | checked |
      | Updated Test Check List Item 02 | Updated test check list item 02 for check list item test | true    |
    And check list items should exist with the following details
      | name                            | description                                              | checked |
      | Test Check List Item 01U        | Test check list item 01 for check list item update test  | false   |
      | Updated Test Check List Item 02 | Updated test check list item 02 for check list item test | true    |
      | Test Check List Item 03U        | Test check list item 03 for check list item update test  | true    |
      | Test Check List Item 04U        | Test check list item 04 for check list item update test  | true    |
    And I should purge the test check list items from the database
    And I should purge the test lists from the database

  Scenario: Delete a Check List Item by Id
    Given a list exists with the following details and I get its id
      | name          | description                                    | type  |
      | Test List 01D | Test list 01 for check list item deletion test | CHECK |
    And check list items exist with that list id and the following details
      | name                     | description                                               | checked |
      | Test Check List Item 01D | Test check list item 01 for check list item deletion test | false   |
      | Test Check List Item 02D | Test check list item 02 for check list item deletion test | true    |
      | Test Check List Item 03D | Test check list item 03 for check list item deletion test | true    |
      | Test Check List Item 04D | Test check list item 04 for check list item deletion test | false   |
    And the list is currently of size 4
    And I get the id of check list item "Test Check List Item 03D"
    When I delete the check list item with that id
    Then I should get no error
    And the list is currently of size 3
    And check list items should exist with the following details
      | name                     | description                                               | checked |
      | Test Check List Item 01D | Test check list item 01 for check list item deletion test | false   |
      | Test Check List Item 02D | Test check list item 02 for check list item deletion test | true    |
      | Test Check List Item 04D | Test check list item 04 for check list item deletion test | false   |
    And no check list item should exist with name "Test List 03D"
    And I should purge the test check list items from the database
    And I should purge the test lists from the database
