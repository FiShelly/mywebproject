package com.lspro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.ProductA;
import com.lspro.service.ProductAService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class ProductAServlet extends HttpServlet {

	private ApplicationContext context;
	private ProductAService aService;
	
	public ProductAServlet() {
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
			this.getProductAList(request, response);
		} else if (action.equals("insert")) {
			this.insertProductA(request, response);
		} else if(action.equals("updatePre")) {
			this.updatePreProductA(request,response);
		} else if(action.equals("update")){
			this.updateProductA(request,response);
		} else if(action.equals("detail")){
			this.findDeatilProductA(request,response);
		} else if(action.equals("delete")){
			this.deleteProductA(request,response);
		}else if (action.equals("deleteAll")){
			this.deleteAllProductA(request,response);
		}
	}
	
	public void deleteAllProductA(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] idAll = request.getParameterValues("check");
		request.setAttribute("URL", "ProductAServlet");
		try {
			if(aService.doBatchDelete(ProductA.class, idAll)){
				request.setAttribute("info",MessageUtil.get("ProductA.deleteAll.true"));
			}else {
				request.setAttribute("info",MessageUtil.get("ProductA.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("ProductA.deleteAll.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}
	
	public ProductA getProductAModel(HttpServletRequest request){
		 String id = request.getParameter("productA_id");
		 String shipperName = request.getParameter("owner");
		 String phoneNum = request.getParameter("phone_number");
		 String productName = request.getParameter("product_name");
		 String number = request.getParameter("amount");
		 String startProvince = request.getParameter("s_province");
		 String startCity = request.getParameter("s_city");
		 String startCountry = request.getParameter("s_county");
		 String startDetail = request.getParameter("produce_place");
		 String endProvince = request.getParameter("cmbProvince");
		 String endCity = request.getParameter("cmbCity");
		 String endCountry = request.getParameter("cmbArea");
		 String endDetail = request.getParameter("productA_target_place");
		 String addressName = StringUtil.toChangeAddress(startProvince, startCity, startCountry)+startDetail;
		 String destination = StringUtil.toChangeAddress(endProvince, endCity, endCountry)+endDetail; 
		 String carrier = request.getParameter("transport_man");
		 String carrierPhone = request.getParameter("transport_phone");
		 String transportWay = request.getParameter("transport");
		 String transportId = request.getParameter("mark");
		 String disinfection = request.getParameter("degass");
		 String note = request.getParameter("remark");
		 String date = request.getParameter("sign_date");
		 String days = request.getParameter("valid_date");
		 String vetName = request.getParameter("doctor");
		 ProductA productA = new ProductA();
		 productA.setAddressName(addressName);
		 productA.setCarrier(carrier);
		 productA.setCarrierPhone(carrierPhone);
		 productA.setDate(date);
		 productA.setDays(days);
		 productA.setDestination(destination);
		 productA.setDisinfection(disinfection);
		 productA.setEndCity(endCity);
		 productA.setEndCountry(endCountry);
		 productA.setEndDetail(endDetail);
		 productA.setEndProvince(endProvince);
		 productA.setId(id);
		 productA.setNote(note);
		 productA.setNumber(number);
		 productA.setPhoneNum(phoneNum);
		 productA.setProductName(productName);
		 productA.setShipperName(shipperName);
		 productA.setStartCity(startCity);
		 productA.setStartCountry(startCountry);
		 productA.setStartDetail(startDetail);
		 productA.setStartProvince(startProvince);
		 productA.setTransportId(transportId);
		 productA.setTransportWay(transportWay);
		 productA.setVetName(vetName);
		 return productA;
	}
	
	public void getProductAList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		List<ProductA> list = aService.findAll(role, loc,number, owner, date[0],date[1], animalName, way, currentPage, lineSize);
		request.setAttribute("allRecorders", aService.getAllrecord(""));
		request.setAttribute("listC", list);
		request.setAttribute("flag", 3);
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
	
	public void insertProductA(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductA productA = this.getProductAModel(request);
		request.setAttribute("URL", "ProductAServlet");
		try {
			if (aService.doCreateOrUpdate(productA)) {
				request.setAttribute("info",MessageUtil.get("productA.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("productA.insert.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("productA.insert.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}
	
	public void updatePreProductA(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductA productA = aService.findById(ProductA.class, id);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("pa", productA);
		request.getRequestDispatcher("model3_admin/update_productA.jsp").forward(request, response);
	}

	public void updateProductA(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductA productA = this.getProductAModel(request);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("URL", "ProductAServlet");
		try {
			if (aService.doCreateOrUpdate(productA)) {
				request.setAttribute("info",MessageUtil.get("productA.update.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("productA.update.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("productA.update.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}
	
	public void findDeatilProductA(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductA productA = aService.findById(ProductA.class, id);
		request.setAttribute("pa", productA);
		request.getRequestDispatcher("model3_admin/detail_productA.jsp").forward(request, response);
	}
	
	public void deleteProductA(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		String id = request.getParameter("id");
		try {
			this.aService.doDelete(ProductA.class, id);
			flag = true;	
		} catch (Exception e) {
			flag = false;
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(flag);
	}
	
	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		aService = (ProductAService) context.getBean("productAService");
	}

}
