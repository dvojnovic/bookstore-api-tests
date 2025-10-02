package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiClient;

public class BookApiTests {

    @Test
    @Description("Verify that GET /Books returns status code 200 and a non-empty list")
    public void testGetAllBooks() {

        Response response = ApiClient.get("/Books");

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("title"), "Response should contain book titles");
    }
}