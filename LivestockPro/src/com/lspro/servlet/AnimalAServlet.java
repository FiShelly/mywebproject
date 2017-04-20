package com.lspro.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.AnimalA;
import com.lspro.service.AnimalAService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class AnimalAServlet extends HttpServlet {

	private ApplicationContext context;
	private AnimalAService aService;

	public AnimalAServlet() {
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
			this.getAnimalAList(request, response);
		} else if (action.equals("insert")) {
			this.insertAnimalA(request, response);
		} else if(action.equals("updatePre")) {
			this.updatePreAnimalA(request,response);
		} else if(action.equals("update")){
			this.updateAnimalA(request,response);
		} else if(action.equals("detail")){
			this.findDeatilAnimalA(request,response);
		} else if(action.equals("delete")){
			this.deleteAnimalA(request,response);
		} else if (action.equals("deleteAll")){
			this.deleteAllAnimalA(request,response);
		}
	}
	
	public void deleteAllAnimalA(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] idAll = request.getParameterValues("check");
		request.setAttribute("URL", "AnimalAServlet");
		try {
			if(aService.doBatchDelete(AnimalA.class, idAll)){
				request.setAttribute("info",MessageUtil.get("animalA.deleteAll.true"));
			}else {
				request.setAttribute("info",MessageUtil.get("animalA.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("animalA.deleteAll.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}

	public void deleteAnimalA(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean flag = false;
		String id = request.getParameter("id");
		try {
			this.aService.doDelete(AnimalA.class, id);
			flag = true;	
		} catch (Exception e) {
			flag = false;
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(flag);
		
	}
	
	public void findDeatilAnimalA(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		AnimalA animalA = aService.findById(AnimalA.class, id);
		request.setAttribute("aa", animalA);
		request.getRequestDispatcher("model3_admin/detail_animalA.jsp").forward(request, response);
	}
	
	
	public void updateAnimalA(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AnimalA animalA = this.getAnimalAModel(request);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("URL", "AnimalAServlet");
		try {
			if (aService.doCreateOrUpdate(animalA)) {
				request.setAttribute("info",MessageUtil.get("animalA.update.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("animalA.update.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("animalA.update.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}
	
	public void updatePreAnimalA(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		AnimalA animalA = aService.findById(AnimalA.class, id);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("aa", animalA);
		request.getRequestDispatcher("model3_admin/update_animalA.jsp").forward(request, response);
	}
	
	public AnimalA getAnimalAModel(HttpServletRequest request){
		String id = request.getParameter("animalA_id");
		String shipperName = request.getParameter("owner");
		String phoneNum = request.getParameter("phone_number");
		String animalSpecies = request.getParameter("animal_type");
		String number = request.getParameter("animal_amount");
		String sprovince = request.getParameter("s_province");
		String scity = request.getParameter("s_city");
		String scounty = request.getParameter("s_county");
		String ssplace = request.getParameter("animalA_start_place");
		String startAddress = StringUtil.toChangeAddress(sprovince, scity, scounty)+ssplace;		
		String eprovince = request.getParameter("cmbProvince");
		String ecity = request.getParameter("cmbCity");
		String ecounty = request.getParameter("cmbArea");
		String esplace = request.getParameter("animalA_target_place");
		String destination = StringUtil.toChangeAddress(eprovince, ecity, ecounty)+esplace;
		String date = request.getParameter("issue_date");
		String use = request.getParameter("purpose");
		String carrier = request.getParameter("carrier");
		String carrierPhone = request.getParameter("carrier_phone");
		String transportWay = request.getParameter("radiobutton");
		String transportId = request.getParameter("sign");
		String disinfection = request.getParameter("animalA_degass");
		String days = request.getParameter("valid_date");
		String vetName = request.getParameter("doctor");
		String note = request.getParameter("remark");
		String[] tempAnimalId = request.getParameterValues("animalID");
		List<String> animalId = StringUtil.getAnimalIdList(tempAnimalId);
		AnimalA animalA = new AnimalA();
		animalA.setAnimalId(animalId);
		animalA.setAnimalSpecies(animalSpecies);
		animalA.setCarrier(carrier);
		animalA.setCarrierPhone(carrierPhone);
		animalA.setDate(date);
		animalA.setDays(days);
		animalA.setDestination(destination);
		animalA.setDisinfection(disinfection);
		animalA.setId(id);
		animalA.setNote(note);
		animalA.setNumber(number);
		animalA.setPhoneNum(phoneNum);
		animalA.setShipperName(shipperName);
		animalA.setStartAddress(startAddress);
		animalA.setTransportId(transportId);
		animalA.setTransportWay(transportWay);
		animalA.setUse(use);
		animalA.setVetName(vetName);
		animalA.setStartProvince(sprovince);
		animalA.setStartCity(scity);
		animalA.setStartCountry(scounty);
		animalA.setEndProvince(eprovince);
		animalA.setEndCity(ecity);
		animalA.setEndCountry(ecounty);
		animalA.setStartDetail(ssplace);
		animalA.setEndDetail(esplace);
		return animalA;
	}

	public void insertAnimalA(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AnimalA animalA = this.getAnimalAModel(request);
		request.setAttribute("URL", "AnimalAServlet");
		try {
			if (aService.doCreateOrUpdate(animalA)) {
				request.setAttribute("info",MessageUtil.get("animalA.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("animalA.insert.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("animalA.insert.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}

	public void getAnimalAList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		int lineSize = 11;
		String[] date = StringUtil.getDataInit(request.getParameter("start_date"), request.getParameter("end_date"));
		String owner = StringUtil.getInitString(request.getParameter("owner"));
		String animalName = StringUtil.getInitString(request.getParameter("animal_name"));
		String number = StringUtil.getInitString(request.getParameter("number"));
		String loc = request.getParameter("address");
		String[] way = request.getParameterValues("way");
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
		List<AnimalA> list = aService.findAll(role,number, loc, owner, date[0],date[1], animalName, way, currentPage, lineSize);
		request.setAttribute("allRecorders", aService.getAllrecord(""));System.out.println(loc);
		request.setAttribute("list", list);
		request.setAttribute("flag", 1);
		request.setAttribute("start_date", StringUtil.getReturnDate(date[0]));
		request.setAttribute("end_date", StringUtil.getReturnDate(date[1]));
		request.setAttribute("owner", owner);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lineSize", lineSize);
		request.setAttribute("animal_name", animalName);
		request.setAttribute("number", number);
		request.setAttribute("way", StringUtil.getWayString(way));
		request.getRequestDispatcher(page).forward(request, response);
	}



	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		aService = (AnimalAService) context.getBean("animalAService");
	}

}
