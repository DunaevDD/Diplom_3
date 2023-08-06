package ru.yandex.praktikum;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.UserJson;
import ru.yandex.praktikum.driver.Webdriver;
import ru.yandex.praktikum.api.CreateUser;
import ru.yandex.praktikum.api.User;
import ru.yandex.praktikum.model.GeneralPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@Feature("Авторизация")
public class AuthorizationTest {
    private UserJson userJson;
    private GeneralPage generalPage;
    private String email;
    private String password;

    @Before
    public void setUp() {
        new Webdriver().startBrowser();
        User user = new User();
        userJson = CreateUser.generateUserAccount();
        user.createUser(userJson);
        generalPage = open(Url.URL_BASE, GeneralPage.class);
        email = userJson.getEmail();
        password = userJson.getPassword();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void checkSuccessLoginFromMainPageButtonLogIn() {
        generalPage.clickMainLogInButton()
                .inputEmailPasswordAndLogIn(email, password);

        assertTrue(generalPage.returnTrueIfCreateOrderButtonExist());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void checkSuccessLoginFromMainPageHeaderButtonAccount() {
        generalPage.clickHeaderAccountButton()
                .inputEmailPasswordAndLogIn(email, password);

        assertTrue(generalPage.returnTrueIfCreateOrderButtonExist());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void checkSuccessLoginFromRegistrationPageAccount() {
        generalPage.openRegisterPage()
                .clickHyperLinkLogIn()
                .inputEmailPasswordAndLogIn(email, password);

        assertTrue(generalPage.returnTrueIfCreateOrderButtonExist());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void checkSuccessLoginFromRestorePassword() {
        generalPage.openRestorePasswordPage()
                .clickHyperLogIn()
                .inputEmailPasswordAndLogIn(email, password);

        assertTrue(generalPage.returnTrueIfCreateOrderButtonExist());
    }

    @After
    public void tearDown() {
        closeWebDriver();
        User.deleteUserAccount(userJson);
    }
}
