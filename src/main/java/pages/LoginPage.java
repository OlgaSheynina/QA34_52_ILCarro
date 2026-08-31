package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;

    @FindBy(xpath = "//input[@type='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    @FindBy(xpath = "//h1[text()='Logged in']")
    WebElement messageLoggedIn;

    @FindBy(xpath = "//div[text()=' Email is required ']")
    WebElement messageEmail;

    @FindBy(xpath = "//div[text()=' Password is required ']")
    WebElement messagePassword;

    public void typeLoginForm(User user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public boolean validateTextInMessageLoggedInIsRequired(String text) {
        return isTextInElementPresent(messageLoggedIn, text);
    }

    public boolean validateTextInMessageEmailIsRequired(String text) {
        return isTextInElementPresent(messageEmail, text);
    }

    public boolean validateTextInMessagePasswordIsRequired(String text) {
        return isTextInElementPresent(messagePassword, text);
    }
}



















