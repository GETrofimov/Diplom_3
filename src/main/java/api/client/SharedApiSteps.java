package api.client;

import api.client.constants.BaseParams;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import api.client.user.body.User;

import static api.client.constants.BaseParams.BASE_CONTENT_TYPE_HEADER;
import static api.client.constants.BaseParams.BASE_CONTENT_TYPE_VALUE;
import static api.client.constants.StatusCodes.*;
import static api.client.user.UserClient.*;
import static api.client.user.constants.Routes.AUTH_USER;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SharedApiSteps {
    @Step("Set URL for test")
    public static void setUp() {
        RestAssured.baseURI = BaseParams.BASE_URI;
    }

    @Step("Удаление тестового пользователя")
    public static void deleteUser(User body) {
        Response response = sendDeleteUserRequest(body);
        response.then().assertThat().statusCode(ACCEPTED)
                .and()
                .body("message", equalTo("User successfully removed"))
                .body("success", equalTo(true));
    }

    @Step("Создание тестового пользователя")
    public static void createUser(User user) {
        Response create = sendCreateUserRequest(user);
        create.then().assertThat().statusCode(OK);
    }

    @Step
    public static String acquireToken(User body) {
        Response response =
                given()
                        .header(BASE_CONTENT_TYPE_HEADER, BASE_CONTENT_TYPE_VALUE)
                        .and()
                        .body(body)
                        .when()
                        .post(AUTH_USER);
        String token = response.then().extract().body().path("accessToken");
        return token;
    }
}
