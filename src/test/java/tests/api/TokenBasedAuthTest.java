package tests.api;

import api.adapters.base.BaseAPI;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class TokenBasedAuthTest extends BaseAPI {

    private static final String USER_ENDPOINT = "/events/user/";

    @BeforeMethod
    public void setUp() {
        authenticate();
    }

    @Test
    public void shouldFetchUserByIdSuccessfully() {
        int userId = 188;

        Response response = makeGetRequest(USER_ENDPOINT + userId, validToken);

        response.then()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", equalTo("TestAQA"))
                .body("lastName", equalTo("Elizaveta"))
                .body("username", equalTo("TestAQA"))
                .body("birthdate", equalTo("1996-07-09"));
    }
}
