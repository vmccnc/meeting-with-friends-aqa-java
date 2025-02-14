package pages;

import dto.Registration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;
import wrappers.RegistrationInputWrapper;

@Log4j2
public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    private final By REGISTER_BUTTON = By.xpath("//button[@type='submit']");

    public RegistrationPage isPageOpened() {
        try {
            log.info("Checking if RegistrationPage is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON));
        } catch (TimeoutException e) {
            log.error("Login page did not open: " + e.getMessage());
        }
        waitForPageLoaded(driver);
        return this;
    }

    public RegistrationPage open() {
        log.info("Opening Registration Page");
        driver.get(baseURL + "registration");
        return this;
    }

    public RegistrationPage signup(Registration tiresInput) {
        log.info("Filling the registration form with provided data");
        log.info("Name: {}", tiresInput.getName());
        log.info("Surname: {}", tiresInput.getSurname());
        log.info("Username: {}", tiresInput.getUsername());
        log.info("Password: {}", tiresInput.getPassword());
        log.info("Birthdate: {}", tiresInput.getBirthdate());
        log.info("Email: {}", tiresInput.getEmail());
        log.info("Telephone: {}", tiresInput.getTelephone());
        log.info("Photo: {}", tiresInput.getPhoto());

        new RegistrationInputWrapper(driver, "Name").write(tiresInput.getName());
        new RegistrationInputWrapper(driver, "Surname").write(tiresInput.getSurname());
        new RegistrationInputWrapper(driver, "Username").write(tiresInput.getUsername());
        new RegistrationInputWrapper(driver, "Password").write(tiresInput.getPassword());
        new RegistrationInputWrapper(driver, "Birth date").write(tiresInput.getBirthdate());
        new RegistrationInputWrapper(driver, "Email").write(tiresInput.getEmail());
        new RegistrationInputWrapper(driver, "Telephone").write(tiresInput.getTelephone());
        new RegistrationInputWrapper(driver, "http://example.com/photo.jpg").write(tiresInput.getPhoto());

        log.info("Registration form filled successfully");
        return this;
    }

    public RegistrationPage clickSignUpButton(){
        log.info("Clicking on the Sign-Up button");
        click(REGISTER_BUTTON);
        return this;
    }
}
