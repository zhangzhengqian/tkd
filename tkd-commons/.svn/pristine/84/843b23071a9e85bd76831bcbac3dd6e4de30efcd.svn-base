package com.lc.zy.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class ExportExcel {

	/**
	 * Excel 2003
	 */
	private final static String XLS = "xls";
	/**
	 * Excel 2007
	 */
	private final static String XLSX = "xlsx";
	/**
	 * 分隔符
	 */
	private final static String SEPARATOR = ",";

	/**
	 * 由Excel文件的Sheet导出至List
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 */
	public static List<String> exportListFromExcel(File file, int sheetNum) throws IOException {
		return exportListFromExcel(new FileInputStream(file), FilenameUtils.getExtension(file.getName()), sheetNum);
	}

	/**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static List<String> exportListFromExcel(InputStream is, String extensionName, int sheetNum)
			throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}

		return exportListFromExcel(workbook, sheetNum);
	}

	/**
	 * 由指定的Sheet导出至List
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	private static List<String> exportListFromExcel(Workbook workbook, int sheetNum) {

		Sheet sheet = workbook.getSheetAt(sheetNum);

		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

		List<String> list = new ArrayList<String>();

		int minRowIx = sheet.getFirstRowNum();
		int maxRowIx = sheet.getLastRowNum();
		for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
			Row row = sheet.getRow(rowIx);
			StringBuilder sb = new StringBuilder();

			short minColIx = row.getFirstCellNum();
			short maxColIx = row.getLastCellNum();
			for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
				Cell cell = row.getCell(new Integer(colIx));
				CellValue cellValue = evaluator.evaluate(cell);
				if (cellValue == null) {
					continue;
				}
				// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
				// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
				switch (cellValue.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					sb.append(SEPARATOR + cellValue.getBooleanValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					DecimalFormat df = new DecimalFormat("#");
					sb.append(SEPARATOR + df.format(cellValue.getNumberValue()));
					break;
				case Cell.CELL_TYPE_STRING:
					sb.append(SEPARATOR + cellValue.getStringValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				case Cell.CELL_TYPE_ERROR:
					break;
				default:
					break;
				}
			}
			list.add(sb.toString());
		}
		return list;
	}
	
	public static void main(String[] args) {
		String path = "excel.xlsx";  
        List<String> list = null;  
        try {  
            list = ExportExcel.exportListFromExcel(new File(path), 0);  
        } catch (IOException e) {  
        }  
	}
}
