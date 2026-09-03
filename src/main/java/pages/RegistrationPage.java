package pages;

import dto.User;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//*[@id='name']")
    WebElement inputFistName;

    @FindBy(xpath = "//*[@id='lastName']")
    WebElement inputLastName;

    @FindBy(xpath = "//*[@id='email']")
    WebElement inputEmail;

    @FindBy(xpath = "//*[@id='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//div[@class='checkbox-container']")
    WebElement checkBoxIAgree;

    @FindBy(xpath = "//input[@id='terms-of-use']")
    WebElement checkBoxTermsOfUse;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;


    public void typeRegistrationForm(User user) {
        inputFistName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickCheckBoxIAgree() {
        checkBoxIAgree.click();
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public void clickCheckBoxTermsOfUse() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",
                checkBoxTermsOfUse);
    }
}
