package com.teacherwork.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.teacherwork.domain.Message;
import com.teacherwork.domain.Page;
import com.teacherwork.domain.User;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.service.IMessageService;

@SuppressWarnings("serial")
public class MessageAction extends ActionSupport {
	private ApplicationContext context ;
	private IMessageService msgService;
	private List<Message> list ;
	private YearMsg ym;
	private String result;
	private Page page;
	private Message msg;
	public MessageAction() {
		page = new Page();
		list = new ArrayList<Message>();
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		msgService = (IMessageService) context.getBean("msgService");
	}
	
	public String delete(){
		try {
			result = msgService.doDelete(Message.class, msg.getId())+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String addMsg(){
		msg.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		try {
			result = msgService.doInsert(msg)+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}

	public String findRepeat(){
		ActionContext ct = ActionContext.getContext();
		User user = (User) ct.getSession().get("user");
		ym = (YearMsg) ct.getSession().get("cyearmsg");
		try {
			result = msgService.findRepeatSub(user.getLoginId(), ym.getYears())+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updateReadState(){
		ActionContext ct = ActionContext.getContext();
		User model = (User) ct.getSession().get("user");
		try {
			result = msgService.updateReadState(Boolean.parseBoolean(result), msg.getId()+"")+"";
			ct.getSession().put("msgCount", model.getRole() == 1?msgService.findNotReadOrReadCount(model.getLoginId(), false):msgService.findNotHandlerOrHandlerCount(0));
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updatePassState(){
		ActionContext ct = ActionContext.getContext();
		User user = (User) ct.getSession().get("user");
		ym = (YearMsg) ct.getSession().get("cyearmsg");
		try {
			result = msgService.updatePassState(msg.getPassState(), msg.getId()+"")+"";
		} catch (Exception e) {
			e.printStackTrace();
			result = false+"";
		}
		if(result.equals("true")){
			if(msg.getPassState()==1){
				msg.setContent("提交的批改申请已被同意，请尽快修改。");
			} else {
				msg.setContent("提交的批改申请被拒绝，具体请与教务员交流。");
			}
			msg.setFromId(user.getLoginId());
			msg.setFromName(user.getUserName());
			msg.setFromRole(true);
			msg.setYears(ym.getYears());
			msg.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			msgService.doInsert(msg);
		}
		return SUCCESS;
	}
	
	public String listFrom(){
		ActionContext ct = ActionContext.getContext();
		String view = "error";
		try {
			list = msgService.findAllFromByPage(page.getCurrentPage(), page.getLineSize(),"", true);
			page.setAllRecord(msgService.getAllrecord());
			ct.put("page", getPage());
			ct.put("allMsg", list);
			view = "dean_msg";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	public String listTo(){
		ActionContext ct = ActionContext.getContext();
		User user = (User) ct.getSession().get("user");
		String view = "error";
		try {
			list = msgService.findAllFromByPage(page.getCurrentPage(), page.getLineSize(),user.getLoginId(), false);
			page.setAllRecord(msgService.getAllrecord());
			ct.put("page", getPage());
			ct.put("allMsg", list);
			view = "teacher_msg";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public YearMsg getYm() {
		return ym;
	}

	public void setYm(YearMsg ym) {
		this.ym = ym;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
