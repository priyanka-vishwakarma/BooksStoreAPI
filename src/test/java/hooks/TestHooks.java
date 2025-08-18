package hooks;

import config.ConfigManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import static io.restassured.RestAssured.*;

import io.cucumber.java.BeforeAll;
import steps.AuthSteps;
import steps.BaseSpecBuilder;
import steps.SpecBuilder;
import utils.ApiConstants;
import steps.BooksCRUD;
import utils.EmailGenerator;
import utils.TestDataManager;

import java.util.Random;

public class TestHooks {

    @Before
    public void setUp() {
        baseURI = ConfigManager.getProperty("base.url");
        basePath = "/";
        requestSpecification = BaseSpecBuilder.getRequestSpec();

    }
    @BeforeAll
    public static void globalSetup() {
        TestDataManager.init();
    }

    @After
    public void cleanup() {
        if (BooksCRUD.bookId != 0) {
            SpecBuilder.doDeleteWithPathParam(
                    ApiConstants.BASE_PATH_BOOK_BY_ID,
                    "bookId",
                    BooksCRUD.bookId,
                    AuthSteps.getToken()
            );
            BooksCRUD.bookId = 0;
        }
    }
}
