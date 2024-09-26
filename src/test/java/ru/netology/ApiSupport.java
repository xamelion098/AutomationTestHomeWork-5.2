package ru.netology;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ApiSupport {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private ApiSupport() {

    }

    public static DataGenerator.RegistrationDto sendRequest(DataGenerator.RegistrationDto user) {
        given()
                .spec(requestSpec)
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when()
                .post("/api/system/users")
                .then() //
                .statusCode(200);

        return user;
    }
}


