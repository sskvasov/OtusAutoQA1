package otusAutoQA_HM1;

import com.automation.remarks.testng.UniversalVideoListener;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Listeners(UniversalVideoListener.class)
public class HomeworkTest {

    private RequestSpecification requestSpecification;
    WireMockServer wireMockServer;

    @BeforeClass
    public void setupClass() {
        wireMockServer = new WireMockServer(options().port(8089));
        wireMockServer.start();
        configureFor("localhost", 8089);
        stubFor(get(urlMatching("/user/get/([0-9]*)"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withTransformers("response-template")
                        .withBody("{\n" +
                                " \"name\":\"Test user\",\n" +
                                " \"course\":\"QA\",\n" +
                                "  \"email\":\"test@test.test\"\n" +
                                "  \"age\": 23\n" +
                                "}")));

        stubFor(get(urlMatching("/user/get/all"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withTransformers("response-template")
                        .withBody("[\n" +
                                " {\n" +
                                " \"name\":\"Test user\",\n" +
                                " \"course\":\"QA\",\n" +
                                "  \"email\":\"test@test.test\"\n" +
                                "  \"age\": 23\n" +
                                " },\n" +
                                " {\n" +
                                " \"name\":\"Test user 2\",\n" +
                                " \"course\":\"SQA\",\n" +
                                "  \"email\":\"test2@test.ru\"\n" +
                                "  \"age\": 25\n" +
                                " }\n" +
                                "]")));

        stubFor(get(urlMatching("/course/get/all"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withTransformers("response-template")
                        .withBody("[\n" +
                                " {\n" +
                                "   \"name\":\"QA java\",\n" +
                                "  \"price\": 15000\n" +
                                " },\n" +
                                " {\n" +
                                "  \"name\":\"Java\",\n" +
                                "   \"price\": 12000\n" +
                                " }\n" +
                                "]")));


        stubFor(get(urlMatching("/get/grade"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withTransformers("response-template")
                        .withBody("{\n" +
                                "  \"name\":\"Test user\",\n" +
                                "  \"score\": 78 \n" +
                                "}")));


        requestSpecification = RestAssured.given().log().all();
        requestSpecification.baseUri("http://localhost:8089/");
    }

    @AfterClass
    public void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void getUser_1() {

        //Создаем юзера и проверяем статус выполнения запроса
        requestSpecification.basePath("/user/get/1");
        requestSpecification.contentType("application/json");
        requestSpecification.when().get().then().statusCode(200);
        Response response = requestSpecification.when().get();
        response.body().prettyPrint();
    }

    @Test
    public void getAllUsers() {

        //Создаем юзера и проверяем статус выполнения запроса
        requestSpecification.basePath("/user/get/all");
        requestSpecification.contentType("application/json");
        requestSpecification.when().get().then().statusCode(200);
        Response response = requestSpecification.when().get();
        response.body().prettyPrint();
    }

    @Test
    public void getAllCourses() {

        //Создаем юзера и проверяем статус выполнения запроса
        requestSpecification.basePath("/course/get/all");
        requestSpecification.contentType("application/json");
        requestSpecification.when().get().then().statusCode(200);
        Response response = requestSpecification.when().get();
        response.body().prettyPrint();
    }

    @Test
    public void getGrade() {

        //Создаем юзера и проверяем статус выполнения запроса
        requestSpecification.basePath("/get/grade");
        requestSpecification.contentType("application/json");
        requestSpecification.when().get().then().statusCode(200);
        Response response = requestSpecification.when().get();
        response.body().prettyPrint();
    }
}
