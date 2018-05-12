package com.grades.utils;


import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel
    private static int readProgress = 0;

    public static int getReadProgress(){
        return readProgress;
    }

    /**
     * 获取工作簿
     * @param fileName
     * @return wb
     * @throws Exception
     */
    public static Workbook getWorkbook(String fileName) throws Exception{
        Workbook wb = null;
        InputStream inStr = new FileInputStream(fileName);
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 获取工作表
     * @param wb
     * @param sheetIndex
     * @return sheet
     */
    public static Sheet getSheet(Workbook wb,int sheetIndex){
        if (wb == null){
            throw new RuntimeException("工作簿为空");
        }
        int sheetSize = wb.getNumberOfSheets();
        if (sheetIndex < 0 || sheetIndex > sheetSize){
            throw new RuntimeException("工作表获取错误");
        }
        return wb.getSheetAt(sheetIndex);
    }

    /**
     * 获取单元格内容
     * @param cell
     * @return cellValue
     */
    public static String getCellValue(Cell cell) {
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

    /**
     * 获取工作表所有行的数据
     * @param sheet
     * @param startLine
     * @param endLine
     * @return List<List<String>>
     */
    public static List<List<String>> getExcelRows(Sheet sheet, int startLine, int endLine){
        readProgress = 0;
        List<List<String>> listResult = new ArrayList<List<String>>();
        // 如果开始行号和结束行号都是-1的话，则全表读取
        if (startLine == -1)
            startLine = 0;
        if (endLine == -1) {
            endLine = sheet.getLastRowNum() + 1;
        } else {
            endLine += 1;
        }

        for (int i = startLine; i < endLine; i++) {
            Row row = sheet.getRow(i);
            if (row == null){
                //空行直接跳过
                continue;
            }
            //获取列数
            int rowSize = row.getLastCellNum();
            //接受每一行的结果
            List<String> rowList = new ArrayList<String>();
            for (int j = 0; j < rowSize; j++) {
                Cell cell = row.getCell(j);
                String temp = getCellValue(cell);
                rowList.add(j,temp);
            }
            listResult.add(i,rowList);
            readProgress = i;
        }
        return listResult;
    }

    /**
     * 获取工作表总行数
     * @param sheet
     * @return
     */
    public static int getRowNumber(Sheet sheet){
        return sheet.getPhysicalNumberOfRows();
    }
}
