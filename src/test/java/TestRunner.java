
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "json:target/json-report/cucumber.json" }, glue = {
		"stepDefinitions" }, features = { "src/test/java/features" })
public class TestRunner {
}
