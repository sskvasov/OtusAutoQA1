package otusAutoQA_HM2.parallel;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src\\test\\resources\\parallel"},glue = {"otusAutoQA_HM2.parallel.stepDefs"})
public class RunCucumberTest extends AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
