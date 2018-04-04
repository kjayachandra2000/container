package test.features;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import test.pages.CartPage;
import test.pages.OrderPage;
import test.pages.SearchPage;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    WebDriver driver;
    SearchPage searchPage;
    OrderPage orderPage;
    CartPage cartPage;

    @BeforeTest
    public void setUp() throws MalformedURLException {


        if (StringUtils.isNotEmpty(System.getProperty("execution"))) {
            if (System.getProperty("browser").equals("firefox"))
                driver = getFirefoxDriver();
            else
                driver = getChromeDriver();
        } else {
            DesiredCapabilities dc = DesiredCapabilities.chrome();

            if (System.getProperty("browser").equals("firefox"))
                dc = DesiredCapabilities.firefox();

            String host = System.getProperty("seleniumHubHost");
            driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), dc);
        }
        searchPage = new SearchPage(driver);
        orderPage = new OrderPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterTest
    public void tearDown() {
        try {
            driver.close();
            driver.quit();
        } catch (Exception ignore) {
        }
    }

    private WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/driver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        return new ChromeDriver(options);
    }

    private WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/test/driver/geckodriver");
        return new FirefoxDriver();
    }
}
