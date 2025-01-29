package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

@Log4j2
public class AccountPage extends BasePage {

    private final By SIGN_OUT_BUTTON = By.xpath("//a[contains(.,'Sign out')]");

    public AccountPage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public boolean isPageOpened() {
        try {
            log.info("Checking if AccountPage is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(SIGN_OUT_BUTTON));
            waitForPageLoaded(driver);
            return true;
        } catch (TimeoutException e) {
            log.error("Account page did not open: {}", e.getMessage());
            return false;
        }
    }
}
