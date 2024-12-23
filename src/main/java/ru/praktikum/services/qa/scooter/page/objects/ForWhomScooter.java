package ru.praktikum.services.qa.scooter.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForWhomScooter {
    // локатор для страницы "Для кого самокат"
    private static final By PAGE_FOR_WHOM_SCOOTER = By.xpath(".//div[@class='Order_Header__BZXOb']");
    // локатор поля "Имя"
    private static final By FIELD_NAME = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    // локатор поля "Фамилия"
    private static final By FIELD_SURNAME = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    // локатор поля "Адрес"
    private static final By FIELD_ADDRESS = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    // локатор поля "Станция метро"
    private static final By FIELD_METRO_STATION = By.xpath(".//input[contains(@placeholder, 'Станция')]");
    // локатор выбора станции метро
    private static final By CHOICE_METRO_STATION = By.cssSelector(".Order_SelectOption__82bhS.select-search__option");
    // локатор поля "Телефон"
    private static final By FIELD_PHONE = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    // локатор кнопки "Далее"
    private static final By NEXT_BUTTON = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    // драйвер
    private WebDriver driver;

    public ForWhomScooter(WebDriver driver) {
        this.driver = driver;
    }

    // метод загрузки страницы "Для кого самокат"
    public ForWhomScooter loadingPageForWhomScooter(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(PAGE_FOR_WHOM_SCOOTER)));
        driver.findElement(PAGE_FOR_WHOM_SCOOTER).isDisplayed();
        return this;
    }
    // метод для заполнения полей "Имя", "Фамилия", "Адрес", "Станция метро" и "Телефон"
    public ForWhomScooter fillingInFieldsOnPageForWhomScooter(String name, String surname, String address, String metro, String phone){
        driver.findElement(FIELD_NAME).click();
        driver.findElement(FIELD_NAME).sendKeys(name);
        driver.findElement(FIELD_SURNAME).click();
        driver.findElement(FIELD_SURNAME).sendKeys(surname);
        driver.findElement(FIELD_ADDRESS).click();
        driver.findElement(FIELD_ADDRESS).sendKeys(address);
        driver.findElement(FIELD_METRO_STATION).click();
        driver.findElement(FIELD_METRO_STATION).sendKeys(metro);
        driver.findElement(CHOICE_METRO_STATION).click();
        driver.findElement(FIELD_PHONE).click();
        driver.findElement(FIELD_PHONE).sendKeys(phone);
        return this;
    }

    // метод нажатия на кнопку "Далее"
    public ForWhomScooter clickNextButton(){
        driver.findElement(NEXT_BUTTON).click();
        return this;
    }
}


