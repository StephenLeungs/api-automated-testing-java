package com.stephen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

    // 4. 获取数据库连接 (此方法无需任何修改！)
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 5. 测试连接 (此方法也无需修改！)
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("数据库连接成功！");
            // 可以进行你的数据库操作...
        } catch (SQLException e) {
            System.err.println("数据库连接失败！");
            e.printStackTrace();
        }
    }
}