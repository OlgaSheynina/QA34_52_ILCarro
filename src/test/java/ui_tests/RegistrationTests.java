package ui_tests;

import data_providers.UserDataProvider;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;
import utils.TestNGListener;

import static utils.UserFactory.*;

@Listeners(TestNGListener.class)

public class RegistrationTests extends AppManager {

    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationPage() {
        logger.info("Start registration test");
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void RegistrationPositiveTest() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxIAgree();
        registrationPage.clickBtnYalla();

        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void RegistrationPositiveWithJSTest() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxTermsOfUse();
        registrationPage.clickBtnYalla();

        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void RegistrationPositiveWithActionsTest() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();

        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationNegativeEmptyFieldsNoClickTest() {

        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();

        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Name is required"),
                "Name is required. Error not found");
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Last name is required"),
                "Last name is required. Error not found");
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Email is required"),
                "Email is required. Error not found");
        softAssert.assertTrue(registrationPage
                .isTextInErrorPresent("Password is required"),
                "Password is required. Error not found");
        softAssert.assertTrue(registrationPage
                .isBtnYallaEnabled(), "Btn Yalla is enabled");
        softAssert.assertAll();
    }

    @Test(dataProvider = "dataProviderForRegistrationWrongPasswordOrEmail",
            dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordTest(User user) {
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();

        Assert.assertTrue(registrationPage.isTextInErrorPresent
                ("Password must contain 1 uppercase letter, " +
                        "1 lowercase letter, 1 number and one " +
                        "special symbol of [@$#^&*!]"));
    }
}
