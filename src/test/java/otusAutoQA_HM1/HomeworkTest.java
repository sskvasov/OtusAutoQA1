package otusAutoQA_HM1;

import com.automation.remarks.testng.UniversalVideoListener;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(UniversalVideoListener.class)
public class HomeworkTest {

    private RequestSpecification requestSpecification;

    @BeforeClass
    public void setupClass() {

        requestSpecification = RestAssured.given().log().all();
        requestSpecification.baseUri("https://petstore.swagger.io/v2");
    }

    @AfterClass
    public void teardown() {
    }

    @Test
    public void createUser_1() {
        //Создаем юзера и проверяем статус выполнения запроса
        requestSpecification.basePath("/user");
        requestSpecification.contentType("application/json");
        requestSpecification.body("{\n" +
                "  \"id\": 0,\n" +
                "  \"username\": \"userName_1\",\n" +
                "  \"firstName\": \"firstName_1\",\n" +
                "  \"lastName\": \"lastName_1\",\n" +
                "  \"email\": \"email@cucu.ha\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"phone\": \"9857456214\",\n" +
                "  \"userStatus\": 0\n" +
                "}");
        requestSpecification.when().post().then().statusCode(200);
    }

    @Test
    public void createUser_2() {
// Создаем юзера и проверяем, что заголовок должен иметь Content-Type = application/json
        requestSpecification.basePath("/user");
        requestSpecification.contentType("application/json");
        requestSpecification.body("{\n" +
                "  \"id\": 0,\n" +
                "  \"username\": \"userName_1\",\n" +
                "  \"firstName\": \"firstName_1\",\n" +
                "  \"lastName\": \"lastName_1\",\n" +
                "  \"email\": \"email@cucu.ha\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"phone\": \"9857456214\",\n" +
                "  \"userStatus\": 0\n" +
                "}");

        requestSpecification
                .when()
                .post()
                .then()
                .header("Content-Type", "application/json");
    }

    @Test
    public void getUserByName_1() {
// Проверяем наличие юзера по имени стринг. Проверяем, что в body есть username=string
        requestSpecification.basePath("/user/string");
        Response response = requestSpecification.get();
        Assert.assertEquals(response.getBody().jsonPath().get("username"), "string");
    }

    @Test
    public void getUserByName_2() {
// Проверяем наличие юзера по имени стринг. Проверяем статус выполнения запроса
        requestSpecification.basePath("/user/string");
        Response response = requestSpecification.get();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
