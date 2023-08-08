package ru.yandex.praktikum.driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Webdriver {
     /*
    Переменные окружения:
    BROWSER_DRIVER - путь к яндексдрайверу
    YANDEX_BROWSER_PATH - путь к исполняемому файлу Яндекс браузера в системе
     */

    public static WebDriver startBrowser() {
        String browser = System.getProperty("browser");

        switch (browser) {
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return null;
    }

    private static WebDriver createYandexDriver() {

        System.setProperty("webdriver.chrome.driver", System.getenv("BROWSER_DRIVER"));
        ChromeOptions driver = new ChromeOptions();
        driver.setBinary(System.getenv("YANDEX_BROWSER_PATH"));
        return  null;
    }
}