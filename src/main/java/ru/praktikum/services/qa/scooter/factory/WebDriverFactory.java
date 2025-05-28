package ru.praktikum.services.qa.scooter.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Locale;
import java.util.Optional;


public class WebDriverFactory {
    private static final String DEFAULT_BROWSER = "YANDEX";
    private static final String YANDEX_BROWSER_PATH_ENV = "YANDEX_BROWSER_PATH";

    public static WebDriver createDriver() {
        String browserName = Optional.ofNullable(System.getenv("BROWSER_NAME"))
                .orElse(DEFAULT_BROWSER)
                .toUpperCase(Locale.ROOT);

        return createForName(browserName);
    }

    private static WebDriver createForName(String browserName) {
        switch (browserName) {
            case "CHROME":
                return createChromeDriver();
            case "YANDEX":
                return createYandexDriver();
            case "FIREFOX":
                return createFirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver createYandexDriver() {
        String yandexPath = Optional.ofNullable(System.getenv(YANDEX_BROWSER_PATH_ENV))
                .orElseThrow(() -> new RuntimeException("Yandex browser path not set in environment variables"));

        WebDriverManager.chromedriver().driverVersion("134").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexPath);
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}