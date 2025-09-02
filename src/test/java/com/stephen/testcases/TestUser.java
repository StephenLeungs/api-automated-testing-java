package com.stephen.testcases;

import com.stephen.api.CheckUsernameAPI;
import com.stephen.utils.BaseTest;
import com.stephen.utils.GetTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 用户模块测试类
 * <p>
 * 所有测试类均需要继承测试基类BaseTest，用于加载config配置里面的api.base.url
 * </p>
 */
public class TestUser extends BaseTest {
    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("TestUser.class");

    /**
     * 查询当前账号接口测试用例
     * <p>
     * 用于测试查询当前账号接口，各个参数均由DataProvider读取Excel文件提供测试数据参数化传入
     * （@Test注解声明了所使用的DataProvider以及DataProvider所在的类）
     * </p>
     *
     * @param username       DataProvider读取到的账号测试数据（要查询的账号）
     * @param expectedResult DataProvider读取到的期望结果测试数据（用于断言）
     */
    @Test(groups = "User", priority = 3, dataProvider = "CheckUsernameData", dataProviderClass = GetTestData.class)
    public void testCheckUsername(String username, String expectedResult) {
        try {
            //调用查询当前账号接口类的checkUsername()方法发起请求，并对响应文本进行断言（要查询的账号 = 接口返回的账号）
            CheckUsernameAPI checkUsernameAPI = new CheckUsernameAPI();
            Assert.assertEquals(checkUsernameAPI.checkUsername(username), expectedResult);

        } catch (Exception e) {
            LOGGER.error("查询当前用户名测试用例异常/TestCheckUsername Testcase Error", e);
        }
    }

}
