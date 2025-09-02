package com.stephen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库工具类
 * <p>
 * 读取jdbc.properties配置文件，获取账号密码等数据库连接信息并提供获取数据库连接的工具方法
 * </p>
 */
public class DBUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    // 使用静态代码块在类加载时初始化配置
    static {
        // 1. 使用类加载器从 src/test/resources 加载 jdbc.properties
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("jdbc.properties")) {

            if (inputStream == null) {
                throw new ExceptionInInitializerError("找不到数据库配置文件 jdbc.properties。请确保它位于 src/test/resources 目录下。");
            }

            Properties props = new Properties();
            props.load(inputStream); // 加载属性文件

            // 2. 读取配置项
            driver = props.getProperty("jdbc.driver");
            url = props.getProperty("jdbc.url");
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");

            // 3. 注册驱动 (可选，但建议)
            Class.forName(driver);
            System.out.println("数据库驱动加载成功: " + driver);

        } catch (IOException e) {
            throw new ExceptionInInitializerError("读取 jdbc.properties 配置文件失败: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("找不到数据库驱动类: " + driver);
        }
    }


    /**
     * 获取数据库连接
     * <p>
     * 获取数据库连接的工具方法，调用java.sql.DriverManager模块的getConnection()方法获取数据库连接
     * </p>
     *
     * @return Connection实例
     * @throws SQLException 数据库相关异常
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}