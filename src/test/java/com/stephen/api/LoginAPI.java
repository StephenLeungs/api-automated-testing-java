package com.stephen.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class LoginAPI {

    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("LoginAPI.class");

    public String login(String username, String password) {

        String loginResult = null;

        try {
            loginResult = given().
                    formParam("username", username).
                    formParam("password", password).
                    when().
                    post("/login").
                    then().
                    extract().response().asString();
        } catch (Exception e) {
            LOGGER.error("发起login请求异常异常 / login Request Error", e);
        }

        return loginResult;
    }
}
