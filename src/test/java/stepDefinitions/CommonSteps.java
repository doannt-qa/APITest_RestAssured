package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import utils.ResourceUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.response.ResponseBody;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import utils.UserVariables;


public class CommonSteps extends UserVariables {

	@Before
	public void resetData() {
		restAssuredExtension.reset();
	}
	
	 @Given("^I set api endpoint \"([^\"]*)\"$")
	    public void i_set_api_endpoint_something(String apiUri) throws Throwable {
		 this.apiUri = apiUri;
	    }

	// SET METHOD: POST, GET, PUT, DELELE and path for REST API
	@Given("^I Set POST posts api endpoint \"([^\"]*)\"$")
	public void i_set_post_posts_api_endpoint_something(String url) throws Throwable {
		restAssuredExtension.setPath(url);
	}

	@Given("^I Set GET posts api endpoint \"([^\"]*)\"$")
	public void i_set_get_posts_api_endpoint_something(String url) throws Throwable {
		restAssuredExtension.setPath(url);
	}

	@Given("^I Set PUT posts api endpoint for \"([^\"]*)\"$")
	public void i_set_put_posts_api_endpoint_for_something(String url) throws Throwable {
		restAssuredExtension.setPath(url);
	}

	@Given("^I Set DELETE posts api endpoint for \"([^\"]*)\"$")
	public void i_set_delete_posts_api_endpoint_for_something(String url) throws Throwable {
		restAssuredExtension.setPath(url);
	}

	// GET TOKEN FROM COOKIE
	@And("^Get token from the cookie$")
	public void get_token_from_the_cookie() throws Throwable {
		ACCESS_TOKEN = response.getCookie("spm_access_token");
	}

	// GET TOKEN FROM RESPONSE BODY
	@And("^Get token from the response body$")
	public void get_token_from_the_response_body() throws Throwable {
		ResponseBody<?> body = response.getBody();
		ACCESS_TOKEN = body.jsonPath().getString("spm_access_token");
	}

	// SET HEADER PARAM REQUEST: Content-Type, Token
	@When("^I Set HEADER param request content type as \"([^\"]*)\"$")
	public void i_set_header_param_request_content_type_as_something(String ContentType) throws Throwable {
		restAssuredExtension.addHeader("Content-Type", ContentType);
	}

	@And("^I Set HEADER param request Auth bearer as \"([^\"]*)\"$")
	public void i_set_header_param_request_auth_bearer_as_something(String strArg1) throws Throwable {
		restAssuredExtension.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
	}


	// SEND REQUEST: POST, GET, PUT, DELETE
	@And("^Send GET HTTP request$")
	public void send_get_http_request() throws Throwable {
		response = restAssuredExtension.get();
	}

	@And("^Send PUT HTTP request$")
	public void send_put_http_request() throws Throwable {
		response = restAssuredExtension.put();
	}

	@And("^Send POST HTTP request$")
	public void send_post_http_request() throws Throwable {
		response = restAssuredExtension.post();
	}

	@And("^Send DELETE HTTP request$")
	public void send_delete_http_request() throws Throwable {
		response = restAssuredExtension.delete();
	}

	/*
	 * verify test result by following criteria; - http code - value in response
	 * body - response schema
	 */
	@Then("^I receive valid HTTP response code \"([^\"]*)\"$")
	public void i_receive_valid_http_response_code_something(int status) throws Throwable {
		assertEquals(status, response.getStatusCode());
	}

	// verify VALUE of specific DATA in the response body
	@And("^Response BODY includes value of \"([^\"]*)\" is \"([^\"]*)\"$")
	public void response_body_includes_value_of_something_is_something(String key, String value) throws Throwable {
		ResponseBody<?> body = response.getBody();
		String actual_value = body.jsonPath().getString(key);
		actual_value.contentEquals(value);
	}

	/*
	 * How to convert json to json schema. Using this site ->
	 * https://jsonschema.net/home version: Draft 07 keyword visibility: title,
	 * description, default, type, additional Properties, additonal Items, required
	 */
	@And("^Response body matches the schema from file \"([^\"]*)\"$")
	public void response_body_matches_the_schema_from_file_something(String jsonSchemaFileName) throws Throwable {
		String responseBody = response.getBody().asString();
		assertThat(responseBody, matchesJsonSchema(ResourceUtils.getResponseJsonSchemaFile(jsonSchemaFileName)));
	}


}
