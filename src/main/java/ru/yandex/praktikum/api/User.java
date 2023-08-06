package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class User extends Req {

    public static final String ENDPOINT_LOGIN = "/auth/login";
    public static final String ENDPOINT_REGISTER = "/auth/register";
    public static final String ENDPOINT_USER = "/auth/user";

    private String bearerToken = "";

    @Step("АПИ Авторизация")
    public Response authorization(UserJson body) {
        Response response = reqSpec
                .body(body)
                .post(ENDPOINT_LOGIN);
        extractToken(response);
        return response;
    }

    @Step("АПИ Создание пользователя")
    public Response createUser(UserJson json) {
        Response response = reqSpec
                .body(json)
                .post(ENDPOINT_REGISTER);
        extractToken(response);
        return response;
    }

    @Step("АПИ Очистка токена")
    public void clearAuthToken() {
        bearerToken = "";
    }

    @Step("АПИ получение токена")
    private void extractToken(Response response) {
        if (response.statusCode() == 200) {
            bearerToken = response.then().extract().body().path("accessToken");
        } else {
            clearAuthToken();
        }
    }

    @Step("АПИ Пользователь удалён")
    public void deleteUser() {
        reqSpec.header("Authorization", bearerToken)
                .delete(ENDPOINT_USER);
    }

    @Step("Удаление пользователя с почтой {userJson.email} и паролем {userJson.password}")
    public static void deleteUserAccount(UserJson userJson) {
        User user = new User();
        Response responseAuth = user.authorization(userJson);
        if (responseAuth.statusCode() == 200) {
            user.deleteUser();
        } else {
            System.out.println("Пользователь создан не был");
        }
    }
}