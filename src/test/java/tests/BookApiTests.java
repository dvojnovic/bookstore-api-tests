package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.Book;
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

    @Test
    @Description("Verify that GET /Books/{id} returns correct book details")
    public void testGetBookById() {
        Response response = ApiClient.get("/Books/" + 1);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("Book 1"), "Response should contain the book title");
    }

    @Test
    @Description("Verify that POST /Books creates a new book and returns status code 200")
    public void testCreateBook() {
        Book newBook = new Book(9999, "Test Book", "Created via API test", 123, "Sample excerpt", "2025-10-02T00:00:00");

        Response response = ApiClient.post("/Books", newBook);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("Test Book"), "Response should contain the book title");
    }

    @Test(dependsOnMethods = "testCreateBook")
    @Description("Verify that PUT /Books updates an existing book and returns status code 200")
    public void testUpdateBook() {
        Book updatedBook = new Book(9999, "Updated Test Book", "Updated via API test", 321, "Updated excerpt", "2025-09-02T00:00:00");

        Response response = ApiClient.put("/Books/9999", updatedBook);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("Updated Test Book"), "Response should reflect updated title");
    }

    @Test(dependsOnMethods = "testUpdateBook")
    @Description("Verify that DELETE /Books removes the book and returns status code 200")
    public void testDeleteBook() {
        Response response = ApiClient.delete("/Books/9999");

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }

    @Test
    @Description("GET /Books/{id} – Non-existent ID should return 404")
    public void testGetNonExistentBook() {
        Response response = ApiClient.get("/Books/99999");
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test
    @Description("PUT /Books/{id} – Updating non-existent book")
    public void testUpdateNonExistentBook() {
        Book book = new Book(
                99999,
                "Ghost Book",
                "This book does not exist",
                100,
                "Excerpt",
                "2025-10-03T00:00:00"
        );

        Response response = ApiClient.put("/Books/99999", book);
        Assert.assertEquals(response.statusCode(), 404);
    }


}