package otusAutoQA_HM2.parallel.stepDefs;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

import static java.lang.Thread.sleep;

public  class Highlighter implements WebDriverEventListener {

    
    public void beforeClickOn(WebElement element, WebDriver driver) {
        try {
            highlight(driver,element, "orange");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
//        try {
//            unhighlight(webDriver,webElement, "orange");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void beforeAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertDismiss(WebDriver webDriver) {

    }

    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    public void beforeNavigateTo(String s, WebDriver webDriver) {

    }

    public void afterNavigateTo(String s, WebDriver webDriver) {

    }

    public void beforeNavigateBack(WebDriver webDriver) {

    }

    public void afterNavigateBack(WebDriver webDriver) {

    }

    public void beforeNavigateForward(WebDriver webDriver) {

    }

    public void afterNavigateForward(WebDriver webDriver) {

    }

    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        try {
            highlight(driver,element, "red");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
//        try {
//            highlight(driver,element, "black");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        try {
            highlight(driver,element, "orange");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    public void beforeScript(String s, WebDriver webDriver) {

    }

    public void afterScript(String s, WebDriver webDriver) {

    }

    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

    }

    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }

    public static <T extends WebElement> T highlight(WebDriver driver,T element) throws InterruptedException {
        return highlight(driver,element, "orange");
    }

    public static <T extends WebElement> T highlight(WebDriver driver,T element, final String color) throws InterruptedException {
        if (element != null && element.getAttribute("__selenideHighlighting") == null) {
            for (int i = 1; i < 4; i++) {
                transform(driver,element, color, i);
                sleep(50);
            }
            for (int i = 3; i > -1; i--) {
                transform(driver,element, color, i);
                sleep(50);
            }
        }
        return element;
    }

    public static <T extends WebElement> T unhighlight(WebDriver driver,T element, final String color) throws InterruptedException {
        if (element != null && element.getAttribute("__selenideHighlighting") != null) {
                transform(driver,element, color, 0);
                sleep(5000);
        }
        return element;
    }

    private static void transform(WebDriver driver,WebElement element, String color, int i) {
        ((JavascriptExecutor) driver).executeScript ("arguments[0].setAttribute('__selenideHighlighting', 'done'); " +
                        "arguments[0].setAttribute(arguments[1], arguments[2])",
                element,
                "style",
                "border: " + i + "px solid " + color + "; border-style: dotted; " +
                        "transform: scale(1, 1." + i + ");");
    }
}
