package com.lspro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.AnimalB;
import com.lspro.service.AnimalBService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class AnimalBServlet extends HttpServlet {

	private ApplicationContext context ;
	private AnimalBService bService;
	
	public AnimalBServlet() {
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
		if (action.equals("list")) {
			this.getAnimalBList(request, response);
		} else if (action.equals("insert")) {
			this.insertAnimalB(request, response);
		} else if(action.equals("updatePre")) {
			this.updatePreAnimalB(request,response);
		} else if(action.equals("update")){
			this.updateAnimalB(request,response);
		} else if(action.equals("detail")){
			this.findDeatilAnimalB(request,response);
		} else if(action.equals("delete")){
			this.deleteAnimalB(request,response);
		}else if (action.equals("deleteAll")){
			this.deleteAllAnimalB(request,response);
		}
	}
	
	public void deleteAllAnimalB(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] idAll = request.getParameterValues("check");
		request.setAttribute("URL", "AnimalBServlet");
		try {
			if(bService.doBatchDelete(AnimalB.class, idAll)){
				request.setAttribute("info",MessageUtil.get("animalB.deleteAll.true"));
			}else {
				request.setAttribute("info",MessageUtil.get("animalB.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("animalB.deleteAll.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}
	
	public AnimalB getAnimalBModel(HttpServletRequest request){
		String id = request.getParameter("animalB_id");
		String shipperName = request.getParameter("owner");
		String phoneNum = request.getParameter("phone_number");
		String animalSpecies = request.getParameter("animal_type");
		String number = request.getParameter("amount");
		String sprovince = request.getParameter("s_province");
		String scity = request.getParameter("s_city");
		String scounty = request.getParameter("s_county");
		String ssplace = request.getParameter("animalB_start_place");
		String startAddress = StringUtil.toChangeAddress(sprovince, scity, scounty)+ssplace;		
		String eprovince = request.getParameter("cmbProvince");
		String ecity = request.getParameter("cmbCity");
		String ecounty = request.getParameter("cmbArea");
		String esplace = request.getParameter("animalB_target_place");
		String destination = StringUtil.toChangeAddress(eprovince, ecity, ecounty)+esplace;
		String date = request.getParameter("sign_date");
		String use = request.getParameter("purpose");
		String days = request.getParameter("valid_date");
		String vetName = request.getParameter("doctor");
		String[] tempAnimalId = request.getParameterValues("animalID");
		List<String> animalId = StringUtil.getAnimalIdList(tempAnimalId);
		AnimalB AnimalB = new AnimalB();
		AnimalB.setAnimalId(animalId);
		AnimalB.setAnimalSpecies(animalSpecies);
		AnimalB.setDate(date);
		AnimalB.setDays(days);
		AnimalB.setDestination(destination);
		AnimalB.setId(id);
		AnimalB.setNumber(number);
		AnimalB.setPhoneNum(phoneNum);
		AnimalB.setShipperName(shipperName);
		AnimalB.setStartAddress(startAddress);
		AnimalB.setUse(use);
		AnimalB.setVetName(vetName);
		AnimalB.setStartProvince(sprovince);
		AnimalB.setStartCity(scity);
		AnimalB.setStartCountry(scounty);
		AnimalB.setEndProvince(eprovince);
		AnimalB.setEndCity(ecity);
		AnimalB.setEndCountry(ecounty);
		AnimalB.setStartDetail(ssplace);
		AnimalB.setEndDetail(esplace);
		return AnimalB;
	}
	
	public void getAnimalBList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		int lineSize = 11;
		String[] date = StringUtil.getDataInit(request.getParameter("start_date"), request.getParameter("end_date"));
		String owner = StringUtil.getInitString(request.getParameter("owner"));
		String animalName = StringUtil.getInitString(request.getParameter("animal_name"));
		String number = StringUtil.getInitString(request.getParameter("number"));
		String loc = request.getParameter("address");
		String role = StringUtil.getInitString(request.getParameter("role"));
		String page = "error.jsp";
		if(role.equals("user")){
			page = "model3_user/model3_user_indexContent.jsp";
		} else {
			page = "model3_admin/model3_index_content.jsp";
		}
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		List<AnimalB> list = bService.findAll(role,number, loc, owner, date[0],date[1], animalName,  currentPage, lineSize);
		request.setAttribute("allRecorders", bService.getAllrecord(""));
		request.setAttribute("listB", list);
		request.setAttribute("flag", 2);
		request.setAttribute("start_date", StringUtil.getReturnDate(date[0]));
		request.setAttribute("end_date", StringUtil.getReturnDate(date[1]));
		request.setAttribute("owner", owner);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lineSize", lineSize);
		request.setAttribute("animal_name", animalName);
		request.setAttribute("number", number);
		request.getRequestDispatcher(page).forward(request, response);
	}
	
	public void insertAnimalB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AnimalB animalB = this.getAnimalBModel(request);
		request.setAttribute("URL", "AnimalBServlet");
		try {
			if (bService.doCreateOrUpdate(animalB)) {
				request.setAttribute("info",MessageUtil.get("animalB.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("animalB.insert.false"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("info",MessageUtil.get("animalB.insert.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}
	
	public void updatePreAnimalB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		AnimalB animalB = bService.findById(AnimalB.class, id);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("ab", animalB);
		request.getRequestDispatcher("model3_admin/update_animalB.jsp").forward(request, response);
	}
	
	public void updateAnimalB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AnimalB animalB = this.getAnimalBModel(request);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("URL", "AnimalBServlet");
		try {
			if (bService.doCreateOrUpdate(animalB)) {
				request.setAttribute("info",MessageUtil.get("animalB.update.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("animalB.update.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("animalB.update.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}
	
	public void findDeatilAnimalB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		AnimalB animalB = bService.findById(AnimalB.class, id);
		request.setAttribute("ab", animalB);
		request.getRequestDispatcher("model3_admin/detail_animalB.jsp").forward(request, response);
	}
	
	public void deleteAnimalB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		String id = request.getParameter("id");
		try {
			this.bService.doDelete(AnimalB.class, id);
			flag = true;	
		} catch (Exception e) {
			flag = false;
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(flag);
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		bService = (AnimalBService) context.getBean("animalBService");
	}

}
