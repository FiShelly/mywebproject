package com.lspro.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jspsmart.upload.SmartUpload;
import com.lspro.pojo.FarmMes;
import com.lspro.pojo.TechnicalPerson;
import com.lspro.pojo.Users;
import com.lspro.service.BackMoreService;
import com.lspro.service.FarmService;
import com.lspro.service.UsersService;
import com.lspro.util.IPTimeStamp;
import com.lspro.util.MD5Code;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class FarmMesServlet extends HttpServlet {

	private FarmService farmService ;
	private ApplicationContext context;
	
	public FarmMesServlet() {
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
		SmartUpload smart = null;
		String action = request.getParameter("action");
		if(action == null || "".equals(action)) {
			try {
				smart = new SmartUpload();
				smart.initialize(super.getServletConfig(), request, response);
				smart.upload();
				action = smart.getRequest().getParameter("action");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(action.equals("list")) {
			this.getFarmList(request,response);
		} else if (action.equals("detail")) {
			this.getFarmDeatil(request,response);
		} else if (action.equals("updatePre")) {
			this.getFarmUpdatePre(request,response);
		} else if (action.equals("update")){
			this.getFarmUpdate(request,response,smart);
		} else if (action.equals("delete")){
			this.getFarmDelete(request,response);
		} else if(action.equals("deleteDetail")) {
			this.getFarmDeleteDetail(request,response);
		} else if(action.equals("deleteAll")) {
			this.getFarmDeleteAll(request,response);
		} else if (action.equals("detailPre")){
			this.getFarmDeatilPre(request, response);
		} else if (action.equals("insert")) {
			this.getFarmInsert(request, response,smart);
		} else if (action.equals("checkId")) {
			this.checkFarmId(request,response);
		} else if (action.equals("checkCerti")) {
			this.checkCertifi(request,response);
		} 
	}
	
	public void checkCertifi(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String certifi = request.getParameter("certification");
		try {
			boolean flag = false;
			flag = farmService.checkCertiId(certifi);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkFarmId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String farmId = request.getParameter("farmId");
		try {
			boolean flag = false;
			flag = farmService.checkId(farmId);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getCurrentDate() {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = new Date();
		 String current = sdf.format(date);
		 return current;
	}
	
	public FarmMes getFarmMes(SmartUpload smart,HttpServletRequest request){
		String farmId = smart.getRequest().getParameter("farmId");
		String farmName = smart.getRequest().getParameter("farmName");
		String species = smart.getRequest().getParameter("species");
		String farmSize = smart.getRequest().getParameter("farmsize");
		String leader = smart.getRequest().getParameter("leader");
		String zipcode = smart.getRequest().getParameter("zipcode");
		String phoneNum = smart.getRequest().getParameter("phoneNum");
		String certificate = smart.getRequest().getParameter("certificate");
		String productFac = smart.getRequest().getParameter("productFac");
		String productEquip = smart.getRequest().getParameter("productEquip");
		String envirEquip = smart.getRequest().getParameter("envirEquip");
		String province = smart.getRequest().getParameter("cmbProvince");
		String city = smart.getRequest().getParameter("cmbCity");
		String county = smart.getRequest().getParameter("cmbArea");
		String dplace = smart.getRequest().getParameter("detailAddress");
		String location = StringUtil.toChangeAddress(province, city, county)+dplace;
		String[] major = smart.getRequest().getParameterValues("major");
		String[] amount = smart.getRequest().getParameterValues("amount");
		List<TechnicalPerson> person = new ArrayList<TechnicalPerson>();
		for(int i = 0;i<major.length;i++) {
			if(major[i] != null || !"".equals(major[i])){
				TechnicalPerson per = new TechnicalPerson();
				if("".equals(amount[i]) || amount[i] == null) {
					per.setPersonNum("0");
				} else {
					per.setPersonNum(amount[i]);
				}		
				per.setProfessSkill(major[i]);
				person.add(per);
			}
		}
		FarmMes mes = new FarmMes();
		mes.setFarmId(farmId);
		mes.setFarmName(farmName);
		mes.setCertificate(certificate);
		mes.setEnvirEquip(envirEquip);
		mes.setProvince(province);
		mes.setCity(city);
		mes.setCountry(county);
		mes.setDetail(dplace);
		mes.setFarmSize(Integer.parseInt(farmSize));
		mes.setLeader(leader);
		mes.setLocation(location);
		mes.setSpecies(species);
		mes.setZipcode(zipcode);
		mes.setPhoneNum(phoneNum);
		mes.setProductEquip(productEquip);
		mes.setProductFac(productFac);
		mes.setPerson(person);
		return mes;
	}
	
	public void getFarmInsert(HttpServletRequest request, HttpServletResponse response,SmartUpload smart)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		String registDate = this.getCurrentDate();				
		FarmMes mes = this.getFarmMes(smart, request);
		mes.setRegistDate(registDate);
		String fileName = null;
		String filePath = null;	
		String loginId = smart.getRequest().getParameter("loginId");
		String password = smart.getRequest().getParameter("password1");
		Users user = new Users();
		user.setLoginId(loginId);
		user.setPassword(new MD5Code().getMD5ofStr(password));
		if(smart.getFiles().getSize() > 0) {
			IPTimeStamp ips = new IPTimeStamp(request.getRemoteAddr());
			fileName = ips.getIPTimeRand() + "." + smart.getFiles().getFile(0).getFileExt();
			filePath = this.getServletContext().getRealPath("/") + "uploadFinal" + java.io.File.separator + fileName;
			mes.setFarmPhoto(fileName);
		}  else {
			mes.setFarmPhoto("nophoto.jpg");
		}
		user.setFarm(mes);
		UsersService usersService = (UsersService) context.getBean("usersService");
		try {
			if(usersService.doCreateOrUpdate(user)) {
				if(smart.getFiles().getSize() > 0) {
					smart.getFiles().getFile(0).saveAs(filePath);
				}
				request.setAttribute("info", MessageUtil.get("farm.insert.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("farm.insert.false"));
			}
			pages = "model1_admin/farm_register_content.jsp";
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	public void cascadeDelete(String farmId,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BackMoreService backService = (BackMoreService) context.getBean("backMoreService");
		try {
			backService.cascadeDelete(farmId);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void getFarmDeleteAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] farmIdAll = request.getParameterValues("check");
		try {
			for(String farmId : farmIdAll) {
				this.cascadeDelete(farmId, request, response);
			}
			request.setAttribute("info", MessageUtil.get("farm.deleteAll.true"));
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("farm.deleteAll.false"));
		}
		this.getFarmList(request, response);
		
	}
	public void getFarmDeleteDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String farmId = request.getParameter("farmId");
		try {
			this.cascadeDelete(farmId, request, response);
			request.setAttribute("info", MessageUtil.get("farm.delete.true"));
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("farm.delete.false"));
		}
		this.getFarmList(request, response);
	}
	
	public void getFarmDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		try {
			String farmId = request.getParameter("farmId");
			this.cascadeDelete(farmId, request, response);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		response.setContentType("text/html;charset=GBK") ;
		response.getWriter().print(flag);
	}
	
	public void getFarmUpdate(HttpServletRequest request, HttpServletResponse response,SmartUpload smart)
			throws ServletException, IOException {
		String oldPhoto = smart.getRequest().getParameter("oldPhoto");
		String registDate = smart.getRequest().getParameter("registDate");
		FarmMes mes = this.getFarmMes(smart, request);
		mes.setRegistDate(registDate);
		String loginId = smart.getRequest().getParameter("loginId");
		String password = smart.getRequest().getParameter("password1");
		String oldpw = smart.getRequest().getParameter("oldpw");
		Users user = new Users();
		user.setLoginId(loginId);
		if(password == null || "".equals(password)) {
			user.setPassword(oldpw);
		} else {
			user.setPassword(new MD5Code().getMD5ofStr(password));
		}
		String fileName = null;
		String filePath = null;
		if(smart.getFiles().getSize() > 0) {
			IPTimeStamp ips = new IPTimeStamp(request.getRemoteAddr());
			fileName = ips.getIPTimeRand() + "." + smart.getFiles().getFile(0).getFileExt();
			filePath = this.getServletContext().getRealPath("/") + "uploadFinal" + java.io.File.separator + fileName;
			mes.setFarmPhoto(fileName);
		} else {
			mes.setFarmPhoto(oldPhoto);
		}
		user.setFarm(mes);
		UsersService usersService = (UsersService) context.getBean("usersService");
		try {
			if(usersService.doCreateOrUpdate(user)) {
				if(smart.getFiles().getSize() > 0) {
					smart.getFiles().getFile(0).saveAs(filePath);
				}
				request.setAttribute("info", MessageUtil.get("farm.update.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("farm.update.false"));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}  
		request.setAttribute("currentPage", smart.getRequest().getParameter("currentPage"));
		request.setAttribute("address", smart.getRequest().getParameter("address"));
		request.getRequestDispatcher("model1_admin/forward.jsp").forward(request, response);
	}
	
	public void getFarmUpdatePre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		String farmId = request.getParameter("farmId");
		try {
			UsersService usersService = (UsersService) context.getBean("usersService");
			Users user = usersService.findByFarmId(farmId);
			if(user != null) {
				request.setAttribute("farm", user.getFarm());
				request.setAttribute("user", user);
				request.setAttribute("currentPage", request.getParameter("currentPage"));
				pages = "model1_admin/farm_update.jsp";
			} else {
				request.setAttribute("info",MessageUtil.get("farmMes.find.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	public void getFarmDeatilPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String farmId = request.getParameter("farmId");
		String pages = "../error.jsp";
		try {
			String farmNmae = farmService.findById(FarmMes.class,farmId).getFarmName();	
			if(farmId != null || !"".equals(farmId)) {
				request.setAttribute("farmId", farmId);
				request.setAttribute("farmName", farmNmae);
				pages = "model1_admin/farm_detail.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
			
			
	public void getFarmDeatil(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String farmId = request.getParameter("farmId");
		String pages = "../error.jsp";
		try {
			FarmMes mes = farmService.findById(FarmMes.class,farmId);
			if(mes != null) {
				request.setAttribute("farm", mes);
				pages = "model1_admin/farm_record_table.jsp";
			} else {
				request.setAttribute("info",MessageUtil.get("farmMes.find.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getFarmList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int currentPage = 1;
		int lineSize = 11;
		String pages = "../../../errors.jsp";
		String area = StringUtil.getInitString(request.getParameter("area"));
		String name = StringUtil.getInitString(request.getParameter("name"));
		String[] date = StringUtil.getDataInit(request.getParameter("date1"), request.getParameter("date2"));
		try{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		try {	
			String address = request.getParameter("address");
			List<FarmMes> list = farmService.findAll(address,name,area,date[0],date[1],currentPage, lineSize);
			request.setAttribute("all",list);
			request.setAttribute("allRecorders", farmService.getAllrecord(""));
			request.setAttribute("name", name);
			request.setAttribute("area", area);
			request.setAttribute("date1", StringUtil.getReturnDate(date[0]));
			request.setAttribute("date2", StringUtil.getReturnDate(date[1]) );
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("URL", "FarmMesServlet");
			pages = getPage(request.getParameter("type"));
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public String getPage(String type){
		String pages = "";
		if(type == null) {
			pages = "model1_admin/farm_list.jsp";
		} else if(type.equals("icount")) {
			pages = "model5_admin/immune_index.jsp";
		} else if(type.equals("mcount")) {
			pages = "model5_admin/material_index.jsp";
		} else if(type.equals("ocount")) {
			pages = "model5_admin/outbreak_index.jsp";
		} else if(type.equals("wcount")) {
			pages = "model5_admin/warning_index.jsp";
		}	else {
			pages = "model5_admin/model5_index_content.jsp";
		}
		return pages;
	}
	
	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		farmService = (FarmService) context.getBean("farmService");
	}

}
