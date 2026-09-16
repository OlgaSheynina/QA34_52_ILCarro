package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//a[@href='/login?url=%2Fsearch']")
    WebElement btnLogin;

    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUp;

    @FindBy(xpath = "//input[@id='city']")
    WebElement inputCity;

    @FindBy(xpath = "//input[@id='dates']")
    WebElement inputDates;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearOnCalendar;


    public void clickBtnSignUp() {
        btnSignUp.click();
    }

    public void clickBtnLogin() {
        btnLogin.click();
    }

    public void typeSearchForm(String city, LocalDate startDate,
                               LocalDate endDate) {
        inputCity.sendKeys(city);
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(startDate.getMonthValue());
        System.out.println(startDate.getDayOfMonth());
        String dates =
                startDate.getMonthValue() + "/"
                        + startDate.getDayOfMonth() + "/"
                        + startDate.getYear() + " - "
                        + endDate.getMonthValue() + "/"
                        + endDate.getDayOfMonth() + "/"
                        + endDate.getYear();
        System.out.println(dates);
        inputDates.sendKeys(dates);
    }

    public void typeSearchFormWithCalendar(String city, LocalDate startDate,
                                           LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);
        typeCalendar(endDate);
    }

    private void typeCalendar(LocalDate date) {
        btnYearOnCalendar.click();
        // //td[@aria-label='2026']   "//td[@aria-label='"+year+"']"
        String year = Integer.toString(date.getYear());
        WebElement btnYear = driver.findElement
                (By.xpath("//td[@aria-label='" + year + "']"));
        btnYear.click();
        // //td[@aria-label='October 2026']
        String month = createMonth(date.getMonth().toString());
        System.out.println(month);
        // //td[@aria-label='October 2026']  "//td[@aria-label='"+month+" "+year"']"
        WebElement btnMonth = driver.findElement
                (By.xpath("//td[@aria-label='" + month + " " + year + "']"));
        btnMonth.click();
        // //td[@aria-label='September 16, 2026']
        System.out.println(date.getDayOfMonth());
        String day = String.valueOf(date.getDayOfMonth());
        WebElement btnDay = driver.findElement(By.xpath
                ("//td[@aria-label='" + month + " " + day + ", " + year + "']"));
        btnDay.click();
    }

    // SEPTEMBER --> September
    private String createMonth(String month) {
        return new StringBuilder()
                .append(month.substring(0, 1).toUpperCase())
                .append(month.substring(1).toLowerCase())
                .toString();
    }

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector" +
                "(\"button[type = 'submit']\").removeAttribute('disabled')");
        btnYalla.click();
    }
}











