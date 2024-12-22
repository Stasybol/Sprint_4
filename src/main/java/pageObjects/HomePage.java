package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    // константа url страницы
    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    // локатор для кнопки "Заказать" вверху экрана
    private static final By ORDER_BUTTON_ABOVE = By.className("Button_Button__ra12g");
    // локатор для кнопки "Заказать" внизу экрана
    private static final By ORDER_BUTTON_BELOW = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    // локатор для заголовка блоков аккардеона
    private static final By ACCORDION_ITEM_HEADING = By.className("accordion__heading");
    // локатор для выпадающего списка блоков аккордеона
    private static final By ACCORDION_ITEM_PANEL = By.xpath(".//div[@class='accordion__panel']/p");
    // локатор для кук
    private static final By COOKIES_BUTTON = By.className("App_CookieButton__3cvqF");

    // драйвер
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // метод для нажатия на кнопку "Заказать" вверху экрана
    public HomePage clickOrderButtonAbove() {
        driver.findElement(ORDER_BUTTON_ABOVE).click();
        return this;
    }

    // метод скролла и нажатия на кнопку "Заказать" внизу экрана
    public HomePage clickOrderButtonBelow() {
        WebElement element = driver.findElement(ORDER_BUTTON_BELOW);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(ORDER_BUTTON_BELOW).click();
        return this;
    }

    // метод открытия сайта
    public HomePage open() {
        driver.get(PAGE_URL);
        return this;
    }

//    // метод нахождения всплывающего окна о принятии кук
//    public boolean isCookiesButton() {
//        driver.findElement(COOKIES_BUTTON).isEnabled();
//        return true;
//    }

    // метод нажатия на кнопку "да все привыкли" для закрытия всплывающего окна о принятии кук
    public HomePage clickCookiesButton() {
        driver.findElement(COOKIES_BUTTON).click();
        return this;
    }

    // метод скролла и возврата текста из заголовков аккордеона
    public String textAccordionHeading(int number) {
        WebElement element = driver.findElements(ACCORDION_ITEM_HEADING).get(number);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        return this.driver.findElements(ACCORDION_ITEM_HEADING).get(number).getText();
    }

    // метод открытия аккордеонной панели
    public HomePage enterAccordionPanel(int number) {
        driver.findElements(ACCORDION_ITEM_HEADING).get(number).click();
        return this;
    }
    // метод ожидания и возврата текста в аккордеонной панели
    public String openAccordionPanel(int number) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElements(ACCORDION_ITEM_PANEL).get(number)));
        return this.driver.findElements(ACCORDION_ITEM_PANEL).get(number).getText();
    }


}
