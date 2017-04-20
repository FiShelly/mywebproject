package com.teacherwork.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.teacherwork.domain.Page;
import com.teacherwork.domain.Post;
import com.teacherwork.domain.Title;
import com.teacherwork.domain.User;
import com.teacherwork.service.IPostService;
import com.teacherwork.service.ITitleService;
import com.teacherwork.service.IUserService;
import com.teacherwork.util.BeanForInsertUtil;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport{

	private ApplicationContext context ;
	private ITitleService titleService;
	private IPostService postService;
	private IUserService userService;
	private List<User> list ;
	private String result;
	private User user;
	private Page page;
	private int cyear;
	private int rolee;
	
	public UserAction(){
		list = new ArrayList<User>();
		this.page = new Page();
		this.user = new User();
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		userService = (IUserService) context.getBean("uService");
		titleService = (ITitleService) context.getBean("titleService");
		postService = (IPostService) context.getBean("pService");
	}

	public String update(){
		try {
			result =  userService.doUpdate(user)+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updatePw(){
		try {
			result =  userService.doUpdatePw(user.getLoginId(), user.getPw())+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}

	public String delete(){
		try {
			result =  userService.doDelete(User.class, user.getLoginId())+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String list(){
		ActionContext ct = ActionContext.getContext();
		String view = "error";
		try {
			List<User> userlist = userService.findAllByPage(page.getCurrentPage(),page.getLineSize(),getRolee());
			List<Title> allTitle = titleService.findAllByYear(Title.class, getCyear());
			List<Post> allPost = postService.findAllByYear(Post.class, getCyear());
			page.setAllRecord(userService.getAllrecord()); 
			ct.put("all",userlist);
			ct.put("allTitle",allTitle);
			ct.put("allPost",allPost);
			ct.put("page", getPage());
			if(getRolee() == 1){
				return view = "teacher_list";
			} else {
				return view = "dean_list";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	public String addUser(){
		list = BeanForInsertUtil.getBean(User.class);
		try {
			result = userService.doBatchInsert(list)+"";
		} catch (Exception e) {
			result = list.size()+"";
		}	
		return SUCCESS;
	}
	
	public String checkLoginId(){
		try {
			result =  userService.findExist(result)+"";
		} catch (Exception e) {
			result = true+"";
		}
		return SUCCESS;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}

	public int getCyear() {
		return cyear;
	}

	public void setCyear(int cyear) {
		this.cyear = cyear;
	}

	public int getRolee() {
		return rolee;
	}

	public void setRolee(int rolee) {
		this.rolee = rolee;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
