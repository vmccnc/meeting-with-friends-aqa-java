package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import static org.testng.Assert.assertEquals;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_INPUT = By.xpath("//input[@type='email']");
    private final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    private final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");
    private final By ERROR_MESSAGE = By.xpath("//p[@class='error']");

    public LoginPage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public LoginPage isPageOpened() {
        try {
            log.info("Checking if LoginPage is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException e) {
            log.error("Login page did not open: {}", e.getMessage());
        }
        waitForPageLoaded(driver);
        return this;
    }

    public LoginPage open() {
        log.info("Opening LoginPage");
        driver.get(baseURL + "signIn");
        return this;
    }

    public LoginPage login(String userName, String password) {
        log.info("Logging in using credentials '{}', '{}'", userName, password);
        enter(USERNAME_INPUT, userName);
        enter(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
        return this;
    }

    public LoginPage enterEmail(String userName) {
        log.info("Entering email");
        enter(USERNAME_INPUT, userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        log.info("Entering password");
        enter(PASSWORD_INPUT, password);
        return this;
    }

    public LoginPage submitLogin() {
        log.info("Logging in using credentials");
        click(LOGIN_BUTTON);
        return this;
    }

    public void verifyErrorMessage(String expectedMessage) {
        log.info("Verifying error message: {}", expectedMessage);
        String actualMessage = getElementText(ERROR_MESSAGE);
        assertEquals(actualMessage, expectedMessage, "Error message does not match expected.");
    }
}
