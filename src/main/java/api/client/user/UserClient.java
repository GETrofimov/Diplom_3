package api.client.user;

import api.client.user.body.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.client.constants.BaseParams.*;
import static api.client.user.constants.Routes.*;
import static io.restassured.RestAssured.given;

public class UserClient {
    @Step("Cоздание пользователя")
    public static Response sendCreateUserRequest(User body) {
        Response response =
                given()
                        .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                        .and()
                        .body(body)
                        .when()
                        .post(CREATE_USER);
        return response;
    }

    @Step("Удаление пользователя")
    public static Response sendDeleteUserRequest(User body) {
        Response response =
                given()
                        .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                        .header(AUTHORIZATION, body.getAccessToken())
                        .and()
                        .when()
                        .delete(DELETE_USER);
        return response;
    }
}
