package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class RegistrationInputWrapper {

    WebDriver driver;
    String label;

    public RegistrationInputWrapper(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        log.info("Writing '{}' into '{}'", text, label);
        driver.findElement(By.xpath(String.format(
                "//input[@placeholder='%s']", label))).sendKeys(text);
    }
}
