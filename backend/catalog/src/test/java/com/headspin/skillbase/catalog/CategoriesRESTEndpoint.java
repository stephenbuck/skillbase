package com.headspin.skillbase.catalog;

import static io.restassured.RestAssured.given;

// import java.util.ArrayList;
// import java.util.List;

import org.apache.http.HttpStatus;

import com.headspin.skillbase.catalog.domain.CatalogCategory;

import co.elastic.clients.util.ContentType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class CategoriesRESTEndpoint {

    private ValidatableResponse response;
//    private List<CatalogCategory> categories = new ArrayList<CatalogCategory>();

    @Given("a running categories endpoint")
    public void a_running_catalog_endpoint() {
        RestAssured.baseURI = "http://localhost:8080/skillbase-backend-catalog-0.1";
        RestAssured.basePath = "/catalog";
    }

    // Scenario: Insert a valid category

    @When("users insert a valid category")
    public void users_insert_a_valid_category() throws Exception {
        final CatalogCategory category = createValidCategory();
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogCategory.toJson(category))
                .put("/categories")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return the inserted category")
    public void the_server_should_return_the_inserted_category() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$.category_id");
        json.get("$.parent_id");
        json.get("$.is_enabled");
        json.get("$.title");
        json.get("$.note");
        json.get("$.image_id");
        json.get("$.created_at");
        json.get("$.updated_at");
        json.get("$.version");
    }

    // Scenario: Insert an invalid category

    @When("users insert an invalid category")
    public void users_insert_an_invalid_category() throws Exception {
        final CatalogCategory category = createInvalidCategory();
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogCategory.toJson(category))
                .put("/categories")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return an invalid category failure")
    public void the_server_should_return_an_invalid_category_failure() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$");
    }

    // Scenario: Update an existant valid category

    @When("users update an existant valid category")
    public void users_update_an_existant_valid_category() throws Exception {
        final CatalogCategory category = null;
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogCategory.toJson(category))
                .post("/categories")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return the updated category")
    public void the_server_should_return_the_updated_category() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$.category_id");
        json.get("$.parent_id");
        json.get("$.is_enabled");
        json.get("$.title");
        json.get("$.note");
        json.get("$.image_id");
        json.get("$.created_at");
        json.get("$.updated_at");
        json.get("$.version");
    }

    // Scenario: Update an existant invalid category

    @When("users update an existant invalid category")
    public void users_update_an_existant_invalid_category() throws Exception {
        final CatalogCategory category = null;
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogCategory.toJson(category))
                .post("/categories")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    // Scenario: Update a non-existant category

    @When("users update a non-existant category")
    public void users_update_a_non_existant_category() throws Exception {
        final CatalogCategory category = null;
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogCategory.toJson(category))
                .post("/categories")
                .then();
        System.out.println(response.extract().asPrettyString());

        // response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    // Scenario: Find an existant category
    
    @When("users find an existant category")
    public void users_find_an_existant_category() {
       response = given()
                .when()
                .pathParam("id", "<valid>")
                .get("/categories/{id}")
                .then();
       System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return the requested category")
    public void the_server_should_return_the_requested_category() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$.category_id");
        json.get("$.parent_id");
        json.get("$.is_enabled");
        json.get("$.title");
        json.get("$.note");
        json.get("$.image_id");
        json.get("$.created_at");
        json.get("$.updated_at");
        json.get("$.version");
    }
    
    // Scenario: Find a non-existant category

    @When("users find a non-existant category")
    public void users_find_a_non_existant_category() {
        response = given()
                .when()
                .pathParam("id", "<invalid>")
                .get("/categories/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());

        // response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    // Scenario: Delete an existant category

    @When("users delete an existant category")
    public void users_delete_an_existant_category() {
        response = given()
                .when()
                .pathParam("id", "<valid>")
                .post("/categories/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    // Scenario: Delete a non-existant category

    @When("users delete a non-existant category")
    public void users_delete_a_non_existant_category() {
        response = given()
                .when()
                .pathParam("id", "<invalid>")
                .delete("/categories/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());

        // response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    // Scenario: Find all categories

    @When("users find all categories")
    public void users_find_all_categories() {
        response = given()
                .when()
//                .queryParam("sort", "")
//                .queryParam("offset", "")
//                .queryParam("length", "")
                .get("/categories")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return all categories")
    public void the_server_should_return_all_categories() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$");

        /*
        List<String> list = json.getList("$");
        for (int i = 0; i < list.size(); i++) {
            json.get("$[].category_id");
            json.get("$[].parent_id");
            json.get("$[].is_enabled");
            json.get("$[].title");
            json.get("$[].note");
            json.get("$[].image_id");
            json.get("$[].created_at");
            json.get("$[].updated_at");
            json.get("$[].version");
        }
        */
    }

    @Then("the server should return success")
    public void the_server_should_return_success() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$");
    }

    @Then("the server should return failure")
    public void the_server_should_return_failure() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$");
    }

    private CatalogCategory createValidCategory() {
        final CatalogCategory category = new CatalogCategory();
        category.category_id = null;
        category.parent_id = null;
        category.is_enabled = true;
        category.title = "Title";
        category.note = "";
        category.image_id = null;
        category.created_at = null;
        category.updated_at = null;
        category.version = 0;
        return category;
    }

    private CatalogCategory createInvalidCategory() {
        return invalidateCategory(createValidCategory());
    }

    private CatalogCategory invalidateCategory(final CatalogCategory category) {
        category.title = null;
        return category;
    }
}
