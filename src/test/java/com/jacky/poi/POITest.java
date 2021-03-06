package com.jacky.poi;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class POITest {
    private static final Pattern CHINESE_CHAR = Pattern.compile("[\\u4e00-\\u9fa5]");
    public static boolean containChineseChar(String value) {
        Matcher matcher = CHINESE_CHAR.matcher(value);
        return matcher.find();
    }
    @Test
    public void poiTestMethod() throws Exception {
        //1.读取Excel文档对象
        String input= "abacbdfd";
        String input1= "全中文";
        String input2= "bufen部分中文";
        String input3= "中  &&& 问";
        Assert.assertTrue(!containChineseChar(input));
        Assert.assertTrue(containChineseChar(input1));
        Assert.assertTrue(containChineseChar(input2));
        Assert.assertTrue(containChineseChar(input3));

//        XSSFWorkbook hssfWorkbook = new XSSFWorkbook(new FileInputStream("/Users/jacky/Desktop/compared.xlsx"));
//        //2.获取要解析的表格（第一个表格）
//        XSSFSheet sheet = hssfWorkbook.getSheetAt(0);
//        //获得最后一行的行号
//        int lastRowNum = sheet.getLastRowNum();
//        Set<String> col1Set = Sets.newHashSet();
//        Set<String> col2Set = Sets.newHashSet();
//        for (int i = 0; i <= lastRowNum; i++) {//遍历每一行
//            if (i==1520)
//                break;
//            XSSFRow row = sheet.getRow(i);
//            //4.获得每个单元格中的内容（String）
//            if (i<=161&&row.getCell(0) != null) {
//                String stringCellValue0 = row.getCell(0).getStringCellValue();
//                col1Set.add(stringCellValue0);
//            }
//            String stringCellValue1 = row.getCell(1).getStringCellValue();
//            col2Set.add(stringCellValue1);
//        }
//        col1Set.removeAll(col2Set);
//        System.out.println(col1Set);
    }
}
