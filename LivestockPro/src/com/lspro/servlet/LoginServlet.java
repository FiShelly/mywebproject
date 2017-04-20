package com.lspro.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.lspro.pojo.AdminUsers;
import com.lspro.pojo.Users;
import com.lspro.service.AdminUsersService;
import com.lspro.service.UsersService;
import com.lspro.util.MD5Code;
import com.lspro.util.MessageUtil;

public class LoginServlet extends HttpServlet {
	
	private ApplicationContext context ;
	private UsersService usersService;
	private AdminUsersService adminService;

	public LoginServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(action.equals("adminLogin")) {
			this.adminLogin(request,response);
		} else if(action.equals("userLogin"))  {
			this.userLogin(request,response);
		} else if(action.equals("checkId")) {
			this.checkLoginId(request,response);
		} else if(action.equals("sadminLogin")){
			this.sadminLogin(request,response);
		}
	}
	
	public void sadminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "login.jsp";
		String loginId = request.getParameter("lg_id");
		String password = request.getParameter("lg_password");
		AdminUsers admin = new AdminUsers();
		admin.setLoginId(loginId);
		admin.setPassword(new MD5Code().getMD5ofStr(password));	
		String flag = "0";
		try {
			if(adminService.findLoginSuper(admin)) {
				admin = adminService.findById(AdminUsers.class, loginId);
				request.getSession().setAttribute("admin", admin) ;
				flag = "1";
				pages = "manage_admin/manage_admin_index.jsp";
				System.out.println("--------");
			} else {
				request.setAttribute("info", MessageUtil.get("adminlogin.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("adminlogin.false"));
		}
		request.setAttribute("flag", flag);
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	public void checkLoginId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		try {
			boolean flag = false;
			flag = usersService.checkId(loginId);
			response.setContentType("text/html;charset=utf-8") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "login.jsp";
		String loginId = request.getParameter("lg_id");
		String password = request.getParameter("lg_password");
		AdminUsers admin = new AdminUsers();
		admin.setLoginId(loginId);
		admin.setPassword(new MD5Code().getMD5ofStr(password));	
		String flag = "0";
		try {
			if(adminService.findLogin(admin)) {
				admin = adminService.findById(AdminUsers.class, loginId);
				request.getSession().setAttribute("admin", admin) ;
				flag = "1";
				pages = "model1_admin/model1_admin_index.jsp";
			} else {
				request.setAttribute("info", MessageUtil.get("adminlogin.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("adminlogin.false"));
		}
		request.setAttribute("flag", flag);
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	public void userLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "login.jsp";
		String loginId = request.getParameter("lg_id");
		String password = request.getParameter("lg_password");
		Users user = new Users();
		user.setLoginId(loginId);
		user.setPassword(new MD5Code().getMD5ofStr(password));
		String flag = "0";
		try {
			if(usersService.findLogin(user)) {
				user = usersService.findById(Users.class, loginId);
				request.getSession().setAttribute("user", user) ;
				flag = "1";
				pages = "model1_user/model1_user_index.jsp";
			} else {
				request.setAttribute("info", MessageUtil.get("userlogin.false"));
			} 
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("userlogin.false"));
		}
		request.setAttribute("flag", flag);
		request.getRequestDispatcher(pages).forward(request, response);
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		adminService = (AdminUsersService) context.getBean("adminUsersService");
		usersService = (UsersService) context.getBean("usersService");
	}

}
