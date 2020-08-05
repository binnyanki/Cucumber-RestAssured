Feature: Validating Place API's

@AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add place payload "<name>" "<phone_number>" "<address>" "<website>" "<language>" "<types1>" "<types2>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And User extracts "place_id" and verifies "<name>" using "getPlaceAPI"

    Examples: 
      | name       | phone_number | address | website           | language | types1    | types2 |
      | Shaw House |   9518711374 | pune    | http://google.com | English  | shoe park | shop   |
  #    |Gupta House|9518711374|Pune|http://google.com|English|shoe park|shop|
  
  @deletePlace
  Scenario: Verify that delete Place API is working successfully
  Given deletePlace Payload
  When User calls "deletePlaceAPI" with "POST" http request
  Then The API call is success with status code 200
  And "status" in response body is "OK"