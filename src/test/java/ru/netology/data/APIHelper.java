package ru.netology.data;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class APIHelper {
    private static DataGenerator generator = new DataGenerator();
    private static final Gson gson = new Gson();
    private static DataGenerator.CardData cardData;

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void payByApprovedCard() {
        cardData = generator.getApprovedCard();
        var body = gson.toJson(cardData);
        given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post("/payment")
                .then()
                .statusCode(200);
    }

    public static void payByCreditWithApprovedCard() {
        cardData = generator.getApprovedCard();
        given()
                .spec(requestSpec)
                .body(cardData)
                .when()
                .post("/credit")
                .then()
                .statusCode(200);
    }

    public static void payByDeclinedCard() {
        cardData = generator.getDeclinedCard();
        given()
                .spec(requestSpec)
                .body(cardData)
                .when()
                .post("/payment")
                .then()
                .statusCode(200);
    }

    public static void payByCreditWithDeclinedCard() {
        cardData = generator.getDeclinedCard();
        given()
                .spec(requestSpec)
                .body(cardData)
                .when()
                .post("/credit")
                .then()
                .statusCode(200);
    }
}
