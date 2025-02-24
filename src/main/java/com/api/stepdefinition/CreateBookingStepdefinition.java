package com.api.stepdefinition;


import com.api.model.BookingDTO;
import com.api.utils.JsonReader;
import com.api.utils.ResponseHandler;
import com.api.utils.TestContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
}
