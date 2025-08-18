package steps;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SpecBuilder extends BaseSpecBuilder {

    // POST request
    public static Response doPost(String endpoint, Object body, String token) {
        return given()
                .spec(getAuthRequestSpec(token))
                .body(body)
                .when()
                .post(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    // GET request
    public static Response doGet(String endpoint, String token) {
        return given()
                .spec(getAuthRequestSpec(token))
                .when()
                .get(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    // GET request with path param
    public static Response doGetWithPathParam(String endpoint, String key, Object value, String token) {
        return given()
                .spec(getAuthRequestSpec(token))
                .pathParam(key, value)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // PUT request
    public static Response doPut(String endpoint, Object body, String token) {
        return given()
                .spec(getAuthRequestSpec(token))
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    // PUT request with path param
    public static Response doPutWithPathParam(String endpoint, Object body, String key, Object value, String token) {
        return given()
                .spec(getAuthRequestSpec(token))
                .pathParam(key, value)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    // DELETE request
    public static Response doDelete(String endpoint, String token) {
        return given()
                .spec(getAuthRequestSpec(token))
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    // DELETE request with path param
    public static Response doDeleteWithPathParam(String endpoint, String key, Object value, String token) {
        return given()
                .spec(getAuthRequestSpec(token))
                .pathParam(key, value)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}