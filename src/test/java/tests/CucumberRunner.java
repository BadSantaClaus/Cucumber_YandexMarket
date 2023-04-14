package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
/**
 * Класс используется для запуска тестов
 *
 * @author Горячев Роман Юрьевич
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm", "pretty", "json:target/cucumber-report/repoert.json"},
        features = "src/test/java/tests/features",
        glue = "tests/helpers",
        tags = {"@run"}
)
public class CucumberRunner {
}
