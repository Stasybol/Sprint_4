import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver createForName(String browserName) {
        if (browserName.equals("CHROME")) {
            return new ChromeDriver();
        } else {
            return new FirefoxDriver();
        }
    }
}

