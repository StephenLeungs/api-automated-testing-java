package com.stephen.api;

import com.stephen.utils.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class CheckUsernameAPI {
    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("CheckUsernameAPI.class");

    public String checkUsername(String username) {

        String checkUsernameResult = null;

        String token = TokenManager.getTokenFromMap(username);

        try {
            checkUsernameResult = given().
                    header("auth", token).
                    when().
                    get("/check-username").
                    then().
                    extract().response().asString();


        } catch (Exception e) {
            LOGGER.error("发起check-username请求异常异常 / check-username Request Error", e);
        }
        return checkUsernameResult;
    }
}
