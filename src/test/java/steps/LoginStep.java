package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

@Log4j2
public class LoginStep {

    private final LoginPage loginPage;
    private final HomePage homePage;
    private final WebDriver driver;
    private final String baseURL;

    public LoginStep(WebDriver driver, String baseURL) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver, baseURL);
        this.homePage = new HomePage(driver, baseURL);
        this.baseURL = baseURL;
        log.info("LoginStep initialized with driver: {}", driver);
    }

    @Step("Log in to the application with valid credentials - username: {userName}, password: {password}.")
    public HomeStep login(String userName, String password) {
        log.info("Starting login process for user: {}", userName);
        try {
            loginPage.isPageOpened()
                    .login(userName, password);
            log.info("Login process completed for user: {}", userName);
        } catch (Exception e) {
            log.error("Login process failed for user: {}. Error: {}", userName, e.getMessage());
            throw e;
        }
        return new HomeStep(driver, baseURL);
    }

    @Step("Open login page")
    public LoginStep openLoginPage() {
        loginPage.open();
        return this;
    }

    @Step("Entering email: {userName}")
    public LoginStep enterEmail(String userName) {
        loginPage.enterEmail(userName);
        return this;
    }

    @Step("Entering password")
    public LoginStep enterPassword(String password) {
        loginPage.enterPassword(password);
        return this;
    }

    @Step("Submitting login")
    public HomeStep submitLogin() {
        loginPage.submitLogin();
        return new HomeStep(driver, baseURL);
    }

    @Step("Verifying error message: {expectedMessage}")
    public void verifyErrorMessage(String expectedMessage) {
        loginPage.verifyErrorMessage(expectedMessage);
    }
}
