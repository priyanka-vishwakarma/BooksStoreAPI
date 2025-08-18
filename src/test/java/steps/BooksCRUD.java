package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.BooksPayload;
import utils.ApiConstants;

import java.util.Map;

import static org.testng.Assert.*;
import static utils.ApiConstants.STATUS_OK;

public class BooksCRUD {

    private Response response;
    public static int bookId;
    private static BooksPayload lastSentPayload;

    @When("I create a book with the following details")
    public void i_create_a_book_with_the_following_details(DataTable dataTable) {
        Map<String, String> bookData = dataTable.asMaps().get(0);
        BooksPayload booksPayload = new BooksPayload(
                Integer.parseInt(bookData.get("id")),
                bookData.get("name"),
                bookData.get("author"),
                bookData.get("published_year"),
                bookData.get("book_summary")
        );
        lastSentPayload = booksPayload;
        response = SpecBuilder.doPost(ApiConstants.BASE_PATH_BOOKS, booksPayload, AuthSteps.getToken());
        try {
            bookId = response.jsonPath().getInt("id");
        } catch (Exception e) {
            bookId = booksPayload.getId();
        }

    }

    @Then("the book should be created successfully")
    public void the_book_should_be_created_successfully() {
        response.then().spec(SpecBuilder.getResponseSpec(STATUS_OK));
    }

    @Then("I should receive a valid book ID")
    public void i_should_receive_a_valid_book_id() {
        assertEquals(bookId, lastSentPayload.getId(), "Book ID should match input");
    }

    @Then("the book details should match")
    public void the_book_details_should_match(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> expectedData = dataTable.asMaps().get(0);
        assertEquals(response.getStatusCode(), STATUS_OK);
        assertEquals(response.jsonPath().getString("name"),expectedData.get("name"),"Name mismatch");
        assertEquals(response.jsonPath().getString("author"),expectedData.get("author"),"author mismatch");
        assertEquals(response.jsonPath().getString("published_year"),expectedData.get("published_year"),"Published_year mismatch");
        assertEquals(response.jsonPath().getString("book_summary"),expectedData.get("book_summary"),"Book_summary mismatch");
    }

    @When("I update the book with the following details")
    public void i_update_the_book_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> bookData = dataTable.asMaps().get(0);
        BooksPayload booksPayload = new BooksPayload(
                bookId,
                bookData.get("name"),
                bookData.get("author"),
                bookData.get("published_year"),
                bookData.get("book_summary")
        );
        lastSentPayload = booksPayload;
        response = SpecBuilder.doPutWithPathParam(ApiConstants.BASE_PATH_BOOK_BY_ID, booksPayload, "bookId", bookId, AuthSteps.getToken());
    }

    @Then("the book should be updated successfully")
    public void the_book_should_be_updated_successfully() {
        response.then().spec(SpecBuilder.getResponseSpec(STATUS_OK));
    }

    @When("I delete the book by the saved ID")
    public void i_delete_the_book_by_the_saved_id() {
        response = SpecBuilder.doDeleteWithPathParam(ApiConstants.BASE_PATH_BOOK_BY_ID, "bookId", bookId, AuthSteps.getToken());
    }

    @Then("the book should be deleted successfully")
    public void the_book_should_be_deleted_successfully() {
        response.then().spec(SpecBuilder.getResponseSpec(STATUS_OK));
    }

    @Then("the API should return a {int}")
    public void the_api_should_return_status(Integer statusCode) {
        response.then().statusCode(statusCode);
    }


    @Then("the response should indicate that the book was not found")
    public void the_response_should_indicate_that_the_book_was_not_found() {
        String errorMsg = response.jsonPath().getString("detail");
        assertNotNull(errorMsg, "Book not found");
        assertTrue(errorMsg.toLowerCase().contains("not found"), "Error message should mention 'not found'");
    }

    @Given("a book already exists with the following details")
    public void a_book_already_exists_with_the_following_details(DataTable dataTable) {
        i_create_a_book_with_the_following_details(dataTable);
    }
    @When("I create a book with the same ID")
    public void i_create_a_book_with_the_same_id(DataTable dataTable) {
        Map<String, String> bookData = dataTable.asMaps().get(0);
        BooksPayload booksPayload = new BooksPayload(
                Integer.parseInt(bookData.get("id")),
                bookData.get("name"),
                bookData.get("author"),
                bookData.get("published_year"),
                bookData.get("book_summary")
        );
        lastSentPayload = booksPayload;
        response = SpecBuilder.doPost(ApiConstants.BASE_PATH_BOOKS, booksPayload, AuthSteps.getToken());
    }

    @When("I retrieve the book by ID {string}")
    public void i_retrieve_the_book_by_id(String id) {
        response = SpecBuilder.doGetWithPathParam(
                ApiConstants.BASE_PATH_BOOK_BY_ID,
                "bookId",
                id,
                AuthSteps.getToken()
        );
    }
}