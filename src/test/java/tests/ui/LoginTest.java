package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class LoginTest extends BaseTest {

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Open home page and login", description = "Check if user can login from home page")
    @Description("Check if user can login from home page")
    public void checkValidLogin() {
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
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "LoginData", testName = "Invalid login data", description = "Check that user cannot login" +
            " with invalid data")
    @Description("Negative login check")
    public void checkInvalidLogin(String user, String password, String expectedMessage) {
        loginStep.openLoginPage()
                .enterEmail(user)
                .enterPassword(password)
                .submitLogin();
        loginStep.verifyErrorMessage(expectedMessage);
    }
}
