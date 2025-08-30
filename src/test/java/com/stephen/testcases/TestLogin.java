package com.stephen.testcases;

import com.stephen.api.LoginAPI;
import com.stephen.api.RegisterAPI;
import com.stephen.utils.BaseTest;
import com.stephen.utils.GetTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.regex.Pattern;

public class TestLogin extends BaseTest {
    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("TestLogin.class");

    @Test(groups = "Login", priority = 1, dataProvider = "RegisterData", dataProviderClass = GetTestData.class)
    public void testRegister(String username, String password, String confirmPassword, String expectedResult) {
        try {
            RegisterAPI registerAPI = new RegisterAPI();
            Assert.assertTrue(registerAPI.register(username, password, confirmPassword).
                    matches(".*" + Pattern.quote(expectedResult) + ".*"));

        }catch (Exception e) {
            LOGGER.error("注册测试用例异常/Register Testcase Error", e);
        }
    }

    @Test(groups = "Login", priority = 2, dataProvider = "LoginData", dataProviderClass = GetTestData.class)
    public void testLogin(String username, String password, String expectedResult) {
        try {
            LoginAPI loginAPI = new LoginAPI();
            Assert.assertTrue(loginAPI.login(username,password).
                    matches(".*" + Pattern.quote(expectedResult) + ".*"));

        }catch (Exception e) {
            LOGGER.error("登录测试用例异常/Login Testcase Error", e);
        }
    }

}
