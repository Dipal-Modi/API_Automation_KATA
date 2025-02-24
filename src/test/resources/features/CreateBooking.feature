@bookerAPI @createBooking
Feature: To create a new booking in restful-booker

  @createBookingFromJSON @happy @regression @mandatory
  Scenario Outline: To create new booking using JSON data
    Given user has access to endpoint "/booking/"
    When user creates a booking using data "<dataKey>" from JSON file "<JSONFile>"
    Then user should get the response code 201
    And user can validate the roomid and bookingId is not null

    Examples:
      | dataKey       | JSONFile         |
      | CreateBooking | bookingBody.json |

  @unhappy @invalidField
  Scenario Outline: User is unable to book a room
    Given user has access to endpoint "/booking/"
    When user creates a booking using invalid data "<dataKey>" from JSON file "<JSONFile>"
    Then user should get the response code 400
    And user not able to book a room

    Examples:
      | dataKey                     | JSONFile         |
      | UserWithInvalidDepositField | bookingBody.json |
      | UserWithInvalidRoomId       | bookingBody.json |

  @unhappy @inputvalidation @missingMandatory
  Scenario: User is unable to book a room without lastName
    Given user has access to endpoint "/booking/"
    When user creates a booking using invalid data "UserWithoutLastName" from JSON file "bookingBody.json"
    Then user should get the response code 400
    And user not able to book a room without lastName

  @unhappy @inputvalidation @missingMandatory
  Scenario: User is unable to book a room without firstName
    Given user has access to endpoint "/booking/"
    When user creates a booking using invalid data "UserWithoutFirstName" from JSON file "bookingBody.json"
    Then user should get the response code 400
    And user not able to book a room without firstName



