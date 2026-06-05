package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpack;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLight;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement addTShirt;

    @FindBy(css = "a.shopping_cart_link")
    private WebElement cart;

    @FindBy(css = "button[data-test='checkout']")
    private WebElement checkoutButton;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement zipCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = "span.title")
    private WebElement checkoutOverview;

    @FindBy(id = "finish")
    private WebElement finish;

    @FindBy(css = "h2.complete-header")
    private WebElement completeCheckout;

    /**
     * Agrega tres productos al carrito: Backpack, Bike Light y Bolt T-Shirt.
     */
    public void addToCart() {
        click(addBackpack);
        click(addBikeLight);
        click(addTShirt);
    }

    /**
     * Elimina el Bike Light del carrito.
     */
    public void deleteItem() {
        click(addBackpack);
        click(addBikeLight);
        click(addTShirt);
        click(addBikeLight);
    }

    /**
     * Navega al carrito y completa el formulario de checkout
     * con datos de prueba (nombre, apellido y zip code).
     */
    public void checkout() {
        click(cart);
        click(checkoutButton);
        type(firstName, "Test");
        type(lastName, "Testing");
        type(zipCode, "12345");
        click(continueButton);
    }

    /**
     * Espera que la página de resumen sea visible y hace click en Finish.
     */
    public void finishCheckout() {
        waitForVisible(checkoutOverview);
        click(finish);
    }

    /**
     * Verifica que el mensaje de confirmación de orden sea visible.
     */
    public void completeCheckout() {
        waitForVisible(completeCheckout);
    }

    /**
     * Ejecuta una acción según el nombre recibido desde los Steps de Cucumber.
     *
     * @param accion acción a ejecutar: "agregar", "eliminar" o "checkout"
     */
    public void actionBy(String accion) {
        switch (accion.trim()) {
            case "agregar"  -> addToCart();
            case "eliminar" -> deleteItem();
            case "checkout" -> {
                checkout();
                finishCheckout();
                completeCheckout();
            }
        }
    }
}