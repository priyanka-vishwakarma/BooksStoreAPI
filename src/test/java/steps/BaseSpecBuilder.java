package steps;

import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;

public class BaseSpecBuilder {
    private static RequestSpecification requestSpec;
    private static RequestSpecification authRequestSpec;
    private static final Map<Integer, ResponseSpecification> responseSpecCache = new HashMap<>();

    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(ConfigManager.getProperty("base.url"))
                    .setContentType(ContentType.JSON)
                    .build();
        }
        return requestSpec;
    }


    public static RequestSpecification getAuthRequestSpec(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getProperty("base.url"))
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    public static ResponseSpecification getResponseSpec(int statusCode) {
        return responseSpecCache.computeIfAbsent(statusCode, code ->
                new ResponseSpecBuilder()
                        .expectStatusCode(code)
                        .expectContentType(ContentType.JSON)
                        .build()
        );
    }
}
