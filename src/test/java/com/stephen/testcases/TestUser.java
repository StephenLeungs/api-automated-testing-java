package com.stephen.testcases;

import com.stephen.api.CheckUsernameAPI;
import com.stephen.utils.BaseTest;
import com.stephen.utils.GetTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUser extends BaseTest {
    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("TestUser.class");

    @Test(groups = "User", priority = 3, dataProvider = "CheckUsernameData", dataProviderClass = GetTestData.class)
    public void testCheckUsername(String username, String expectedResult) {
        try {
            CheckUsernameAPI checkUsernameAPI = new CheckUsernameAPI();
            Assert.assertEquals(checkUsernameAPI.checkUsername(username), expectedResult);

        } catch (Exception e) {
            LOGGER.error("查询当前用户名测试用例异常/TestCheckUsername Testcase Error", e);
        }
    }

}
