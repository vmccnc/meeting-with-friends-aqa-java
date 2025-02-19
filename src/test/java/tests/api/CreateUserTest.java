package tests.api;

import api.adapters.UserApi;
import api.models.user.CreateUserRq;
import api.adapters.base.BaseAPI;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseAPI {

    private UserApi userApi;
    private Faker faker;

    @BeforeMethod
    public void setUp() {
        userApi = new UserApi();
        faker = new Faker();
    }

    @Test
    public void shouldCreateUserSuccessfully() {
        CreateUserRq newUser = CreateUserRq.builder()
                .name("apitestaqa" + faker.name().firstName())
                .lastName(faker.name().lastName())
                .username(faker.name().username())
                .birthdate(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .email("apitestaqa" + faker.internet().emailAddress())
                .password(faker.internet().password())
                .phone(faker.phoneNumber().phoneNumber())
                .photoUrl(faker.internet().avatar())
                .build();

        Response response = userApi.createUser(newUser);

        response.then()
                .log().all()
                .statusCode(201)//должна быть 200
                .body("name", equalTo(newUser.getName()))
                .body("lastName", equalTo(newUser.getLastName()))
                .body("username", equalTo(newUser.getUsername()))
                .body("birthdate", equalTo(newUser.getBirthdate()))
                .body("email", equalTo(newUser.getEmail()))
                .body("phone", equalTo(newUser.getPhone()))
                .body("photoUrl", equalTo(newUser.getPhotoUrl()));
    }

    @Test
    public void shouldReturnConflictWhenCreatingUserWithExistingEmail() {

        String uniqueEmail = "apitestaqa" + faker.internet().emailAddress();


        CreateUserRq existingUser = CreateUserRq.builder()
                .name("apitestaqa" + faker.name().firstName())
                .lastName(faker.name().lastName())
                .username(faker.name().username())
                .birthdate(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .email(uniqueEmail)
                .password(faker.internet().password())
                .phone(faker.phoneNumber().phoneNumber())
                .photoUrl(faker.internet().avatar())
                .build();

        userApi.createUser(existingUser);

        CreateUserRq newUser = CreateUserRq.builder()
                .name("apitestaqa" + faker.name().firstName())
                .lastName(faker.name().lastName())
                .username(faker.name().username())
                .birthdate(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .email(existingUser.getEmail())
                .password(faker.internet().password())
                .phone(faker.phoneNumber().phoneNumber())
                .photoUrl(faker.internet().avatar())
                .build();

        Response response = userApi.createUser(newUser);

        response.then()
                .log().all()
                .statusCode(409)
                .body("message", equalTo("Conflict"));
    }
}