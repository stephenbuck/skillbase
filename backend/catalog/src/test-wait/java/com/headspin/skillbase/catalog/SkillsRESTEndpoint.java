package com.headspin.skillbase.catalog;

import static io.restassured.RestAssured.given;

// import java.util.ArrayList;
// import java.util.List;

import org.apache.http.HttpStatus;

import com.headspin.skillbase.catalog.domain.CatalogSkill;

import co.elastic.clients.util.ContentType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class SkillsRESTEndpoint {

    private ValidatableResponse response;
    // private List<CatalogSkill> skills = new ArrayList<CatalogSkill>();

    @Given("a running skills endpoint")
    public void a_running_catalog_endpoint() {
        RestAssured.baseURI = "http://localhost:8080/skillbase-backend-catalog-0.1";
        RestAssured.basePath = "/catalog";
    }

    // Scenario: Insert a valid skill

    @When("users insert a valid skill")
    public void users_insert_a_valid_skill() throws Exception {
        final CatalogSkill skill = createValidSkill();
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogSkill.toJson(skill))
                .put("/skills")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return the inserted skill")
    public void the_server_should_return_the_inserted_skill() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$.skill_id");
        json.get("$.parent_id");
        json.get("$.is_enabled");
        json.get("$.title");
        json.get("$.note");
        json.get("$.image_id");
        json.get("$.created_at");
        json.get("$.updated_at");
        json.get("$.version");
    }

    // Scenario: Insert an invalid skill

    @When("users insert an invalid skill")
    public void users_insert_an_invalid_skill() throws Exception {
        final CatalogSkill skill = createInvalidSkill();
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogSkill.toJson(skill))
                .put("/skills")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return an invalid skill failure")
    public void the_server_should_return_an_invalid_skill_failure() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$");
    }

    // Scenario: Update an existant valid skill

    @When("users update an existant valid skill")
    public void users_update_an_existant_valid_skill() throws Exception {
        final CatalogSkill skill = null;
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogSkill.toJson(skill))
                .post("/skills")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return the updated skill")
    public void the_server_should_return_the_updated_skill() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$.skill_id");
        json.get("$.parent_id");
        json.get("$.is_enabled");
        json.get("$.title");
        json.get("$.note");
        json.get("$.image_id");
        json.get("$.created_at");
        json.get("$.updated_at");
        json.get("$.version");
    }

    // Scenario: Update an existant invalid skill

    @When("users update an existant invalid skill")
    public void users_update_an_existant_invalid_skill() throws Exception {
        final CatalogSkill skill = null;
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogSkill.toJson(skill))
                .post("/skills")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    // Scenario: Update a non-existant skill

    @When("users update a non-existant skill")
    public void users_update_a_non_existant_skill() throws Exception {
        final CatalogSkill skill = null;
        response = given()
                .when()
                .contentType(ContentType.APPLICATION_JSON)
                .body(CatalogSkill.toJson(skill))
                .post("/skills")
                .then();
        System.out.println(response.extract().asPrettyString());

        // response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    // Scenario: Find an existant skill

    @When("users find an existant skill")
    public void users_find_an_existant_skill() {
        response = given()
                .when()
                .pathParam("id", "<valid>")
                .get("/skills/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return the requested skill")
    public void the_server_should_return_the_requested_skill() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$.skill_id");
        json.get("$.parent_id");
        json.get("$.is_enabled");
        json.get("$.title");
        json.get("$.note");
        json.get("$.image_id");
        json.get("$.created_at");
        json.get("$.updated_at");
        json.get("$.version");
    }

    // Scenario: Find a non-existant skill

    @When("users find a non-existant skill")
    public void users_find_a_non_existant_skill() {
        response = given()
                .when()
                .pathParam("id", "<invalid>")
                .get("/skills/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());

        // response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    // Scenario: Delete an existant skill

    @When("users delete an existant skill")
    public void users_delete_an_existant_skill() {
        response = given()
                .when()
                .pathParam("id", "<valid>")
                .post("/skills/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    // Scenario: Delete a non-existant skill

    @When("users delete a non-existant skill")
    public void users_delete_a_non_existant_skill() {
        response = given()
                .when()
                .pathParam("id", "<invalid>")
                .delete("/skills/{id}")
                .then();
        System.out.println(response.extract().asPrettyString());

        // response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    // Scenario: Find all skills

    @When("users find all skills")
    public void users_find_all_skills() {
        response = given()
                .when()
                // .queryParam("sort", "")
                // .queryParam("offset", "")
                // .queryParam("length", "")
                .get("/skills")
                .then();
        System.out.println(response.extract().asPrettyString());
    }

    @Then("the server should return all skills")
    public void the_server_should_return_all_skills() {

        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.APPLICATION_JSON);

        final JsonPath json = JsonPath.from(response.toString());
        json.get("$");

        /*
         * List<String> list = json.getList("$");
         * for (int i = 0; i < list.size(); i++) {
         * json.get("$[].skill_id");
         * json.get("$[].parent_id");
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

    private CatalogSkill createValidSkill() {
        return CatalogSkill.builder()
                .skill_id(null)
                .is_enabled(true)
                .title("Title")
                .note("")
                .image_id(null)
                .created_at(null)
                .updated_at(null)
                .version(0)
                .build();
    }

    private CatalogSkill createInvalidSkill() {
        return invalidateSkill(createValidSkill());
    }

    private CatalogSkill invalidateSkill(final CatalogSkill skill) {
        skill.title = null;
        return skill;
    }
}
