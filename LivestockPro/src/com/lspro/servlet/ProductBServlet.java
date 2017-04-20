package com.lspro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.ProductB;
import com.lspro.service.ProductBService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class ProductBServlet extends HttpServlet {

	private ApplicationContext context;
	private ProductBService bService;
	
	public ProductBServlet() {
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
			this.getProductBList(request, response);
		} else if (action.equals("insert")) {
			this.insertProductB(request, response);
		} else if(action.equals("updatePre")) {
			this.updatePreProductB(request,response);
		} else if(action.equals("update")){
			this.updateProductB(request,response);
		} else if(action.equals("detail")){
			this.findDeatilProductB(request,response);
		} else if(action.equals("delete")){
			this.deleteProductB(request,response);
		}else if (action.equals("deleteAll")){
			this.deleteAllProductB(request,response);
		}
	}
	
	public void deleteAllProductB(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] idAll = request.getParameterValues("check");
		request.setAttribute("URL", "ProductAServlet");
		try {
			if(bService.doBatchDelete(ProductB.class, idAll)){
				request.setAttribute("info",MessageUtil.get("ProductB.deleteAll.true"));
			}else {
				request.setAttribute("info",MessageUtil.get("ProductB.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("ProductB.deleteAll.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}
	
	public ProductB getProductBModel(HttpServletRequest request){
		 String id = request.getParameter("productB_id");
		 String shipperName = request.getParameter("owner");
		 String productName = request.getParameter("product_name");
		 String number = request.getParameter("amount");
		 String startProvince = request.getParameter("s_province");
		 String startCity = request.getParameter("s_city");
		 String startCountry = request.getParameter("s_county");
		 String startDetail = request.getParameter("produce_place2");
		 String endProvince = request.getParameter("cmbProvince");
		 String endCity = request.getParameter("cmbCity");
		 String endCountry = request.getParameter("cmbArea");
		 String endDetail = request.getParameter("productB_target_place");
		 String addressName = StringUtil.toChangeAddress(startProvince, startCity, startCountry)+startDetail;
		 String destination = StringUtil.toChangeAddress(endProvince, endCity, endCountry)+endDetail; 
		 String note = request.getParameter("remark");
		 String date = request.getParameter("sign_date");
		 String days = request.getParameter("valid_date");
		 String vetName = request.getParameter("doctor");
		 String quarantinemarks = request.getParameter("code");
		 String producter = request.getParameter("produce_place1");
		 ProductB productB = new ProductB();
		 productB.setAddressName(addressName);
		 productB.setDate(date);
		 productB.setDays(days);
		 productB.setDestination(destination);
		 productB.setEndCity(endCity);
		 productB.setEndCountry(endCountry);
		 productB.setEndDetail(endDetail);
		 productB.setEndProvince(endProvince);
		 productB.setId(id);
		 productB.setNote(note);
		 productB.setNumber(number);
		 productB.setProductName(productName);
		 productB.setShipperName(shipperName);
		 productB.setStartCity(startCity);
		 productB.setStartCountry(startCountry);
		 productB.setStartDetail(startDetail);
		 productB.setStartProvince(startProvince);
		 productB.setVetName(vetName);
		 productB.setQuarantinemarks(quarantinemarks);
		 productB.setProducter(producter);
		 return productB;
	}
	
	public void getProductBList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		int lineSize = 11;
		String[] date = StringUtil.getDataInit(request.getParameter("start_date"), request.getParameter("end_date"));
		String owner = StringUtil.getInitString(request.getParameter("owner"));
		String animalName = StringUtil.getInitString(request.getParameter("animal_name"));
		String number = StringUtil.getInitString(request.getParameter("number"));
		String loc = request.getParameter("address");
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
		List<ProductB> list = bService.findAll(role,loc,number,  owner, date[0],date[1], animalName,  currentPage, lineSize);
		request.setAttribute("allRecorders", bService.getAllrecord(""));
		request.setAttribute("listD", list);
		request.setAttribute("flag", 4);
		request.setAttribute("start_date", StringUtil.getReturnDate(date[0]));
		request.setAttribute("end_date", StringUtil.getReturnDate(date[1]));
		request.setAttribute("owner", owner);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lineSize", lineSize);
		request.setAttribute("animal_name", animalName);
		request.setAttribute("number", number);
		request.getRequestDispatcher(page).forward(request, response);
	} 
	
	public void insertProductB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductB productB = this.getProductBModel(request);
		request.setAttribute("URL", "ProductBServlet");
		try {
			if (bService.doCreateOrUpdate(productB)) {
				request.setAttribute("info",MessageUtil.get("productB.insert.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("productB.insert.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("productB.insert.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}
	
	public void updatePreProductB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductB productB = bService.findById(ProductB.class, id);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("pb", productB);
		request.getRequestDispatcher("model3_admin/update_productB.jsp").forward(request, response);
	}

	public void updateProductB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductB productB = this.getProductBModel(request);
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		request.setAttribute("URL", "ProductBServlet");
		try {
			if (bService.doCreateOrUpdate(productB)) {
				request.setAttribute("info",MessageUtil.get("productB.update.true"));
			} else {
				request.setAttribute("info",MessageUtil.get("productB.update.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",MessageUtil.get("productB.update.false"));
		}
		request.getRequestDispatcher("model3_admin/forward.jsp").forward(request, response);
	}
	
	public void findDeatilProductB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductB productB = bService.findById(ProductB.class, id);
		request.setAttribute("pb", productB);
		request.getRequestDispatcher("model3_admin/detail_productB.jsp").forward(request, response);
	}
	
	public void deleteProductB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		String id = request.getParameter("id");
		try {
			this.bService.doDelete(ProductB.class, id);
			flag = true;	
		} catch (Exception e) {
			flag = false;
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(flag);
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		bService = (ProductBService) context.getBean("productBService");
	}

}
