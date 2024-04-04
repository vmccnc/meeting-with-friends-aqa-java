package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import propertyUtils.PropertyReader;

import java.time.Duration;

public class DriverCreation {

    private final static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static void startWebDriver(DriverTypes type) {
        if(webDriver.get() == null) {
            switch (type) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments(PropertyReader.getProperties().getProperty("browser.option").split(";"));
                    webDriver.set(new ChromeDriver(chromeOptions));
                    break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments(PropertyReader.getProperties().getProperty("browser.option").split(";"));
                    webDriver.set(new FirefoxDriver(firefoxOptions));
                    break;
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments(PropertyReader.getProperties().getProperty("browser.option").split(";"));
                    webDriver.set(new EdgeDriver(edgeOptions));
                    break;
            }
            webDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        }

    }

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static void quitWebDriver() {
        if(webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }

}
