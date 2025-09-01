package com.stephen.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;


//API类均需要继承测试基类加载config配置里面的api.base.url
public class RegisterAPI {

    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("RegisterAPI.class");

    public String register(String username, String password, String confirmPassword) {
        String registerResult = null;

        try {
            registerResult = given().
                    formParam("username", username).
                    formParam("password", password).
                    formParam("confirm_password", confirmPassword).
                    when().
                    post("/register").
                    then().
                    extract().response().asString();
        } catch (Exception e) {
            LOGGER.error("发起register请求异常异常 / register Request Error", e);
        }

        return registerResult;
    }


}
