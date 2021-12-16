package otusAutoQA_HM2.parallel.stepDefs;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Given;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class StepDefs {

    private RequestSpecification requestSpecification;
    WireMockServer wireMockServer;
    @Given("^Останавливаем сервер с заглушкой")
    public void teardown() {
        wireMockServer.stop();
    }
    @Given("^Поднимаем сервер с заглушкой$")
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

    @Given("Получить данные всех пользователей")
    public void getAllUsers() {
        requestSpecification.basePath("/user/get/all");
        requestSpecification.contentType("application/json");
        requestSpecification.when().get().then().statusCode(200);
        Response response = requestSpecification.when().get();
        response.body().prettyPrint();
    }

    @Given("^Получить данные о всех курсах$")
    public void getAllCourses() {
        requestSpecification.basePath("/course/get/all");
        requestSpecification.contentType("application/json");
        requestSpecification.when().get().then().statusCode(200);
        Response response = requestSpecification.when().get();
        response.body().prettyPrint();
    }

    @Given("^Получить данные об оценке$")
    public void getGrade() {
        requestSpecification.basePath("/get/grade");
        requestSpecification.contentType("application/json");
        requestSpecification.when().get().then().statusCode(200);
        Response response = requestSpecification.when().get();
        response.body().prettyPrint();
    }

    @Given("^Получить данные одного пользователя$")
    public void getUser_1() {
        requestSpecification.basePath("/user/get/1");
        requestSpecification.contentType("application/json");
        requestSpecification.when().get().then().statusCode(200);
        Response response = requestSpecification.when().get();
        response.body().prettyPrint();
    }


}
