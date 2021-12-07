package otusAutoQA_HM1;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(UniversalVideoListener.class)
public class HomeworkTest {
    private EventFiringWebDriver driver;

    @BeforeClass
    public void setupClass() {

        driver = new WebDriverFactory(TestConfig.getInstance().getBrowser()).createDriver();
        System.setProperty("video.folder", "target/video");
        System.setProperty("video.save.mode", "ALL");

        driver.register(new Highlighter());
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Video(name = "test")
    public void test() {

        driver.navigate().to("https://otus.ru/");
        filterCourses();
        clickCourse();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void filterCourses() {
        List<WebElement> webElements = driver.findElements(By.xpath("//*[contains(@class,\"lessons__new-item-title \")]"));
        webElements.stream().filter(x -> x.getText().contains("QA Automation Engineer")).forEach(x -> System.out.println(x.getText()));
    }

    public void clickCourse() {

//        List<WebElement> webElement1 = driver.findElements(By.xpath("//*[@class=\"lessons__new-item-start\"]"));
//        List<String> list = new ArrayList<>();
//        webElement1.forEach(x -> list.add((x.getText()).replace("С ", "")));
//        System.out.println("list " + list);
//        list.stream().reduce(Integer.MAX_VALUE, (left, right) -> left < right ? left : right);
                /* Про reduce задачу понял так:
        нужно со страницы отуса собрать курсы и их даты.
        Даты отделить от продолжительности курса. Отредьюсить. Потом связать с элементами. Кликнуть по элементу.
        За это дается 1 балл. Очень сложно. Или я что-то упустил...
         */
        WebElement webElement = driver.findElement(By.xpath("//a[@href=\"/promo/qa-auto-java-specialization/\"]"));
        new Actions(driver)
                .moveToElement(webElement)
                .click()
                .build()
                .perform();
    }
}
