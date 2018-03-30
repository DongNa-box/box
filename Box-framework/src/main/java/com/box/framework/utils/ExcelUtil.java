package com.box.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:ExcelUtil Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date:     2017年5月9日 下午5:17:28
 * 
 * @author Jay
 * @version
 * @since JDK 1.8
 * @see
 */
public class ExcelUtil {
	public static List<Map> readExcel(MultipartFile file) throws Exception {
		List<Map> valueList = new ArrayList<Map>();
		String fileName = file.getOriginalFilename();
		String ExtensionName = getExtensionName(fileName);
		if (ExtensionName.equalsIgnoreCase("xls")) {
			valueList = readExcel2003(file);
		} else if (ExtensionName.equalsIgnoreCase("xlsx")) {
			valueList = readExcel2007(file);
		}
		return valueList;
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> readExcel2003(MultipartFile file) throws IOException {
		List<Map> valueList = new ArrayList<Map>();
		InputStream fis = null;
		try {
			fis = file.getInputStream();
			HSSFWorkbook wookbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = wookbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			Map<Integer, String> keys = new HashMap<Integer, String>();
			int cells = 0;
			HSSFRow firstRow = sheet.getRow(0);
			if (firstRow != null) {
				cells = firstRow.getPhysicalNumberOfCells();
				for (int j = 0; j < cells; j++) {
					try {
						HSSFCell cell = firstRow.getCell(j);
						String cellValue = getCellValue(cell);
						keys.put(j, cellValue);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			for (int i = 1; i < rows; i++) {
				HSSFRow row = sheet.getRow(i);
				if (row != null) {
					Map<String, Object> val = new HashMap<String, Object>();
					boolean isValidRow = false;
					for (int j = 0; j < cells; j++) {
						try {
							HSSFCell cell = row.getCell(j);
							String cellValue = getCellValue(cell);
							val.put(keys.get(j), cellValue);
							if (!isValidRow && cellValue != null && cellValue.trim().length() > 0) {
								isValidRow = true;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (isValidRow) {
						valueList.add(val);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fis.close();
		}
		return valueList;
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> readExcel2007(MultipartFile file) throws IOException {
		List<Map> valueList = new ArrayList<Map>();
		InputStream fis = null;
		try {
			fis = file.getInputStream();
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet sheet = xwb.getSheetAt(0);
			XSSFRow row;
			Map<Integer, String> keys = new HashMap<Integer, String>();
			row = sheet.getRow(0);
			if (row != null) {

				for (int j = row.getFirstCellNum(); j <= row.getPhysicalNumberOfCells(); j++) {

					if (row.getCell(j) != null) {
						if (!row.getCell(j).toString().isEmpty()) {
							keys.put(j, row.getCell(j).toString());
						}
					} else {
						keys.put(j, "K-R1C" + j + "E");
					}
				}
			}

			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					boolean isValidRow = false;
					Map<String, Object> val = new HashMap<String, Object>();
					for (int j = row.getFirstCellNum(); j <= row.getPhysicalNumberOfCells(); j++) {
						XSSFCell cell = row.getCell(j);
						if (cell != null) {
							String cellValue = null;
							if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
								if (DateUtil.isCellDateFormatted(cell)) {
									cellValue = new DataFormatter().formatRawCellContents(cell.getNumericCellValue(), 0,
											"yyyy-MM-ddHH:mm:ss");
								} else {
									DecimalFormat df = new DecimalFormat("0");    
									cellValue = df.format(cell.getNumericCellValue()); 
									//cellValue = String.valueOf(cell.getNumericCellValue());
								}
							} else {
								cellValue = cell.toString();
							}
							if (cellValue != null && cellValue.trim().length() <= 0) {
								cellValue = null;
							}
							val.put(keys.get(j), cellValue);
							if (!isValidRow && cellValue != null && cellValue.trim().length() > 0) {
								isValidRow = true;
							}
						}
					}

					if (isValidRow) {
						valueList.add(val);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fis.close();
		}

		return valueList;
	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	
	@SuppressWarnings("deprecation")
	private static String getCellValue(HSSFCell cell) {
		DecimalFormat df = new DecimalFormat("#");
		String cellValue = null;
		if (cell == null)
			return null;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
				cellValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
				break;
			}
			cellValue = df.format((int)cell.getNumericCellValue()); 
			break;
		case HSSFCell.CELL_TYPE_STRING:
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = null;
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			cellValue = String.valueOf(cell.getErrorCellValue());
			break;
		}
		if (cellValue != null && cellValue.trim().length() <= 0) {
			cellValue = null;
		}
		return cellValue;
	}
}
