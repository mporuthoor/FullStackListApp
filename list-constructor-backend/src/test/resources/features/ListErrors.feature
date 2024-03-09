Feature: List API Expected Errors

  Scenario: Try to Create a Constructed List without Necessary Data
    When I create a list with the following details
      | name | description | type |
      |      |             |      |
    Then I should get a bad request error with the following message and message details
      | message         | messageDetails                                 | requestDetails |
      | Invalid Payload | name: must not be null, type: must not be null |                |

  Scenario: Try to Get a Constructed List with an Invalid Name
    Given lists exist with the following details
      | name           | description                              | type   |
      | Test List 01RE | Test list 01 for list reading error test | CHECK  |
      | Test List 02RE | Test list 02 for list reading error test | RANK   |
      | Test List 03RE | Test list 03 for list reading error test | DETAIL |
      | Test List 04RE | Test list 04 for list reading error test | RANK   |
    When I get a list by name "Invalid Name"
    Then I should get a resource not found by name error
    And I should purge the test lists from the database

  Scenario: Try to Get a Constructed List with an Invalid Id
    Given lists exist with the following details
      | name           | description                              | type   |
      | Test List 05RE | Test list 05 for list reading error test | CHECK  |
      | Test List 06RE | Test list 06 for list reading error test | RANK   |
      | Test List 07RE | Test list 07 for list reading error test | CHECK  |
      | Test List 08RE | Test list 08 for list reading error test | DETAIL |
    When I get a randomly generated id
    And I get the list with that id
    Then I should get a resource not found by id error
    And I should purge the test lists from the database

  Scenario: Try to Update a Constructed List with an Invalid Id
    Given lists exist with the following details
      | name           | description                             | type   |
      | Test List 01UE | Test list 01 for list update error test | CHECK  |
      | Test List 02UE | Test list 02 for list update error test | RANK   |
      | Test List 03UE | Test list 03 for list update error test | DETAIL |
      | Test List 04UE | Test list 04 for list update error test | RANK   |
    When I get a randomly generated id
    And I update the list with that id and the following details
      | name                 | description                                    | type |
      | Test List 04 Updated | Updated Description for list update error test | RANK |
    Then I should get a resource not found by id error
    And lists should exist with the following details
      | name           | description                             | type   |
      | Test List 01UE | Test list 01 for list update error test | CHECK  |
      | Test List 02UE | Test list 02 for list update error test | RANK   |
      | Test List 03UE | Test list 03 for list update error test | DETAIL |
      | Test List 04UE | Test list 04 for list update error test | RANK   |
    And I should purge the test lists from the database

  Scenario: Try to Delete a Constructed List with an Invalid Id
    Given lists exist with the following details
      | name           | description                               | type   |
      | Test List 01DE | Test list 01 for list deletion error test | RANK   |
      | Test List 02DE | Test list 02 for list deletion error test | RANK   |
      | Test List 03DE | Test list 03 for list deletion error test | DETAIL |
      | Test List 04DE | Test list 04 for list deletion error test | RANK   |
    When I get a randomly generated id
    And I delete the list with that id
    Then I should get a resource not found by id error
    And lists should exist with the following details
      | name           | description                               | type   |
      | Test List 01DE | Test list 01 for list deletion error test | RANK   |
      | Test List 02DE | Test list 02 for list deletion error test | RANK   |
      | Test List 03DE | Test list 03 for list deletion error test | DETAIL |
      | Test List 04DE | Test list 04 for list deletion error test | RANK   |
    And I should purge the test lists from the database
