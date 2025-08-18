package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.UserPayload;
import utils.TestDataManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utils.ApiConstants.LOGIN;
import static utils.ApiConstants.SIGNUP;

public class Signup_LoginValidation {

    private Response response;

    @When("I sign up with an existing email")
    public void i_sign_up_with_existing_email() {
        UserPayload payload = new UserPayload(TestDataManager.getUserId(), TestDataManager.getEmail(), TestDataManager.getPassword());

        response = given()
                .spec(BaseSpecBuilder.getRequestSpec())
                .body(payload)
                .when()
                .post(SIGNUP)
                .then()
                .extract().response();

        response.prettyPrint();
    }

    @When("I sign up with email {string} and password {string}")
    public void i_sign_up_with_invalid_email_and_password(String email, String password) {
        UserPayload payload = new UserPayload(TestDataManager.getUserId(), email, password);

        response = given()
                .spec(BaseSpecBuilder.getRequestSpec())
                .body(payload)
                .when()
                .post(SIGNUP)
                .then()
                .extract().response();

        response.prettyPrint();
    }

    @When("I sign up with email {string} and no password")
    public void i_sign_up_with_email_and_no_password(String email) {
        UserPayload payload = new UserPayload(TestDataManager.getUserId(), email, null);

        response = given()
                .spec(BaseSpecBuilder.getRequestSpec())
                .body(payload)
                .when()
                .post(SIGNUP)
                .then()
                .extract().response();

        response.prettyPrint();
    }

    @Then("the API should return status {int}")
    public void the_api_should_return_status(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should indicate {string}")
    public void the_response_should_indicate(String expectedMessage) {
        response.then().body("detail", equalTo(expectedMessage));
    }
    @When("I login with email {string} and password {string}")
    public void i_login_with_email_and_password(String email, String password) {
        UserPayload loginPayload = new UserPayload(TestDataManager.getUserId(), email, password);

        response = given()
                .spec(BaseSpecBuilder.getRequestSpec())
                .body(loginPayload)
                .when()
                .post(LOGIN)   // replace with your actual login endpoint
                .then()
                .extract()
                .response();
    }

    @When("I login with no email and password {string}")
    public void i_login_with_no_email_and_password(String password) {
        UserPayload loginPayload = new UserPayload(TestDataManager.getUserId(), "", password);

        response = given()
                .spec(BaseSpecBuilder.getRequestSpec())
                .body(loginPayload)
                .when()
                .post(LOGIN)   // replace with your actual login endpoint
                .then()
                .extract()
                .response();

    }
}
