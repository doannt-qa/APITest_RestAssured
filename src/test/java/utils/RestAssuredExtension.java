package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import utils.UserVariables;

import java.util.Map;

public class RestAssuredExtension {

    public String hostName = UserVariables.hostName;
    private RequestSpecBuilder builder;

    public RestAssuredExtension() {
        builder = new RequestSpecBuilder();
        builder.setBaseUri(hostName);
    }

    public RestAssuredExtension(String baseUri) {
        builder = new RequestSpecBuilder();
        builder.setBaseUri(baseUri);
    }

    public void reset() {
        builder = new RequestSpecBuilder();
        builder.setBaseUri(hostName);
    }

    public void setPath(String path) {
        builder.setBasePath(path);
    }

    public void addHeader(String headerName, String headerValue) {
        builder.addHeader(headerName, headerValue);
    }

    public void setQueryParams(Map<String, String> queryParams) {
        if (queryParams != null) {
            builder.addQueryParams(queryParams);
        }
    }

    public void setPathParams(Map<String, String> pathParams) {
        if (pathParams != null) {
            builder.addPathParams(pathParams);
        }
    }

    public void setBody(Object body) {
        if (body != null) {
            builder.setBody(body);
        }
    }

    public ResponseOptions<Response> get() {
        return executeAPI(HttpMethod.GET);
    }

    public ResponseOptions<Response> post() {
        return executeAPI(HttpMethod.POST);
    }

    public ResponseOptions<Response> put() {
        return executeAPI(HttpMethod.PUT);
    }

    public ResponseOptions<Response> delete() {
        return executeAPI(HttpMethod.DELETE);
    }

    public ResponseOptions<Response> patch() {
        return executeAPI(HttpMethod.PUT);
    }

    private ResponseOptions<Response> executeAPI(HttpMethod method) {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given().spec(requestSpecification);
        request.contentType(ContentType.JSON);
        if (HttpMethod.GET.equals(method)) {
            return request.get();
        } else if (HttpMethod.POST.equals(method)) {
            return request.post();
        } else if (HttpMethod.PUT.equals(method)) {
            return request.put();
        } else if (HttpMethod.DELETE.equals(method)) {
            return request.delete();
        } else if (HttpMethod.PUT.equals(method))
            return request.patch();
        return null;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

}
