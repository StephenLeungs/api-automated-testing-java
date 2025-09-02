package com.stephen.api;

import com.stephen.utils.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

/**
 * 查询当前账号接口类
 */
public class CheckUsernameAPI {
    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("CheckUsernameAPI.class");

    /**
     * 查询当前账号请求
     * <p>
     * 调用RestAssured相关方法，封装查询当前账号接口的请求
     * </p>
     *
     * @param username 要查询的账号（注意：该数据并非接口参数，而是需要此账号数据作为获取token的key）
     * @return 查询当前账号接口的响应文本
     */
    public String checkUsername(String username) {
        //初始化一个接口响应文本的String实例
        String checkUsernameResult = null;

        //根据传入的username，调用token管理工具类的获取token方法，获取该账号的token
        String token = TokenManager.getTokenFromMap(username);

        try {
            checkUsernameResult = given().
                    header("auth", token). //添加当前账号的token作为请求头
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
