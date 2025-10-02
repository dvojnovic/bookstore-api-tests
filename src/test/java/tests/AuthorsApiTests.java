package tests;

import io.qameta.allure.Description;
import models.Author;
import org.testng.annotations.Test;
import utils.ApiClient;
import io.restassured.response.Response;
import org.testng.Assert;

public class AuthorsApiTests {

    private static int createdAuthorId;

    @Test
    @Description("GET /Authors – Retrieve all authors")
    public void testGetAllAuthors() {
        Response response = ApiClient.get("/Authors");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0);
    }

    @Description("GET /Authors/{id} – Retrieve author by ID")
    public void testGetAuthorById() {
        Response response = ApiClient.get("/Authors/" + 1);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstName"), "John");
    }

    @Test
    @Description("POST /Authors – Create a new author")
    public void testCreateAuthor() {
        Author newAuthor = new Author(9999, 1, "John", "Doe");


        Response response = ApiClient.post("/Authors", newAuthor);
        Assert.assertEquals(response.statusCode(), 200);

        createdAuthorId = response.jsonPath().getInt("id");
        Assert.assertTrue(createdAuthorId > 0);
    }



    @Test(dependsOnMethods = "testCreateAuthor")
    @Description("PUT /Authors/{id} – Update author details")
    public void testUpdateAuthor() {
        Author updatedAuthor = new Author(createdAuthorId, 1, "Jane", "Smith");

        Response response = ApiClient.put("/Authors/" + createdAuthorId, updatedAuthor);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstName"), "Jane");
    }

    @Test(dependsOnMethods = "testUpdateAuthor")
    @Description("DELETE /Authors/{id} – Delete author by ID")
    public void testDeleteAuthor() {
        Response response = ApiClient.delete("/Authors/" + createdAuthorId);
        Assert.assertEquals(response.statusCode(), 200);

        Response check = ApiClient.get("/Authors/" + createdAuthorId);
        Assert.assertEquals(check.statusCode(), 404);
    }

    @Test
    @Description("GET /Authors/{id} – Non-existent ID should return 404")
    public void testGetNonExistentAuthor() {
        Response response = ApiClient.get("/Authors/99999");
        Assert.assertEquals(response.statusCode(), 404);
    }
}