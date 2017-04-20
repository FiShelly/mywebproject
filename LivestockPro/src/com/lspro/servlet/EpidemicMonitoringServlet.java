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

import com.lspro.pojo.EpidemicMonitoring;
import com.lspro.pojo.FarmMes;
import com.lspro.service.EpidemicMonitoringService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class EpidemicMonitoringServlet extends HttpServlet {

	private ApplicationContext context ;
	private EpidemicMonitoringService epiService;
	
	public EpidemicMonitoringServlet() {
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
			this.getEpiRecList(request,response);
		} else if (action.equals("delete")) {
			this.getDeleteEpiRec(request,response);
		} else if (action.equals("updatePre")) {
			this.getUpdateEpiRecPre(request,response);
		} else if (action.equals("update")) {
			this.getUpdateEpiRec(request,response);
		} else if (action.equals("deleteAll")){
			this.getDeleteAllEpiRec(request,response);
		} else if (action.equals("updateAllPre")) {
			this.getUpdateAllEpiRecPre(request,response);
		} else if (action.equals("updateAll")) {
			this.getUpdateAllEpiRec(request,response);
		}else if (action.equals("insert")){
			this.getInsertEpiRecPre(request,response);
		}
	}
	
	public void getInsertEpiRecPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		EpidemicMonitoring epi = null ;
		FarmMes farm = null;
		try {
			String farmId = request.getParameter("farmId"); 
			farm = new FarmMes();
			farm.setFarmId(farmId);
			String[] samplingTime = request.getParameterValues("samplingDate"); 
			String[] roomNum = request.getParameterValues("roomNumber"); 
			String[] samplingNum = request.getParameterValues("sampleNumber"); 
			String[] monitoringName = request.getParameterValues("detectPro"); 
			String[] monitoringStation = request.getParameterValues("detectUnit"); 			
			String[] monitoringResult = request.getParameterValues("detectResult"); 
			String[] disposalConditions = request.getParameterValues("processCon");
			String[] note = request.getParameterValues("remark");
			int temp = 0;
			for(int i = 0;i<samplingTime.length;i++){
				epi = new EpidemicMonitoring();
				epi.setDisposalConditions(disposalConditions[i]);
				epi.setFarm(farm);
				epi.setMonitoringName(monitoringName[i]);
				epi.setMonitoringResult(monitoringResult[i]);
				epi.setMonitoringStation(monitoringStation[i]);
				epi.setNote(note[i]);
				epi.setRoomNum(roomNum[i]);
				epi.setSamplingNum(samplingNum[i]);
				epi.setSamplingTime(samplingTime[i]);
				if(epiService.doCreateOrUpdate(epi)){
					temp++;
				}
			}
			if(temp==samplingTime.length) {
				request.setAttribute("info",MessageUtil.get("epidemicMonitoring.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("epidemicMonitoring.insert.false"));
			}		 		 
			pages = "model1_user/insertData_preventRecord.jsp";
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllEpiRec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EpidemicMonitoring epi = null ;
		FarmMes farm = null;
		try {
			Integer index = Integer.parseInt(request.getParameter("index"));
			String farmId = request.getParameter("farmId"); 
			int temp = 0;
			for(int i = 0 ;i<index;i++) {
				Integer id  = Integer.parseInt(request.getParameter("id_"+i)); 
				String samplingTime = request.getParameter("samplingTime_"+i); 
				String roomNum = request.getParameter("roomNum_"+i); 
				String samplingNum = request.getParameter("samplingNum_"+i); 
				String monitoringName = request.getParameter("monitoringName_"+i); 
				String monitoringStation = request.getParameter("monitoringStation_"+i); 			
				String monitoringResult = request.getParameter("monitoringResult_"+i); 
				String disposalConditions = request.getParameter("disposalConditions_"+i); 
				String note = request.getParameter("note_"+i); 
				farm = new FarmMes();
				farm.setFarmId(farmId);
				epi = new EpidemicMonitoring();
				epi.setDisposalConditions(disposalConditions);
				epi.setFarm(farm);
				epi.setId(id);
				epi.setMonitoringName(monitoringName);
				epi.setMonitoringResult(monitoringResult);
				epi.setMonitoringStation(monitoringStation);
				epi.setNote(note);
				epi.setRoomNum(roomNum);
				epi.setSamplingNum(samplingNum);
				epi.setSamplingTime(samplingTime);
				if(epiService.doCreateOrUpdate(epi)) {
					temp++;
				} 
			}
			if(temp==index) {
				request.setAttribute("info",MessageUtil.get("epidemicMonitoring.updateAll.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("epidemicMonitoring.updateAll.false"));
			}
			this.getEpiRecList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllEpiRecPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EpidemicMonitoring mes = null ;
		Map<Integer,EpidemicMonitoring> map = null;
		String pages = "../error.jsp";
		try {
			String[] idAll = request.getParameterValues("check");
			map = new TreeMap<Integer, EpidemicMonitoring>();
			int index = 0;
			for(String id : idAll) {
				Integer key = Integer.parseInt(id);
				mes = epiService.findById(EpidemicMonitoring.class,key);
				if(mes != null) {
					map.put(index++,mes);
				}
			}
			if(!map.isEmpty()) {
				request.setAttribute("currentPage",request.getParameter("currentPage"));
				request.setAttribute("index", index);
				request.setAttribute("all",map);
				pages = "model1_user/batch_prevent_update.jsp";
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeleteAllEpiRec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer[] idAll = StringUtil.getStringArr2intArr(request.getParameterValues("check"));
		try {
			if(epiService.doBatchDelete(EpidemicMonitoring.class,idAll)){
				request.setAttribute("info", MessageUtil.get("epidemicMonitoring.deleteAll.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("epidemicMonitoring.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("epidemicMonitoring.deleteAll.false"));
		}
		this.getEpiRecList(request, response);
	}
	
	public void getUpdateEpiRec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id  = Integer.parseInt(request.getParameter("id")); 
			String samplingTime = request.getParameter("samplingTime"); 
			String roomNum = request.getParameter("roomNum"); 
			String samplingNum = request.getParameter("samplingNum"); 
			String monitoringName = request.getParameter("monitoringName"); 
			String monitoringStation = request.getParameter("monitoringStation"); 			
			String monitoringResult = request.getParameter("monitoringResult"); 
			String disposalConditions = request.getParameter("disposalConditions"); 
			String note = request.getParameter("note"); 
		
			String farmId = request.getParameter("farmId"); 
			FarmMes farm = new FarmMes();
			farm.setFarmId(farmId);
			EpidemicMonitoring epi = new EpidemicMonitoring();
			epi.setDisposalConditions(disposalConditions);
			epi.setFarm(farm);
			epi.setId(id);
			epi.setMonitoringName(monitoringName);
			epi.setMonitoringResult(monitoringResult);
			epi.setMonitoringStation(monitoringStation);
			epi.setNote(note);
			epi.setRoomNum(roomNum);
			epi.setSamplingNum(samplingNum);
			epi.setSamplingTime(samplingTime);
			if (epiService.doCreateOrUpdate(epi)) {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("epidemicMonitoring.update.true"));
			} else {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("epidemicMonitoring.update.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateEpiRecPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			EpidemicMonitoring mes = epiService.findById(EpidemicMonitoring.class,id);
			if(mes != null) {
				request.setAttribute("epi", mes);
				pages = "model1_user/update_prevent_record.jsp";
			} else {
				request.setAttribute("info",MessageUtil.get("epidemicMonitoring.find.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeleteEpiRec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			boolean flag = epiService.doDelete(EpidemicMonitoring.class,id);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getEpiRecList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		int lineSize = 11;
		String farmId = request.getParameter("farmId");
		String pages = "../../../errors.jsp";
		String content = StringUtil.getInitString(request.getParameter("content_search"));
		String[] date = StringUtil.getDataInit(request.getParameter("date1"), request.getParameter("date2"));
		String role = StringUtil.getInitString(request.getParameter("role"));
		try{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		try {
			List<EpidemicMonitoring> list = epiService.findAll(farmId,content,currentPage, lineSize,date[0],date[1]);
			request.setAttribute("all",list);
			request.setAttribute("allRecorders", epiService.getAllrecord(""));
			request.setAttribute("date1", StringUtil.getReturnDate(date[0]));
			request.setAttribute("date2", StringUtil.getReturnDate(date[1]) );
			request.setAttribute("content_search", content);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("URL", "EpidemicMonitoringServlet");
			if(role.equals("user")) {
				pages = "model1_user/farm_prevent_record.jsp";
				request.setAttribute("role", role);
			} else {
				String farmName = request.getParameter("farmName");
				request.setAttribute("farmName", farmName);
				pages = "model1_admin/farm_prevent_record.jsp?farmId="+farmId;
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		epiService = (EpidemicMonitoringService) context.getBean("epidemicMonitoringService");
 	}

}
