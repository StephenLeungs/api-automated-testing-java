package com.stephen.utils;


import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

/**
 * 所有测试类的基类。
 * 负责完成全局的初始化配置。
 */
public class BaseTest {

    // @BeforeSuite: TestNG注解，表示在整个测试套件开始前，只执行一次。
    @BeforeClass(alwaysRun = true) // `alwaysRun = true` 表示即使这个类被继承，也保证会运行
    public void globalSetup() {
        // 1. 从配置文件中加载并设置基准URL
        String baseUrl = ConfigLoader.getInstance().getBaseUrl();
        RestAssured.baseURI = baseUrl;
        System.out.println("全局基准路径已设置为: " + baseUrl);

        // 2. (可选但推荐) 配置RestAssured的全局默认请求规格，比如通用的请求头
        /* RequestSpecification commonSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON) // 默认Content-Type为JSON
                // .addHeader("Authorization", "Bearer " + ConfigLoader.getInstance().getProperty("api.auth.token")) // 如果有全局Token
                .build();

        RestAssured.requestSpecification = commonSpec;*/

        // 3. (可选) 其他全局设置，比如日志
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); // 只在验证失败时打印日志
    }
}
