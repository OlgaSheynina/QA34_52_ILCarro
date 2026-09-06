package pages;

import dto.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.enums.Fuel;

import java.io.File;


public class LetTheCarWorkPage extends BasePage {

    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;

    @FindBy(xpath = "//input[@formcontrolname='pickUpPlace']")
    WebElement inputLocation;

    @FindBy(xpath = "//div[@class='input-container']" +
            "/input[@ng-reflect-name='make']")
    WebElement inputManufacture;

    @FindBy(xpath = "//input[@formcontrolname='model']")
    WebElement inputModel;

    @FindBy(xpath = "//input[@ng-reflect-name='year']")
    WebElement inputYear;

    @FindBy(xpath = "//*[@id='fuel']")
    WebElement inputFuel;

    @FindBy(xpath = "//*[@id='seats']")
    WebElement inputSeats;

    @FindBy(xpath = "//*[@formcontrolname='carClass']")
    WebElement inputSerialNumber;

    @FindBy(xpath = "//div/input[@ng-reflect-name='serialNumber']")
    WebElement inputCarRegistrationNumber;

    @FindBy(xpath = "//div/input[@id='price']")
    WebElement inputPricePerDay;

    @FindBy(xpath = "//textarea[@formcontrolname='about']")
    WebElement inputAreaAbout;

    @FindBy(xpath = "//input[@id='photos']")
    WebElement inputImage;

    public void typeNewCarForm(Car car) {
        inputLocation.sendKeys(car.getCity());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        chooseFuel(car.getFuel());
        //inputSeats.sendKeys(car.getSeats().toString());
        //inputSeats.sendKeys(String.valueOf(car.getSeats()));
        //inputSeats.sendKeys(car.getSeats() + "");
        inputSeats.sendKeys(Integer.toString(car.getSeats()));
        inputSerialNumber.sendKeys(car.getCarClass());
        inputPricePerDay.sendKeys(Double.toString(car.getPricePerDay()));
        inputCarRegistrationNumber.sendKeys(car.getSerialNumber());
        inputAreaAbout.sendKeys(car.getAbout());
    }

    public void chooseFuel(Fuel fuel) {
        inputFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();
    }

    public void downloadImage(String fileName) {
        inputImage.sendKeys(new File("src/test/resources/"
                + fileName).getAbsolutePath());
    }

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector" +
                "(\"button[type = 'submit']\").removeAttribute('disabled')");
        btnSubmit.click();
    }
}
