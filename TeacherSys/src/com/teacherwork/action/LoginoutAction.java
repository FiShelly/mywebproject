package com.teacherwork.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherwork.domain.User;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.service.IMessageService;
import com.teacherwork.service.IUserService;
import com.teacherwork.service.IYearMsgService;
import com.teacherwork.util.MessageUtil;

@SuppressWarnings("serial")
public class LoginoutAction extends ActionSupport implements ModelDriven<User> {
	
	private ApplicationContext context ;
	private IUserService usersService;
	private User model = new User();
	private YearMsg msg ;
	private IYearMsgService ymService;
	private IMessageService msgService;
	public LoginoutAction(){
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		usersService = (IUserService) context.getBean("uService");
		ymService = (IYearMsgService) context.getBean("ymService");
		msgService = (IMessageService) context.getBean("msgService");
	}
	
	public String login(){
		ActionContext ct = ActionContext.getContext();
		String view = "login_failed";
		boolean flag = false;
		try {
			flag = usersService.findLogin(model);
			if(flag){
				model = usersService.findById(User.class, model.getLoginId());
				if(!model.getState()){
					ct.put("info", MessageUtil.get("user.login.nostate"));
					ct.put("flag", false);
					return view;
				}
				msg = ymService.findByCurrent(true);
				List<YearMsg> allYears = ymService.findAll(YearMsg.class);
				ct.getSession().put("user",model);
				ct.getSession().put("allYears", allYears); 
				ct.getSession().put("cyearmsg",getMsg() );
				ct.getSession().put("msgCount", model.getRole() == 1?msgService.findNotReadOrReadCount(model.getLoginId(), false):msgService.findNotHandlerOrHandlerCount(0));
				view = model.getRole() == 1 ? "teacher_index" : "dean_index";
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
	
	public String logout(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setDateHeader("Expires",0);
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if(session == null) {
			return "logout";
		}
		session.invalidate();
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
	
	@Override
	public User getModel() {
		return model;
	}

	public YearMsg getMsg() {
		return msg;
	}

	public void setMsg(YearMsg msg) {
		this.msg = msg;
	}

}
