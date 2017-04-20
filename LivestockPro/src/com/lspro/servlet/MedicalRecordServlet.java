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
import com.lspro.pojo.MedicalRecord;
import com.lspro.service.MedicalRecordService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class MedicalRecordServlet extends HttpServlet {

	private ApplicationContext context ;
	private MedicalRecordService medService;
	
	public MedicalRecordServlet() {
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
			this.getMedRecList(request,response);
		} else if (action.equals("delete")) {
			this.getDeleteMedical(request,response);
		} else if (action.equals("updatePre")) {
			this.getUpdateMedicalPre(request,response);
		} else if (action.equals("update")) {
			this.getUpdateMedical(request,response);
		} else if (action.equals("deleteAll")){
			this.getDeleteAllMedical(request,response);
		} else if (action.equals("updateAllPre")) {
			this.getUpdateAllMedicalPre(request,response);
		} else if (action.equals("updateAll")) {
			this.getUpdateAllMedical(request,response);
		}else if (action.equals("insert")){
			this.getInsertMedical(request,response);
		}
	}
	
	public void getInsertMedical(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		FarmMes farm = null;
		MedicalRecord med = null;
		try {
			String farmId = request.getParameter("farmId"); 
			farm = new FarmMes();
			farm.setFarmId(farmId);
			String[] medicalTime = request.getParameterValues("date"); 
			String[] roomNum = request.getParameterValues("roomNumber"); 
			String[] livestockId = request.getParameterValues("animalId"); 
			String[] dateAge = request.getParameterValues("dayAge"); 
			String[] sickNum = request.getParameterValues("attackNumber"); 			
			String[] sickReason = request.getParameterValues("reason"); 
			String[] medicalPeo = request.getParameterValues("treatName"); 
			String[] drugName = request.getParameterValues("medicineName"); 
			String[] method = request.getParameterValues("medicineMethod"); 
			String[] medicalResult = request.getParameterValues("result"); 
			String[] remainNums = request.getParameterValues("remain");
			int temp = 0;
			for(int i = 0;i<medicalTime.length;i++) {
				med = new MedicalRecord();
				med.setDateAge(dateAge[i]);
				med.setDrugName(drugName[i]);
				med.setFarm(farm);
				med.setLivestockId(livestockId[i]);
				med.setMedicalPeo(medicalPeo[i]);
				med.setMedicalResult(medicalResult[i]);
				med.setMedicalTime(medicalTime[i]);
				med.setMethod(method[i]);
				med.setRoomNum(roomNum[i]);
				med.setSickNum(StringUtil.toInitInteger(sickNum[i]));
				med.setRemain(StringUtil.toInitInteger(remainNums[i]));
				med.setSickReason(sickReason[i]);
				if (medService.doCreateOrUpdate(med)) {
					temp++;
				}
			}
			if(temp==medicalTime.length) {
				request.setAttribute("info",MessageUtil.get("medicalRecord.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("medicalRecord.insert.false"));
			}
			pages = "moedl1_user/insertData_treatRecord.jsp";
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllMedical(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FarmMes farm = null;
		MedicalRecord med = null;
		try {
			Integer index = Integer.parseInt(request.getParameter("index"));
			String farmId = request.getParameter("farmId"); 
			int temp = 0;
			for(int i = 0 ;i<index;i++) {
				Integer id  = Integer.parseInt(request.getParameter("id_"+i)); 
				String medicalTime = request.getParameter("medicalTime_"+i); 
				String roomNum = request.getParameter("roomNum_"+i); 
				String livestockId = request.getParameter("livestockId_"+i); 
				String dateAge = request.getParameter("dateAge_"+i); 
				String sickNum = request.getParameter("sickNum_"+i); 			
				String sickReason = request.getParameter("sickReason_"+i); 
				String medicalPeo = request.getParameter("medicalPeo_"+i); 
				String drugName = request.getParameter("drugName_"+i); 
				String method = request.getParameter("method_"+i); 
				String medicalResult = request.getParameter("medicalResult_"+i); 
				String remain = request.getParameter("remain_"+i);
				farm = new FarmMes();
				farm.setFarmId(farmId);
				med = new MedicalRecord();
				med.setDateAge(dateAge);
				med.setDrugName(drugName);
				med.setFarm(farm);
				med.setId(id);
				med.setDrugName(drugName);
				med.setLivestockId(livestockId);
				med.setMedicalPeo(medicalPeo);
				med.setMedicalResult(medicalResult);
				med.setMedicalTime(medicalTime);
				med.setMethod(method);
				med.setRoomNum(roomNum);
				med.setSickNum(StringUtil.toInitInteger(sickNum));
				med.setRemain(StringUtil.toInitInteger(remain));
				med.setSickReason(sickReason);
				if(medService.doCreateOrUpdate(med)) {
					temp++;
				} 
			}
			if(temp==index) {
				request.setAttribute("info",MessageUtil.get("medicalRecord.updateAll.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("medicalRecord.updateAll.false"));
			}
			this.getMedRecList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllMedicalPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MedicalRecord mes = null ;
		Map<Integer,MedicalRecord> map = null;
		String pages = "../error.jsp";
		try {
			String[] idAll = request.getParameterValues("check");
			map = new TreeMap<Integer, MedicalRecord>();
			int index = 0;
			for(String id : idAll) {
				Integer key = Integer.parseInt(id);
				mes = medService.findById(MedicalRecord.class,key);
				if(mes != null) {
					map.put(index++,mes);
				}
			}
			if(!map.isEmpty()) {
				request.setAttribute("currentPage",request.getParameter("currentPage"));
				request.setAttribute("index", index);
				request.setAttribute("all",map);
				pages = "model1_user/batch_treat_update.jsp";
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeleteAllMedical(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer[] idAll = StringUtil.getStringArr2intArr(request.getParameterValues("check"));
		try {
			if(medService.doBatchDelete(MedicalRecord.class,idAll)){
				request.setAttribute("info", MessageUtil.get("medicalRecord.deleteAll.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("medicalRecord.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("medicalRecord.deleteAll.false"));
		}
		this.getMedRecList(request, response);
	}
	
	public void getUpdateMedical(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id  = Integer.parseInt(request.getParameter("id")); 
			String medicalTime = request.getParameter("medicalTime"); 
			String roomNum = request.getParameter("roomNum"); 
			String livestockId = request.getParameter("livestockId"); 
			String dateAge = request.getParameter("dateAge"); 
			String sickNum = request.getParameter("sickNum"); 			
			String sickReason = request.getParameter("sickReason"); 
			String medicalPeo = request.getParameter("medicalPeo"); 
			String drugName = request.getParameter("drugName"); 
			String method = request.getParameter("method"); 
			String medicalResult = request.getParameter("medicalResult"); 
			String farmId = request.getParameter("farmId"); 
			String remain = request.getParameter("remain");
			FarmMes farm = new FarmMes();
			farm.setFarmId(farmId);
			MedicalRecord med = new MedicalRecord();
			med.setDateAge(dateAge);
			med.setDrugName(drugName);
			med.setFarm(farm);
			med.setId(id);
			med.setDrugName(drugName);
			med.setLivestockId(livestockId);
			med.setMedicalPeo(medicalPeo);
			med.setMedicalResult(medicalResult);
			med.setMedicalTime(medicalTime);
			med.setMethod(method);
			med.setRoomNum(roomNum);
			med.setSickNum(StringUtil.toInitInteger(sickNum));
			med.setRemain(StringUtil.toInitInteger(remain));
			med.setSickReason(sickReason);
			if (medService.doCreateOrUpdate(med)) {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("medicalRecord.update.true"));
			} else {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("medicalRecord.update.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateMedicalPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			MedicalRecord mes = medService.findById(MedicalRecord.class,id);
			if(mes != null) {
				request.setAttribute("med", mes);
				pages = "model1_user/update_treat_record.jsp";
			} else {
				request.setAttribute("info",MessageUtil.get("medicalRecord.find.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeleteMedical(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			boolean flag = medService.doDelete(MedicalRecord.class,id);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void getMedRecList(HttpServletRequest request, HttpServletResponse response)
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
			List<MedicalRecord> list = medService.findAll(farmId,content,currentPage, lineSize,date[0],date[1]);
			request.setAttribute("all",list);
			request.setAttribute("allRecorders", medService.getAllrecord(""));
			request.setAttribute("date1", StringUtil.getReturnDate(date[0]));
			request.setAttribute("date2", StringUtil.getReturnDate(date[1]) );
			request.setAttribute("content_search", content);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("URL", "MedicalRecordServlet");
			if(role.equals("user")) {
				pages = "model1_user/farm_treat_record.jsp";
				request.setAttribute("role", role);
			} else {
				String farmName = request.getParameter("farmName");
				request.setAttribute("farmName", farmName);
				pages = "model1_admin/farm_treat_record.jsp?farmId="+farmId;
			}	
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		medService = (MedicalRecordService) context.getBean("medicalRecordService");
	}
}
