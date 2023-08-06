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
import ru.yandex.praktikum.model.GeneralPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@Feature("Выход из аккаунта")
public class LogOutTest {
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
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void checkSuccessLoginFromMainPageButtonLogIn() {
        boolean expected = generalPage.openLoginPage()
                .inputEmailPasswordAndLogIn(email, password)
                .clickHeaderAccountButton()
                .clickLogOutButton()
                .returnTrueIfOpenLogInPage();

        assertTrue(expected);
    }

    @After
    public void tearDown() {
        closeWebDriver();
        User.deleteUserAccount(userJson);
    }
}
