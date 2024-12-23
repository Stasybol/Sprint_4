import org.openqa.selenium.WebDriver;
import ru.praktikum.services.qa.scooter.page.objects.ForWhomScooter;
import ru.praktikum.services.qa.scooter.page.objects.HomePage;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class TestAccordion {
    private static final String DEFAULT_BROWSER_NAME = "CHROME";
    private static final String BROWSER_NAME_ENV_VARIABLE = "BROWSER_NAME";
    private final int number;
    private final String expendHeader;
    private final String expendPanel;

    private WebDriver driver;

    public TestAccordion(int number, String expendHeader, String expendPanel) {
        this.number = number;
        this.expendHeader = expendHeader;
        this.expendPanel = expendPanel;
    }

    @Parameterized.Parameters
    public static Object[][] getDataTest() {
        return new Object[][] {
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области." }
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
    public void testAccordion(){
        HomePage homePage = new HomePage(driver);
        MatcherAssert.assertThat(homePage.textAccordionHeading(number), is(expendHeader));
        homePage.enterAccordionPanel(number);
        MatcherAssert.assertThat(homePage.openAccordionPanel(number), is(expendPanel));
        homePage.clickOrderButtonBelow(); // убрать
        ForWhomScooter forWhomScooter = new ForWhomScooter(driver);
        forWhomScooter.loadingPageForWhomScooter();
    }

    @After
    public void after() {
        driver.quit();
    }
}
