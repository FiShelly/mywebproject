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

import com.lspro.pojo.DisinfectionRecord;
import com.lspro.pojo.FarmMes;
import com.lspro.service.DisinfectionRecordService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class DisinfectionRecordServlet extends HttpServlet {

	private ApplicationContext context ;
	private DisinfectionRecordService disService;
	
	public DisinfectionRecordServlet() {
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
			this.getDisinfectList(request,response);
		} else if (action.equals("delete")) {
			this.getDeleteDisfect(request,response);
		} else if (action.equals("updatePre")) {
			this.getUpdateDisfectPre(request,response);
		} else if (action.equals("update")) {
			this.getUpdateDisfect(request,response);
		} else if (action.equals("deleteAll")){
			this.getDeleteAllDisfect(request,response);
		} else if (action.equals("updateAllPre")) {
			this.getUpdateAllDisfectPre(request,response);
		} else if (action.equals("updateAll")) {
			this.getUpdateAllDisfect(request,response);
		}else if (action.equals("insert")){
			this.getInsertDisfect(request,response);
		}
	}
	
	public void getInsertDisfect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		FarmMes farm = null;
		DisinfectionRecord dis = null;
		try {
			String farmId = request.getParameter("farmId"); 
			farm = new FarmMes();
			farm.setFarmId(farmId);
			String[] disinfectionTime = request.getParameterValues("date"); 
			String[] place = request.getParameterValues("degassPlace"); 
			String[] disinfectionName = request.getParameterValues("degassName"); 
			String[] disinfectionDose = request.getParameterValues("batchNumber"); 
			String[] method = request.getParameterValues("degassMethod"); 
			String[] sign = request.getParameterValues("user"); 
			int temp = 0;
			for(int i = 0;i<disinfectionTime.length;i++) {
				dis = new DisinfectionRecord();
				dis.setDisinfectionDose(disinfectionDose[i]);
				dis.setDisinfectionName(disinfectionName[i]);
				dis.setDisinfectionTime(disinfectionTime[i]);
				dis.setFarm(farm);
				dis.setMethod(method[i]);
				dis.setPlace(place[i]);
				dis.setSign(sign[i]);
				if (disService.doCreateOrUpdate(dis)) {
					temp++;
				}
			}
			if (temp ==disinfectionTime.length) {
				request.setAttribute("info",MessageUtil.get("disinfectionRecord.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("disinfectionRecord.insert.false"));		
			}
			pages = "model1_user/insertData_degassRecord.jsp";
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
		}
	}
	public void getUpdateAllDisfect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FarmMes farm = null;
		try {
			Integer index = Integer.parseInt(request.getParameter("index"));
			String farmId = request.getParameter("farmId"); 
			int temp = 0;
			for(int i = 0 ;i<index;i++) {				
				Integer id  = Integer.parseInt(request.getParameter("id_"+i)); 
				String disinfectionTime = request.getParameter("disinfectionTime_"+i); 
				String place = request.getParameter("place_"+i); 
				String disinfectionName = request.getParameter("disinfectionName_"+i); 
				String disinfectionDose = request.getParameter("disinfectionDose_"+i); 
				String method = request.getParameter("method_"+i); 
				String sign = request.getParameter("sign_"+i); 
				farm = new FarmMes();
				farm.setFarmId(farmId);
				DisinfectionRecord dis = new DisinfectionRecord();
				dis.setFarm(farm);
				dis.setId(id);
				dis.setDisinfectionDose(disinfectionDose);
				dis.setDisinfectionName(disinfectionName);
				dis.setDisinfectionTime(disinfectionTime);
				dis.setMethod(method);
				dis.setPlace(place);
				dis.setSign(sign);
				if(disService.doCreateOrUpdate(dis)) {
					temp++;
				} 
			}
			if(temp==index) {
				request.setAttribute("info",MessageUtil.get("disinfectionRecord.updateAll.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("disinfectionRecord.updateAll.false"));
			}
			this.getDisinfectList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllDisfectPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DisinfectionRecord mes = null ;
		Map<Integer,DisinfectionRecord> map = null;
		String pages = "../error.jsp";
		try {
			String[] idAll = request.getParameterValues("check");
			map = new TreeMap<Integer, DisinfectionRecord>();
			int index = 0;
			for(String id : idAll) {
				Integer key = Integer.parseInt(id);
				mes = disService.findById(DisinfectionRecord.class,key);
				if(mes != null) {
					map.put(index++,mes);
				}
			}
			if(!map.isEmpty()) {
				request.setAttribute("currentPage",request.getParameter("currentPage"));
				request.setAttribute("index", index);
				request.setAttribute("all",map);
				pages = "model1_user/batch_degass_update.jsp";
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getDeleteAllDisfect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer[] idAll = StringUtil.getStringArr2intArr(request.getParameterValues("check"));
		try {
			if(disService.doBatchDelete(DisinfectionRecord.class,idAll)){
					request.setAttribute("info", MessageUtil.get("disinfectionRecord.deleteAll.true"));
			} else {
					request.setAttribute("info", MessageUtil.get("disinfectionRecord.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("disinfectionRecord.deleteAll.false"));
		}
		this.getDisinfectList(request, response);
	}
	
	public void getUpdateDisfect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id  = Integer.parseInt(request.getParameter("id")); 
			String disinfectionTime = request.getParameter("disinfectionTime"); 
			String place = request.getParameter("place"); 
			String disinfectionName = request.getParameter("disinfectionName"); 
			String disinfectionDose = request.getParameter("disinfectionDose"); 
			String method = request.getParameter("method"); 
			String sign = request.getParameter("sign"); 
			String farmId = request.getParameter("farmId"); 
			FarmMes farm = new FarmMes();
			farm.setFarmId(farmId);
			DisinfectionRecord dis = new DisinfectionRecord();
			dis.setFarm(farm);
			dis.setId(id);
			dis.setDisinfectionDose(disinfectionDose);
			dis.setDisinfectionName(disinfectionName);
			dis.setDisinfectionTime(disinfectionTime);
			dis.setMethod(method);
			dis.setPlace(place);
			dis.setSign(sign);
			if (disService.doCreateOrUpdate(dis)) {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("disinfectionRecord.update.true"));
			} else {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("disinfectionRecord.update.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateDisfectPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			DisinfectionRecord mes = disService.findById(DisinfectionRecord.class,id);
			if(mes != null) {
				request.setAttribute("dis", mes);
				pages = "model1_user/update_degass_record.jsp";
			} else {
				request.setAttribute("info",MessageUtil.get("disinfectionRecord.find.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeleteDisfect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			boolean flag = disService.doDelete(DisinfectionRecord.class,id);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDisinfectList(HttpServletRequest request, HttpServletResponse response)
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
			List<DisinfectionRecord> list = disService.findAll(farmId,content,currentPage, lineSize,date[0],date[1]);
			request.setAttribute("all",list);
			request.setAttribute("allRecorders", disService.getAllrecord(""));
			request.setAttribute("date1", StringUtil.getReturnDate(date[0]));
			request.setAttribute("date2", StringUtil.getReturnDate(date[1]) );
			request.setAttribute("content_search", content);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("URL", "DisinfectionRecordServlet");
			if(role.equals("user")) {
				pages = "model1_user/farm_degass_record.jsp";
				request.setAttribute("role", role);
			} else {
				String farmName = request.getParameter("farmName");
				request.setAttribute("farmName", farmName);
				pages = "model1_admin/farm_degass_record.jsp?farmId="+farmId;
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		disService = (DisinfectionRecordService) context.getBean("disinfectionRecordService");
	}

}
