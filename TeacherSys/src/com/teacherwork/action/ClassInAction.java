package com.teacherwork.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.teacherwork.domain.ClassIn;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.service.IClassInService;

@SuppressWarnings("serial")
public class ClassInAction extends ActionSupport {
	private ApplicationContext context ;
	private IClassInService icService;
	private List<ClassIn> list ;
	private YearMsg msg;
	private String result;
	public ClassInAction() {
		list = new ArrayList<ClassIn>();
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		icService = (IClassInService) context.getBean("cService");
	}
	
	public String getIcByAjax(){
		ClassIn classin = icService.findById(ClassIn.class, getMsg().getYears());
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
        JSONObject json = JSONObject.fromObject(classin);
		result = json.toString();
		return SUCCESS;
	}
	
	public List<ClassIn> findAllByYear(){
		list = icService.findAllByYear(ClassIn.class, getMsg().getYears());
		return list;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public YearMsg getMsg() {
		return msg;
	}

	public void setMsg(YearMsg msg) {
		this.msg = msg;
	}
}
