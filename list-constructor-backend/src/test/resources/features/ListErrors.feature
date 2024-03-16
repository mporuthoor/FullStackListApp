Feature: List API Expected Errors

  Scenario: Try to Create a Constructed List without Necessary Data
    When I create a list with the following details
      | name | description | type |
      |      |             |      |
    Then I should get a bad request error with the following message and message details
      | message         | messageDetails                                 | requestDetails |
      | Invalid Payload | name: must not be null, type: must not be null |                |

  Scenario: Try to Get a Constructed List with an Invalid Id
    Given lists exist with the following details
      | name           | description                              | type  |
      | Test List 01RE | Test list 01 for list reading error test | CHECK |
      | Test List 02RE | Test list 02 for list reading error test | RANK  |
    And I get a randomly generated id
    When I get the list with that id
    Then I should get a resource not found by id error
    And I should purge the test lists from the database

  Scenario: Try to Update a Constructed List with an Invalid Id
    Given lists exist with the following details
      | name           | description                             | type  |
      | Test List 01UE | Test list 01 for list update error test | CHECK |
      | Test List 02UE | Test list 02 for list update error test | RANK  |
    And I get a randomly generated id
    When I update the list with that id and the following details
      | name                 | description                                    | type |
      | Test List 02 Updated | Updated Description for list update error test | RANK |
    Then I should get a resource not found by id error
    And lists should exist with the following details
      | name           | description                             | type  |
      | Test List 01UE | Test list 01 for list update error test | CHECK |
      | Test List 02UE | Test list 02 for list update error test | RANK  |
    And I should purge the test lists from the database

  Scenario: Try to Delete a Constructed List with an Invalid Id
    Given lists exist with the following details
      | name           | description                               | type |
      | Test List 01DE | Test list 01 for list deletion error test | RANK |
      | Test List 02DE | Test list 02 for list deletion error test | RANK |
    And I get a randomly generated id
    When I delete the list with that id
    Then I should get a resource not found by id error
    And lists should exist with the following details
      | name           | description                               | type |
      | Test List 01DE | Test list 01 for list deletion error test | RANK |
      | Test List 02DE | Test list 02 for list deletion error test | RANK |
    And I should purge the test lists from the database

  Scenario: Update List Order With the Wrong Number of Ids
    Given lists exist with the following details
      | name           | description                             | type  |
      | Test List 01UO | Test list 01 for list update order test | CHECK |
      | Test List 02UO | Test list 02 for list update order test | RANK  |
    And I get all list ids in order
    And I remove the last list id
    When I send an update list order request with that order
    Then I should get a bad request error with the following message and message details
      | message                                                                                    |
      | Cannot update order because number of ids provided does not match current number of lists. |
    When I get all lists
    Then I should get no error
    And I should get the following lists in order with the following details
      | name           | description                             | type  |
      | Test List 01UO | Test list 01 for list update order test | CHECK |
      | Test List 02UO | Test list 02 for list update order test | RANK  |
    And I should purge the test lists from the database

  Scenario: Update List Order With an Invalid Id
    Given lists exist with the following details
      | name           | description                             | type  |
      | Test List 03UO | Test list 03 for list update order test | CHECK |
      | Test List 04UO | Test list 04 for list update order test | RANK  |
    And I get all list ids in order
    And I remove the last list id
    And I add a randomly generated list id to the end
    When I send an update list order request with that order
    Then I should get a resource not found by list id error
    When I get all lists
    Then I should get no error
    And I should get the following lists in order with the following details
      | name           | description                             | type  |
      | Test List 03UO | Test list 03 for list update order test | CHECK |
      | Test List 04UO | Test list 04 for list update order test | RANK  |
    And I should purge the test lists from the database