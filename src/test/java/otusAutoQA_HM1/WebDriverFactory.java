package otusAutoQA_HM1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverFactory {
    private String browser;

    public WebDriverFactory(String browser) {
        this.browser = browser;
    }

    public EventFiringWebDriver createDriver() {
        EventFiringWebDriver webDriver;

        // Create local webdriver
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver = new EventFiringWebDriver(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new EventFiringWebDriver(new FirefoxDriver());
        } else if (browser.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().setup();
            webDriver = new EventFiringWebDriver(new OperaDriver());
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
        return webDriver;
    }
}
