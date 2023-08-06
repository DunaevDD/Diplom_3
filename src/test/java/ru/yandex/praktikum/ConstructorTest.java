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

@Feature("Раздел «Конструктор»")
public class ConstructorTest {

    GeneralPage generalPage;

    @Before
    public void setUp() {
        new Webdriver().startBrowser();
        generalPage = open(Url.URL_BASE, GeneralPage.class);
    }

    @Test
    @DisplayName("Переходы к разделу Булки")
    public void checkOpenSectionBun() {
        boolean expected = generalPage
                .clickConstructorBunButton()
                .returnTrueIfBunSectionIsDisplayed();

        assertTrue(expected);
    }

    @Test
    @DisplayName("Переходы к разделу Соусы")
    public void checkOpenSectionSauces() {
        boolean expected = generalPage
                .clickConstructorSaucesButton()
                .returnTrueIfSaucesSectionIsDisplayed();

        assertTrue(expected);
    }

    @Test
    @DisplayName("Переходы к разделу Начинки")
    public void checkOpenSectionFilling() {
        boolean expected = generalPage
                .clickConstructorFillingButton()
                .returnTrueIfFillingSectionIsDisplayed();

        assertTrue(expected);
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}
