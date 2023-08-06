package ru.yandex.praktikum.api;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

public class CreateUser {
    private static String email;
    private static String password;
    private static String name;
    private static final Faker faker = new Faker();

    private static void createUserData() {
        generateEmail();
        generatePassword();
        generateName();
    }

    @Step("Генерация данных пользователя")
    public static UserJson generateUserAccount() {
        createUserData();
        return new UserJson(email, password, name);
    }

    public static void generateEmail() {
        email = faker.internet().emailAddress();
    }

    public static void generatePassword() {
        password = faker.internet().password();
    }

    public static void generateName() {
        name = faker.name().username();
    }
}
