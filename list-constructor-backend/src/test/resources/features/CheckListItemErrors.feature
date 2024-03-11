Feature: Check List Item API Expected Errors

  Scenario: Try to Create a Check List Item without List Id
    Given I have no list id
    When I create a check list item with that list id and the following details
      | name | description | checked |
      |      |             |         |
    Then I should get a bad request error with the following message and message details
      | message                                        | messageDetails | requestDetails |
      | List id can't be null when creating list items |                |                |

  Scenario: Try to Create a Check List Item with an Invalid List Id
    Given lists exist with the following details
      | name           | description                                          | type  |
      | Test List 01CE | Test list 01 for check list item creation error test | CHECK |
      | Test List 02CE | Test list 02 for check list item creation error test | CHECK |
    And I get a randomly generated list id
    When I create a check list item with that list id and the following details
      | name | description | checked |
      |      |             |         |
    Then I should get a resource not found by list id error
    And I should purge the test lists from the database

  Scenario: Try to Create a Check List Item without Necessary Data
    Given a list exists with the following details and I get its id
      | name           | description                                          | type  |
      | Test List 03CE | Test list 03 for check list item creation error test | CHECK |
    When I create a check list item with that list id and the following details
      | name | description | checked |
      |      |             |         |
    Then I should get a bad request error with the following message and message details
      | message         | messageDetails         | requestDetails |
      | Invalid Payload | name: must not be null |                |
    And I should purge the test lists from the database

  Scenario: Try to Create Multiple Check List Items without List Id
    Given I have no list id
    When I create multiple check list items with that list id and the following details
      | name | description | checked |
      |      |             |         |
      |      |             |         |
      |      |             |         |
    Then I should get a bad request error with the following message and message details
      | message                                        | messageDetails | requestDetails |
      | List id can't be null when creating list items |                |                |

  Scenario: Try to Create Multiple Check List Items with an Invalid List Id
    Given lists exist with the following details
      | name           | description                                          | type  |
      | Test List 04CE | Test list 04 for check list item creation error test | CHECK |
      | Test List 05CE | Test list 05 for check list item creation error test | CHECK |
    And I get a randomly generated list id
    When I create multiple check list items with that list id and the following details
      | name | description | checked |
      |      |             |         |
      |      |             |         |
      |      |             |         |
    Then I should get a resource not found by list id error
    And I should purge the test lists from the database

  Scenario: Try to Create Multiple Check List Items without Necessary Data
    Given a list exists with the following details and I get its id
      | name           | description                                          | type  |
      | Test List 06CE | Test list 06 for check list item creation error test | CHECK |
    When I create multiple check list items with that list id and the following details
      | name | description | checked |
      |      |             |         |
      |      |             |         |
      |      |             |         |
    Then I should get a bad request error with the following message and message details
      | message         | messageDetails         | requestDetails |
      | Invalid Payload | name: must not be null |                |
    And I should purge the test lists from the database

  Scenario: Try to Get a Check List Item with an Invalid Id
    Given a list exists with the following details and I get its id
      | name          | description                                   | type  |
      | Test List 01R | Test list 01 for check list item reading test | CHECK |
    And check list items exist with that list id and the following details
      | name                      | description                                                    | checked |
      | Test Check List Item 01RE | Test check list item 01 for check list item reading error test | true    |
      | Test Check List Item 02RE | Test check list item 02 for check list item reading error test | true    |
    And I get a randomly generated id
    When I get the check list item with that id
    Then I should get a resource not found by id error
    And I should purge the test check list items from the database
    And I should purge the test lists from the database

  Scenario: Try to Get Check List Items with an Invalid List Id
    Given a list exists with the following details and I get its id
      | name          | description                                   | type  |
      | Test List 01R | Test list 01 for check list item reading test | CHECK |
    And check list items exist with that list id and the following details
      | name                      | description                                                    | checked |
      | Test Check List Item 01RE | Test check list item 01 for check list item reading error test | true    |
      | Test Check List Item 02RE | Test check list item 02 for check list item reading error test | true    |
    And I get a randomly generated list id
    When I get all check list items with that list id
    Then I should get a resource not found by list id error
    And I should purge the test check list items from the database
    And I should purge the test lists from the database

  Scenario: Try to Update a Check List Item with an Invalid Id
    Given a list exists with the following details and I get its id
      | name           | description                                        | type  |
      | Test List 01UE | Test list 01 for check list item update error test | CHECK |
    And check list items exist with that list id and the following details
      | name                      | description                                                   | checked |
      | Test Check List Item 01UE | Test check list item 01 for check list item update error test | false   |
      | Test Check List Item 02UE | Test check list item 02 for check list item update error test | false   |
    And I get a randomly generated id
    When I update the check list item with that id and the following details
      | name                            | description                                                    | checked |
      | Updated Test Check List Item 02 | Updated test check list item 02 for check list item error test | true    |
    Then I should get a resource not found by id error
    And check list items should exist with the following details
      | name                      | description                                                   | checked |
      | Test Check List Item 01UE | Test check list item 01 for check list item update error test | false   |
      | Test Check List Item 02UE | Test check list item 02 for check list item update error test | false   |
    And I should purge the test check list items from the database
    And I should purge the test lists from the database

  Scenario: Try to Delete a Check List Item with an Invalid Id
    Given a list exists with the following details and I get its id
      | name           | description                                          | type  |
      | Test List 01DE | Test list 01 for check list item deletion error test | CHECK |
    And check list items exist with that list id and the following details
      | name                      | description                                                     | checked |
      | Test Check List Item 01DE | Test check list item 01 for check list item deletion error test | false   |
      | Test Check List Item 02DE | Test check list item 02 for check list item deletion error test | true    |
    And I get a randomly generated id
    When I delete the check list item with that id
    Then I should get a resource not found by id error
    And check list items should exist with the following details
      | name                      | description                                                     | checked |
      | Test Check List Item 01DE | Test check list item 01 for check list item deletion error test | false   |
      | Test Check List Item 02DE | Test check list item 02 for check list item deletion error test | true    |
    And I should purge the test check list items from the database
    And I should purge the test lists from the database