package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.util.Optional;

@Log4j2
public class EventPage extends BasePage {

    private final By MAP_BUTTON = By.xpath("//button[@class='button small']");
    private final By MAP = By.xpath("//div[contains(@class, 'leaflet-container')]");
    private final By MEETINGS_LIST = By.xpath("//div[text()='Meetings close to you']");

    public EventPage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public EventPage isPageOpened() {
        try {
            log.info("Checking if EventPage is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(MAP_BUTTON));
        } catch (TimeoutException e) {
            log.error("Event page did not open: {}", e.getMessage());
        }
        waitForPageLoaded(driver);
        return this;
    }

    public EventPage open() {
        log.info("Opening EventPage");
        driver.get(baseURL + "nearby");
        return this;
    }

    public EventPage clickToOpenMap() {
        log.info("Click on button 'map' to open map");
        click(MAP_BUTTON);
        return this;
    }

    public EventPage waitMapToBeVisible() {
        log.info("Waiting for map to be visible");
        waitUntilElementBeVisible(MAP);
        return this;
    }

    public void setGeolocation(double latitude, double longitude, double accuracy) {
        log.info("Setting geolocation to latitude: {}, longitude: {}", latitude, longitude);
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(latitude),
                Optional.of(longitude),
                Optional.of(accuracy)
        ));
    }

    public void clickOnMap(int offsetX, int offsetY) {
        log.info("Clicking on the map at offset X: {}, Y: {}", offsetX, offsetY);
        WebElement mapElement = driver.findElement(MAP);
        actions.moveToElement(mapElement).moveByOffset(offsetX, offsetY).click().perform();
    }

    public boolean isMapDisplayed() {
        try {
            boolean isDisplayed = driver.findElement(MAP).isDisplayed();
            log.info("Map element is displayed: {}", isDisplayed);
            return isDisplayed;
        } catch (NoSuchElementException e) {
            log.error("Map element not found: {}", e.getMessage());
            return false;
        }
    }

    public boolean isMeetingsListDisplayed() {
        try {
            boolean isDisplayed = driver.findElement(MEETINGS_LIST).isDisplayed();
            waitUntilElementBeVisible(MEETINGS_LIST);
            log.info("Meetings list element is displayed: {}", isDisplayed);
            return isDisplayed;
        } catch (NoSuchElementException e) {
            log.error("Meetings element not found: {}", e.getMessage());
            return false;
        }
    }
}