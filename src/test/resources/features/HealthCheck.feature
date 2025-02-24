@bookerAPI @viewBooking
  Feature: Hotel Room Booking
    The purpose of these tests are to cover isolated tests for customer to book a room/suite.

    @prereq @healthcheck
    Scenario: To confirm whether the API is up and running
      Given User has access to endpoint "/ping"
      When User makes a request to check the health of booking service
      Then User should get the response code 201



