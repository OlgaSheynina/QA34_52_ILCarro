package ui_tests;

import dto.Car;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.CarFactory;
import utils.TestNGListener;
import utils.enums.HeaderMenu;

import java.time.LocalDate;

import static utils.PropertiesReader.getProperty;
import static utils.CarFactory.*;

@Listeners(TestNGListener.class)

public class AddNewCarTests extends AppManager {

    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void goToLetTheCarWorkPage() {

        //new HomePage(getDriver()).clickBtnLogin();
        //loginPage = new LoginPage(getDriver());
        loginPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LOGIN);

        User user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        new PopUpPage(getDriver()).clickBtnOk();
        letTheCarWorkPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);
    }

    @Test
    public void addNewCarPositiveTest() {
        Car car = positiveCar();
        System.out.println(car);

        letTheCarWorkPage.typeNewCarForm(car);
        letTheCarWorkPage.downloadImage("cat2.jpg");
        letTheCarWorkPage.clickBtnSubmitWithJS();

        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent
                        ("{\"city\":\"must not be blank\"}"));
    }

    @Test
    public void addNewCarNegativeLineAnyFieldErrorMessageTest() {
        Car car = positiveCar();
        car.setFuel(null);
        System.out.println(car);

        letTheCarWorkPage.typeNewCarForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();

        Assert.assertTrue(letTheCarWorkPage
                .isTextInErrorPresent("Fuel is required"),
                " text is not present");
    }

    @Test
    public void addNewCarNegativeWrongYearTest() {
        Car car = positiveCar();
        car.setYear(String.valueOf(LocalDate.now().getYear() + 1));
        System.out.println(car);

        letTheCarWorkPage.typeNewCarForm(car);
        letTheCarWorkPage.downloadImage("cat2.jpg");

        Assert.assertTrue(letTheCarWorkPage
                .isTextInErrorPresent("Wrong year"));
    }

    @Test
    public void addNewCarNegativeWrongYearNoDigitTest() {
        Car car = positiveCar();
        car.setYear("p");
        System.out.println(car);

        letTheCarWorkPage.typeNewCarForm(car);
        letTheCarWorkPage.downloadImage("cat2.jpg");

        Assert.assertTrue(letTheCarWorkPage
                .isTextInErrorPresent("Year required"));
    }

    @DataProvider(name = "invalidYearsSingleArray")
    public Object[] invalidYearsData() {
        return new Object[]{"v", "A", " ", "%", "שלום", "555Y"};
    }
    @Test(dataProvider = "invalidYearsSingleArray")
    public void addNewCarNegativeWrongYearTest(String invalidYear) {
        Car car = positiveCar();
        car.setYear(invalidYear);

        letTheCarWorkPage.typeNewCarForm(car);
        letTheCarWorkPage.downloadImage("cat2.jpg");

        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Year required"));
    }
}













