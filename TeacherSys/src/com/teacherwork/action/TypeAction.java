package com.teacherwork.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.teacherwork.domain.Type;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.service.ITypeService;

@SuppressWarnings("serial")
public class TypeAction extends ActionSupport {
	private ApplicationContext context ;
	private ITypeService typeService;
	private List<Type> list ;
	private YearMsg msg;
	private String result;
	public TypeAction() {
		list = new ArrayList<Type>();
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		typeService = (ITypeService) context.getBean("typeService");
	}
	
	public String getTypeByAjax(){
		list = findAllByYear();
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
		JSONArray json = JSONArray.fromObject(list,config);
		result = json.toString();
		return SUCCESS;
	}
	
	public List<Type> findAllByYear(){
		list = typeService.findAllByYear(Type.class, getMsg().getYears());
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
