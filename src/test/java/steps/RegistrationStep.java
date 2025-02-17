package steps;

import dto.Registration;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.RegistrationPage;

@Log4j2
public class RegistrationStep {

    private final HomePage homePage;
    @Getter
    private final RegistrationPage registrationPage;
    private final WebDriver driver;
    private final String baseURL;

    public RegistrationStep(WebDriver driver, String baseURL) {
        this.driver = driver;
        this.homePage = new HomePage(driver, baseURL);
        this.registrationPage = new RegistrationPage(driver, baseURL);
        this.baseURL = baseURL;
        log.info("LoginStep initialized with driver: {}", driver);
    }

    @Step("Open home page")
    public void openHomePage() {
        log.info("Opening home page");
        homePage.open();
        homePage.isPageOpened();
    }

    @Step("Click sign up button")
    public void clickSignUpButton() {
        log.info("Clicking sign up button");
        homePage.clickSignUpButton();
    }

    @Step("Sign up with contact registration details")
    public void signUp(Registration registration) {
        log.info("Signing up with email: {}", registration.getEmail());
        registrationPage.isPageOpened()
                .signup(registration);
    }

    @Step("Submit sign up form")
    public void submitSignUp() {
        log.info("Submitting sign up form");
        registrationPage.clickSignUpButton();
    }

    @Step("Create a new account")
    public void create(Registration registration) {
        log.info("Starting account creation for {}", registration.getEmail());
        openHomePage();
        clickSignUpButton();
        signUp(registration);
        submitSignUp();
    }
}
