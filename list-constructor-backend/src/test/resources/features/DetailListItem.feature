Feature: Detail List Item API CRUD Operations

  Scenario: Create a Detail List Item
    Given no detail list item exists with name "Test Title Detail List Item 01C"
    And a list exists with the following details and I get its id
      | name          | description                    | type   |
      | Test List 01C | Test list 01 for creation test | DETAIL |
    And the list is currently of size 0
    When I create a detail list item with that list id and the following details
      | name                            | description                                      | details      |
      | Test Title Detail List Item 01C | Test title detail list item 01 for creation test | Rating, Date |
    Then I should get no error
    And I should get a detail list item with the following details
      | name                            | description                                      | details      |
      | Test Title Detail List Item 01C | Test title detail list item 01 for creation test | Rating, Date |
    And the list is currently of size 1
    And detail list items should exist with the following details
      | name                            | description                                      | details      |
      | Test Title Detail List Item 01C | Test title detail list item 01 for creation test | Rating, Date |
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database

  Scenario: Create Multiple Detail List Items
    Given no detail list items exist with names "Test Detail List Item 01C"
    And a list exists with the following details and I get its id
      | name          | description                    | type   |
      | Test List 02C | Test list 02 for creation test | DETAIL |
    And the list is currently of size 0
    When I create multiple detail list items with that list id and the following details
      | name                            | description                                      | details       |
      | Test Title Detail List Item 02C | Test title detail list item 02 for creation test | Rating, Date  |
      | Test Detail List Item 03C       | Test detail list item 03 for creation test       | 7, 01/01/2000 |
      | Test Detail List Item 04C       | Test detail list item 04 for creation test       | 9, 01/02/2000 |
      | Test Detail List Item 05C       | Test detail list item 05 for creation test       | 8, 01/03/2000 |
    Then I should get no error
    And I should get detail list items with the following details
      | name                            | description                                      | details       |
      | Test Title Detail List Item 02C | Test title detail list item 02 for creation test | Rating, Date  |
      | Test Detail List Item 03C       | Test detail list item 03 for creation test       | 7, 01/01/2000 |
      | Test Detail List Item 04C       | Test detail list item 04 for creation test       | 9, 01/02/2000 |
      | Test Detail List Item 05C       | Test detail list item 05 for creation test       | 8, 01/03/2000 |
    And the list is currently of size 4
    And detail list items should exist with the following details
      | name                            | description                                      | details       |
      | Test Title Detail List Item 02C | Test title detail list item 02 for creation test | Rating, Date  |
      | Test Detail List Item 03C       | Test detail list item 03 for creation test       | 7, 01/01/2000 |
      | Test Detail List Item 04C       | Test detail list item 04 for creation test       | 9, 01/02/2000 |
      | Test Detail List Item 05C       | Test detail list item 05 for creation test       | 8, 01/03/2000 |
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database

  Scenario: Get a Detail List Item by Id
    Given a list exists with the following details and I get its id
      | name          | description                   | type   |
      | Test List 01R | Test list 01 for reading test | DETAIL |
    And detail list items exist with that list id and the following details
      | name                            | description                                     | details                |
      | Test Title Detail List Item 01R | Test title detail list item 01 for reading test | Reading Level          |
      | Test Detail List Item 02R       | Test detail list item 02 for reading test       | Fifth grade            |
      | Test Detail List Item 03R       | Test detail list item 03 for reading test       | Only reads Manga       |
      | Test Detail List Item 04R       | Test detail list item 04 for reading test       | Has read Percy Jackson |
    And I get the id of detail list item "Test Detail List Item 04R"
    When I get the detail list item with that id
    Then I should get no error
    And I should get a detail list item with the following details
      | name                      | description                               | details                |
      | Test Detail List Item 04R | Test detail list item 04 for reading test | Has read Percy Jackson |
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database

  Scenario: Get Detail List Items by List Id
    Given a list exists with the following details and I get its id
      | name          | description                   | type   |
      | Test List 02R | Test list 02 for reading test | DETAIL |
    And detail list items exist with that list id and the following details
      | name                            | description                                     | details       |
      | Test Title Detail List Item 05R | Test title detail list item 05 for reading test | Rating, Date  |
      | Test Detail List Item 06R       | Test detail list item 06 for reading test       | 4, 01/07/2000 |
      | Test Detail List Item 07R       | Test detail list item 07 for reading test       | 5, 01/03/2000 |
    And a list exists with the following details and I get its id
      | name          | description                   | type   |
      | Test List 03R | Test list 03 for reading test | DETAIL |
    And detail list items exist with that list id and the following details
      | name                            | description                                     | details     |
      | Test Title Detail List Item 08R | Test title detail list item 08 for reading test | Power Level |
      | Test Detail List Item 09R       | Test detail list item 09 for reading test       | 8001        |
      | Test Detail List Item 10R       | Test detail list item 10 for reading test       | 42          |
    When I get all detail list items with that list id
    Then I should get no error
    And I should only get detail list items with the following details
      | name                            | description                                     | details     |
      | Test Title Detail List Item 08R | Test title detail list item 08 for reading test | Power Level |
      | Test Detail List Item 09R       | Test detail list item 09 for reading test       | 8001        |
      | Test Detail List Item 10R       | Test detail list item 10 for reading test       | 42          |
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database

  Scenario: Get all Detail List Items
    Given a list exists with the following details and I get its id
      | name          | description                   | type   |
      | Test List 04R | Test list 04 for reading test | DETAIL |
    And detail list items exist with that list id and the following details
      | name                            | description                                     | details       |
      | Test Title Detail List Item 11R | Test title detail list item 11 for reading test | Breakfast     |
      | Test Detail List Item 12R       | Test detail list item 12 for reading test       | Cereal        |
      | Test Detail List Item 13R       | Test detail list item 13 for reading test       | Oatmeal       |
      | Test Detail List Item 14R       | Test detail list item 14 for reading test       | Avocado toast |
    When I get all detail list items
    Then I should get no error
    And I should get detail list items with the following details
      | name                            | description                                     | details       |
      | Test Title Detail List Item 11R | Test title detail list item 11 for reading test | Breakfast     |
      | Test Detail List Item 12R       | Test detail list item 12 for reading test       | Cereal        |
      | Test Detail List Item 13R       | Test detail list item 13 for reading test       | Oatmeal       |
      | Test Detail List Item 14R       | Test detail list item 14 for reading test       | Avocado toast |
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database

  Scenario: Update a Detail List Item by Id
    Given a list exists with the following details and I get its id
      | name          | description                  | type   |
      | Test List 01U | Test list 01 for update test | DETAIL |
    And detail list items exist with that list id and the following details
      | name                            | description                                    | details        |
      | Test Title Detail List Item 01U | Test title detail list item 01 for update test | Favorite Food  |
      | Test Detail List Item 02U       | Test detail list item 02 for update test       | Steak          |
      | Test Detail List Item 03U       | Test detail list item 03 for update test       | Sundubu Jjigae |
      | Test Detail List Item 04U       | Test detail list item 04 for update test       | Fried Chicken  |
    And I get the id of detail list item "Test Detail List Item 02U"
    When I update the detail list item with that id and the following details
      | name                             | description                                                | details |
      | Updated Test Detail List Item 02 | Updated test detail list item 02 for detail list item test | Tofu    |
    Then I should get no error
    And I should get a detail list item with the following details
      | name                             | description                                                | details |
      | Updated Test Detail List Item 02 | Updated test detail list item 02 for detail list item test | Tofu    |
    And detail list items should exist with the following details
      | name                             | description                                                | details        |
      | Test Title Detail List Item 01U  | Test title detail list item 01 for update test             | Favorite Food  |
      | Updated Test Detail List Item 02 | Updated test detail list item 02 for detail list item test | Tofu           |
      | Test Detail List Item 03U        | Test detail list item 03 for update test                   | Sundubu Jjigae |
      | Test Detail List Item 04U        | Test detail list item 04 for update test                   | Fried Chicken  |
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database

  Scenario: Delete a Detail List Item by Id
    Given a list exists with the following details and I get its id
      | name          | description                    | type   |
      | Test List 01D | Test list 01 for deletion test | DETAIL |
    And detail list items exist with that list id and the following details
      | name                            | description                                      | details   |
      | Test Title Detail List Item 01D | Test title detail list item 01 for deletion test | Eye color |
      | Test Detail List Item 02D       | Test detail list item 02 for deletion test       | Brown     |
      | Test Detail List Item 03D       | Test detail list item 03 for deletion test       | Black     |
      | Test Detail List Item 04D       | Test detail list item 04 for deletion test       | Blue      |
    And the list is currently of size 4
    And I get the id of detail list item "Test Detail List Item 03D"
    When I delete the detail list item with that id
    Then I should get no error
    And the list is currently of size 3
    And detail list items should exist with the following details
      | name                            | description                                      | details   |
      | Test Title Detail List Item 01D | Test title detail list item 01 for deletion test | Eye color |
      | Test Detail List Item 02D       | Test detail list item 02 for deletion test       | Brown     |
      | Test Detail List Item 04D       | Test detail list item 04 for deletion test       | Blue      |
    And no detail list item should exist with name "Test List 03D"
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database
