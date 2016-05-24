package org.ltsh.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {
	/**
	 * 用于将对象写进excel
	 * @param title 标题
	 * @param fields 需要进行写入的字段
	 * @param showColumn 需要根据值显示中文提示的列
	 * @param values 值集合
	 * @throws Exception
	 */
	public static String derivedExcel(String path, String title, String[] fields, String[] showColumn, List values) throws Exception {
		HSSFWorkbook wb = getHSSFWorkbook(title, fields, showColumn, values);

		String fileName = path+"/upFile/temp/"+java.util.Calendar.getInstance().getTimeInMillis()+".xls";
		File file = new File(fileName);
		if(!file.exists()){
			new File(path+"/upFile/temp/").mkdirs();
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		fos.flush();
		fos.close();
		return fileName;
	}
	/**
	 * 用于将对象写进excel
	 * @param title 标题
	 * @param fields 需要进行写入的字段
	 * @param showColumn 需要根据值显示中文提示的列
	 * @param values 值集合
	 * @throws Exception
	 */
	public static InputStream derivedExcel(String title, String[] fields, String[] showColumn, List values) throws Exception {
		HSSFWorkbook wb = getHSSFWorkbook(title, fields, showColumn, values);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		
		baos.flush();
		baos.close();
	    return bais;
	}
	private static HSSFWorkbook getHSSFWorkbook(String title, String[] fields, String[] showColumn, List values) throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();//创建工作表
	    CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, fields.length-1);//新建一个合并单元格对象
	    sheet.addMergedRegion(rangeAddress);//添加合并单元格
	    HSSFRow row = sheet.createRow(0);//创建行
	    HSSFCellStyle titleCellStyle = wb.createCellStyle();//创建单元格样式
	    HSSFFont font =wb.createFont();//字体
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//设置加粗
	    font.setFontHeightInPoints((short)15);//设置字体大小
	    titleCellStyle.setFont(font);//将字体添加到单元格样式
//	    createCellStyle.setWrapText(true);
	    titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
	    titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
	    HSSFCell cell = row.createCell(0);//创建单元格
	    cell.setCellStyle(titleCellStyle);//给单元格添加样式
	    cell.setCellValue(new HSSFRichTextString(title));//添加文本
	    row.setHeightInPoints(50);//设置高度
	    HSSFRow row2 = sheet.createRow(1);
	    row2.setHeightInPoints(20);
	    HSSFCellStyle topCellStyle = wb.createCellStyle();
	    topCellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
	    topCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
		for (int i = 0; i < fields.length; i++) {//生成表头
			HSSFCell cell2 = row2.createCell(i);
			HSSFSheet sheet1 = cell2.getSheet();
			sheet1.autoSizeColumn(cell2.getColumnIndex());
			String[] split = fields[i].split("_");
			cell2.setCellValue(new HSSFRichTextString(split[0]));
			cell2.setCellStyle(topCellStyle);
		}
		if(values != null){
			HSSFCellStyle cellStyle = wb.createCellStyle();//单元格样式
//			cellStyle.setWrapText(true);
			cellStyle.setBorderBottom((short)1);//设置边框
			cellStyle.setBorderLeft((short)1);
			cellStyle.setBorderRight((short)1);
			cellStyle.setBorderTop((short)1);
			for (int i = 0; i < values.size(); i++) {
				HSSFRow row3 = sheet.createRow(i+2);
				Object object = values.get(i);//获取对象
				for (int ii = 0; ii < fields.length; ii++) {
					HSSFCell createCell = row3.createCell(ii);
					createCell.setCellStyle(cellStyle);
					String[] split = fields[ii].split("_");
					if(object != null && object.getClass().isArray()){
						recursion(((Object[])object)[ii], createCell);
					}else if(split.length>1){
						recursion(split[1], object, showColumn, createCell);
					} else {
						recursion(split[0], object, showColumn, createCell);
					}
				}
			}
		}
		return wb;
	}
	private static void recursion(Object obj, HSSFCell cell) throws Exception{
		setCellValue(cell, obj, obj.getClass());
	}
	/**
	 * 私有方法,用于derivedExcel方法的字段有对象的递归调用
	 * @param columnName字段名
	 * @param obj 对象
	 * @param showColumn 需要根据值显示中文提示的列
	 * @param cell 单元格对象
	 * @throws Exception
	 */
	private static void recursion(String columnName, Object obj, 
			String[] showColumn, HSSFCell cell) throws Exception{
		if(obj == null)
			return;
		Class<? extends Object> class1 = obj.getClass();
		Object invoke = null;
		Class<?> toType = null;
		if(columnName.indexOf(".") != -1){
			String substring = columnName.substring(0, columnName.indexOf("."));
			columnName = columnName.substring(columnName.indexOf(".") + 1 , columnName.length());
			Method getMethod = class1.getMethod("get" + columnName.substring(0,1)
					.toUpperCase()+columnName.substring(1));
			invoke = getMethod.invoke(obj);
			toType = getMethod.getReturnType();
			if(invoke != null) {
				recursion(columnName, invoke, showColumn, cell);
			}
		} else {
			
			Method getMethod = class1.getMethod("get" + columnName.substring(0,1)
					.toUpperCase()+columnName.substring(1));
			invoke = getMethod.invoke(obj);
			toType = getMethod.getReturnType();
			boolean isrn = true;
			if(showColumn != null){
				for (int i = 0; i < showColumn.length; i++) {
					String[] split = showColumn[i].split("_");
					if(columnName.equals(split[0])){
						String[] split2 = split[1].split(",");
						for (int j = 0; j < split2.length; j++) {
							String[] split3 = split2[j].split(":");
							if(split3[1].equals(String.valueOf(invoke))) {
								setCellValue(cell, split3[0], String.class);
								isrn = false;
							}
						}
					}
				}
			} 
			if(invoke != null && isrn)
				setCellValue(cell, invoke, toType);
		}
	}
	/**
	 * 私有方法,用于填充单元格
	 * @param cell 单元格
	 * @param value 值
	 * @param toType 类型
	 */
	private static void setCellValue(HSSFCell cell, Object value, Class toType) {
		HSSFSheet sheet = cell.getSheet();
//		sheet.autoSizeColumn(cell.getColumnIndex());
		if ((toType == Integer.class) || (toType == Integer.TYPE))
			cell.setCellValue(Integer.valueOf((int) DefaultTypeValue.longValue(value)));
        if ((toType == Double.class) || (toType == Double.TYPE))
        	cell.setCellValue(new Double(DefaultTypeValue.doubleValue(value)));
        if ((toType == Boolean.class) || (toType == Boolean.TYPE))
        	cell.setCellValue(DefaultTypeValue.booleanValue(value) ? Boolean.TRUE : Boolean.FALSE);
        if ((toType == Byte.class) || (toType == Byte.TYPE))
        	cell.setCellValue(Byte.valueOf((byte) DefaultTypeValue.longValue(value)));
        if ((toType == Character.class) || (toType == Character.TYPE))
        	cell.setCellValue(new Character((char) DefaultTypeValue.longValue(value)));
        if ((toType == Short.class) || (toType == Short.TYPE))
        	cell.setCellValue(Short.valueOf((short) DefaultTypeValue.longValue(value)));
        if ((toType == Long.class) || (toType == Long.TYPE))
        	cell.setCellValue(Long.valueOf(DefaultTypeValue.longValue(value)));
        if ((toType == Float.class) || (toType == Float.TYPE))
        	cell.setCellValue(new Float(DefaultTypeValue.doubleValue(value)));
        if (toType == String.class){
        	String stringValue = DefaultTypeValue.stringValue(value);    		
        	if(stringValue.getBytes().length!=stringValue.length()){
	    		sheet.setColumnWidth(cell.getColumnIndex(), stringValue.getBytes().length * 256);
        	}
//        	row.set(stringValue.length());
        	cell.setCellValue(stringValue);
        }
	}
	
}
