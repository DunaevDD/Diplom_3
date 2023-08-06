package ru.yandex.praktikum;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.driver.Webdriver;
import ru.yandex.praktikum.model.GeneralPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@Feature("Переход в личный кабинет")
public class OpenPersonalAccountTest {
    private GeneralPage generalPage;

    @Before
    public void setUp() {
        new Webdriver().startBrowser();
        generalPage = open(Url.URL_BASE, GeneralPage.class);
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет».")
    public void checkOpenPersonalAccount() {
        boolean expected = generalPage.clickHeaderAccountButton()
                .returnTrueIfOpenLogInPage();

        assertTrue(expected);
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}
