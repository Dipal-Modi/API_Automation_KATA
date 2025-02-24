package com.api.stepdefinition;


import com.api.model.BookingDTO;
import com.api.model.BookingErrorDTO;
import com.api.model.BookingInputValidationErrorDTO;
import com.api.utils.JsonReader;
import com.api.utils.ResponseHandler;
import com.api.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateBookingStepdefinition {
    private TestContext context;
    private Response response;
    private static final Logger LOG = LogManager.getLogger(CreateBookingStepdefinition.class);

    public CreateBookingStepdefinition(TestContext context) {
        this.context = context;
    }

    @When("user creates a booking using data {string} from JSON file {string}")
    public void userCreatesABookingUsingDataFromJSONFile(String dataKey, String JSONFile) {

        context.response = context.requestSetup().body(JsonReader.getRequestBody(JSONFile, dataKey))
                .when().post(context.session.get("endpoint").toString());
        BookingDTO bookingDTO = ResponseHandler.deserializedResponse(context.response, BookingDTO.class);
        assertNotNull("Booking not created", bookingDTO);
        LOG.info("Newly created booking ID: " + bookingDTO.getBookingid());
        context.session.put("bookingID", bookingDTO.getBookingid());
    }

    @And("user not able to book a room")
    public void userNotAbleToBookARoom() {

        BookingErrorDTO bookingErrorDTO = ResponseHandler.deserializedResponse(context.response, BookingErrorDTO.class);
        assertEquals("Bad Request", bookingErrorDTO.getError());
        assertEquals(400, bookingErrorDTO.getStatus());
        assertNotNull(bookingErrorDTO.getTimestamp());
    }

    @When("user creates a booking using invalid data {string} from JSON file {string}")
    public void userCreatesABookingUsingInvalidDataFromJSONFile(String dataKey, String JSONFile) {
        context.response = context.requestSetup().body(JsonReader.getRequestBody(JSONFile, dataKey))
                .when().post(context.session.get("endpoint").toString());
    }

    @And("user not able to book a room without lastName")
    public void userNotAbleToBookARoomWithInvalidInput() {
        BookingInputValidationErrorDTO bookingError = ResponseHandler.deserializedResponse(context.response, BookingInputValidationErrorDTO.class);
        assertEquals("Lastname should not be blank",bookingError.getFieldErrors().get(0));
    }

    @And("user not able to book a room without firstName")
    public void userNotAbleToBookARoomWithoutFirstName() {
        BookingInputValidationErrorDTO bookingError = ResponseHandler.deserializedResponse(context.response, BookingInputValidationErrorDTO.class);
        assertEquals("Firstname should not be blank",bookingError.getFieldErrors().get(0));
    }
}
