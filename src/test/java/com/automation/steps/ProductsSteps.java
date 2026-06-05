package com.automation.steps;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import io.cucumber.java.en.*;

public class ProductsSteps {

    private final ProductsPage productsPage = new ProductsPage();
    private final LoginPage loginPage = new LoginPage();

    @Given("El usuario ha iniciado sesión correctamente")
    public void succesfulllLogin() {
        loginPage.navigateTo("https://www.saucedemo.com/"); // <- cambia tu URL
        loginPage.login("standard_user", "secret_sauce");
        loginPage.closeModal();
    }

    @Then("El usuario puede {string} un producto")
    public void actionBy(String action) {
        productsPage.actionBy(action);
    }

}
