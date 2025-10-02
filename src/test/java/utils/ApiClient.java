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

    // Send POST request with body
    public static Response post(String endpoint, Object body) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint);
    }

    // Send PUT request with body
    public static Response put(String endpoint, Object body) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(endpoint);
    }

    // Send DELETE request
    public static Response delete(String endpoint) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .when()
                .delete(endpoint);
    }
}