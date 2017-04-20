package com.teacherwork.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.teacherwork.domain.DataCount;

public class CreateExcel {

	public static String createExcelFile(List<DataCount> dcList) throws IOException{
		String outputFile = ServletActionContext.getServletContext().getRealPath("/downLoad")+"/";
		IPTimeStamp ips = new IPTimeStamp(ServletActionContext.getRequest().getRemoteAddr());
		
		String fileName = ips.getIPTimeRand() + ".xls" ;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(dcList.get(0).getYears()+"年工作量统计");
		HSSFRow row = sheet.createRow((short) 0);
		
		HSSFCell termCell = row.createCell(0);
		termCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		termCell.setCellValue("学期");

		HSSFCell nameCell = row.createCell(1);
		nameCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		nameCell.setCellValue("教师姓名");

		HSSFCell postCell = row.createCell(2);
		postCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		postCell.setCellValue("职称");

		HSSFCell titleCell = row.createCell(3);
		titleCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		titleCell.setCellValue("职务");

		HSSFCell postCoeCell = row.createCell(4);
		postCoeCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		postCoeCell.setCellValue("职称系数");

		HSSFCell titleCoeCell = row.createCell(5);
		titleCoeCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		titleCoeCell.setCellValue("职务补贴");

		HSSFCell inWorkCell = row.createCell(6);
		inWorkCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		inWorkCell.setCellValue("额内工作量");

		HSSFCell outWrokCell = row.createCell(7);
		outWrokCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		outWrokCell.setCellValue("额外工作量");

		HSSFCell allWorkCell = row.createCell(8);
		allWorkCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		allWorkCell.setCellValue("总工作量");

		HSSFCell moenyCell = row.createCell(9);
		moenyCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		moenyCell.setCellValue("工作量金额");

		for(int i = 1;i<10;i++){
			sheet.setColumnWidth(i, 15* 256);
		}
		
		for (int i = 1; i < dcList.size()+1; i++) {
			DataCount dc = dcList.get(i - 1);
			row = sheet.createRow((short) i);
			termCell = row.createCell(0);
			termCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(dc.getTerm() == 0){
				termCell.setCellValue("上");
			} else if(dc.getTerm() == 1){
				termCell.setCellValue("下");
			} else if(dc.getTerm() == 2){
				termCell.setCellValue("全部");
			}
			

			nameCell = row.createCell(1);
			nameCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			nameCell.setCellValue(dc.getUser().getUserName());

			postCell = row.createCell(2);
			postCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			postCell.setCellValue(dc.getUser().getTitle().getTitleName());

			titleCell = row.createCell(3);
			titleCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			titleCell.setCellValue(dc.getUser().getPost().getPostName());

			postCoeCell = row.createCell(4);
			postCoeCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			postCoeCell.setCellValue(dc.getUser().getTitle().getCoefficient());

			titleCoeCell = row.createCell(5);
			titleCoeCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			titleCoeCell.setCellValue(dc.getUser().getPost().getCoefficient());

			inWorkCell = row.createCell(6);
			inWorkCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			inWorkCell.setCellValue(dc.getInWork());

			outWrokCell = row.createCell(7);
			outWrokCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			outWrokCell.setCellValue(dc.getInOut());

			allWorkCell = row.createCell(8);
			allWorkCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			allWorkCell.setCellValue(dc.getAllSal());

			moenyCell = row.createCell(9);
			moenyCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			moenyCell.setCellValue(dc.getAllSal());
		}
		 FileOutputStream fOut = new FileOutputStream(outputFile+fileName);  
         // 把相应的Excel 工作簿存盘  
         workbook.write(fOut);  
         fOut.flush();  
         // 操作结束，关闭文件  
         fOut.close();  
         return fileName;
	}

}
