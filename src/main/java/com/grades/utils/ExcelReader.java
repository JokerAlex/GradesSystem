package com.grades.utils;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.text.DecimalFormat;

public class ExcelReader {

    public static Workbook getWb(String path) {
        try {
            return WorkbookFactory.create(new File(path));
        } catch (Exception e) {
            throw new RuntimeException("读取EXCEL文件出错", e);
        }
    }

    public static String getCellValue(HSSFCell cell) {
        String cellValue = "";
        if (cell == null){
            cellValue = "null";
        } else {
          switch (cell.getCellType()) {
              case Cell.CELL_TYPE_STRING:
                  cellValue = cell.getStringCellValue().trim();
                  cellValue = StringUtils.isEmpty(cellValue) ? "NULL" : cellValue;
                  break;
              case Cell.CELL_TYPE_BOOLEAN:
                  cellValue = String.valueOf(cell.getBooleanCellValue());
                  break;
              case Cell.CELL_TYPE_FORMULA:
                  cellValue = String.valueOf(cell.getCellFormula().trim());
                  break;
              case Cell.CELL_TYPE_NUMERIC:
                  if (HSSFDateUtil.isCellDateFormatted(cell)) {
                      cellValue = DateUtil.parseYYYYMMDDDate(cell.getDateCellValue().toString()).toString();
                  } else {
                      cellValue = new DecimalFormat("#.##").format(cell.getNumericCellValue());
                  }
                  break;
              case Cell.CELL_TYPE_BLANK:
                  cellValue = "NULL";
                  break;
              case Cell.CELL_TYPE_ERROR:
                  cellValue = "ERROR";
                  break;
              default:
                  cellValue = cell.toString().trim();
                  break;
          }
        }
        return cellValue;
    }
}
