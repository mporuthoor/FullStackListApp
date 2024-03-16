Feature: List API CRUD Operations

  Scenario: Create a Constructed List
    Given no list exists with name "Test List 01C"
    When I create a list with the following details
      | name          | description                         | type  |
      | Test List 01C | Test list 01 for list creation test | CHECK |
    Then I should get no error
    And I should get a list with the following details
      | name          | description                         | type  |
      | Test List 01C | Test list 01 for list creation test | CHECK |
    And lists should exist with the following details
      | name          | description                         | type  |
      | Test List 01C | Test list 01 for list creation test | CHECK |
    And I should purge the test lists from the database

  Scenario: Get a Constructed List by Id
    Given lists exist with the following details
      | name          | description                        | type   |
      | Test List 01R | Test list 01 for list reading test | CHECK  |
      | Test List 02R | Test list 02 for list reading test | RANK   |
      | Test List 03R | Test list 03 for list reading test | DETAIL |
      | Test List 04R | Test list 04 for list reading test | RANK   |
    And I get the id of list "Test List 03R"
    When I get the list with that id
    Then I should get no error
    And I should get a list with the following details
      | name          | description                        | type   |
      | Test List 03R | Test list 03 for list reading test | DETAIL |
    And I should purge the test lists from the database

  Scenario: Get all Constructed Lists
    Given lists exist with the following details
      | name          | description                        | type   |
      | Test List 05R | Test list 05 for list reading test | CHECK  |
      | Test List 06R | Test list 06 for list reading test | RANK   |
      | Test List 07R | Test list 07 for list reading test | CHECK  |
      | Test List 08R | Test list 08 for list reading test | DETAIL |
    When I get all lists
    Then I should get no error
    And I should get the following lists in order with the following details
      | name          | description                        | type   |
      | Test List 05R | Test list 05 for list reading test | CHECK  |
      | Test List 06R | Test list 06 for list reading test | RANK   |
      | Test List 07R | Test list 07 for list reading test | CHECK  |
      | Test List 08R | Test list 08 for list reading test | DETAIL |
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
    Then I should get no error
    And I should get a list with the following details
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
    Then I should get no error
    And lists should exist with the following details
      | name          | description                         | type   |
      | Test List 02D | Test list 02 for list deletion test | RANK   |
      | Test List 03D | Test list 03 for list deletion test | DETAIL |
      | Test List 04D | Test list 04 for list deletion test | RANK   |
    And no list should exist with name "Test List 01D"
    And I should purge the test lists from the database

  Scenario: Update List Order
    Given lists exist with the following details
      | name           | description                             | type   |
      | Test List 01UO | Test list 01 for list update order test | CHECK  |
      | Test List 02UO | Test list 02 for list update order test | RANK   |
      | Test List 03UO | Test list 03 for list update order test | DETAIL |
      | Test List 04UO | Test list 04 for list update order test | RANK   |
    And I get all list ids in order
    And I change the order to reverse the last 4 lists
    When I send an update list order request with that order
    Then I should get no error
    And I should get list ids in the updated order
    When I get all lists
    Then I should get no error
    And I should get the following lists in order with the following details
      | name           | description                             | type   |
      | Test List 04UO | Test list 04 for list update order test | RANK   |
      | Test List 03UO | Test list 03 for list update order test | DETAIL |
      | Test List 02UO | Test list 02 for list update order test | RANK   |
      | Test List 01UO | Test list 01 for list update order test | CHECK  |
    And I should purge the test lists from the database
