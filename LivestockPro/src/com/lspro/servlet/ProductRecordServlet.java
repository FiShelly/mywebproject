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
import com.lspro.pojo.ProductionRecords;
import com.lspro.service.ProductRecordService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class ProductRecordServlet extends HttpServlet {

	private ApplicationContext context ;
	private ProductRecordService proService;
	
	public ProductRecordServlet() {
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
			this.getProRecList(request,response);
		} else if (action.equals("delete")) {
			this.getDeletePro(request,response);
		} else if (action.equals("updatePre")) {
			this.getUpdatePre(request,response);
		} else if (action.equals("update")) {
			this.getUpdatePro(request,response);
		} else if (action.equals("deleteAll")){
			this.getDeleteAllPro(request,response);
		} else if (action.equals("updateAllPre")) {
			this.getUpdateAllProPre(request,response);
		} else if (action.equals("updateAll")) {
			this.getUpdateAllPro(request,response);
		} else if (action.equals("insert")){
			this.getInsertPro(request,response);
		}
	}
	
	public void getInsertPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		FarmMes farm = null;
		ProductionRecords mes = null;
		try {
			String farmId = request.getParameter("farmId"); 
			farm = new FarmMes();
			farm.setFarmId(farmId);
			String[] roomNum = request.getParameterValues("roomNumber");
			String[] recordDate = request.getParameterValues("date");
			String[] birthNum = request.getParameterValues("birthNumber");
			String[] putNum = request.getParameterValues("enterNumber");
			String[] inNum = request.getParameterValues("exportNumber");
			String[] deadNum = request.getParameterValues("deathNumber");
			String[] remainNum = request.getParameterValues("livestockNumber");
			String[] note = request.getParameterValues("remark");
			int temp = 0;
			for(int i = 0 ;i<roomNum.length;i++) {
				mes = new ProductionRecords();
				mes.setBirthNum(StringUtil.toInitInteger(birthNum[i]));
				mes.setDeadNum(StringUtil.toInitInteger(deadNum[i]));
				mes.setFarm(farm);
				mes.setInNum(StringUtil.toInitInteger(inNum[i]));
				mes.setNote(note[i]);
				mes.setPutNum(StringUtil.toInitInteger(putNum[i]));
				mes.setRecordDate(recordDate[i]);
				mes.setRemainNum(StringUtil.toInitInteger(remainNum[i]));
				mes.setRoomNum(roomNum[i]);
				if(proService.doCreateOrUpdate(mes)) {
					temp++;
				}
			}
			if(temp == roomNum.length) {
				request.setAttribute("info",MessageUtil.get("productionRecords.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("productionRecords.insert.false"));
			}
			pages="model1_user/insertData_productRecord.jsp";
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
		}
	}
	
	public void getUpdateAllPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FarmMes farm = null;
		ProductionRecords mes = null;
		try {
			Integer index = Integer.parseInt(request.getParameter("index"));
			String farmId = request.getParameter("farmId"); 
			
			int temp = 0;
			for(int i = 0 ;i<index;i++) {				
				String roomNum = request.getParameter("roomNum_"+i);
				String recordDate = request.getParameter("recordDate_"+i);
				String birthNum = request.getParameter("birthNum_"+i);
				String putNum = request.getParameter("putNum_"+i);
				String inNum = request.getParameter("inNum_"+i);
				String deadNum = request.getParameter("deadNum_"+i);
				String remainNum = request.getParameter("remainNum_"+i);
				String note = request.getParameter("note_"+i);
				Integer id = Integer.parseInt(request.getParameter("id_"+i));
				mes = new ProductionRecords();
				farm = new FarmMes();
				farm.setFarmId(farmId);
				mes.setFarm(farm);
				mes.setRoomNum(roomNum);
				mes.setBirthNum(StringUtil.toInitInteger(birthNum));
				mes.setDeadNum(StringUtil.toInitInteger(deadNum));
				mes.setId(id);
				mes.setInNum(StringUtil.toInitInteger(inNum));
				mes.setNote(note);
				mes.setPutNum(StringUtil.toInitInteger(putNum));
				mes.setRecordDate(recordDate);
				mes.setRemainNum(StringUtil.toInitInteger(remainNum));
				if(proService.doCreateOrUpdate(mes)) {
					temp++;
				} 
			}
			if(temp==index) {
				request.setAttribute("info",MessageUtil.get("productionRecords.updateAll.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("productionRecords.updateAll.false"));
			}
			this.getProRecList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllProPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductionRecords mes = null ;
		Map<Integer,ProductionRecords> map = null;
		String pages = "../error.jsp";
		try {
			String[] idAll = request.getParameterValues("check");
			map = new TreeMap<Integer, ProductionRecords>();
			int index = 0;
			for(String id : idAll) {
				Integer key = Integer.parseInt(id);
				mes = proService.findById(ProductionRecords.class,key);
				if(mes != null) {
					map.put(index++,mes);
				}
			}
			if(!map.isEmpty()) {
				request.setAttribute("currentPage",request.getParameter("currentPage"));
				request.setAttribute("index", index);
				request.setAttribute("all",map);
				pages = "model1_user/batch_product_update.jsp";
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getDeleteAllPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer[] idAll = StringUtil.getStringArr2intArr(request.getParameterValues("check"));
		try {
			if(proService.doBatchDelete(ProductionRecords.class, idAll)){
				request.setAttribute("info", MessageUtil.get("productionRecords.deleteAll.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("productionRecords.deleteAll.false"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("info", MessageUtil.get("productionRecords.deleteAll.false"));
		}
		this.getProRecList(request, response);
	}
	
	
	
	public void getUpdatePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String pages = "../error.jsp";
			String farmId = request.getParameter("farmId");
			String roomNum = request.getParameter("roomNum");
			String recordDate = request.getParameter("recordDate");
			String birthNum = request.getParameter("birthNum");
			String putNum = request.getParameter("putNum");
			String inNum = request.getParameter("inNum");
			String deadNum = request.getParameter("deadNum");
			String remainNum = request.getParameter("remainNum");
			String note = request.getParameter("note");
			Integer id = Integer.parseInt(request.getParameter("id"));
			ProductionRecords mes = new ProductionRecords();
			FarmMes farm = new FarmMes();
			farm.setFarmId(farmId);
			mes.setFarm(farm);
			mes.setRoomNum(roomNum);
			mes.setBirthNum(StringUtil.toInitInteger(birthNum));
			mes.setDeadNum(StringUtil.toInitInteger(deadNum));
			mes.setId(id);
			mes.setInNum(StringUtil.toInitInteger(inNum));
			mes.setNote(note);
			mes.setPutNum(StringUtil.toInitInteger(putNum));
			mes.setRecordDate(recordDate);
			mes.setRemainNum(StringUtil.toInitInteger(remainNum));
			if(proService.doCreateOrUpdate(mes)) {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("productRecord.update.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("productRecord.update.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdatePre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			ProductionRecords mes = proService.findById(ProductionRecords.class,id);
			if(mes != null) {
				request.setAttribute("pro", mes);
				pages = "model1_user/update_product_record.jsp";
			} else {
				request.setAttribute("info",MessageUtil.get("productRecord.find.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeletePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			boolean flag = proService.doDelete(ProductionRecords.class,id);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getProRecList(HttpServletRequest request, HttpServletResponse response)
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
			List<ProductionRecords> list = proService.findAll(farmId,content,currentPage, lineSize,date[0],date[1]);
			request.setAttribute("all",list);
			request.setAttribute("allRecorders", proService.getAllrecord(""));
			request.setAttribute("date1", StringUtil.getReturnDate(date[0]));
			request.setAttribute("date2", StringUtil.getReturnDate(date[1]) );
			request.setAttribute("content_search", content);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("URL", "ProductRecordServlet");
			if(role.equals("user")){
				pages = "model1_user/farm_product_record.jsp";
				request.setAttribute("role", role);
			} else {
				String farmName = request.getParameter("farmName");
				request.setAttribute("farmName", farmName);
				pages = "model1_admin/farm_product_record.jsp?farmId="+farmId;
			}			
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		proService = (ProductRecordService) context.getBean("productRecordService");
	}

}
