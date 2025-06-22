Feature: Price reader microservice

  Background:
    * url 'http://localhost:8080/price/v1/get'
    * header flowId = 'test-flow-001'

  Scenario: 1. Get applicable price on 2020-06-14 10:00:00 (should return price list 1)
    Given param applicationDate = '2020-06-14T10:00:00'
    And param productId = 35455
    And param brandId = 1
    When method GET
    Then status 200
    And match response.data.price == 35.50
    And match response.data.priceList == 1

  Scenario: 2. Get applicable price on 2020-06-14 16:00:00 (should return price list 2)
    Given param applicationDate = '2020-06-14T16:00:00'
    And param productId = 35455
    And param brandId = 1
    When method GET
    Then status 200
    And match response.data.price == 25.45
    And match response.data.priceList == 2

  Scenario: 3. Get applicable price on 2020-06-14 21:00:00 (should return price list 1 again)
    Given param applicationDate = '2020-06-14T21:00:00'
    And param productId = 35455
    And param brandId = 1
    When method GET
    Then status 200
    And match response.data.price ==  35.50
    And match response.data.priceList == 1

  Scenario: 4. Get applicable price on 2020-06-15 10:00:00 (should return price list 3)
    Given param applicationDate = '2020-06-15T10:00:00'
    And param productId = 35455
    And param brandId = 1
    When method GET
    Then status 200
    And match response.data.price == 30.50
    And match response.data.priceList == 3

  Scenario: 5. Get applicable price on 2020-06-16 21:00:00 (should return price list 4)
    Given param applicationDate = '2020-06-16T21:00:00'
    And param productId = 35455
    And param brandId = 1
    When method GET
    Then status 200
    And match response.data.price == 38.95
    And match response.data.priceList == 4

  Scenario: 6. No matching price for given parameters should return 404
    Given param applicationDate = '2022-01-01T00:00:00'
    And param productId = 99999
    And param brandId = 99
    And header flowId = 'test-flow-008'
    When method GET
    Then status 404
    And match response.data.errorDetails.code == '404'
    And match response.data.errorDetails.fields.exceptionType == 'ApiException'

  Scenario: 7. Missing flowId header should return 400
    Given param applicationDate = '2020-06-14T10:00:00'
    And param productId = 35455
    And param brandId = 1
    And header flowId = ''
    When method GET
    Then status 400
    And match response.data.errorDetails.code == '400'
    And match response.data.errorDetails.fields.exceptionType == 'NotBlank'
    And match response.data.errorDetails.fields.exceptionMessage == 'The \'flowId\' header is required and cannot be empty.'
    And match response.message == 'Bad Request - One or more of the supplied parameters do not meet the defined constraints.'

  Scenario: 8. Missing applicationDate should return 400
    Given param applicationDate = ''
    And param productId = 35455
    And param brandId = 1
    And header flowId = 'test-flow-002'
    When method GET
    Then status 400
    And match response.data.errorDetails.code == '400'
    And match response.data.errorDetails.fields.exceptionType == 'MissingServletRequestParameterException'
    And match response.data.errorDetails.fields.exceptionMessage == 'Required request parameter \'applicationDate\' for method parameter type LocalDateTime is present but converted to null'
    And match response.message == 'Bad Request - A required parameter is missing from the request. Please ensure you include all required parameters, such as \'applicationDate\', \'productId\', or \'brandId\'.'

  Scenario: 9. Missing productId should return 400
    Given param applicationDate = '2020-06-14T10:00:00'
    And param productId = ''
    And param brandId = 1
    And header flowId = 'test-flow-003'
    When method GET
    Then status 400
    And match response.data.errorDetails.code == '400'
    And match response.data.errorDetails.fields.exceptionType == 'MissingServletRequestParameterException'
    And match response.data.errorDetails.fields.exceptionMessage == 'Required request parameter \'productId\' for method parameter type Integer is present but converted to null'
    And match response.message == 'Bad Request - A required parameter is missing from the request. Please ensure you include all required parameters, such as \'applicationDate\', \'productId\', or \'brandId\'.'

  Scenario: 10. Missing brandId should return 400
    Given param applicationDate = '2020-06-14T10:00:00'
    And param productId = 35455
    And param brandId = ''
    And header flowId = 'test-flow-004'
    When method GET
    Then status 400
    And match response.data.errorDetails.code == '400'
    And match response.data.errorDetails.fields.exceptionType == 'MissingServletRequestParameterException'
    And match response.data.errorDetails.fields.exceptionMessage == 'Required request parameter \'brandId\' for method parameter type Integer is present but converted to null'
    And match response.message == 'Bad Request - A required parameter is missing from the request. Please ensure you include all required parameters, such as \'applicationDate\', \'productId\', or \'brandId\'.'

  Scenario: 11. Invalid format for applicationDate should return 400
    Given param applicationDate = 'invalid-date-format'
    And param productId = 35455
    And param brandId = 1
    And header flowId = 'test-flow-005'
    When method GET
    Then status 400
    And match response.data.errorDetails.code == '400'
    And match response.data.errorDetails.fields.exceptionType == 'MethodArgumentTypeMismatchException'
    And match response.message == 'Bad Request - The provided data type is invalid for one of the request parameters. Verify that each value sent matches the expected type.'

  Scenario: 12. Invalid format for productId should return 400
    Given param applicationDate = '2020-06-14T10:00:00'
    And param productId = 'abc'
    And param brandId = 1
    And header flowId = 'test-flow-006'
    When method GET
    Then status 400
    And match response.data.errorDetails.code == '400'
    And match response.data.errorDetails.fields.exceptionType == 'MethodArgumentTypeMismatchException'
    And match response.message == 'Bad Request - The provided data type is invalid for one of the request parameters. Verify that each value sent matches the expected type.'

  Scenario: 13. Invalid format for brandId should return 400
    Given param applicationDate = '2020-06-14T10:00:00'
    And param productId = 35455
    And param brandId = 'xyz'
    And header flowId = 'test-flow-007'
    When method GET
    Then status 400
    And match response.data.errorDetails.code == '400'
    And match response.data.errorDetails.fields.exceptionType == 'MethodArgumentTypeMismatchException'
    And match response.message == 'Bad Request - The provided data type is invalid for one of the request parameters. Verify that each value sent matches the expected type.'
