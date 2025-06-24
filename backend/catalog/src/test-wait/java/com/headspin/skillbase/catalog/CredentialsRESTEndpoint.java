package com.headspin.skillbase.catalog;

import static io.restassured.RestAssured.given;

// import java.util.ArrayList;
// import java.util.List;

import org.apache.http.HttpStatus;

import com.headspin.skillbase.catalog.domain.CatalogCredential;

import co.elastic.clients.util.ContentType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class CredentialsRESTEndpoint {

    private ValidatableResponse response;
    // private List<CatalogCredential> credentials = new
    // ArrayList<CatalogCredential>();

    @Given("a running credentials endpoint")
    public void a_running_catalog_endpoint() {
        RestAssured.baseURI = "http://localhost:8080/skillbase-backend-catalog-0.1";
        RestAssured.basePath = "/catalog";
    }

    // Scenario: Insert a valid credential

    @When("users insert a valid credential")
    public void users_insert_a_valid_credential() throws Exception {
        final CatalogCredential credential = createValidCredential();
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogCredential.toJson(credential))
                .put("/credentials")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return the inserted credential")
    public void the_server_should_return_the_inserted_credential() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$.credential_id");
        json.get("$.parent_id");
        json.get("$.is_enabled");
        json.get("$.title");
        json.get("$.note");
        json.get("$.image_id");
        json.get("$.created_at");
        json.get("$.updated_at");
        json.get("$.version");
    }

    // Scenario: Insert an invalid credential

    @When("users insert an invalid credential")
    public void users_insert_an_invalid_credential() throws Exception {
        final CatalogCredential credential = createInvalidCredential();
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogCredential.toJson(credential))
                .put("/credentials")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return an invalid credential failure")
    public void the_server_should_return_an_invalid_credential_failure() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$");
    }

    // Scenario: Update an existant valid credential

    @When("users update an existant valid credential")
    public void users_update_an_existant_valid_credential() throws Exception {
        final CatalogCredential credential = null;
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogCredential.toJson(credential))
                .post("/credentials")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return the updated credential")
    public void the_server_should_return_the_updated_credential() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$.credential_id");
        json.get("$.parent_id");
        json.get("$.is_enabled");
        json.get("$.title");
        json.get("$.note");
        json.get("$.image_id");
        json.get("$.created_at");
        json.get("$.updated_at");
        json.get("$.version");
    }

    // Scenario: Update an existant invalid credential

    @When("users update an existant invalid credential")
    public void users_update_an_existant_invalid_credential() throws Exception {
        final CatalogCredential credential = null;
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogCredential.toJson(credential))
                .post("/credentials")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    // Scenario: Update a non-existant credential

    @When("users update a non-existant credential")
    public void users_update_a_non_existant_credential() throws Exception {
        final CatalogCredential credential = null;
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogCredential.toJson(credential))
                .post("/credentials")
                .then();
        System.out.println(response.extract().asPrettyString());

        // response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    // Scenario: Find an existant credential

    @When("users find an existant credential")
    public void users_find_an_existant_credential() {
        response = given()
                .when()
                .pathParam("id", "<valid>")
                .get("/credentials/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return the requested credential")
    public void the_server_should_return_the_requested_credential() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$.credential_id");
        json.get("$.parent_id");
        json.get("$.is_enabled");
        json.get("$.title");
        json.get("$.note");
        json.get("$.image_id");
        json.get("$.created_at");
        json.get("$.updated_at");
        json.get("$.version");
    }

    // Scenario: Find a non-existant credential

    @When("users find a non-existant credential")
    public void users_find_a_non_existant_credential() {
        response = given()
                .when()
                .pathParam("id", "<invalid>")
                .get("/credentials/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());

        // response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    // Scenario: Delete an existant credential

    @When("users delete an existant credential")
    public void users_delete_an_existant_credential() {
        response = given()
                .when()
                .pathParam("id", "<valid>")
                .post("/credentials/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    // Scenario: Delete a non-existant credential

    @When("users delete a non-existant credential")
    public void users_delete_a_non_existant_credential() {
        response = given()
                .when()
                .pathParam("id", "<invalid>")
                .delete("/credentials/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());

        // response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    // Scenario: Find all credentials

    @When("users find all credentials")
    public void users_find_all_credentials() {
        response = given()
                .when()
                // .queryParam("sort", "")
                // .queryParam("offset", "")
                // .queryParam("length", "")
                .get("/credentials")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return all credentials")
    public void the_server_should_return_all_credentials() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$");

        /*
         * List<String> list = json.getList("$");
         * for (int i = 0; i < list.size(); i++) {
         * json.get("$[].credential_id");
         * json.get("$[].is_enabled");
         * json.get("$[].title");
         * json.get("$[].note");
         * json.get("$[].image_id");
         * json.get("$[].created_at");
         * json.get("$[].updated_at");
         * json.get("$[].version");
         * }
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

    private CatalogCredential createValidCredential() {
        return CatalogCredential.builder()
                .credential_id(null)
                .is_enabled(true)
                .title("Title")
                .note("")
                .image_id(null)
                .created_at(null)
                .updated_at(null)
                .version(0)
                .build();
    }

    private CatalogCredential createInvalidCredential() {
        return invalidateCredential(createValidCredential());
    }

    private CatalogCredential invalidateCredential(final CatalogCredential credential) {
        credential.title = null;
        return credential;
    }
}
