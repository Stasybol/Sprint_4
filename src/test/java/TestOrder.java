import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikum.services.qa.scooter.page.objects.AboutRent;
import ru.praktikum.services.qa.scooter.page.objects.ForWhomScooter;
import ru.praktikum.services.qa.scooter.page.objects.HomePage;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class TestOrder {
    private static final String DEFAULT_BROWSER_NAME = "CHROME";
    private static final String BROWSER_NAME_ENV_VARIABLE = "BROWSER_NAME";
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String metro;
    private final String date;
    private final String term;
    private final String color;
    private final String comment;

    private WebDriver driver;

    public TestOrder(String name, String surname, String address, String metro, String phone, String date, String term, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.term = term;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"Ксения", "Браун", "ул. Мира, д. 14", "Лубянка", "89991234567", "01.01.2025", "сутки", "чёрный жемчуг", "Не звонить"},
                {"Ольга", "Иванова", "пр-кт Ленина, д. 100", "Аэропорт", "89997654321", "31.12.2024", "семеро суток", "серая безысходность", "Дверной звонок не работает"}
        };
    }

    @Before
    public void before() {
        String browserName = System.getenv(BROWSER_NAME_ENV_VARIABLE);
        driver =
                WebDriverFactory.createForName(browserName != null ? browserName : DEFAULT_BROWSER_NAME);
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickCookiesButton();
    }

    @Test
    public void testOrderButtonBelow() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOrderButtonBelow();
        Assert.assertTrue(homePage.clickOrderButtonBelow());
    }

    @Test
    public void testOrderButtonAboveAndSuccessOrder(){
        HomePage homePage = new HomePage(driver);
        homePage.clickOrderButtonAbove();
        Assert.assertTrue(homePage.clickOrderButtonAbove());
        ForWhomScooter forWhomScooter = new ForWhomScooter(driver);
        forWhomScooter.loadingPageForWhomScooter();
        forWhomScooter.fillingInFieldsOnPageForWhomScooter(name, surname, address, metro, phone);
        forWhomScooter.clickNextButton();
        AboutRent aboutRent = new AboutRent(driver);
        aboutRent.loadingPageAboutRent();
        aboutRent.fillingInFieldsOnPageAboutRent(date, term, color, comment);
        aboutRent.clickOrderButton();
        aboutRent.clickYesButton();
        Assert.assertTrue(aboutRent.displayedOrderPlaced());
    }

    @After
    public void after() {
        driver.quit();
    }
}