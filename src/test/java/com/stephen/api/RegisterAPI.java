package com.stephen.api;


import static io.restassured.RestAssured.given;


//API类均需要继承测试基类加载config配置里面的api.base.url
public class RegisterAPI {

    public String register(String username, String password, String confirmPassword) {
        String registerResult;

        registerResult = given().
                formParam("username", username).
                formParam("password", password).
                formParam("confirm_password", confirmPassword).
                when().
                post("/register").
                then().
                extract().response().asString();

        return registerResult;
    }


}
