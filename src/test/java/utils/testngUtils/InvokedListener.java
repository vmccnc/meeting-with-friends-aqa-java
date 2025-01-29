package utils.testngUtils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

import static driver.DriverCreation.getWebDriver;

public class InvokedListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (getWebDriver() != null && !testResult.isSuccess()) {
            Allure.addAttachment(testResult.getName(), new ByteArrayInputStream(((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES)));
        }
    }
}
