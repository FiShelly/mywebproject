package com.lspro.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.FarmMes;
import com.lspro.pojo.FoodDrugUseRecord;
import com.lspro.service.FoodDrugUseRecordService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class FoodDrugUseRecordServlet extends HttpServlet {

	private ApplicationContext context ;
	private FoodDrugUseRecordService foodService;
	
	public FoodDrugUseRecordServlet() {
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
		if(action.equals("list")) {
			this.getFDURList(request,response);
		} else if (action.equals("delete")) {
			this.getDeleteFdur(request,response);
		} else if (action.equals("updatePre")) {
			this.getUpdateFdurPre(request,response);
		} else if (action.equals("update")) {
			this.getUpdateFdur(request,response);
		} else if (action.equals("deleteAll")){
			this.getDeleteAllFdur(request,response);
		} else if (action.equals("updateAllPre")) {
			this.getUpdateAllFdurPre(request,response);
		} else if (action.equals("updateAll")) {
			this.getUpdateAllFdur(request,response);
		} else if (action.equals("insert")){
			this.getInsertFdur(request,response);
		}
	}
	
	public void getInsertFdur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		FarmMes farm = null;
		FoodDrugUseRecord fdur = null;
		try {
			String farmId = request.getParameter("farmId"); 
			farm = new FarmMes();
			farm.setFarmId(farmId);
			String[] startTime = request.getParameterValues("startDate");
			String[] stopTime = request.getParameterValues("endDate");
			String[] processDate = request.getParameterValues("processDate");
			String[] batchNum = request.getParameterValues("batchNumber");
			String[] productName = request.getParameterValues("productName");
			String[] manufacturer = request.getParameterValues("producer");
			String[] dosage = request.getParameterValues("useLevel");
			String[] note = request.getParameterValues("remark");
			int temp = 0;
			for(int i = 0;i<startTime.length;i++){
				fdur = new FoodDrugUseRecord();
				fdur.setBatchNum(batchNum[i]);
				fdur.setDosage(dosage[i]);
				fdur.setFarm(farm);
				fdur.setManufacturer(manufacturer[i]);
				fdur.setNote(note[i]);
				fdur.setProcessDate(processDate[i]);
				fdur.setProductName(productName[i]);
				fdur.setStartTime(startTime[i]);
				fdur.setStopTime(stopTime[i]);
				if (foodService.doCreateOrUpdate(fdur)) {
					temp++;
				}
			}
			if (temp == startTime.length) {
				request.setAttribute("info",MessageUtil.get("foodDrugUseRecord.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("foodDrugUseRecord.insert.false"));
			}
			pages = "model1_user/insertData_medicineRecord.jsp";
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllFdur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FarmMes farm = null;
		FoodDrugUseRecord fdur = null;
		try {
			Integer index = Integer.parseInt(request.getParameter("index"));
			String farmId = request.getParameter("farmId"); 
			int temp = 0;
			for(int i = 0 ;i<index;i++) {				
				Integer id  = Integer.parseInt(request.getParameter("id_"+i)); 
				String startTime = request.getParameter("startTime_"+i); 
				String stopTime = request.getParameter("stopTime_"+i); 
				String processDate = request.getParameter("processDate_"+i); 
				String batchNum = request.getParameter("batchNum_"+i); 
				String productName = request.getParameter("productName_"+i); 
				String manufacturer = request.getParameter("manufacturer_"+i); 
				String dosage = request.getParameter("dosage_"+i); 
				String note = request.getParameter("note_"+i); 
				farm = new FarmMes();
				farm.setFarmId(farmId);
				fdur = new FoodDrugUseRecord();
				fdur.setBatchNum(batchNum);
				fdur.setDosage(dosage);
				fdur.setFarm(farm);
				fdur.setId(id);
				fdur.setManufacturer(manufacturer);
				fdur.setNote(note);
				fdur.setProcessDate(processDate);
				fdur.setProductName(productName);
				fdur.setStartTime(startTime);
				fdur.setStopTime(stopTime);
				if(foodService.doCreateOrUpdate(fdur)) {
					temp++;
				} 
			}
			if(temp==index) {
				request.setAttribute("info",MessageUtil.get("foodDrugUseRecord.updateAll.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("foodDrugUseRecord.updateAll.false"));
			}
			this.getFDURList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllFdurPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FoodDrugUseRecord mes = null ;
		Map<Integer,FoodDrugUseRecord> map = null;
		String pages = "../error.jsp";
		try {
			String[] idAll = request.getParameterValues("check");
			map = new TreeMap<Integer, FoodDrugUseRecord>();
			int index = 0;
			for(String id : idAll) {
				Integer key = Integer.parseInt(id);
				mes = foodService.findById(FoodDrugUseRecord.class,key);
				if(mes != null) {
					map.put(index++,mes);
				}
			}
			if(!map.isEmpty()) {
				request.setAttribute("currentPage",request.getParameter("currentPage"));
				request.setAttribute("index", index);
				request.setAttribute("all",map);
				pages = "model1_user/batch_medicine_update.jsp";
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getDeleteAllFdur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer[] idAll = StringUtil.getStringArr2intArr(request.getParameterValues("check"));
		try {
			if(foodService.doBatchDelete(FoodDrugUseRecord.class,idAll)){
				request.setAttribute("info", MessageUtil.get("foodDrugUseRecord.deleteAll.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("foodDrugUseRecord.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("foodDrugUseRecord.deleteAll.false"));
		}
		this.getFDURList(request, response);
	}
	
	public void getUpdateFdur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id  = Integer.parseInt(request.getParameter("id")); 
			String startTime = request.getParameter("startTime"); 
			String stopTime = request.getParameter("stopTime"); 
			String processDate = request.getParameter("processDate"); 
			String batchNum = request.getParameter("batchNum"); 
			String productName = request.getParameter("productName"); 
			String manufacturer = request.getParameter("manufacturer"); 
			String dosage = request.getParameter("dosage"); 
			String note = request.getParameter("note"); 
			String farmId = request.getParameter("farmId"); 
			FarmMes farm = new FarmMes();
			farm.setFarmId(farmId);
			FoodDrugUseRecord fdur = new FoodDrugUseRecord();
			fdur.setBatchNum(batchNum);
			fdur.setDosage(dosage);
			fdur.setFarm(farm);
			fdur.setId(id);
			fdur.setManufacturer(manufacturer);
			fdur.setNote(note);
			fdur.setProcessDate(processDate);
			fdur.setProductName(productName);
			fdur.setStartTime(startTime);
			fdur.setStopTime(stopTime);
			if (foodService.doCreateOrUpdate(fdur)) {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("foodDrugUseRecord.update.true"));
			} else {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("foodDrugUseRecord.update.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateFdurPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			FoodDrugUseRecord mes = foodService.findById(FoodDrugUseRecord.class,id);
			if(mes != null) {
				request.setAttribute("fdur", mes);
				pages = "model1_user/update_medicine_record.jsp";
			} else {
				request.setAttribute("info",MessageUtil.get("foodDrugUseRecord.find.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeleteFdur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			boolean flag = foodService.doDelete(FoodDrugUseRecord.class,id);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getFDURList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		int lineSize = 11;
		String farmId = request.getParameter("farmId");
		String pages = "../../../errors.jsp";
		String content = StringUtil.getInitString(request.getParameter("content_search"));		
		String[] date1 = StringUtil.getDataInit(request.getParameter("date1"), request.getParameter("date2"));
		String[] date2 = StringUtil.getDataInit(request.getParameter("date3"), request.getParameter("date4"));
		String role = StringUtil.getInitString(request.getParameter("role"));
		try{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		try {
			List<FoodDrugUseRecord> list = foodService.findAll(farmId,content,currentPage, lineSize,date1[0],date1[1],date2[0],date2[1]);
			request.setAttribute("all",list);
			request.setAttribute("allRecorders", foodService.getAllrecord(""));
			request.setAttribute("date1", StringUtil.getReturnDate(date1[0]));
			request.setAttribute("date2", StringUtil.getReturnDate(date1[1]) );
			request.setAttribute("date3", StringUtil.getReturnDate(date2[0]));
			request.setAttribute("date4", StringUtil.getReturnDate(date2[1]) );
			request.setAttribute("content_search", content);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("URL", "FoodDrugUseRecordServlet");
			if(role.equals("user")) {
				pages = "model1_user/farm_medicine_record.jsp";
				request.setAttribute("role", role);
			} else {
				String farmName = request.getParameter("farmName");
				request.setAttribute("farmName", farmName);
				pages = "model1_admin/farm_medicine_record.jsp?farmId="+farmId;
			}			
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		foodService = (FoodDrugUseRecordService) context.getBean("foodDrugUseRecordService");
	}

}
