package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import listeners.ExtentTestNGListener;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html"
        },
        monochrome = true
)

@Listeners({ExtentTestNGListener.class})
public class TestNgRunner extends AbstractTestNGCucumberTests{

}
