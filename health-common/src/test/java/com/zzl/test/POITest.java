package com.zzl.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/8/16 23:01
 * Version 1.0
 */
public class POITest {
    //使用POI读取文件中的数据
    @Test
    public void test1() throws IOException {
        //加载指定文件，创建一个excel对象
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("d:\\abc.xlsx")));
        //读取Excel文件中第一个sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        //遍历
        for (Row row : sheet) {
            //获得每个单元格对象
            for (Cell cell : row) {
                System.out.println(cell);
            }
        }
        //关闭资源
        excel.close();
    }

    @Test
    public void test2() throws IOException {
        //加载指定文件，创建一个excel对象
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("d:\\abc.xlsx")));
        //读取Excel文件中第一个sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        //获取当前工作表中最后一个行号，需要注意行号从0开始
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum ; i++) {
            //根据行号获取每一行
            XSSFRow row = sheet.getRow(i);
            //获取当前行中最后一个单元格索引
            //lastCellNum相当于列总数
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum ; j++) {
                XSSFCell cell = row.getCell(j);//根据单元格索引获取单元格对象
//                System.out.println(cell.getStringCellValue());
                System.out.println(cell);
            }
        }
        //关闭资源
        excel.close();
    }
    //使用POI向excel文件写入数据，并且通过输出流将创建的Excel文件保存到本地磁盘
    @Test
    public void test3() throws Exception {
        //在内存中创建一个Excel文件
        XSSFWorkbook excel = new XSSFWorkbook();
        //创建一个工作表对象
        XSSFSheet sheet = excel.createSheet("传智播客");
        //在工作表中创建行对象
        XSSFRow title = sheet.createRow(0);
        //在行中创建列对象
        title.createCell(0).setCellValue("姓名");
        title.createCell(1).setCellValue("地址");
        title.createCell(2).setCellValue("年龄");

        XSSFRow datarow1 = sheet.createRow(1);
        datarow1.createCell(0).setCellValue("zzl");
        datarow1.createCell(1).setCellValue("河南省");
        datarow1.createCell(2).setCellValue(23);

        //创建一个输出流，通过输出流将内存中的Excel文件写到磁盘
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\abcde.xlsx"));
        excel.write(fileOutputStream);
        fileOutputStream.flush();
        excel.close();
    }
}
