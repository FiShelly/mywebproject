package com.teacherwork.action;


import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.teacherwork.domain.DataCount;
import com.teacherwork.domain.User;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.service.IDataCountService;

@SuppressWarnings("serial")
public class DataCountAction extends ActionSupport {
	private ApplicationContext context ;
	private IDataCountService dataService;
//	private List<DataCount> list ;
	private YearMsg cyear;
	private String result;
	private String inputPath;
	private String fileName;
	public DataCountAction() {
//		list = new ArrayList<DataCount>();
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		dataService = (IDataCountService) context.getBean("dcService");
	}
	
	public String getDataCountByAjax(){
		ActionContext ct = ActionContext.getContext();
		User tuser = (User) ct.getSession().get("user");
		cyear = (YearMsg) ct.getSession().get("cyearmsg");
		DataCount dc = dataService.findViewById(tuser.getLoginId(), cyear.getYears(),false);
		JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object arg0, String arg1, Object arg2) {
                if (arg1.equals("yearMsg")) {
                        return true;
                } else {
                    return false;
                }
            }
        });
        JSONObject json = JSONObject.fromObject(dc);
		result = json.toString();
		return SUCCESS;
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
