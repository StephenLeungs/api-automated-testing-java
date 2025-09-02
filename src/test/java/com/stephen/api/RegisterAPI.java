package com.stephen.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;


/**
 * 注册接口类
 */
public class RegisterAPI {

    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("RegisterAPI.class");

    /**
     * 注册请求
     * <p>
     * 调用RestAssured相关方法，封装注册接口的请求
     * </p>
     *
     * @param username        账号
     * @param password        密码
     * @param confirmPassword 确认密码
     * @return 注册接口的响应文本
     */
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
