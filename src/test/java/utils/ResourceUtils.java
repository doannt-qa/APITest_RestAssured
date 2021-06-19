package utils;
import java.io.File;
public class ResourceUtils {
	 public static final String RESOURCES_PATH = "src/test/resources";
	    public static final String RESPONSE_JSON_SCHEMA_RESOURCES_PATH = "src/test/resources/json/response-schema";

	    public static String getResponseJsonSchemaFilePath(String fileName) {
	        return RESPONSE_JSON_SCHEMA_RESOURCES_PATH + "/" + fileName;
	    }

	    public static File getResponseJsonSchemaFile(String fileName) {
	        return new File(getResponseJsonSchemaFilePath(fileName));
	    }
}
