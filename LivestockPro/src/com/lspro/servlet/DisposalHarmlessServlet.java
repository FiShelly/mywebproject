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
import com.lspro.pojo.DisposalHarmless;
import com.lspro.pojo.FarmMes;
import com.lspro.service.DisposalHarmlessService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class DisposalHarmlessServlet extends HttpServlet {

	private ApplicationContext context ;
	private DisposalHarmlessService disService;

	public DisposalHarmlessServlet() {
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
			this.getDispHarmList(request,response);
		} else if (action.equals("delete")) {
			this.getDeleteDisposal(request,response);
		} else if (action.equals("updatePre")) {
			this.getUpdateDisposalPre(request,response);
		} else if (action.equals("update")) {
			this.getUpdateDisposal(request,response);
		} else if (action.equals("deleteAll")){
			this.getDeleteAllDisposal(request,response);
		} else if (action.equals("updateAllPre")) {
			this.getUpdateAllDisposalPre(request,response);
		} else if (action.equals("updateAll")) {
			this.getUpdateAllDisposal(request,response);
		} else if (action.equals("insert")){
			this.getInsertDisposal(request,response);
		}
	}
	
	public void getInsertDisposal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		DisposalHarmless disp = null ;
		FarmMes farm = null;
		try {
			String farmId = request.getParameter("farmId");
			farm = new FarmMes();
			farm.setFarmId(farmId);
			
			String[] disposalTime = request.getParameterValues("date"); 
			String[] number = request.getParameterValues("amount"); 
			String[] disposalOrResult = request.getParameterValues("deathReason"); 
			String[] disposalMethod = request.getParameterValues("processMethod"); 			
			String[] disposalStation = request.getParameterValues("principle"); 
			String[] note = request.getParameterValues("remark"); 
			int temp = 0;
			for(int i = 0;i<disposalTime.length;i++) {
				disp = new DisposalHarmless();
				disp.setDisposalMethod(disposalMethod[i]);
				disp.setDisposalOrResult(disposalOrResult[i]);
				disp.setDisposalStation(disposalStation[i]);
				disp.setDisposalTime(disposalTime[i]);
				disp.setFarm(farm);
				disp.setNote(note[i]);
				disp.setNumber(StringUtil.toInitInteger(number[i]));
				if (disService.doCreateOrUpdate(disp)) {
					temp++;
				}
			}
			if(temp==disposalTime.length) {
				request.setAttribute("info",MessageUtil.get("disposalHarmless.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("disposalHarmless.insert.false"));
			}
			pages = "model1_user/insertData_handleRecord.jsp";
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getUpdateAllDisposalPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DisposalHarmless mes = null ;
		Map<Integer,DisposalHarmless> map = null;
		String pages = "../error.jsp";
		try {
			String[] idAll = request.getParameterValues("check");
			map = new TreeMap<Integer, DisposalHarmless>();
			int index = 0;
			for(String id : idAll) {
				Integer key = Integer.parseInt(id);
				mes = disService.findById(DisposalHarmless.class,key);
				if(mes != null) {
					map.put(index++,mes);
				}
			}
			if(!map.isEmpty()) {
				request.setAttribute("index", index);
				request.setAttribute("all",map);
				request.setAttribute("currentPage",request.getParameter("currentPage"));
				pages = "model1_user/batch_handle_update.jsp";
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateAllDisposal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DisposalHarmless disp = null ;
		FarmMes farm = null;
		try {
			Integer index = Integer.parseInt(request.getParameter("index"));
			String farmId = request.getParameter("farmId"); 
			int temp = 0;
			for(int i = 0 ;i<index;i++) {
				Integer id  = Integer.parseInt(request.getParameter("id_"+i)); 
				String disposalTime = request.getParameter("disposalTime_"+i); 
				String number = request.getParameter("number_"+i); 
				String disposalOrResult = request.getParameter("disposalOrResult_"+i); 
				String disposalMethod = request.getParameter("disposalMethod_"+i); 			
				String disposalStation = request.getParameter("disposalStation_"+i); 
				String note = request.getParameter("note_"+i); 
				farm = new FarmMes();
				farm.setFarmId(farmId);
				disp = new DisposalHarmless();
				disp.setDisposalMethod(disposalMethod);
				disp.setDisposalOrResult(disposalOrResult);
				disp.setDisposalStation(disposalStation);
				disp.setDisposalTime(disposalTime);
				disp.setFarm(farm);
				disp.setId(id);
				disp.setNote(note);
				disp.setNumber(StringUtil.toInitInteger(number));
				if(disService.doCreateOrUpdate(disp)) {
					temp++;
				} 
			}
			if(temp==index) {
				request.setAttribute("info",MessageUtil.get("disposalHarmless.updateAll.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("disposalHarmless.updateAll.false"));
			}
			this.getDispHarmList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeleteAllDisposal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer[] idAll = StringUtil.getStringArr2intArr(request.getParameterValues("check"));
		try {
			if(disService.doBatchDelete(DisposalHarmless.class,idAll)){
				request.setAttribute("info", MessageUtil.get("disposalHarmless.deleteAll.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("disposalHarmless.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("disposalHarmless.deleteAll.false"));
		}
		this.getDispHarmList(request, response);
	}
	
	public void getUpdateDisposal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id  = Integer.parseInt(request.getParameter("id")); 
			String disposalTime = request.getParameter("disposalTime"); 
			String number = request.getParameter("number"); 
			String disposalOrResult = request.getParameter("disposalOrResult"); 
			String disposalMethod = request.getParameter("disposalMethod"); 			
			String disposalStation = request.getParameter("disposalStation"); 
			String note = request.getParameter("note"); 
			String farmId = request.getParameter("farmId"); 
			FarmMes farm = new FarmMes();
			farm.setFarmId(farmId);
			DisposalHarmless disp = new DisposalHarmless();
			disp.setDisposalMethod(disposalMethod);
			disp.setDisposalOrResult(disposalOrResult);
			disp.setDisposalStation(disposalStation);
			disp.setDisposalTime(disposalTime);
			disp.setFarm(farm);
			disp.setId(id);
			disp.setNote(note);
			disp.setNumber(StringUtil.toInitInteger(number));
			if (disService.doCreateOrUpdate(disp)) {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("disposalHarmless.update.true"));
			} else {
				pages = "model1_user/forward.jsp";
				request.setAttribute("info",MessageUtil.get("disposalHarmless.update.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUpdateDisposalPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			DisposalHarmless mes = disService.findById(DisposalHarmless.class,id);
			if(mes != null) {
				request.setAttribute("disp", mes);
				pages = "model1_user/update_handle_record.jsp";
			} else {
				request.setAttribute("info",MessageUtil.get("disposalHarmless.find.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDeleteDisposal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			boolean flag = disService.doDelete(DisposalHarmless.class,id);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDispHarmList(HttpServletRequest request, HttpServletResponse response)
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
			List<DisposalHarmless> list = disService.findAll(farmId,content,currentPage, lineSize,date[0],date[1]);
			request.setAttribute("all",list);
			request.setAttribute("allRecorders", disService.getAllrecord(""));
			request.setAttribute("date1", StringUtil.getReturnDate(date[0]));
			request.setAttribute("date2", StringUtil.getReturnDate(date[1]) );
			request.setAttribute("content_search", content);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("URL", "DisposalHarmlessServlet");
			if(role.equals("user")) {
				pages = "model1_user/farm_handle_record.jsp";
				request.setAttribute("role", role);
			} else {
				String farmName = request.getParameter("farmName");
				request.setAttribute("farmName", farmName);
				pages = "model1_admin/farm_handle_record.jsp?farmId="+farmId;
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		disService = (DisposalHarmlessService) context.getBean("disposalHarmlessService");
	}

}
