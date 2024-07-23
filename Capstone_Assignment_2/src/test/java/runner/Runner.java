package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\test\\resources\\feature",
		glue = "stepDefinitions",
		tags = "@custlogin or @managerLogin",
		plugin = {"pretty","html:target/Reports/Cucumber.html"}
		)
public class Runner {

}
