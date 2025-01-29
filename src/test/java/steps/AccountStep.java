package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;

@Log4j2
public class AccountStep {

    private final AccountPage accountPage;

    public AccountStep(WebDriver driver, String baseURL) {
        this.accountPage = new AccountPage(driver, baseURL);
        log.info("HomeStep initialized with driver: {}", driver);
    }

    @Step("Open the Account page")
    public boolean isAccountPageOpen() {
        log.info("Checking if Account page is open");
        try {
            boolean isOpened = accountPage.isPageOpened();
            if (isOpened) {
                log.info("Account page opened");
            } else {
                log.warn("Account page is not opened");
            }
            return isOpened;
        } catch (Exception e) {
            log.error("Failed to check if Account page is open: {}", e.getMessage());
            return false;
        }
    }
}
