package com.stephen.api;

import static io.restassured.RestAssured.given;

public class LoginAPI {
    public String login(String username, String password) {
        String loginResult;

        loginResult = given().
                formParam("username", username).
                formParam("password", password).
                when().
                post("/login").
                then().
                extract().response().asString();

        return loginResult;
    }
}
