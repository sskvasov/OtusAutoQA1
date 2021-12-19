package otusAutoQA_HM2.parallel.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

public class StepDefs {

    private EventFiringWebDriver driver;

    @Before
    public void beforeScenario() {
        System.out.println("This will run before the Scenario");
    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("^открываю браузер \"([^\"]*)\"$")
    public void openChrome(String browser) {
        driver = new WebDriverFactory(browser).createDriver();
        System.setProperty("video.folder", "target/video");
        System.setProperty("video.save.mode", "ALL");

        driver.register(new Highlighter());
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://otus.ru/");

    }

    @Given("^найти на странице курс \"([^\"]*)\"$")
    public void findCourseOnPage(String course) {
        List<WebElement> webElements = driver.findElements(By.xpath("//*[contains(@class,'lessons__new-item-title ') and contains(text(),'" + course + "')]"));
        new Actions(driver)
                .moveToElement(webElements.get(0))
                .click()
                .build()
                .perform();
    }

    @Given("^нахожу курс, который начинается после даты \"([^\"]*)\"$")
    public void findCoursesAfterDate(String date) throws ParseException {
        List<WebElement> courses = driver.findElements(By.xpath("//*[contains(@href,\"/lessons/\")]/descendant::*[contains(@class,\"lessons__new-item-title lessons\")]"));
        List<WebElement> beginDate = driver.findElements(By.xpath("//*[contains(@class,\"lessons__new-item-start\")]"));
        Date afterDate = formatDate(date);
        Map<String, Date> map = new HashMap<>();
        Map<String, Date> map1 = new HashMap<>();

        /*
            Примерно. Не уверен в точности написания
         */

        List<WebElement> sortedList = courses
                .stream()
                .filter(val -> {
                    try {
                        return formatDate(val.getText().replace("C", "")).after(afterDate);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        //        for (int i = 0; i < courses.size(); i++) {
//            map.put(courses.get(i).getText(), formatDate(beginDate.get(i).getText().replace("С ", "")));
//        }
//        map.entrySet().stream().filter(a -> a.getValue().after(afterDate)).forEach(System.out::println);
//
//        map.forEach((x, y) -> {
//            if (y.after(afterDate)) {
//                System.out.println("forEach " + x + " " + y);
//            }
//        });

        findCourseOnPage(map.entrySet().stream().filter(a -> a.getValue().after(afterDate)).findAny().stream().collect(Collectors.toList()).get(0).getKey());
    }

    public Date formatDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM");
        Date date1 = dateFormat.parse(date);
        DateFormat dateFormat1;
        dateFormat1 = new SimpleDateFormat("dd.MM");
        date = dateFormat1.format(date1);
        System.out.println("date " + date1);
        return date1;
    }

    @Given("^перейти в раздел Курсы > Подготовительные курсы и сделать фильтр по дорогим курсам$")
    public void filterExpensiveCourse() {
        WebElement webElement = driver.findElement(By.xpath("(//p[@class=\"header2-menu__item-text\" and text()='Курсы'])[1]"));
        webElement.click();
        webElement.findElement(By.xpath("./following::*[@title=\"Подготовительные курсы\"][1]")).click();
        List<WebElement> courses = driver.findElements(By.xpath("//*[contains(@class,\"lessons__new-item-title lessons\")]"));
        List<WebElement> price = driver.findElements(By.xpath("//*[contains(@class,\"lessons__new-item-price\")]"));
        Map<String, Integer> coursesMap = new HashMap<>();

        for (int i = 0; i < courses.size(); i++) {
            coursesMap.put(courses.get(i).getText(), Integer.parseInt(price.get(i).getText().replace(" ₽", "")));
        }
//        System.out.println("coursesMap " + coursesMap);
        Map.Entry<String, Integer> println = coursesMap.entrySet()
                .stream()
                .max(comparingInt(Map.Entry::getValue)).get();
        //        System.out.println("println "+println);
        findCourseOnPage(println.getKey());
    }

    @Given("^перейти в раздел Курсы > Подготовительные курсы и сделать фильтр по дешевым курсам$")
    public void filterCheapCourse() {
        WebElement webElement = driver.findElement(By.xpath("(//p[@class=\"header2-menu__item-text\" and text()='Курсы'])[1]"));
        webElement.click();
        webElement.findElement(By.xpath("./following::*[@title=\"Подготовительные курсы\"][1]")).click();
        List<WebElement> courses = driver.findElements(By.xpath("//*[contains(@class,\"lessons__new-item-title lessons\")]"));
        List<WebElement> price = driver.findElements(By.xpath("//*[contains(@class,\"lessons__new-item-price\")]"));
        Map<String, Integer> coursesMap = new HashMap<>();

        for (int i = 0; i < courses.size(); i++) {
            coursesMap.put(courses.get(i).getText(), Integer.parseInt(price.get(i).getText().replace(" ₽", "")));
        }
//        System.out.println("coursesMap " + coursesMap);
        Map.Entry<String, Integer> println = coursesMap.entrySet()
                .stream()
                .min(comparingInt(Map.Entry::getValue)).get();
//        System.out.println("println "+println);
        findCourseOnPage(println.getKey());
    }

    @Given("^закрываем браузер")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
