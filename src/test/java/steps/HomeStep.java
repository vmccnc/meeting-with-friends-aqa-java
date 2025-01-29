package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

@Log4j2
public class HomeStep {

    private final HomePage homePage;

    public HomeStep(WebDriver driver, String baseURL) {
        this.homePage = new HomePage(driver, baseURL);
        log.info("HomeStep initialized with driver: {}", driver);
    }

    @Step("Open the Home page")
    public HomeStep openHomePage() {
        log.info("Attempting to open Home page");
        try {
            homePage.open()
                    .isPageOpened();
            log.info("Home page opened successfully");
        } catch (Exception e) {
            log.error("Failed to open Home page: {}", e.getMessage());
            throw e;
        }
        return this;
    }

    @Step("Click the Sign In button")
    public HomeStep clickSignIn() {
        log.info("Attempting to click Sign In button on Home page");
        try {
            homePage.clickSignInButton();
            log.info("Sign In button clicked successfully");
        } catch (Exception e) {
            log.error("Failed to click Sign In button: {}", e.getMessage());
            throw e;
        }
        return this;
    }
}

//    @Step("Change language to English")
//    public HomeStep changeLanguage() {
//        log.info("Changing language");
//        try {
//            homePage.changeLanguage();
//            log.info("Language changed");
//        } catch (Exception e) {
//            log.error("Failed to change language", e.getMessage());
//            throw e;
//        }
//        return this;
//    }
