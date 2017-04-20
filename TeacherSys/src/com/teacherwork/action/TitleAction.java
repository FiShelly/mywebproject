package com.teacherwork.action;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.opensymphony.xwork2.ActionSupport;
import com.teacherwork.domain.Title;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.service.ITitleService;

@SuppressWarnings("serial")
public class TitleAction extends ActionSupport {
	private ApplicationContext context ;
	private ITitleService titleService;
	private List<Title> list ;
	private YearMsg msg;
	private String result;
	public TitleAction() {
		list = new ArrayList<Title>();
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		titleService = (ITitleService) context.getBean("titleService");
	}
	
	public String getTitleByAjax(){
		list = findAllByYear();
		JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object arg0, String arg1, Object arg2) {
                 if (arg1.equals("coefficient") ||arg1.equals("yearMsg")) {
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
	
	public List<Title> findAllByYear(){
		list = titleService.findAllByYear(Title.class, getMsg().getYears());
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
