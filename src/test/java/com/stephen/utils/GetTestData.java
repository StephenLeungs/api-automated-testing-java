package com.stephen.utils;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import java.io.File;

/**
 * 获取测试数据的工具类 / Test Data Retrieval Utility Class
 * <p>
 * 读取Excel文件，通过不同的DataProvider方法获取测试数据并提供给对应的测试方法<br>
 * Reads Excel files, retrieves test data through various DataProvider methods,
 * and supplies it to corresponding test methods.
 * </p>
 */
public class GetTestData {
    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("GetTestData.class");

    /**
     * 读取Excel文件 / Reads Excel file
     * <p>
     * 通过传入的Excel文件路径以及sheet名称，读取Excel文件内对应sheet的测试数据（从第二行开始读取，不会读取标题行）<br>
     * Reads test data from specified sheet in Excel file (starts from second row,
     * skips header row) using provided file path and sheet name.
     * </p>
     *
     * @param excelPath Excel文件路径 / Excel file path
     * @param sheet Excel文件内需要读取的sheet的名称 / Sheet name to read
     * @return 提供给DataProvider的存放测试数据的Object二维数组 / two-dimensional Object array containing test data for DataProvider
     */
    public static Object[][] getDataFromExcel(String excelPath, String sheet) {
        try (Workbook workbook = WorkbookFactory.create(new File(excelPath))) {
            Sheet dataSheet = workbook.getSheet(sheet);
            if (dataSheet == null) return new Object[0][0];

            // 获取实际数据行数（跳过标题行） / Get actual data row count (skip header)
            int startDataRowIndex = 1; // 数据从第二行开始（索引1） / Data starts from second row (index 1)
            int lastRowIndex = dataSheet.getLastRowNum(); // 最后一行索引 / Last row index
            int dataRowCount = lastRowIndex - startDataRowIndex + 1; // 实际数据行数 / Actual data row count

            // 当没有数据行时直接返回空数组 / Return empty array when no data rows
            if (dataRowCount <= 0) return new Object[0][0];

            // 获取列数（基于标题行） / Get column count (based on header row)
            Row titleRow = dataSheet.getRow(0); // 第一行是标题 / First row is header
            int columnCount = titleRow.getLastCellNum(); // 列数量 / Column count

            // 初始化二维数组 [数据行数][列数] / Initialize 2D array [data rows][columns]
            Object[][] datas = new Object[dataRowCount][columnCount];

            DataFormatter dataFormatter = new DataFormatter();

            // 遍历数据行（从索引1开始） / Iterate data rows (start from index 1)
            for (int i = startDataRowIndex; i <= lastRowIndex; i++) {
                Row dataRow = dataSheet.getRow(i);
                if (dataRow == null) continue;

                // 遍历所有列 / Iterate all columns
                for (int j = 0; j < columnCount; j++) {
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String value = dataFormatter.formatCellValue(cell);
                    datas[i - startDataRowIndex][j] = value; // 计算数组行索引 / Calculate array row index
                }
            }
            return datas;
        } catch (Exception e) {
            //发生异常时记录日志信息 / Log error when exception occurs
            LOGGER.error("获取测试数据异常/Get Testdata Error", e);
            return new Object[0][0]; // 返回空数组避免NullPointerException / Return empty array to avoid NullPointerException
        }
    }

    /**
     * 注册测试数据DataProvider / Registration Test Data DataProvider
     * <p>
     * 调用上方的getDataFromExcel()方法读取Excel文件里注册接口的测试数据，作为DataProvider把测试数据提供给测试方法testRegister()<br>
     * Invokes getDataFromExcel() to read registration test data from Excel file,
     * supplies data to test method testRegister() as DataProvider.
     * </p>
     *
     * @return 注册接口的测试数据 / Register test data
     */
    @DataProvider(name = "RegisterData")
    public static  Object[][] getRegisterData(){
        return getDataFromExcel("src/test/resources/TestData.xlsx",  "RegisterData");
    }

    /**
     * 登录测试数据DataProvider / Login Test Data DataProvider
     * <p>
     * 调用上方的getDataFromExcel()方法读取Excel文件里登录接口的测试数据，作为DataProvider把测试数据提供给测试方法testLogin()<br>
     * Invokes getDataFromExcel() to read login test data from Excel file,
     * supplies data to test method testLogin() as DataProvider.
     * </p>
     *
     * @return 登录接口的测试数据 / Login test data
     */
    @DataProvider(name = "LoginData")
    public static  Object[][] getLoginData(){
        return getDataFromExcel("src/test/resources/TestData.xlsx",  "LoginData");
    }

    /**
     * 查询当前账号测试数据DataProvider / Check Username Test Data DataProvider
     * <p>
     * 调用上方的getDataFromExcel()方法读取Excel文件里查询当前账号接口的测试数据，作为DataProvider把测试数据提供给测试方法testCheckUsername()<br>
     * Invokes getDataFromExcel() to read check username test data from Excel file,
     * supplies data to test method testCheckUsername() as DataProvider.
     * </p>
     *
     * @return 查询当前账号接口的测试数据 / check username test data
     */
    @DataProvider(name = "CheckUsernameData")
    public static  Object[][] getCheckUsernameData(){
        return getDataFromExcel("src/test/resources/TestData.xlsx",  "CheckUsernameData");
    }
}