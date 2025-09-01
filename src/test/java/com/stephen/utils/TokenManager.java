package com.stephen.utils;

import java.util.HashMap;
import java.util.Map;

public class TokenManager {

    public static Map<String, String> userToken = new HashMap<>();

    private TokenManager() {
    }

    public static void addTokenToMap(String username, String token) {
        userToken.put(username, token);
    }

    public static String getTokenFromMap(String username) {
        return userToken.get(username);
    }

}
