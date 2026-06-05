package com.automation.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*** LoginPage */
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "div.error-message-container")
    private WebElement errorMessage;

    @FindBy(css = "button.btn_action")
    private WebElement acceptButton;

    @FindBy(css = "div.header_label")
    private WebElement successMessage;

    /*** Function to put the values on the page
     **@param username The username value to log in.
     *@param password The password value to log in.
     ****/
    public void inputValues(String username, String password){
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    /*** Function to click the login button */
    public void clickingLoginButton(){
        loginButton.click();
    }

    /*** Function to check if the user is in the principal page */
    public void verifyPrincipalPage(){
       successMessage.isDisplayed();
    }

    public void login(String username, String password){
        inputValues(username,password);
        clickingLoginButton();
    }

    /*** Function to closet the modal */
    public void closeModal() {
        if (isDisplayed(acceptButton)) {
            click(acceptButton);
        }
    }

    /*** Function to get the message error */
    public String  getErrorMessage(){
        return getText(errorMessage);
    }
}
