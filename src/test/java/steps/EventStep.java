package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.EventPage;

@Log4j2
public class EventStep {

    private final EventPage eventPage;

    public EventStep(WebDriver driver, String baseURL) {
        this.eventPage = new EventPage(driver, baseURL);
        log.info("EventStep initialized with driver: {}", driver);
    }

    @Step("Opening the Event Page")
    public EventStep openEventPage() {
        eventPage.open();
        eventPage.isPageOpened();
        return this;
    }

    @Step("Clicking on the 'Map' button")
    public EventStep clickToOpenMap() {
        eventPage.clickToOpenMap();
        eventPage.waitMapToBeVisible();
        return this;
    }

    @Step("Setting geolocation: latitude {latitude}, longitude {longitude}")
    public EventStep setGeolocation(double latitude, double longitude, double accuracy) {
        eventPage.setGeolocation(latitude, longitude, accuracy);
        return this;
    }

    @Step("Clicking on the map with offset X: {offsetX}, Y: {offsetY}")
    public EventStep clickOnMap(int offsetX, int offsetY) {
        eventPage.clickOnMap(offsetX, offsetY);
        return this;
    }

    public boolean isMapDisplayed() {
        return eventPage.isMapDisplayed();
    }

    public boolean isMeetingsListDisplayed() {
        return eventPage.isMeetingsListDisplayed();
    }
}