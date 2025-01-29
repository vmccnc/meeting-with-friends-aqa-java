package driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import propertyUtils.PropertyReader;

import java.time.Duration;

@Log4j2
public class DriverCreation {

    private final static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver startWebDriver(DriverTypes type) {
        if (webDriver.get() == null) {
            log.info("Starting WebDriver for browser type: {}", type);
            switch (type) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments(PropertyReader.getProperty("browser.option").split(";"));
                    webDriver.set(new ChromeDriver(chromeOptions));
                    break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments(PropertyReader.getProperty("browser.option").split(";"));
                    webDriver.set(new FirefoxDriver(firefoxOptions));
                    break;
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments(PropertyReader.getProperty("browser.option").split(";"));
                    webDriver.set(new EdgeDriver(edgeOptions));
                    break;
                default:
                    log.error("Unsupported browser type: " + type);
                    throw new IllegalArgumentException("Unsupported browser type: " + type);
            }
            webDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            log.info("WebDriver started successfully for browser type: {}", type);
        }
        return webDriver.get();
    }

    public static WebDriver getWebDriver() {
        log.info("Getting current WebDriver instance");
        return webDriver.get();
    }

    public static void quitWebDriver() {
        if (webDriver.get() != null) {
            log.info("Quitting WebDriver");
            webDriver.get().quit();
            webDriver.remove();
        }
    }
}