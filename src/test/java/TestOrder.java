import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.AboutRent;
import pageObjects.ForWhomScooter;
import pageObjects.HomePage;

@RunWith(Parameterized.class)
public class TestOrder {
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
        driver = new ChromeDriver();
    }

    @Test
    public void testOrderButtonAboveAndSuccessOrder(){
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickCookiesButton();
        homePage.clickOrderButtonAbove();
        ForWhomScooter forWhomScooter = new ForWhomScooter(driver);
        forWhomScooter.loadingPageForWhomScooter();
        forWhomScooter.inputName(name);
        forWhomScooter.inputSurname(surname);
        forWhomScooter.inputAddress(address);
        forWhomScooter.inputMetro(metro);
        forWhomScooter.inputPhone(phone);
        forWhomScooter.clickNextButton();
        AboutRent aboutRent = new AboutRent(driver);
        aboutRent.loadingPageAboutRent();
        aboutRent.inputDate(date);
        aboutRent.choiceTerm(term);
        aboutRent.choiceColor(color);
        aboutRent.inputComment(comment);
        aboutRent.clickOrderButton();
        aboutRent.clickYesButton();
        Assert.assertTrue(aboutRent.displayedOrderPlaced());
    }

    @After
    public void after() {
        driver.quit();
    }
}