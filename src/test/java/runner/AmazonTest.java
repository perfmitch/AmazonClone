package runner;


import cucumber.api.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        monochrome = true,
        plugin= {"pretty","html:target/cucumber-report-html/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

//        plugin = {"pretty","html:target/cucumber-report-html/report.html"})
public class AmazonTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}