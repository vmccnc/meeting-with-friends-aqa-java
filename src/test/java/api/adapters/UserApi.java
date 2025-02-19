package api.adapters;

import api.models.user.CreateUserRq;
import api.adapters.base.BaseAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserApi extends BaseAPI {

    private static final String CREATE_USER_ENDPOINT = "/events/user";

    public Response createUser(CreateUserRq userRequest) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(userRequest))
                .post(CREATE_USER_ENDPOINT)
                .then()
                .extract()
                .response();
    }
}
