package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static constants.Constants.TimeOuts.IMPLICIT_WAIT;

public class CommonActions {
    private CommonActions(){
    }

    public static WebDriver createDriver(){
        Duration timeoutDuration = Duration.ofSeconds(IMPLICIT_WAIT);
        WebDriver driver =null;
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeoutDuration);
        return driver;
    }
}
