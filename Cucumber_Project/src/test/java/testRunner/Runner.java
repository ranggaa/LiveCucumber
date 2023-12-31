package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"FeatureFiles"},monochrome=true,glue= {"stepDefination"},
plugin = {"pretty","html:target/cucumber-reports"})
public class Runner extends AbstractTestNGCucumberTests {

}
