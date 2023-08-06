package ru.yandex.praktikum;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.CreateUser;
import ru.yandex.praktikum.api.User;
import ru.yandex.praktikum.api.UserJson;
import ru.yandex.praktikum.driver.Webdriver;
import ru.yandex.praktikum.model.RegistrationPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@Feature("Регистрация")
public class RegistrationTest {

    private UserJson userJson;
    private RegistrationPage registrationPage;

    @Before
    public void setUp() {
        new Webdriver().startBrowser();
        userJson = CreateUser.generateUserAccount();
        registrationPage = open(Url.URL_REGISTRATION, RegistrationPage.class);
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void checkSuccessRegistration() {
        registrationPage
                .inputNameEmailPasswordAndRegister(userJson.getName(), userJson.getEmail(), userJson.getPassword());

        assertTrue(registrationPage.returnTrueIfRegistrationSuccess());
    }

    @Test
    @DisplayName("Проверка ошибки что пароль должен быть больше пяти знаков")
    public void checkReturnErrorIfShortPassword() {
        userJson.setPassword("12345");
        registrationPage
                .inputNameEmailPasswordAndRegister(userJson.getName(), userJson.getEmail(), userJson.getPassword());
        assertTrue(registrationPage.returnTrueIfShowShortPasswordError());

    }

    @After
    public void tearDown() {
        closeWebDriver();
        User.deleteUserAccount(userJson);
    }
}