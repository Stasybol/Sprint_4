package ru.praktikum.services.qa.scooter.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class AboutRent {
    // локатор для страницы "Про аренду"
    private static final By PAGE_ABOUT_RENT = By.className("Order_Header__BZXOb");
    // локатор поля "Когда привезти самокат"
    private static final By FIELD_DATE = By.xpath(".//input[contains(@placeholder, 'Когда привезти')]");
    // локатор поля "Срок аренды"
    private static final By FIELD_TERM = By.xpath(".//span[@class='Dropdown-arrow']");
    // локатор выпадающего списка срока аренды
    private static final By PANEL_FIELD_TERM = By.className("Dropdown-option");
    // константа черного цвета
    private static final String COLOR_BLACK = "чёрный жемчуг";
    // локатор чекбокса черного цвета
    private static final By CHECKBOX_COLOR_BLACK = By.xpath(".//input[@id='black']");
    // локатор чекбокса серого цвета
    private static final By CHECKBOX_COLOR_GREY = By.xpath(".//input[@id='grey']");
    // локатор поля "Комментарий"
    private static final By FIELD_COMMENT = By.xpath(".//input[contains(@placeholder, 'Комментарий')]");
    // локатор для кнопки "Заказать"
    private static final By ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // локатор для кнопки "ДА"
    private static final By YES_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    // локатор текста "Заказ оформлен"
    private static final By ORDER_PLACED = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");
    private WebDriver driver;

    public AboutRent(WebDriver driver) {
        this.driver = driver;
    }

    // метод загрузки страницы "Про аренду"
    public AboutRent loadingPageAboutRent(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(PAGE_ABOUT_RENT)));
        driver.findElement(PAGE_ABOUT_RENT).isDisplayed();
        return this;
    }

    // метод заполнения поля "Когда привезти самокат", выбор срока и цвета, заполнения поля "Комментарий"
    public AboutRent fillingInFieldsOnPageAboutRent(String date, String term, String color, String comment){
        driver.findElement(FIELD_DATE).click();
        driver.findElement(FIELD_DATE).sendKeys(date);
        driver.findElement(FIELD_TERM).click();
        List<WebElement> elementsOption = driver.findElements(PANEL_FIELD_TERM);
        for (WebElement element : elementsOption) {
            if (element.getText().equals(term)) {
                element.click();
                break;
            }
        }
        if (Objects.equals(color, COLOR_BLACK)) {
            driver.findElement(CHECKBOX_COLOR_BLACK).click();
        }
        else {
            driver.findElement(CHECKBOX_COLOR_GREY).click();
        }
        driver.findElement(FIELD_COMMENT).click();
        driver.findElement(FIELD_COMMENT).sendKeys(comment);
        return this;
    }

    // метод нажатия на кнопку "Заказать"
    public AboutRent clickOrderButton(){
        driver.findElement(ORDER_BUTTON).click();
        return this;
    }

    // метод ожидания и нажатия на кнопку "Да"
    public AboutRent clickYesButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(YES_BUTTON)));
        driver.findElement(YES_BUTTON).click();
        return this;
    }

    // метод отображение окна "Заказ оформлен"
    public boolean displayedOrderPlaced(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(ORDER_PLACED)));
        return driver.findElement(ORDER_PLACED).isDisplayed();
    }

}
