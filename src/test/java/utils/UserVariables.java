package utils;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utils.RestAssuredExtension;

@RunWith(SpringRunner.class)
@PropertySource("classpath:configuration.properties")
public class UserVariables {
	public static String TEST_RESOURCE_PATH = "src/test/resources";
	public static String JSON_REQUEST_BODY_PATH = "json/request-body";

	@Value("${hostName}")
	protected String serverHost_;

	protected static String hostName = "http://dummy.restapiexample.com/api/v1";
	// Dummy RestAPI: http://dummy.restapiexample.com/api/v1
	protected static String ACCESS_TOKEN_INVALID = "token_invalid";
	protected static String ACCESS_TOKEN;
	protected static String REFRESH_TOKEN;
	protected static RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
	protected static ResponseOptions<Response> response;
	protected String apiUri;
	protected Map<String, Object> pathParams = new HashMap<String, Object>();
    protected Map<String, Object> queryParams = new HashMap<String, Object>();
    protected Map<String, Object> body = new HashMap<String, Object>();

	@PostConstruct
	public void init() {
		restAssuredExtension.setHostName(serverHost_);
	}
}