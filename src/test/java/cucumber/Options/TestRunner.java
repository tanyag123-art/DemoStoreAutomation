package cucumber.Options;

import org.junit.runner.RunWith;

//import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",
				glue = {"stepDefinations"},
				 monochrome = true,
			        dryRun = false
//			        tags="@PET_01"
//			        strict = true
//			        plugin = {
//			                "pretty",
//			                "html:target/cucumber-reports/cucumber-pretty",
//			                "json:target/cucumber-reports/CucumberTestReport.json",
//			                "rerun:target/cucumber-reports/rerun.txt"

		)
public class TestRunner {

}
