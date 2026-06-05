package com.automation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner - CUCUMBER
 * Punto de entrada de la suite. Configura features, steps, reportes y tags.
 *
 * Correr todos:          mvn test
 * Correr solo @smoke:    mvn test -Dcucumber.filter.tags="@smoke"
 * Correr solo @login:    mvn test -Dcucumber.filter.tags="@login"
 * Excluir @negativo:     mvn test -Dcucumber.filter.tags="not @negativo"
 */
@CucumberOptions(
    features = "src/test/resources/features",
    glue     = {"com.automation.steps"},
    plugin   = {
        "pretty",
        "html:target/cucumber-reports/report.html",
        "json:target/cucumber-reports/report.json",
        "junit:target/cucumber-reports/report.xml"
    },
    monochrome = true,
    tags = ""
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false) // cambia a true para paralelo
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
