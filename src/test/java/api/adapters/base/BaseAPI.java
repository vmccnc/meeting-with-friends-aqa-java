package api.adapters.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaseAPI {

    protected String user = Config.getUser();
    protected String password = Config.getPassword();
    protected String validToken;
    protected String refreshToken;

    public static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public BaseAPI() {
        RestAssured.baseURI = "https://q11.jvmhost.net";
    }

    public void authenticate() {
        Response loginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"" + user + "\", \"password\": \"" + password + "\" }")
                .log().all()
                .when()
                .post("/events/auth")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        validToken = loginResponse.path("accessToken");
        refreshToken = loginResponse.path("refreshToken");
    }

    public Response makeGetRequest(String endpoint, String token) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
}
