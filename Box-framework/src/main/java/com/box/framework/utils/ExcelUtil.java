package com.box.framework.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


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
	 private final static ExcelUtil jxlTable = new ExcelUtil();

	    public static ExcelUtil getInstance() {
	        return jxlTable;
	    }

	    public ExcelUtil() {
	    }
	    /**
	     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	     * @param file 读取数据的源Excel
	     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	     * @return 读出的Excel中数据的内容
	     * @throws FileNotFoundException
	     * @throws IOException
	     */
	    @SuppressWarnings("deprecation")
		public static String[][] getData(File file, int ignoreRows)
	           throws FileNotFoundException, IOException {
	       List<String[]> result = new ArrayList<String[]>();
	       int rowSize = 0;
	       BufferedInputStream in = new BufferedInputStream(new FileInputStream(
	              file));
	       // 打开HSSFWorkbook
	       POIFSFileSystem fs = new POIFSFileSystem(in);
	       HSSFWorkbook wb = new HSSFWorkbook(fs);
	       HSSFCell cell = null;
	       for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
	           HSSFSheet st = wb.getSheetAt(sheetIndex);
	           // 第一行为标题，不取
	           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
	              HSSFRow row = st.getRow(rowIndex);
	              if (row == null) {
	                  continue;
	              }
	              int tempRowSize = row.getLastCellNum() + 1;
	              if (tempRowSize > rowSize) {
	                  rowSize = tempRowSize;
	              }
	              String[] values = new String[rowSize];
	              Arrays.fill(values, "");
	              boolean hasValue = false;
	              for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
	                  String value = "";
	                  cell = row.getCell(columnIndex);
	                  if (cell != null) {
	                     // 注意：一定要设成这个，否则可能会出现乱码
	                    // ((Object) cell).setEncoding(HSSFCell.ENCODING_UTF_16);
	                     switch (cell.getCellType()) {
	                     case HSSFCell.CELL_TYPE_STRING:
	                         value = cell.getStringCellValue();
	                         break;
	                     case HSSFCell.CELL_TYPE_NUMERIC:
	                         if (HSSFDateUtil.isCellDateFormatted(cell)) {
	                            Date date = cell.getDateCellValue();
	                            if (date != null) {
	                                value = new SimpleDateFormat("yyyy-MM-dd")
	                                       .format(date);
	                            } else {
	                                value = "";
	                            }
	                         } else {
	                            value = new DecimalFormat("0").format(cell
	                                   .getNumericCellValue());
	                         }
	                         break;
	                     case HSSFCell.CELL_TYPE_FORMULA:
	                         // 导入时如果为公式生成的数据则无值
	                         if (!cell.equals("")) {
	                        	 cell.setCellType(Cell.CELL_TYPE_STRING);
	                            value = cell.getStringCellValue();
	                         } else {
	                        	 cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	                            value = cell.getNumericCellValue() + "";
	                         }
	                         break;
	                     case HSSFCell.CELL_TYPE_BLANK:
	                         break;
	                     case HSSFCell.CELL_TYPE_ERROR:
	                         value = "";
	                         break;
	                     case HSSFCell.CELL_TYPE_BOOLEAN:
	                         value = (cell.getBooleanCellValue() == true ? "Y"
	                                : "N");
	                         break;
	                     default:
	                         value = "";
	                     }
	                  }
	                  if (columnIndex == 0 && value.trim().equals("")) {
	                     break;
	                  }
	                  values[columnIndex] = rightTrim(value);
	                  hasValue = true;
	              }

	              if (hasValue) {
	                  result.add(values);
	              }
	           }
	       }
	       in.close();
	       String[][] returnArray = new String[result.size()][rowSize];
	       for (int i = 0; i < returnArray.length; i++) {
	           returnArray[i] = (String[]) result.get(i);
	       }
	       return returnArray;
	    }

	    /**
	     * 去掉字符串右边的空格
	     * @param str 要处理的字符串
	     * @return 处理后的字符串
	     */
	     public static String rightTrim(String str) {
	       if (str == null) {
	           return "";
	       }
	       int length = str.length();
	       for (int i = length - 1; i >= 0; i--) {
	           if (str.charAt(i) != 0x20) {
	              break;
	           }
	           length--;
	       }
	       return str.substring(0, length);
	    }
	     
	   

	     public boolean createTable(String[][] body, String filePath,int rowLength) {
	         boolean createFlag = true;
	         WritableWorkbook book;
	         try {
	             // 根据路径生成excel文件
	        	 File targetFile = new File(filePath);
	        	 if(!targetFile.exists()){
	        		    //先得到文件的上级目录，并创建上级目录，在创建文件
	        		 targetFile.getParentFile().mkdirs();
	        		    try {
	        		        //创建文件
	        		    	targetFile.createNewFile();
	        		    } catch (IOException e) {
	        		        e.printStackTrace();
	        		    }
	        		}
	             book = Workbook.createWorkbook(targetFile);
	             // 创建一个sheet名为"表格"
	             WritableSheet sheet = book.createSheet("内部格式", 0);
	             WritableSheet sheet1 = book.createSheet("在线格式", 1);
	         
	             // 设置NO列宽度
	             sheet.setColumnView(1, 5);
	             // 去掉整个sheet中的网格线
	             sheet.getSettings().setShowGridLines(false);
	             Label tempLabel = null;
	             
	             // 表体输出
	             int bodyLen = body.length; 
	             String[][] bodyTempArr = body ;
	             // 循环写入表体内容
	             for(int i=0;i<rowLength;i++) {
	                   for(int j=0;j<body[i].length;j++) {
	                	   if (i<=20) {
	                		   WritableCellFormat tempCellFormat = null;
	                           tempCellFormat = getBlueCellStyle();
	                           WritableCellFormat rwritableCellFormat = null;
	                           rwritableCellFormat=getRedCellStyle();
	                           WritableCellFormat writableCellFormat = null;
	                           writableCellFormat=getBodyCellStyle();
	                           if (tempCellFormat != null) {
	                               if (j == 0 || j == (bodyTempArr.length - 1)) {
	                                   tempCellFormat.setAlignment(Alignment.CENTRE);
	                               }
	                           }
	                           if((j==0)||(i==3)){
	                        	   tempLabel = new Label(1 + j, 2 + i, bodyTempArr[i][j],
	                        			   writableCellFormat);
	                           }else if (j==6&i!=3) {
	                        	   tempLabel = new Label(1 + j, 2 + i, bodyTempArr[i][j],
	                        			   rwritableCellFormat);
	                           }else {
	                        	   tempLabel = new Label(1 + j, 2 + i, bodyTempArr[i][j],
	                        			   tempCellFormat);
							   }
	                           
	                           sheet.addCell(tempLabel);
	                           /*sheet.mergeCells(6,5,6,6);
		                       sheet.mergeCells(2,3,4,3);*/
						}else {
							 WritableCellFormat tempCellFormat = null;
	                         tempCellFormat = getBlueCellStyle();
	                         WritableCellFormat rwritableCellFormat = null;
	                         rwritableCellFormat=getRedCellStyle();
	                         WritableCellFormat writableCellFormat = null;
	                         writableCellFormat=getBodyCellStyle();
	                         if (tempCellFormat != null) {
	                             if (j == 0 || j == (bodyTempArr.length - 1)) {
	                                 tempCellFormat.setAlignment(Alignment.CENTRE);
	                             }
	                         }
	                         if((j==0)||(i==24)){
	                      	   tempLabel = new Label(1 + j, 2 + i-20, bodyTempArr[i][j],
	                      			 writableCellFormat);
	                         }else if (j==6&i!=24) {
	                      	   tempLabel = new Label(1 + j, 2 + i-20, bodyTempArr[i][j],
	                      			   rwritableCellFormat);
	                         }else {
	                      	   tempLabel = new Label(1 + j, 2 + i-20, bodyTempArr[i][j],
	                      			 tempCellFormat);
							   }
	                        
	                         sheet1.addCell(tempLabel);
	                        /* sheet.mergeCells(6,5,6,6);
	                         sheet.mergeCells(2,3,4,3);*/
						}
	                     
	                 }
	             }
	             book.write();
	             book.close();
	         } catch (IOException e) {
	             createFlag = false;
	             System.out.println("EXCEL创建失败！");
	             e.printStackTrace();
	         } catch (RowsExceededException e) {
	             createFlag = false;
	             System.out.println("EXCEL单元设置创建失败！");
	             e.printStackTrace();
	         } catch (WriteException e) {
	             createFlag = false;
	             System.out.println("EXCEL写入失败！");
	             e.printStackTrace();
	         }

	         return createFlag;
	     }

	     public WritableCellFormat getBlueCellStyle() {
	         WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,
	                 WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
	         WritableCellFormat bodyFormat = new WritableCellFormat(font);
	         try {
	             // 设置单元格背景色：表体为白色
	             bodyFormat.setBackground(Colour.WHITE);
	             // 设置表头表格边框样式
	             // 整个表格线为细线、黑色
	             bodyFormat
	                     .setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GRAY_50);
	         } catch (WriteException e) {
	             System.out.println("表体单元格样式设置失败！");
	         }
	         return bodyFormat;
	     }

	     public WritableCellFormat getRedCellStyle() {
	         WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,
	                 WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.RED);
	         WritableCellFormat bodyFormat = new WritableCellFormat(font);
	         try {
	             // 设置单元格背景色：表体为白色
	             bodyFormat.setBackground(Colour.WHITE);
	             // 设置表头表格边框样式
	             // 整个表格线为细线、黑色
	             bodyFormat
	                     .setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GRAY_25);
	         } catch (WriteException e) {
	             System.out.println("表体单元格样式设置失败！");
	         }
	         return bodyFormat;
	     }
	     public WritableCellFormat getBodyCellStyle() {
	         WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,
	                 WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
	         WritableCellFormat bodyFormat = new WritableCellFormat(font);
	         try {
	             // 设置单元格背景色：表体为白色
	             bodyFormat.setBackground(Colour.WHITE);
	             // 设置表头表格边框样式
	             // 整个表格线为细线、黑色
	             bodyFormat
	                     .setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GRAY_25);
	         } catch (WriteException e) {
	             System.out.println("表体单元格样式设置失败！");
	         }
	         return bodyFormat;
	     }
	    
}
