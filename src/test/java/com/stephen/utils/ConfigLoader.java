package com.stephen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置加载工具类
 * <p>
 * 通过单例模式，实现加载config.properties配置文件中的基准路径api.base.url
 * </p>
 */
public class ConfigLoader {
    // 单例实例
    private static final ConfigLoader INSTANCE = new ConfigLoader();
    private final Properties properties;

    /**
     * 私有化构造函数，防止外部实例化
     */
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

    /**
     * 获取单例实例
     *
     * @return ConfigLoader实例
     */
    public static ConfigLoader getInstance() {
        return INSTANCE;
    }


    /**
     * 获取配置项
     * <p>
     * 根据配置项的key获取配置项的值
     * </p>
     *
     * @param key 配置项的key
     * @return 配置项的值
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * 获取api.base.url配置
     * <p>
     * 调用上方获取配置项的getProperty()方法，获取配置项api.base.url
     * </p>
     *
     * @return 配置项api.base.url的值
     */
    public String getBaseUrl() {
        return getProperty("api.base.url");
    }
}
