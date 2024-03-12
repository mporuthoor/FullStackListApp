Feature: Detail List Item API Expected Errors

  Scenario: Try to Create a Detail List Item without List Id
    Given I have no list id
    When I create a detail list item with that list id and the following details
      | name | description | details |
      |      |             |         |
    Then I should get a bad request error with the following message and message details
      | message                                        | messageDetails | requestDetails |
      | List id can't be null when creating list items |                |                |

  Scenario: Try to Create a Detail List Item with an Invalid List Id
    Given lists exist with the following details
      | name           | description                          | type   |
      | Test List 01CE | Test list 01 for creation error test | DETAIL |
      | Test List 02CE | Test list 02 for creation error test | DETAIL |
    And I get a randomly generated list id
    When I create a detail list item with that list id and the following details
      | name | description | details |
      |      |             |         |
    Then I should get a resource not found by list id error
    And I should purge the test lists from the database

  Scenario: Try to Create a Detail List Item without Necessary Data
    Given a list exists with the following details and I get its id
      | name           | description                          | type   |
      | Test List 03CE | Test list 03 for creation error test | DETAIL |
    When I create a detail list item with that list id and the following details
      | name | description | details |
      |      |             |         |
    Then I should get a bad request error with the following message and message details
      | message         | messageDetails                                    | requestDetails |
      | Invalid Payload | name: must not be null, details: must not be null |                |
    And I should purge the test lists from the database

  Scenario: Try to Create Multiple Detail List Items without List Id
    Given I have no list id
    When I create multiple detail list items with that list id and the following details
      | name | description | details |
      |      |             |         |
      |      |             |         |
      |      |             |         |
    Then I should get a bad request error with the following message and message details
      | message                                        | messageDetails | requestDetails |
      | List id can't be null when creating list items |                |                |

  Scenario: Try to Create Multiple Detail List Items with an Invalid List Id
    Given lists exist with the following details
      | name           | description                          | type   |
      | Test List 04CE | Test list 04 for creation error test | DETAIL |
      | Test List 05CE | Test list 05 for creation error test | DETAIL |
    And I get a randomly generated list id
    When I create multiple detail list items with that list id and the following details
      | name | description | details |
      |      |             |         |
      |      |             |         |
      |      |             |         |
    Then I should get a resource not found by list id error
    And I should purge the test lists from the database

  Scenario: Try to Create Multiple Detail List Items without Necessary Data
    Given a list exists with the following details and I get its id
      | name           | description                          | type   |
      | Test List 06CE | Test list 06 for creation error test | DETAIL |
    When I create multiple detail list items with that list id and the following details
      | name | description | details |
      |      |             |         |
      |      |             |         |
      |      |             |         |
    Then I should get a bad request error with the following message and message details
      | message         | messageDetails         | requestDetails |
      | Invalid Payload | name: must not be null |                |
    And I should purge the test lists from the database

  Scenario: Try to Get a Detail List Item with an Invalid Id
    Given a list exists with the following details and I get its id
      | name          | description                   | type   |
      | Test List 01R | Test list 01 for reading test | DETAIL |
    And detail list items exist with that list id and the following details
      | name                             | description                                           | details       |
      | Test Title Detail List Item 01RE | Test title detail list item 01 for reading error test | Rating, Date  |
      | Test Detail List Item 02RE       | Test detail list item 02 for reading error test       | 4, 01/07/2000 |
    And I get a randomly generated id
    When I get the detail list item with that id
    Then I should get a resource not found by id error
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database

  Scenario: Try to Get Detail List Items with an Invalid List Id
    Given a list exists with the following details and I get its id
      | name          | description                   | type   |
      | Test List 01R | Test list 01 for reading test | DETAIL |
    And detail list items exist with that list id and the following details
      | name                             | description                                           | details     |
      | Test Title Detail List Item 01RE | Test title detail list item 01 for reading error test | Power Level |
      | Test Detail List Item 02RE       | Test detail list item 02 for reading error test       | 8001        |
    And I get a randomly generated list id
    When I get all detail list items with that list id
    Then I should get a resource not found by list id error
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database

  Scenario: Try to Update a Detail List Item with an Invalid Id
    Given a list exists with the following details and I get its id
      | name           | description                        | type   |
      | Test List 01UE | Test list 01 for update error test | DETAIL |
    And detail list items exist with that list id and the following details
      | name                             | description                                          | details   |
      | Test Title Detail List Item 01UE | Test title detail list item 01 for update error test | Eye color |
      | Test Detail List Item 02UE       | Test detail list item 02 for update error test       | Brown     |
    And I get a randomly generated id
    When I update the detail list item with that id and the following details
      | name                             | description                                     | details |
      | Updated Test Detail List Item 02 | Updated test detail list item 02 for error test | Black   |
    Then I should get a resource not found by id error
    And detail list items should exist with the following details
      | name                             | description                                          | details   |
      | Test Title Detail List Item 01UE | Test title detail list item 01 for update error test | Eye color |
      | Test Detail List Item 02UE       | Test detail list item 02 for update error test       | Brown     |
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database

  Scenario: Try to Delete a Detail List Item with an Invalid Id
    Given a list exists with the following details and I get its id
      | name           | description                          | type   |
      | Test List 01DE | Test list 01 for deletion error test | DETAIL |
    And detail list items exist with that list id and the following details
      | name                             | description                                            | details       |
      | Test Title Detail List Item 01DE | Test title detail list item 01 for deletion error test | Favorite Food |
      | Test Detail List Item 02DE       | Test detail list item 02 for deletion error test       | Tofu          |
    And I get a randomly generated id
    When I delete the detail list item with that id
    Then I should get a resource not found by id error
    And detail list items should exist with the following details
      | name                             | description                                            | details       |
      | Test Title Detail List Item 01DE | Test title detail list item 01 for deletion error test | Favorite Food |
      | Test Detail List Item 02DE       | Test detail list item 02 for deletion error test       | Tofu          |
    And I should purge the test detail list items from the database
    And I should purge the test lists from the database