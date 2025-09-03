package com.stephen.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Token管理工具类
 * <p>
 * 提供添加和获取各个用户token的工具方法
 * </p>
 */
public class TokenManager {
    //存放用户token的HashMap集合，key：账号，value：token值
    public static Map<String, String> userToken = new HashMap<>();

    /**
     * 私有化构造函数，防止外部实例化
     */
    private TokenManager() {
    }

    /**
     * 添加token
     * <p>
     * 添加token的工具方法，往HashMap里面添加一个元素：{ 账号，该账号的token值 }。
     * 建议每次登录成功后都添加一个token，方便后续获取和使用
     * </p>
     *
     * @param username token所属的账号
     * @param token    该账号的token值
     */
    public static void addTokenToMap(String username, String token) {
        userToken.put(username, token);
    }

    /**
     * 获取token
     * <p>
     * 获取token的工具方法，根据传入的账号username，从HashMap里面获取一个账号的token值。
     * 在发起需要token鉴权的接口请求前，可根据该接口的业务场景，选择需要添加的账号的token作为请求头
     * （比如需要管理员权限，就传入一个管理员账号，获取该账号的token。前提是该管理员账号已调用过登录接口并添加到了存放token的HashMap集合中）
     * </p>
     *
     * @param username token所属的账号
     * @return 该账号的token
     */
    public static String getTokenFromMap(String username) {
        return userToken.get(username);
    }

}
