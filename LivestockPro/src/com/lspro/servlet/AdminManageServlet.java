package com.lspro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.AdminUsers;
import com.lspro.service.AdminUsersService;
import com.lspro.util.MD5Code;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;


public class AdminManageServlet extends HttpServlet {
	
	private ApplicationContext context;
	private AdminUsersService aService;

	public AdminManageServlet() {
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
		System.out.println(action);
		if (action.equals("list")) {
			this.getAdminList(request, response);
		} else if (action.equals("insert")) {
			this.insertAdmin(request, response);
		} else if(action.equals("updatePre")) {
			this.updatePreAdmin(request,response);
		} else if(action.equals("update")){
			this.updateAdmin(request,response);
		} else if(action.equals("updatePw")){
			this.updatePwAdmin(request,response);
		} else if(action.equals("delete")){
			this.deleteAdmin(request,response);
		} else if (action.equals("deleteAll")){
			this.deleteAllAdmin(request,response);
		} else if (action.equals("checkId")){
			this.checkIdAdmin(request,response);
		}
	}
	
	public void getAdminList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		int lineSize = 11;
		
		String[] date = StringUtil.getDataInit(request.getParameter("start_date"), request.getParameter("end_date"));
		String loginId = StringUtil.getInitString(request.getParameter("number"));
		String address = StringUtil.getInitString(request.getParameter("district"));
		String sAddress = StringUtil.getInitString(request.getParameter("sAddress"));
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		List<AdminUsers> list = aService.findAll(sAddress, loginId, address, date[0], date[1], currentPage, lineSize);
		request.setAttribute("allRecorders", aService.getAllrecord(""));
		request.setAttribute("list", list);
		request.setAttribute("start_date", StringUtil.getReturnDate(date[0]));
		request.setAttribute("end_date", StringUtil.getReturnDate(date[1]));
		request.setAttribute("loginId", loginId);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lineSize", lineSize);
		request.setAttribute("district", address);
		request.getRequestDispatcher("manage_admin/manage_admin_index.jsp").forward(request, response);
	}
	
	public AdminUsers getModel(HttpServletRequest request){
		String loginId = request.getParameter("number");
		String password = StringUtil.getInitString(request.getParameter("password"));
		String oldpw = StringUtil.getInitString(request.getParameter("oldpw"));
		int grade = Integer.parseInt(request.getParameter("district"));	 
		String province = StringUtil.getInitString(request.getParameter("cmbProvince"));
		String city = StringUtil.getInitString(request.getParameter("cmbCity"));
		String country = StringUtil.getInitString(request.getParameter("cmbArea"));
		String detail = request.getParameter("dept");
		String registDate = StringUtil.getCurrentDate();
		String address = StringUtil.toChangeAddress(province,city,country);
		AdminUsers admin = new AdminUsers();
		boolean isSup = StringUtil.getInitString(request.getParameter("adminType")).equals("是");
		admin.setAddress(address);
		admin.setCity(city);
		admin.setCountry(country);
		admin.setDetail(detail);
		admin.setGrade(grade);
		admin.setIsSuperAdmin(isSup);
		admin.setLoginId(loginId);
		admin.setProvince(province);
		if("".equals(oldpw)){
			admin.setPassword(new MD5Code().getMD5ofStr(password));
		} else if(!("".equals(oldpw)) && password.equals("")){
			admin.setPassword(oldpw);
		} else {
			admin.setPassword(new MD5Code().getMD5ofStr(password));
		}
		admin.setRegistDate(registDate);
		return admin;
	}
	
	public void insertAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUsers admin = this.getModel(request);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		try {
			if (aService.doCreateOrUpdate(admin)) {
				request.setAttribute("info",MessageUtil.get("admin.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("admin.insert.false"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("info",MessageUtil.get("admin.insert.false"));
		}
		request.getRequestDispatcher("manage_admin/forward.jsp").forward(request, response);
		
	}

	public void updatePreAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("loginId");
		AdminUsers admin = aService.findById(AdminUsers.class, id);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("ad", admin);
		request.getRequestDispatcher("manage_admin/manage_admin_update.jsp").forward(request, response);
	}
	
	public void updateAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUsers admin = this.getModel(request);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		try {
			if (aService.doCreateOrUpdate(admin)) {
				request.setAttribute("info",MessageUtil.get("admin.update.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("admin.update.false"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("info",MessageUtil.get("admin.update.false"));
		}
		request.getRequestDispatcher("manage_admin/forward.jsp").forward(request, response);
	}
	
	public void updatePwAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		String loginId = request.getParameter("loginId");
		String password = new MD5Code().getMD5ofStr(request.getParameter("pw"));
		try {
			flag = this.aService.updatePw(loginId, password);	
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(flag);
	}
	
	public void deleteAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		String id = request.getParameter("loginId");
		try {
			this.aService.doDelete(AdminUsers.class, id);
			flag = true;	
		} catch (Exception e) {
			flag = false;
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(flag);
	}
	
	public void deleteAllAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] idAll = request.getParameterValues("check");
		try {
			if(aService.doBatchDelete(AdminUsers.class, idAll)){
				request.setAttribute("info",MessageUtil.get("admin.deleteAll.true"));
			}else {
				request.setAttribute("info",MessageUtil.get("admin.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("admin.deleteAll.false"));
		}
		request.getRequestDispatcher("manage_admin/forward.jsp").forward(request, response);
	}
	
	public void checkIdAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		try {
			boolean flag = false;
			flag = aService.checkId(loginId);
			response.setContentType("text/html;charset=utf-8") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		aService = (AdminUsersService) context.getBean("adminUsersService");
	}

}
