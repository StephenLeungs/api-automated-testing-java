package com.stephen.testcases;

import com.stephen.api.LoginAPI;
import com.stephen.api.RegisterAPI;
import com.stephen.utils.BaseTest;
import com.stephen.utils.GetTestData;
import com.stephen.utils.TokenManager;
import io.restassured.path.json.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

/**
 * 注册登录模块测试类
 * <p>
 * 所有测试类均需要继承测试基类BaseTest，用于加载config配置里面的api.base.url
 * </p>
 */
public class TestLogin extends BaseTest {
    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("TestLogin.class");

    /**
     * 注册接口测试用例
     * <p>
     * 用于测试注册接口，各个参数均由DataProvider读取Excel文件提供测试数据参数化传入
     * （@Test注解声明了所使用的DataProvider以及DataProvider所在的类）
     * </p>
     *
     * @param username        DataProvider读取到的账号测试数据
     * @param password        DataProvider读取到的密码测试数据
     * @param confirmPassword DataProvider读取到的确认密码测试数据
     * @param expectedResult  DataProvider读取到的期望结果测试数据（用于断言）
     */
    @Test(groups = "Login", priority = 1, dataProvider = "RegisterData", dataProviderClass = GetTestData.class)
    public void testRegister(String username, String password, String confirmPassword, String expectedResult) {
        try {
            //调用注册接口类的实例方法register()发起请求，并对响应文本通过正则表达式进行断言
            RegisterAPI registerAPI = new RegisterAPI();
            Assert.assertTrue(registerAPI.register(username, password, confirmPassword).
                    matches(".*" + Pattern.quote(expectedResult) + ".*"));

        } catch (Exception e) {
            LOGGER.error("注册测试用例异常/Register Testcase Error", e);
        }
    }

    /**
     * 登录接口测试用例
     * <p>
     * 用于测试登录接口，各个参数均由DataProvider读取Excel文件提供测试数据参数化传入
     * （@Test注解声明了所使用的DataProvider以及DataProvider所在的类）
     * </p>
     *
     * @param username       DataProvider读取到的账号测试数据
     * @param password       DataProvider读取到的密码测试数据
     * @param expectedResult DataProvider读取到的期望结果测试数据（用于断言）
     */
    @Test(groups = "Login", priority = 2, dataProvider = "LoginData", dataProviderClass = GetTestData.class)
    public void testLogin(String username, String password, String expectedResult) {
        try {
            //调用登录接口类的实例方法login()发起请求，并对响应文本通过正则表达式进行断言
            LoginAPI loginAPI = new LoginAPI();
            String response = loginAPI.login(username, password);
            Assert.assertTrue(response.
                    matches(".*" + Pattern.quote(expectedResult) + ".*"));

            //断言通过（登录成功）后提取响应文本内的data的值（当前用户的token），并调用Token管理工具类的添加token的方法添加到存放token的HashMap中
            String token = JsonPath.from(response).getString("data");
            TokenManager.addTokenToMap(username, token);

        } catch (Exception e) {
            LOGGER.error("登录测试用例异常/Login Testcase Error", e);
        }
    }

}
