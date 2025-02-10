package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class EventMapTest extends BaseTest {

    @Owner("Elizaveta Nikolaenya")
    @Test(testName = "Open Street Map Test", description = "This test verifies the functionality of opening the street " +
            "map and checking its visibility.")
    @Description("The test logs into the application, sets the geolocation, opens the event page, clicks on the map," +
            " and asserts that the map is displayed.")
    public void checkMapMarker() {
        homeStep.openHomePage()
                .clickSignIn();

        loginStep.login(user, password);

        eventStep
                .setGeolocation(52.52437, 13.41053, 1)
                .openEventPage()
                .clickToOpenMap()
                .clickOnMap(100, 100);

        Assert.assertTrue(eventStep.isMapDisplayed(), "Map is not displayed.");
        Assert.assertTrue(eventStep.isMeetingsListDisplayed(), "Meetings list is not displayed");
    }
}