package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {

    LoginPage loginPage;
    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        User user = User.builder()
                .username("ldfffasv55@qwer.com")
                .password("Olgacv345!")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

        Assert.assertTrue(new LoginPage(getDriver())
                .validateTextInMessageLoggedInIsRequired("Logged in"));
    }

    @Test
    public void  loginNegativeEmptyAllFieldsTest() {
        User user = User.builder()
                .username("")
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
    }

    @Test
    public void  loginNegativeEmptyEmailFieldTest() {
        User user = User.builder()
                .username("")
                .password("Olgacv345!")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

        Assert.assertTrue(new LoginPage(getDriver())
                .validateTextInMessageEmailIsRequired("Email is required"));
    }

    @Test
    public void  loginNegativeEmptyPasswordFieldTest() {
        User user = User.builder()
                .username("ldfffasv55@qwer.com")
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

        Assert.assertTrue(new LoginPage(getDriver())
                .validateTextInMessagePasswordIsRequired("Password is required"));
    }
}
