@Dummy
Feature: Example for dummy RESTAPI

  # http://dummy.restapiexample.com/
  Scenario: Create new record in database (POST) - set request body from json file
    Given I Set POST posts api endpoint "/create"
    When I Set HEADER param request content type as "application/json"
    # And I Set HEADER param request Auth bearer as "access_token"
    And Set request Body as request body from file "create-new-employee.json"
    And Send POST HTTP request
    Then I receive valid HTTP response code "200"
    And Response BODY includes value of "status" is "success"

  Scenario Outline: Create new record in database (POST) - set request body from table data - HashMap
    Given I Set POST posts api endpoint "/create"
    When I Set HEADER param request content type as "application/json"
    # And I Set HEADER param request Auth bearer as "access_token"
    And Set request Body as request body with data <name> <salary> <age>
    And Send POST HTTP request
    Then I receive valid HTTP response code "200"
    And Response BODY includes value of "status" is "success"

    Examples: 
      | name | salary | age |
      | test |    123 |  23 |

  Scenario Outline: Create new record in database (POST) - set request body from table data - Pojo
    Given I Set POST posts api endpoint "/create"
    When I Set HEADER param request content type as "application/json"
    # And I Set HEADER param request Auth bearer as "access_token"
    And Set request Body as request body using pojo with data <name> <salary> <age>
    And Send POST HTTP request
    Then I receive valid HTTP response code "200"
    And Response BODY includes value of "status" is "success"

    Examples:
      | name | salary | age |
      | test |    123 |  23 |

  Scenario: Get all employee data (GET)
    Given I Set GET posts api endpoint "/employees"
    When I Set HEADER param request content type as "application/json"
    # And I Set HEADER param request Auth bearer as "access_token"
    And Send GET HTTP request
    Then I receive valid HTTP response code "200"
    And Response BODY includes value of "status" is "success"
    And Response body matches the schema from file "get_employee_list.json"

  Scenario Outline: Get a single employee data (GET)
    Given I Set GET posts api endpoint "/employee/{ID}"  with param <ID>
    When I Set HEADER param request content type as "application/json"
#    And I Set HEADER param request Auth bearer as "access_token"
    And Send GET HTTP request
    Then I receive valid HTTP response code "200"
    And Response BODY includes value of "status" is "success"
    
    Examples:
      | ID  |
      | 719 |

  Scenario: Update an employee record (UPDATE)
    Given I Set PUT posts api endpoint for "/update/{id}"
    When I Set Update request Body
    # And Send PUT HTTP request
    # Then I receive valid HTTP response code "200"

  Scenario: Delete an employee record (DELETE)
    Given I Set DELETE posts api endpoint for "/delete/719"
    When Send DELETE HTTP request
    Then I receive valid HTTP response code "200"
    And Response BODY includes value of "status" is "success"
