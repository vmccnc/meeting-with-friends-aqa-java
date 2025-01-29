package tests.base;

import driver.DriverTypes;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import propertyUtils.PropertyReader;
import steps.AccountStep;
import steps.HomeStep;
import steps.LoginStep;
import testngUtils.TestListener;

import static driver.DriverCreation.quitWebDriver;
import static driver.DriverCreation.startWebDriver;
import static driver.DriverTypes.CHROME;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected LoginStep loginStep;
    protected HomeStep homeStep;
    protected AccountPage accountPage;
    protected AccountStep accountStep;

    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));
    protected String baseURL = System.getProperty("baseURL", PropertyReader.getProperty("baseURL"));

    @Parameters("browser")
    @BeforeTest(alwaysRun = true)
    @Description("Opening browser")
    protected void setUp() {
        log.info("Starting browser");
        driver = startWebDriver(System.getProperties().containsKey("config")
                ? DriverTypes.valueOf(PropertyReader.getProperty("browser").toUpperCase())
                : CHROME
        );
        loginPage = new LoginPage(driver, baseURL);
        homePage = new HomePage(driver, baseURL);
        loginStep = new LoginStep(driver, baseURL);
        homeStep = new HomeStep(driver, baseURL);
        accountPage = new AccountPage(driver, baseURL);
        accountStep = new AccountStep(driver, baseURL);
        log.info("Browser started successfully");
    }

    @AfterTest(alwaysRun = true)
    @Description("Closing browser")
    public void tearDown() {
        log.info("Closing browser");
        quitWebDriver();
        log.info("Browser closed successfully");
    }
}