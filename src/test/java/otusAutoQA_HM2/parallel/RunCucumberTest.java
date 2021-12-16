package otusAutoQA_HM2.parallel;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.testng.CucumberOptions;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@CucumberOptions(features = {"src\\test\\resources\\parallel"},glue = {"otusAutoQA_HM2.parallel.stepDefs"})
public class RunCucumberTest extends AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
