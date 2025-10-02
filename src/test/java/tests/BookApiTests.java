package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiClient;

import java.util.HashMap;
import java.util.Map;

public class BookApiTests {

    @Test
    @Description("Verify that GET /Books returns status code 200 and a non-empty list")
    public void testGetAllBooks() {

        Response response = ApiClient.get("/Books");

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("title"), "Response should contain book titles");
    }

    @Test
    @Description("Verify that POST /Books creates a new book and returns status code 200")
    public void testCreateBook() {
        Map<String, Object> newBook = new HashMap<>();
        newBook.put("id", 9999);
        newBook.put("title", "Test Book");
        newBook.put("description", "Created via API test");
        newBook.put("pageCount", 123);
        newBook.put("excerpt", "Sample excerpt");
        newBook.put("publishDate", "2025-10-02T00:00:00");

        Response response = ApiClient.post("/Books", newBook);

        System.out.println("POST response:\n" + response.getBody().asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("Test Book"), "Response should contain the book title");
    }

    @Test(dependsOnMethods = "testCreateBook")
    @Description("Verify that PUT /Books updates an existing book and returns status code 200")
    public void testUpdateBook() {
        Map<String, Object> updatedBook = new HashMap<>();
        updatedBook.put("id", 9999);
        updatedBook.put("title", "Updated Test Book");
        updatedBook.put("description", "Updated via API test");
        updatedBook.put("pageCount", 321);
        updatedBook.put("excerpt", "Updated excerpt");
        updatedBook.put("publishDate", "2025-10-02T00:00:00");

        Response response = ApiClient.put("/Books/9999", updatedBook);

        System.out.println("PUT response:\n" + response.getBody().asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("Updated Test Book"), "Response should reflect updated title");
    }

    @Test(dependsOnMethods = "testUpdateBook")
    @Description("Verify that DELETE /Books removes the book and returns status code 200")
    public void testDeleteBook() {
        Response response = ApiClient.delete("/Books/9999");

        System.out.println("DELETE response:\n" + response.getBody().asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }
}