package org.simple.crawerDemo.crawer;

import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * 写入excel
 * @author ge
 *
 */
public class ExcelUtil {

	/**
	 * 追加写入excel
	 * 
	 * @param filePath
	 * @param list
	 * @param c
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	public static <T> void appendWrite(String filePath, List<T> list, Class c)
			throws Exception {
		Workbook wb = ReadFactory.readExcel(filePath);
		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		OutputStream outPutStream = ReadFactory.writeFile(filePath);
		int i = 1;
		for (T t : list) {
			Row content = sheet.createRow(rowNum + i);
			i++;
			List<CellVo> contentCells = BeanUtil.beanToCell(t, c);
			for (CellVo cellVo : contentCells) {
				Cell cell = content.createCell(cellVo.getIndex());
				cell.setCellValue(cellVo.getValue().toString());
			}
		}
		outPutStream.flush();
		wb.write(outPutStream);
		outPutStream.close();
	}

	public static CellStyle createHeadStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		style.setAlignment(CellStyle.ALIGN_CENTER);
		// 生成一个字体
		Font font = wb.createFont();
		font.setColor(IndexedColors.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}

	public static CellStyle createDateStyle(Workbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
		DataFormat format = wb.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("m/d/yy h:mm"));
		return cellStyle;
	}

	public static Sheet getSheet(String name, Workbook wb) {
		Sheet sheet = null;
		if (StringUtils.isEmpty(name)) {
			sheet = wb.createSheet();
		} else {
			sheet = wb.getSheet(name);
			if (sheet == null) {
				sheet = wb.createSheet(name);
			}
		}
		return sheet;
	}
	
}
