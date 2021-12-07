package otusAutoQA_HM1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public enum Browsers {
    CHROME {
        public WebDriver create() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    },
    OPERA {
        public WebDriver create() {
            WebDriverManager.operadriver().setup();
            return new OperaDriver();
        }
    },
    FIREFOX {
        public WebDriver create() {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    };

    public WebDriver create() {
        return null;
    }
}
