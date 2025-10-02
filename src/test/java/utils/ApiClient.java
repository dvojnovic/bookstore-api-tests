package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiClient {

    private static final String BASE_URL = "https://fakerestapi.azurewebsites.net/api/v1";

    // Send GET request to a specific endpoint
    public static Response get(String endpoint) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .when()
                .get(endpoint);
    }
}