package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features="src/test/resources/feature",
    glue = "stepDefinition",
    tags ="@FlipkartAddCart",
    plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
    )
public class RunTest extends AbstractTestNGCucumberTests{
	
}
