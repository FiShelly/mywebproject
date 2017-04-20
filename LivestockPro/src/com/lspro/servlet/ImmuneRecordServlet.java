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
import com.lspro.pojo.ImmuneRecord;
import com.lspro.service.ImmuneRecordService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class ImmuneRecordServlet extends HttpServlet {

	private ApplicationContext context;
	private ImmuneRecordService immService;
	
	public ImmuneRecordServlet() {
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
			this.getImmuneRecordList(request,response);
		} else if (action.equals("delete")) {
			this.getDeleteImmune(request,response);
		} else if (action.equals("updatePre")) {
			this.getUpdateImmunePre(request,response);
		} else if (action.equals("update")) {
			this.getUpdateImmune(request,response);
		} else if (action.equals("deleteAll")){
			this.getDeleteAllImmune(request,response);
		} else if (action.equals("updateAllPre")) {
			this.getUpdateAllImmunePre(request,response);
		} else if (action.equals("updateAll")) {
			this.getUpdateAllImmune(request,response);
		} else if (action.equals("insert")){
			this.getInsertImmune(request,response);
		}
	}
	
	public void getInsertImmune(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		FarmMes farm = null;
		ImmuneRecord immune = null;
		try {
			String farmId = request.getParameter("farmId"); 
			farm = new FarmMes();
			farm.setFarmId(farmId);		
			String[] immuneTime = request.getParameterValues("date"); 
			String[] roomNum = request.getParameterValues("roomNumber"); 
			String[] remainNum = request.getParameterValues("liveStockNumber"); 
			String[] immuneNum = request.getParameterValues("immuneNumber"); 
			String[] vaccineName = request.getParameterValues("immuneName"); 
			String[] vaccineProducers = request.getParameterValues("immuneProducer"); 			
			String[] batchNum = request.getParameterValues("batchNumber"); 
			String[] vaccineValidTime = request.getParameterValues("validTime"); 
			String[] immuneMethod = request.getParameterValues("immuneMethod"); 
			String[] immuneDosage = request.getParameterValues("immuneLevel"); 
			String[] immunePeople = request.getParameterValues("immuneUser"); 
			String[] note = request.getParameterValues("remark"); 
			int temp = 0;
			for(int i = 0; i < immuneTime.length;i++) {
				immune = new ImmuneRecord();
				immune.setBatchNum(batchNum[i]);
				immune.setFarm(farm);
				immune.setImmuneDosage(immuneDosage[i]);
				immune.setImmuneMethod(immuneMethod[i]);
				immune.setImmuneNum(StringUtil.toInitInteger(immuneNum[i]));
				immune.setImmunePeople(immunePeople[i]);
				immune.setImmuneTime(immuneTime[i]);
				immune.setNote(note[i]);
				immune.setRemainNum(StringUtil.toInitInteger(remainNum[i]));
				immune.setRoomNum(roomNum[i]);
				immune.setVaccineName(vaccineName[i]);
				immune.setVaccineProducers(vaccineProducers[i]);
				immune.setVaccineValidTime(vaccineValidTime[i]);
				if (immService.doCreateOrUpdate(immune)) {
					temp++;
				}
			}
			if(temp == immuneTime.length) {
				request.setAttribute("info",MessageUtil.get("immuneRecord.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("immuneRecord.insert.false"));
			}                    
			pages = "model1_user/insertData_immuneRecord.jsp";
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllImmune(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FarmMes farm = null;
		ImmuneRecord immune = null;
		try {
			Integer index = Integer.parseInt(request.getParameter("index"));
			String farmId = request.getParameter("farmId"); 
			int temp = 0;
			for(int i = 0 ;i<index;i++) {				
				Integer id  = Integer.parseInt(request.getParameter("id_"+i)); 
				String immuneTime = request.getParameter("immuneTime_"+i); 
				String roomNum = request.getParameter("roomNum_"+i); 
				String remainNum = request.getParameter("remainNum_"+i); 
				String immuneNum = request.getParameter("immuneNum_"+i); 
				String vaccineProducers = request.getParameter("vaccineProducers_"+i); 			
				String batchNum = request.getParameter("batchNum_"+i); 
				String vaccineValidTime = request.getParameter("vaccineValidTime_"+i); 
				String immuneMethod = request.getParameter("immuneMethod_"+i); 
				String immuneDosage = request.getParameter("immuneDosage_"+i); 
				String immunePeople = request.getParameter("immunePeople_"+i); 
				String note = request.getParameter("note_"+i); 
				String vaccineName = request.getParameter("vaccineName_"+i); 
				farm = new FarmMes();
				farm.setFarmId(farmId);
				immune = new ImmuneRecord();
				immune.setFarm(farm);
				immune.setBatchNum(batchNum);
				immune.setId(id);
				immune.setImmuneDosage(immuneDosage);
				immune.setImmuneMethod(immuneMethod);
				immune.setImmuneNum(StringUtil.toInitInteger(immuneNum));
				immune.setImmunePeople(immunePeople);
				immune.setImmuneTime(immuneTime);
				immune.setNote(note);
				immune.setRemainNum(StringUtil.toInitInteger(remainNum));
				immune.setRoomNum(roomNum);
				immune.setVaccineName(vaccineName);
				immune.setVaccineProducers(vaccineProducers);
				immune.setVaccineValidTime(vaccineValidTime);
				if(immService.doCreateOrUpdate(immune)) {
					temp++;
				} 
			}
			if(temp==index) {
				request.setAttribute("info",MessageUtil.get("immuneRecord.updateAll.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("immuneRecord.updateAll.false"));
			}
			this.getImmuneRecordList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllImmunePre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImmuneRecord mes = null ;
		Map<Integer,ImmuneRecord> map = null;
		String pages = "../error.jsp";
		try {
			String[] idAll = request.getParameterValues("check");
			map = new TreeMap<Integer, ImmuneRecord>();
			int index = 0;
			for(String id : idAll) {
				Integer key = Integer.parseInt(id);
				mes = immService.findById(ImmuneRecord.class,key);
				if(mes != null) {
					map.put(index++,mes);
				}
			}
			if(!map.isEmpty()) {
				request.setAttribute("currentPage",request.getParameter("currentPage"));
				request.setAttribute("index", index);
				request.setAttribute("all",map);
				pages = "model1_user/batch_immune_update.jsp";
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeleteAllImmune(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer[] idAll = StringUtil.getStringArr2intArr(request.getParameterValues("check"));
		try {
			if(immService.doBatchDelete(ImmuneRecord.class,idAll)){
				request.setAttribute("info", MessageUtil.get("immuneRecord.deleteAll.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("immuneRecord.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("immuneRecord.deleteAll.false"));
		}
		this.getImmuneRecordList(request, response);
	}
	
	public void getUpdateImmune(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id  = Integer.parseInt(request.getParameter("id")); 
			String immuneTime = request.getParameter("immuneTime"); 
			String roomNum = request.getParameter("roomNum"); 
			String remainNum = request.getParameter("remainNum"); 
			String immuneNum = request.getParameter("immuneNum"); 
			String vaccineProducers = request.getParameter("vaccineProducers"); 			
			String batchNum = request.getParameter("batchNum"); 
			String vaccineValidTime = request.getParameter("vaccineValidTime"); 
			String immuneMethod = request.getParameter("immuneMethod"); 
			String immuneDosage = request.getParameter("immuneDosage"); 
			String immunePeople = request.getParameter("immunePeople"); 
			String note = request.getParameter("note"); 
			String vaccineName = request.getParameter("vaccineName"); 
			String farmId = request.getParameter("farmId"); 
			FarmMes farm = new FarmMes();
			farm.setFarmId(farmId);
			ImmuneRecord immune = new ImmuneRecord();
			immune.setFarm(farm);
			immune.setBatchNum(batchNum);
			immune.setId(id);
			immune.setImmuneDosage(immuneDosage);
			immune.setImmuneMethod(immuneMethod);
			immune.setImmuneNum(StringUtil.toInitInteger(immuneNum));
			immune.setImmunePeople(immunePeople);
			immune.setImmuneTime(immuneTime);
			immune.setNote(note);
			immune.setRemainNum(StringUtil.toInitInteger(remainNum));
			immune.setRoomNum(roomNum);
			immune.setVaccineName(vaccineName);
			immune.setVaccineProducers(vaccineProducers);
			immune.setVaccineValidTime(vaccineValidTime);
			if (immService.doCreateOrUpdate(immune)) {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("immuneRecord.update.true"));
			} else {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("immuneRecord.update.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateImmunePre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			ImmuneRecord mes = immService.findById(ImmuneRecord.class,id);
			if(mes != null) {
				request.setAttribute("immune", mes);
				pages = "model1_user/update_immune_record.jsp";
			} else {
				request.setAttribute("info",MessageUtil.get("disinfectionRecord.find.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeleteImmune(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			boolean flag = immService.doDelete(ImmuneRecord.class,id);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void getImmuneRecordList(HttpServletRequest request, HttpServletResponse response)
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
			List<ImmuneRecord> list = immService.findAll(farmId,content,currentPage, lineSize,date[0],date[1]);
			request.setAttribute("all",list);
			request.setAttribute("allRecorders", immService.getAllrecord(""));
			request.setAttribute("date1", StringUtil.getReturnDate(date[0]));
			request.setAttribute("date2", StringUtil.getReturnDate(date[1]) );
			request.setAttribute("content_search", content);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("URL", "ImmuneRecordServlet");
			if(role.equals("user")) {
				pages = "model1_user/farm_immune_record.jsp";
				request.setAttribute("role", role);
			} else {
				String farmName = request.getParameter("farmName");
				request.setAttribute("farmName", farmName);
				pages = "model1_admin/farm_immune_record.jsp?farmId="+farmId;
			}	
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		immService = (ImmuneRecordService) context.getBean("immuneRecordService");
	}

}
