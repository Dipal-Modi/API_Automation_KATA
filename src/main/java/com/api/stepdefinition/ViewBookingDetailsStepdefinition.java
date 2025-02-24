package com.api.stepdefinition;

import com.api.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertEquals;

public class ViewBookingDetailsStepdefinition {

    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(ViewBookingDetailsStepdefinition.class);

    public ViewBookingDetailsStepdefinition(TestContext context) {this.context = context;}

    @Given("User has access to endpoint {string}")
    public void userHasAccessToEndpoint (String endpoint) {
        context.session.put("endpoint", endpoint);
    }

    @Then("User should get the response code {int}")
    public void userShouldGetTheResponseCode (Integer statusCode){
        assertEquals(Long.valueOf(statusCode),Long.valueOf(context.response.getStatusCode()));
    }

    @Then ("User validates the response with JSON schema {string}")
    public void userValidatesResponseWithJSONSchema(String schemaFileName){
        context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/"+schemaFileName));
        LOG.info("successfully validated schema from "+schemaFileName);
    }

    @When("User makes a request to check the health of booking service")
    public void userMakesARequestToCheckTheHealthOFBookingService(){
        context.response = context.requestSetup().get(context.session.get("endpoint").toString());
    }
}
