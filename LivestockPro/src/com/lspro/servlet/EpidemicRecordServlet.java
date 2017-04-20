package com.lspro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.EpidemicReport;
import com.lspro.pojo.FarmMes;
import com.lspro.service.EpidemicReportService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;


public class EpidemicRecordServlet extends HttpServlet {
	private ApplicationContext context;
	private EpidemicReportService service;
	
	public EpidemicRecordServlet() {
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
			this.getEpidemicRecordList(request, response);
		} else if (action.equals("insert")) {
			this.insertEpidemicRecord(request, response);
		} else if(action.equals("updatePre")) {
			this.updatePreEpidemicRecord(request,response);
		} else if(action.equals("update")){
			this.updateEpidemicRecord(request,response);
		} else if(action.equals("detail")){
			this.findDeatilEpidemicRecord(request,response);
		} else if(action.equals("delete")){
			this.deleteEpidemicRecord(request,response);
		}else if (action.equals("deleteAll")){
			this.deleteAllEpidemicRecord(request,response);
		}else if (action.equals("admin_list")){
			this.getEpidemicRecordAdminList(request,response);
		}else if (action.equals("changeStatus")){
			this.changeStatusEpidemicRecord(request,response);
		} else if (action.equals("detail_ajax")){
			this.findDeatilEpidemicRecordAjax(request,response);
		}
	}
	
	public void findDeatilEpidemicRecordAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		EpidemicReport epi = null;
		try {
			epi = this.service.findById(EpidemicReport.class, id);;
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		JSONObject jsonEpi = JSONObject.fromObject(epi);
		response.getWriter().print(jsonEpi.toString());
	}
	
	public void changeStatusEpidemicRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			flag = this.service.changeStatus(id);
		} catch (Exception e) {
			flag = false;
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(flag);
	}
	
	public void getEpidemicRecordAdminList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		int lineSize = 11;
		String[] date = StringUtil.getDataInit(request.getParameter("start_date"), request.getParameter("end_date"));
		String address = StringUtil.getInitString(request.getParameter("epidemic_address"));
		int status = Integer.parseInt(StringUtil.getInitString(request.getParameter("status")));
		String adminLoc = request.getParameter("address");
		String page = "error.jsp";
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		List<EpidemicReport> list = service.findAllForAdmin(adminLoc, address, date[0], date[1], status, currentPage, lineSize);
		request.setAttribute("allRecorders", service.getAllrecord(""));
		request.setAttribute("list", list);
		request.setAttribute("start_date", StringUtil.getReturnDate(date[0]));
		request.setAttribute("end_date", StringUtil.getReturnDate(date[1]));
		request.setAttribute("epidemic_address", address);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lineSize", lineSize);
		request.setAttribute("status", status);
		page = "model4_admin/model4_content_epidemic.jsp";
		request.getRequestDispatcher(page).forward(request, response);
	}
	
	public EpidemicReport getEpidemicRecordModel(HttpServletRequest request){
		 FarmMes farm =new FarmMes();
		 String feedSpecies	= request.getParameter("feed_type");
		 String sickSpecies	= request.getParameter("animal_type");
		 String feedScale	= request.getParameter("feed_size");
		 String feedSitua	= request.getParameter("feed_method");
		 String clinicalSysp	= request.getParameter("symptom");
		 String deaths = request.getParameter("death");
		 boolean isPeoInfect	= Boolean.parseBoolean(request.getParameter("infect"));
		 String blockadeSitua	= request.getParameter("close");	
		 String immunitySitua	= request.getParameter("immune_situation");
		 String treatmentSitua	= request.getParameter("treat");
		 int status	= Integer.parseInt(request.getParameter("status"));
		 String date	= request.getParameter("date");
		 String farmId = request.getParameter("farmId");
		 farm.setFarmId(farmId);
		 EpidemicReport epi = new EpidemicReport();
		 epi.setBlockadeSitua(blockadeSitua);
		 epi.setClinicalSysp(clinicalSysp);
		 epi.setDate(date);
		 epi.setFarm(farm);
		 epi.setFeedScale(feedScale);
		 epi.setFeedSitua(feedSitua);
		 epi.setFeedSpecies(feedSpecies);
		 epi.setImmunitySitua(immunitySitua);
		 epi.setPeoInfect(isPeoInfect);
		 epi.setSickSpecies(sickSpecies);
		 epi.setStatus(status);
		 epi.setTreatmentSitua(treatmentSitua);
		 epi.setDeaths(deaths);
		 return epi;
		
	}
	
	public void getEpidemicRecordList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		int lineSize = 11;
		String[] date = StringUtil.getDataInit(request.getParameter("start_date"), request.getParameter("end_date"));
		String animalType = StringUtil.getInitString(request.getParameter("animal_type"));
		int status = Integer.parseInt(StringUtil.getInitString(request.getParameter("status")));
		String farmId = request.getParameter("farmId");
		String page = "error.jsp";
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		List<EpidemicReport> list = service.findAllForUser(farmId, date[0], date[1], animalType, status, currentPage, lineSize);
		request.setAttribute("allRecorders", service.getAllrecord(""));
		request.setAttribute("list", list);
		request.setAttribute("start_date", StringUtil.getReturnDate(date[0]));
		request.setAttribute("end_date", StringUtil.getReturnDate(date[1]));
		request.setAttribute("animal_type", animalType);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lineSize", lineSize);
		request.setAttribute("status", status);
		page = "model4_user/model4_index_content.jsp";
		request.getRequestDispatcher(page).forward(request, response);
	}
	
	public void insertEpidemicRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EpidemicReport epi = this.getEpidemicRecordModel(request);
		request.setAttribute("status", request.getParameter("status"));
		try {
			if (service.doCreateOrUpdate(epi)) {
				request.setAttribute("info",MessageUtil.get("epidemicReport.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("epidemicReport.insert.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("epidemicReport.insert.false"));
		}
		if(request.getParameter("role").equals("admin")){
			request.getRequestDispatcher("model4_admin/forward.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("model4_user/forward.jsp").forward(request, response);
		}
			
	}
	
	public void updatePreEpidemicRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		EpidemicReport epi = service.findById(EpidemicReport.class, id);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("epi", epi);
		if(request.getParameter("role").equals("admin")){
			request.getRequestDispatcher("model4_admin/model4_epidemic_update.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("model4_user/epidemic_update.jsp").forward(request, response);
		}
		
	}
	
	public void updateEpidemicRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EpidemicReport epi = this.getEpidemicRecordModel(request);
		epi.setId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("status", request.getParameter("status"));
		try {
			if (service.doCreateOrUpdate(epi)) {
				request.setAttribute("info",MessageUtil.get("epidemicReport.update.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("epidemicReport.update.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("epidemicReport.update.false"));
		}
		if(request.getParameter("role").equals("admin")){
			request.getRequestDispatcher("model4_admin/forward.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("model4_user/forward.jsp").forward(request, response);
		}
	}
	
	public void findDeatilEpidemicRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		EpidemicReport epi = service.findById(EpidemicReport.class, id);
		request.setAttribute("epi", epi);
		if(StringUtil.getInitString(request.getParameter("role")).equals("admin")){
			request.getRequestDispatcher("model4_admin/model4_epidemic_detail.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("model4_user/epidemic_detail.jsp").forward(request, response);
		}
		
	}
	
	public void deleteEpidemicRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			this.service.doDelete(EpidemicReport.class, id);
			flag = true;	
		} catch (Exception e) {
			flag = false;
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(flag);
	}
	
	public void deleteAllEpidemicRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer[] idAll = StringUtil.getStringArr2intArr(request.getParameterValues("check"));
		try{
			if(service.doBatchDelete(EpidemicReport.class,idAll)){
				request.setAttribute("info",  MessageUtil.get("epidemicReport.deleteAll.true"));
			} else {
				request.setAttribute("info",  MessageUtil.get("epidemicReport.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",  MessageUtil.get("epidemicReport.deleteAll.false"));
		}
		this.getEpidemicRecordList(request, response);
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = (EpidemicReportService) context.getBean("epidemicReportService");
	}

}
