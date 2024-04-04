package pageobject.baseobject;

import driver.DriverTypes;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import testngUtils.InvokedListener;
import testngUtils.Listener;

import java.lang.reflect.InvocationTargetException;

import static driver.DriverCreation.quitWebDriver;
import static driver.DriverTypes.CHROME;
import static propertyUtils.PropertyReader.*;

import static driver.DriverCreation.startWebDriver;

@Listeners({InvokedListener.class, Listener.class})
public abstract class BaseTest {

    @BeforeTest
    protected void setUp() {
        startWebDriver(System.getProperties().containsKey("config")
                    ? DriverTypes.valueOf(getProperties().getProperty("browser").toUpperCase())
                    : CHROME
        );
    }

    protected <T> T get(Class<T> page) {
        T instance;
        try {
            instance = page.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e ) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @AfterTest
    public void tearDown() {
        quitWebDriver();
    }

}
