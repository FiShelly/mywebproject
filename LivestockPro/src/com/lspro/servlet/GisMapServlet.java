package com.lspro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.EpidemicReport;
import com.lspro.pojo.Supplies;
import com.lspro.pojo.SuppliesDispatch;
import com.lspro.service.EpidemicReportService;
import com.lspro.service.SuppliesService;
import com.lspro.util.StringUtil;

public class GisMapServlet extends HttpServlet {
	
	private ApplicationContext context;
	private EpidemicReportService service;
	private SuppliesService supService;
	
	public GisMapServlet() {
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
		if(action.equals("epi_map")){
			this.getEpiMap(request,response);
		} else if(action.equals("admin_sup_Map")){
			this.getAdminSupMap(request,response);
		}
	}
	
	public void getAdminSupMap(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = request.getParameter("address");
		List<Supplies> supList =supService.findAllSuppliesForMap(address);
		List<SuppliesDispatch> disList = supService.findAllSuppliesItemDisForMap(address,false);
		request.setAttribute("supList", supList);
		request.setAttribute("disList", disList);
		request.setAttribute("flag", true);
		request.getRequestDispatcher("model4_admin/model4_content_mapMeterial.jsp").forward(request, response);
	}

	public void getEpiMap(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String search = StringUtil.getInitString(request.getParameter("key"));
		System.out.println(province);
		System.out.println(city == null); 
		List<EpidemicReport> list = service.findAllLoc(province, city,search);
		System.out.println(list.size() + "  =====");
		request.setAttribute("list", list);
		if(request.getParameter("role").equals("user")){
			request.getRequestDispatcher("model4_user/model4_map_outbreak.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("model4_admin/model4_content_mapOutbreak.jsp").forward(request, response);
		}
	}
	
	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = (EpidemicReportService) context.getBean("epidemicReportService");
		supService = (SuppliesService) context.getBean("suppliesService");
	}

}
