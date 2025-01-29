package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class LoginTest extends BaseTest {

    @Test(testName = "Open home page and login", description = "Check if user can login from home page")
    @Description("Check if user can login from home page")
    public void checkValidLogin() {
        homePage.open()
                .isPageOpened()
                .clickSignInButton();
        loginPage.isPageOpened()
                .login(user, password);
        Assert.assertTrue(accountPage.isPageOpened(), "Account page did not open after login");
    }

    @Test(testName = "Open home page and login", description = "Check if user can login from home page")
    @Description("Check if user can login from home page")
    public void checkValidLogin1() {
        homeStep.openHomePage()
                .clickSignIn();
        loginStep.login(user, password);
        Assert.assertTrue(accountStep.isAccountPageOpen(), "Account page did not open after login");
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", password, "Неправильный email или пароль"},
                {user, "", "Неправильный email или пароль"},
                {user, "qwerty", "Неправильный email или пароль"}
        };
    }

    @Owner("Elizaveta Nikolaenya")
    @Test(dataProvider = "LoginData", testName = "Invalid login data", description = "Check that user cannot login" +
            " with invalid data")
    @Description("Negative login check")
    public void invalidLogin(String user, String password, String expectedMessage) {
        loginStep.openLoginPage()
                .enterEmail(user)
                .enterPassword(password)
                .submitLogin();
        loginStep.verifyErrorMessage(expectedMessage);
    }
}
