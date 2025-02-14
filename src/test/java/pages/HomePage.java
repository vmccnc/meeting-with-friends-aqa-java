package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

@Log4j2
public class HomePage extends BasePage {

    private final By SIGN_IN_HEADER_BUTTON = By.xpath("//a[@class='link signIn']");
    private final By SIGN_UP_HEADER_BUTTON = By.xpath("//a[@class='link registration']");

    public HomePage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public HomePage isPageOpened() {
        try {
            log.info("Checking if Home page is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN_HEADER_BUTTON));
        } catch (TimeoutException e) {
            log.error("Home page did not open: {}", e.getMessage());
            throw e;
        }
        return this;
    }

    public HomePage open() {
        log.info("Opening Home page");
        driver.get(baseURL);
        return this;
    }

    public HomePage clickSignInButton() {
        log.info("Clicking 'Sign In' button");
        click(SIGN_IN_HEADER_BUTTON);
        return this;
    }

    public HomePage clickSignUpButton() {
        log.info("Clicking 'Sign Up' button");
        click(SIGN_UP_HEADER_BUTTON);
        return this;
    }
//
//    public HomePage changeLanguage() {
//        log.info("Changing language to");
//        click(EN_LANGUAGE);
//        return this;
//    }
}
