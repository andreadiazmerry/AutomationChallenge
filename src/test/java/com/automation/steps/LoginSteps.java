package com.automation.steps;

import com.automation.pages.LoginPage;
import com.automation.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private LoginPage loginPage;

    @Before
    public void setUp() {

        DriverManager.initDriver("chrome");
        loginPage = new LoginPage();
    }

    @After
    public void tearDown(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Screenshot");

        DriverManager.quitDriver();
    }

    @Given("El usuario está en la página de login")
    public void LoginStep() {
        loginPage.navigateTo("https://www.saucedemo.com/");
    };


    @When("El usuario ingresa el {string} y {string}")
    public void inputValues(String username, String password) {
        loginPage.inputValues(username,password);
    }

    @And("El usuario hace click en el botón de login")
    public void loginButton() {
        loginPage.clickingLoginButton();
    }

    @And("El usuario debe ver la página de productos")
    public void verifyPrincipalPage() {
        loginPage.verifyPrincipalPage();
    }

    @Then("El usuario debe ver {string} de error")
    public void mensajeDeError(String mensaje) {
        assertThat(loginPage.getErrorMessage()).contains(mensaje);
    }
}