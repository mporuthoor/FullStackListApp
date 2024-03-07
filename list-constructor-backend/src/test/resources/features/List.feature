Feature: List API CRUD Operations

  Scenario: Create a Constructed List
    Given no list exists with name "Test List 1C"
    When I create a list with the following details
      | name         | description                        | type  |
      | Test List 1C | Test list 1 for list creation test | CHECK |
    Then lists should exist with the following details
      | name         | description                        | type  |
      | Test List 1C | Test list 1 for list creation test | CHECK |
    And I should purge the test lists from the database

  Scenario: Get a Constructed List by Name
    Given lists exist with the following details
      | name          | description                        | type   |
      | Test List 01R | Test list 01 for list reading test | CHECK  |
      | Test List 02R | Test list 02 for list reading test | RANK   |
      | Test List 03R | Test list 03 for list reading test | DETAIL |
      | Test List 04R | Test list 04 for list reading test | RANK   |
    When I get a list by name "Test List 03R"
    Then I should get a list with the following details
      | name          | description                        | type   |
      | Test List 03R | Test list 03 for list reading test | DETAIL |
    And I should purge the test lists from the database

  Scenario: Get a Constructed List by Id
    Given lists exist with the following details
      | name          | description                        | type   |
      | Test List 05R | Test list 05 for list reading test | CHECK  |
      | Test List 06R | Test list 06 for list reading test | RANK   |
      | Test List 07R | Test list 07 for list reading test | CHECK  |
      | Test List 08R | Test list 08 for list reading test | DETAIL |
    And I get the id of list "Test List 06R"
    When I get the list with that id
    Then I should get a list with the following details
      | name          | description                        | type |
      | Test List 06R | Test list 06 for list reading test | RANK |
    And I should purge the test lists from the database

  Scenario: Get all Constructed Lists
    Given lists exist with the following details
      | name          | description                        | type   |
      | Test List 09R | Test list 09 for list reading test | RANK   |
      | Test List 10R | Test list 10 for list reading test | DETAIL |
      | Test List 11R | Test list 11 for list reading test | DETAIL |
      | Test List 12R | Test list 12 for list reading test | CHECK  |
    When I get all lists
    Then I should get lists with the following details
      | name          | description                        | type   |
      | Test List 09R | Test list 09 for list reading test | RANK   |
      | Test List 10R | Test list 10 for list reading test | DETAIL |
      | Test List 11R | Test list 11 for list reading test | DETAIL |
      | Test List 12R | Test list 12 for list reading test | CHECK  |
    And I should purge the test lists from the database

  Scenario: Update a Constructed List by Id
    Given lists exist with the following details
      | name          | description                       | type   |
      | Test List 01U | Test list 01 for list update test | CHECK  |
      | Test List 02U | Test list 02 for list update test | RANK   |
      | Test List 03U | Test list 03 for list update test | DETAIL |
      | Test List 04U | Test list 04 for list update test | RANK   |
    And I get the id of list "Test List 04U"
    When I update the list with that id and the following details
      | name                 | description                              | type |
      | Test List 04 Updated | Updated Description for list update test | RANK |
    Then I should get a list with the following details
      | name                 | description                              | type |
      | Test List 04 Updated | Updated Description for list update test | RANK |
    And lists should exist with the following details
      | name                 | description                              | type   |
      | Test List 01U        | Test list 01 for list update test        | CHECK  |
      | Test List 02U        | Test list 02 for list update test        | RANK   |
      | Test List 03U        | Test list 03 for list update test        | DETAIL |
      | Test List 04 Updated | Updated Description for list update test | RANK   |
    And I should purge the test lists from the database

  Scenario: Delete a Constructed List by Id
    Given lists exist with the following details
      | name          | description                         | type   |
      | Test List 01D | Test list 01 for list deletion test | RANK   |
      | Test List 02D | Test list 02 for list deletion test | RANK   |
      | Test List 03D | Test list 03 for list deletion test | DETAIL |
      | Test List 04D | Test list 04 for list deletion test | RANK   |
    And I get the id of list "Test List 01D"
    When I delete the list with that id
    Then no list should exist with name "Test List 01D"
    And lists should exist with the following details
      | name          | description                         | type   |
      | Test List 02D | Test list 02 for list deletion test | RANK   |
      | Test List 03D | Test list 03 for list deletion test | DETAIL |
      | Test List 04D | Test list 04 for list deletion test | RANK   |
    And I should purge the test lists from the database