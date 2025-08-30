package com.stephen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    // 单例实例
    private static final ConfigLoader INSTANCE = new ConfigLoader();
    private final Properties properties;

    // 私有构造函数，防止外部实例化
    private ConfigLoader() {
        properties = new Properties();
        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("config.properties")) {

            if (inputStream == null) {
                throw new RuntimeException("配置文件 config.properties 未找到。");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件失败", e);
        }
    }

    // 全局访问点
    public static ConfigLoader getInstance() {
        return INSTANCE;
    }

    // 获取配置项的方法
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // 提供便捷方法，直接获取BASE_URL
    public String getBaseUrl() {
        return getProperty("api.base.url");
    }
}
