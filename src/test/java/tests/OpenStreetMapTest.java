package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OpenStreetMapTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testOpenStreetMap() {
        // Navigate to the page containing OpenStreetMap
        driver.get("https://meetings-management-pearl.vercel.app/nearby");

        // Wait for the map to load
        try {
            Thread.sleep(5000); // Adjust the sleep time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement map = driver.findElement(By.id("map")); // Adjust the locator as needed

        // Use JavaScript to center the map on London
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setView([51.5074, -0.1278], 13);", map); // Latitude and longitude of London

        // Wait for the map to update
        try {
            Thread.sleep(2000); // Adjust the sleep time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Perform the click action on the map
        int xOffset = 300; // Adjust the x offset as needed
        int yOffset = 200; // Adjust the y offset as needed
        Actions actions = new Actions(driver);
        actions.moveToElement(map, xOffset, yOffset).click().perform();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

