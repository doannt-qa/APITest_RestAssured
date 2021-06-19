package stepDefinitions;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.filter.ValueNode;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import pojo.PostData;
import utils.UserVariables;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DummyRestAPISteps extends UserVariables {

    // SET PATH WITH VARIABLE(S) from Table
    @Given("^I Set GET posts api endpoint with param \"([^\"]*)\"$")
    public void i_set_get_posts_api_endpoint_with_param_something(String url, DataTable data) throws Throwable {

        List<Map<String, String>> list = data.asMaps();
        Map<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("ID", list.get(0).get("ID"));
        restAssuredExtension.setPathParams(pathParams);
        restAssuredExtension.setPath(url);
    }

    // SET PATH WITH VARIABLE(S) from Examples
    @Given("^I Set GET posts api endpoint \"([^\"]*)\"  with param (.+)$")
    public void i_set_get_posts_api_endpoint_something_with_param(String url, String EmployeeID) throws Throwable {
        Map<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("ID", EmployeeID);
        System.out.println("Info: " + pathParams);
        restAssuredExtension.setPathParams(pathParams);
        restAssuredExtension.setPath(url);
    }

    /*
     * SET BODY REQUEST - option1: set body request from table data --> should use
     * for a simple request body - option2: set body from json file --> should use
     * for a complex request body
     */

    // body
    // option1 - 1.1: set body request from table data --> should use for a simple request - Using hashmap
    @And("^Set request Body as request body with data (.+) (.+) (.+)$")
    public void set_request_body_as_request_body_with_data(String value1, String value2, String value3) throws Throwable {
        Map<String, String> body = new HashMap<String, String>();
        body.put("name", value1);
        body.put("salary", value2);
        body.put("age", value3);
        System.out.println("request_body: " + body);
        restAssuredExtension.setBody(body);
    }

    // option1 - 1.1: set body request from table data --> should use for a simple request - Using Pojo
    @And("^Set request Body as request body using pojo with data (.+) (.+) (.+)$")
    public void set_request_body_as_request_body_with_data_pojp(String value1, String value2, String value3) throws Throwable {
//        Map<String, String> body = new HashMap<String, String>();
//        body.put("name", value1);
//        body.put("salary", value2);
//        body.put("age", value3);
        PostData BodyRequest  = new PostData(value1,value2,value3);
        System.out.println("request_body: " + BodyRequest.toString());
        restAssuredExtension.setBody(BodyRequest);
    }

    // option2: set body from json file: Scenarios Outline --> should use for a complex request body
    @And("^Set request Body as request body from file \"([^\"]*)\"$")
    public void set_request_body_as_request_body_from_file_something(String fileName) throws Throwable {
        Stream<String> lines = Files.lines(Paths.get(TEST_RESOURCE_PATH + "/" + JSON_REQUEST_BODY_PATH + "/" + fileName));
        String BodyRequest = lines.collect(Collectors.joining(System.lineSeparator()));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(BodyRequest);
        restAssuredExtension.setBody(jsonNode.toString());
    }




    @When("^I Set Update request Body$")
    public void i_set_update_request_body() throws Throwable {
        System.out.print("skipping");
    }
}
