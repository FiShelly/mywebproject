package com.teacherwork.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.teacherwork.domain.DataCount;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.service.IDataCountService;
import com.teacherwork.util.CreateExcel;

@SuppressWarnings("serial")
public class DownloadAction extends ActionSupport {
	private ApplicationContext context ;
	private IDataCountService dataService;
	private YearMsg cyear;
	private String result;
	private String inputPath;
	private String fileName;
	public DownloadAction() {
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		dataService = (IDataCountService) context.getBean("dcService");
	}
	
	public InputStream getTargetFile(){
		ActionContext ct = ActionContext.getContext();
		cyear = (YearMsg) ct.getSession().get("cyearmsg");
		List<DataCount> dcList = dataService.findAllByYearAndTerm(true, Integer.parseInt(result), cyear.getYears());
		try {
			this.fileName = CreateExcel.createExcelFile(dcList);
			return ServletActionContext.getServletContext().getResourceAsStream(getInputPath()+"/"+fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public YearMsg getCyear() {
		return cyear;
	}

	public void setCyear(YearMsg cyear) {
		this.cyear = cyear;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
