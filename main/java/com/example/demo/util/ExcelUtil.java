package com.example.demo.util;

import cn.hutool.core.convert.Convert;
import lombok.extern.log4j.Log4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExcelUtil {
    public static void main(String[] args) throws Exception {
        System.out.println(readSpecifyRows(new File("C:\\Users\\Administrator\\Desktop\\年初预算表样0817.xlsx")));
    }
    public static Map<String, Object > readSpecifyRows(File file)throws Exception{
        if (!file.getName().matches("^.+\\.(?i)(xls)$") && !file.getName().matches("^.+\\.(?i)(xlsx)$")) {
            //return ResponseHelper.errorModel(304, "上传文件格式不正确");
            throw new Exception("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (file.getName().matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = new FileInputStream(file);
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        //表的字段
        Map<String, Object > result=new HashMap<>();
        List<Map<Integer,String>> columns = new ArrayList<>();
        Row row1 = sheet.getRow(0);
        int firstCellNum = Convert.toInt(row1.getFirstCellNum());
        int lastCellNum = Convert.toInt(row1.getLastCellNum());
        for (int i = firstCellNum; i < lastCellNum; i++) {
            Map<Integer,String> column=new HashMap<>();
            column.put(i,row1.getCell(i).getStringCellValue());
            System.out.println("列名："+column);
            columns.add(column);
        }
        result.put("field",columns);
        Map<Integer, Object > rowGet=new HashMap<>();
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {//r = n-1 表示从第 n 行开始循环 如果你的第n行开始是数据(例如第五行就是四)
            Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
            if (row == null) {
                continue;
            }
            Cell cell = row.getCell(0);
            if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                continue;
            }
            List<String> cells= new ArrayList<>();
            for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
                row.getCell(i).setCellType(CellType.STRING);
                cells.add(row.getCell(i).getStringCellValue());
            }
            rowGet.put(r,cells);
        }
        result.put("row",rowGet);
        return result;
    }
}
