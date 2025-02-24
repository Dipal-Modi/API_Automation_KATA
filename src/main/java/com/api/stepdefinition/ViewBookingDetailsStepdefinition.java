package com.api.stepdefinition;

import com.api.model.BookingDTO;
import com.api.utils.ResponseHandler;
import com.api.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ViewBookingDetailsStepdefinition {

    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(ViewBookingDetailsStepdefinition.class);

    public ViewBookingDetailsStepdefinition(TestContext context) {
        this.context = context;
    }

    @Given("user has access to endpoint {string}")
    public void userHasAccessToEndpoint(String endpoint) {
        context.session.put("endpoint", endpoint);
    }

    @Then("user should get the response code {int}")
    public void userShouldGetTheResponseCode(Integer statusCode) {
        assertEquals(Long.valueOf(statusCode), Long.valueOf(context.response.getStatusCode()));
    }

    @Then("user can validate the roomid and bookingId is not null")
    public void user_can_validate_the_roomid_and_booking_id_is_not_null() {
        BookingDTO bookingDTO = ResponseHandler.deserializedResponse(context.response, BookingDTO.class);

        assertNotNull(bookingDTO.getBookingid());
        assertNotNull(bookingDTO.getBooking().getBookingid());
        assertNotNull(bookingDTO.getBooking().getRoomid());
    }

    @Then("user validates the response with JSON schema {string}")
    public void userValidatesResponseWithJSONSchema(String schemaFileName) {
        context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + schemaFileName)); //Schema between swagger and actual request is wrong
        LOG.info("successfully validated schema from " + schemaFileName);
    }
}