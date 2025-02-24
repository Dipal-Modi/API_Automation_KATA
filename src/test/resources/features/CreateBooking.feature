@bookerAPI @createBooking
Feature: To create a new booking in restful-booker

  @createBookingFromJSON @happy @regression @mandatory
  Scenario Outline: To create new booking using JSON data
    Given user has access to endpoint "/booking/"
    When user creates a booking using data "<dataKey>" from JSON file "<JSONFile>"
    Then user should get the response code 201
    And user can validate the roomid and bookingId is not null

    Examples:
      | dataKey               | JSONFile         |
      | CreateBooking         | bookingBody.json |

