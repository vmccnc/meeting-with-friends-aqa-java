package tests;

import com.github.javafaker.Faker;
import dto.Registration;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class RegistrationTest extends BaseTest {

    Faker faker = new Faker();
    Registration registration = Registration.builder()
            .name("testAQA" + faker.name().firstName())
            .surname(faker.name().lastName())
            .username(faker.name().username())
            .password(faker.internet().password())
            .birthdate("01.02.2003")
            .email(faker.internet().emailAddress())
            .telephone("+375221112211")
            .photo("https://parnis.co.il/wp-content/uploads/2020/03/Test-Logo-Small-Black-transparent-1.png")
            .build();

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Create a new account", description = "Verify that a new account can be created with valid data")
    @Description("Creating a new account with valid data")
    public void checkCreateNewAccount() {
        registrationStep.create(registration);

        boolean isLoginPageOpened = loginPage.isPageOpenedAfterRegistration();

        Assert.assertTrue(isLoginPageOpened, "Login page was not opened after account creation.");
    }
}
