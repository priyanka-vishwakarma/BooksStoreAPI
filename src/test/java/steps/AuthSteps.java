package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.UserPayload;
import utils.TestDataManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utils.ApiConstants.*;

public class AuthSteps {
    private static String token;
    private Response signupResponse;

    public static String getToken() {
        return token;
    }

    @Given("the API is available")
    public void api_is_available() {
        given()
                .spec(BaseSpecBuilder.getRequestSpec())
                .get(HEALTH_CHECK)
                .then()
                .spec(BaseSpecBuilder.getResponseSpec(STATUS_OK));
    }

    @When("I sign up with stored credentials")
    public void i_sign_up_with_stored_credentials() {
        UserPayload signupPayload = new UserPayload(TestDataManager.getUserId(),
                TestDataManager.getEmail(),
                TestDataManager.getPassword()
        );

        signupResponse = given()
                .spec(BaseSpecBuilder.getRequestSpec())
                .body(signupPayload)
                .post(SIGNUP);
    }

    @Then("the signup should be successful or user already exists")
    public void signup_should_be_successful_or_exists() {
        int statusCode = signupResponse.statusCode();


        if (statusCode == STATUS_OK) {
            signupResponse.then().statusCode(200);
        } else if (statusCode == STATUS_BAD_REQUEST) {
            signupResponse.then()
                    .statusCode(STATUS_BAD_REQUEST)
                    .body("detail", equalTo("Email already registered"));
        } else {
            throw new AssertionError("Unexpected status code: " + statusCode);
        }
    }

    @When("I login with stored credentials")
    public void i_login_with_stored_credentials() {
        UserPayload loginPayload = new UserPayload(TestDataManager.getUserId(),
                TestDataManager.getEmail(),
                TestDataManager.getPassword()
        );
        Response res = given().log().all()
                .spec(BaseSpecBuilder.getRequestSpec())
                .body(loginPayload)
                .post(LOGIN);
        res.then().log().all().statusCode(STATUS_OK);
        token = res.jsonPath().getString("access_token");
    }

    @Then("I should receive a valid token")
    public void validate_token() {
        assert token != null && !token.isEmpty();
    }

}