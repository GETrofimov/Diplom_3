package api.client;

import api.client.constants.BaseParams;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import api.client.user.body.User;

import static api.client.constants.StatusCodes.*;
import static api.client.user.UserClient.*;
import static api.client.user.constants.Credentials.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class BaseTest {
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
        user.setEmail(EMAIL);
        user.setName(NAME);
        user.setPassword(PASSWORD);
        Response create = sendCreateUserRequest(user);
        user.setAccessToken(create.then().extract().body().path("accessToken"));
    }
}
