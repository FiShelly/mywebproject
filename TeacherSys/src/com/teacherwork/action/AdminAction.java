package com.teacherwork.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherwork.domain.Admin;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.domain.YearTerm;
import com.teacherwork.service.IAdminService;
import com.teacherwork.service.IYearMsgService;
import com.teacherwork.util.MessageUtil;

@SuppressWarnings("serial")
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {
	private ApplicationContext context ;
	private IAdminService adminService;
	private Admin model = new Admin();
	private IYearMsgService ymService;
	private List<YearMsg> list ;
	private YearMsg msg ;
	private String result;
	
	public AdminAction(){
		msg = new YearMsg();
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		adminService = (IAdminService) context.getBean("adminService");
		ymService = (IYearMsgService) context.getBean("ymService");
	}

	public String updateCurYear(){
		ActionContext ct = ActionContext.getContext();
		try {
			YearMsg tempYm = ymService.findByCurrent(true);
			boolean flag = false;
			flag = ymService.doUpdateCurYear(tempYm.getYears(), false);
			if(flag){
				flag = ymService.doUpdateCurYear(msg.getYears(), true);
				list = ymService.findAll(YearMsg.class);
				ct.getSession().put("allYears", list);
			}
			result = flag + "";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updatePw(){
		try {
			result = adminService.doUpdate(model)+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String addYear(){
		ActionContext ct = ActionContext.getContext();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String fdate = format.format(date);
		YearTerm last = new YearTerm();
		YearTerm next = new YearTerm();
		last.setLastTerm(true);
		next.setLastTerm(false);
		msg.setDate(fdate);
		msg.setLastTerm(last);
		msg.setNextTerm(next); 
		try {
			result = ymService.doInsert(msg)+"";
			list = ymService.findAll(YearMsg.class);
			ct.getSession().put("allYears", list); 
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String checkYearExist(){
		try {
			msg =  ymService.findById(YearMsg.class, msg.getYears());
			if(msg != null){
				result=true+"";
			} else {
				result = false+"";
			}
		} catch (Exception e) {
			result = true+"";
		}
		return SUCCESS;
	}
	
	public String login(){
		ActionContext ct = ActionContext.getContext();
		String view = "login_failed";
		boolean flag = false;
		try {
			flag = adminService.findLogin(model);
			if(flag){
				model = adminService.findById(Admin.class, model.getLoginId());
				list = ymService.findAll(YearMsg.class);
				msg = ymService.findByCurrent(true);
				ct.getSession().put("admin",model);
				ct.getSession().put("allYears", list); 
				ct.getSession().put("cyearmsg",msg );
				view = "admin_index";
			} else {
				ct.put("info", MessageUtil.get("user.login.false"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ct.put("info", MessageUtil.get("user.login.false"));
		}
		ct.put("flag", flag);
		return view;
	}
	
	@Override
	public Admin getModel() {
		return model;
	}

	public List<YearMsg> getList() {
		return list;
	}

	public void setList(List<YearMsg> list) {
		this.list = list;
	}

	public YearMsg getMsg() {
		return msg;
	}

	public void setMsg(YearMsg msg) {
		this.msg = msg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
