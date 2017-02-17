package com.lc.zy.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


/**
 * @author liangc
 * @param <T>
 * 			      应用泛型，代表任意一个符合javabean风格的类 
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx() 
 *            byte[]表jpg格式的图片数据 
 */
public class ExcelUtil<T>{
	
	/**
	 * 日期格式
	 */
	public static String pattern = "yyyy-MM-dd";
	
    /** 
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上 
     *  
     * @param title 
     *            表格标题名 
     * @param headers 
     *            表格属性列名数组 
     * @param dataset 
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的 
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据) 
     * @param out 
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中 
     * @param pattern 
     *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd" 
     */  
    @SuppressWarnings("deprecation")
	public void exportExcel(String title, String[] headers,
            Collection<T> dataset, OutputStream out, String pattern){
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index);
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式  
        style.setFont(font);  
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);
        //生成标题行
        String[] column = createHeader(row, style, headers);
        // 遍历集合数据，产生数据行  
        Iterator<T> it = dataset.iterator();
        int index = 0;
        int sheetIndex = 1;
        while (it.hasNext()){
            index ++ ;
            //如果数据的行数超过65535，建立一个新sheet,继续写
            if(65536 == index){
            	index = 0;
            	sheet = null;
            	sheet = workbook.createSheet(title + sheetIndex);
            	sheetIndex ++;
                // 设置表格默认列宽度为15个字节  
                sheet.setDefaultColumnWidth((short) 15);
                row = null;
                row = sheet.createRow(0);
                //生成标题行
                createHeader(row, style, headers);
            }
            row = sheet.createRow(index);  
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值  
//            Field[] fields = t.getClass().getDeclaredFields();
            int temp = 0;
            for (short i = 0; i < column.length; i++){
            	String fieldName = column[i];
            	if("serialVersionUID".equals(fieldName)){
            		temp = 1;
            		continue;
            	}
                HSSFCell cell = row.createCell(i-temp);
                cell.setCellStyle(style2);  
                String getMethodName = "get"  
                        + fieldName.substring(0, 1).toUpperCase()  
                        + fieldName.substring(1);  
                try{  
                    Class<? extends Object> tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});  
                    // 判断值的类型后进行强制类型转换  
                    String textValue = null;
                    if (value instanceof Boolean){
                        boolean bValue = (Boolean) value;
                        textValue = "true";  
                        if (!bValue){
                            textValue = "false";  
                        }
                    }else if (value instanceof Date){  
                        Date date = (Date) value;  
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
                        textValue = sdf.format(date);  
                    }else{  
                        // 其它数据类型都当作字符串简单处理  
                    	if(null != value){
                    		textValue = value.toString(); 
                    	}else{
                    		textValue = "";
                    	}
                    }  
                    // 利用正则表达式判断textValue是否全部由数字组成  
                    if (textValue != null)
                    {  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                        Matcher matcher = p.matcher(textValue);  
                        if (matcher.matches())  
                        {  
                            // 是数字当作double处理  
                            cell.setCellValue(Double.parseDouble(textValue));  
                        }else{  
                            HSSFRichTextString richString = new HSSFRichTextString(textValue);  
                            richString.applyFont(font3);  
                            cell.setCellValue(richString);  
                        }  
                    }  
                }catch (NoSuchMethodException e){  
                    e.printStackTrace();
                }catch (IllegalAccessException e){  
                    e.printStackTrace();  
                }catch (InvocationTargetException e){  
                    e.printStackTrace();  
                }finally{
                    // 清理资源 
                }
            }
        }try{  
            workbook.write(out);  
        }catch (IOException e){  
            e.printStackTrace();  
        }finally{
        	try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        }
    }
    
    /**
     * 生成Excel的标题行
     * @param row
     * @param headers 格式为   中文名称::英文参数名称     (用户名::userName)
     */
    @SuppressWarnings("deprecation")
	public static String[] createHeader(HSSFRow row, HSSFCellStyle style, String[] headers){
    	String[] retArgs = new String[headers.length];
    	String[] cnAndEn = null;
    	for (short i = 0; i < headers.length; i++)  
        {  
    		cnAndEn = headers[i].split("::");
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(cnAndEn[0]);
            cell.setCellValue(text);
            
            retArgs[i] = cnAndEn[1];
        }
    	return retArgs;
    }
    
    /**
	 * 生成xls文件名 
	 * @param targetStr	有特殊意义的字符串,例如 角色：role
	 * @return
	 */
	public static String createtFileName(String targetStr) {
		Date dt = new Date(System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName= fmt.format(dt);
		fileName =targetStr+"_"+ fileName + ".xls"; 
		return fileName;
	}
}  