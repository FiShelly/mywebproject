package com.lspro.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.Supplies;
import com.lspro.pojo.SuppliesDispatch;
import com.lspro.pojo.SuppliesItem;
import com.lspro.service.SuppliesService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class SuppliesServlet extends HttpServlet {

	private ApplicationContext context ;
	private SuppliesService supService;

	public SuppliesServlet() {
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
			this.getSuppliesList(request,response);
		} else if (action.equals("insert")) {
			this.getSuppliesInsert(request, response);
		} else if (action.equals("delete")) {
			this.getSuppliesDelete(request, response);
		} else if (action.equals("updatePre")) {
			this.getSuppliesUpdatePre(request, response);
		} else if (action.equals("update")) {
			this.getSuppliesUpdate(request,response);
		} else if (action.equals("checkId")){
			this.getSuppliesCheckId(request,response);
		} else if (action.equals("deleteAll")){
			this.getSuppliesDeleteAll(request,response);
		} else if (action.equals("detail_list")){
			this.getSuppliesDetaillList(request,response);
		} else if (action.equals("insert_supItem")){
			this.getSuppliesItemInsert(request,response);
		} else if (action.equals("delete_supItem")){
			this.getSuppliesItemDelete(request,response);
		} else if (action.equals("updatePre_supItem")){
			this.getSuppliesItemUpdatePre(request,response);
		} else if (action.equals("update_supItem")){
			this.getSuppliesItemUpdate(request,response);
		} else if (action.equals("insertPre_supItem")){
			this.getSuppliesItemInsertPre(request, response);
		}else if (action.equals("allSup_list")){
			this.getAllSuppliesList(request, response);
		}else if (action.equals("allSupName_list")){
			this.getAllSuppliesNameList(request, response);
		}else if (action.equals("insert_disSup")){
			this.getDispatchSupInsert(request, response);
		}else if (action.equals("insertPre_disSup")){
			this.getDispatchSupInsertPre(request, response);
		}else if (action.equals("supItemDis_list")){
			this.getDispatchSupLis(request, response);
		}else if (action.equals("supItemDis_delete")){
			this.getDispatchSupDelete(request, response);
		}else if (action.equals("supItemDis_change")){
			this.getDispatchSupChange(request, response);
		}else if (action.equals("supItemDis_deleteAll")){
			this.getDispatchSupDeleteAll(request, response);
		}
	}
	
	public void getDispatchSupDeleteAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer[] ids = StringUtil.getStringArr2intArr(request.getParameterValues("check"));
		try{
			if(supService.batchDeleteForSupItemDis(SuppliesDispatch.class,ids)){
				request.setAttribute("info",  MessageUtil.get("suppliesItemDispatch.deleteAll.true"));
			} else {
				request.setAttribute("info",  MessageUtil.get("suppliesItemDispatch.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",  MessageUtil.get("suppliesItemDispatch.deleteAll.false"));
		}
		request.getRequestDispatcher("model4_admin/model4_content_meterialDispatch.jsp").forward(request, response);
	}
	
	public void getDispatchSupChange(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			flag = supService.updateArriveStatus(true, id);
		} catch (Exception e) {
			flag = false;
		}
		response.setContentType("text/html;charset=UTF-8") ;
		response.getWriter().print(flag);
	}
	
	public void getDispatchSupDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			boolean flag = supService.doDeleteSuppliesItemDispatch(SuppliesDispatch.class,id);
			response.setContentType("text/html;charset=UTF-8") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getDispatchSupLis(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		int currentPage = 1;
		int lineSize = 11;
		String supName = StringUtil.getInitString(request.getParameter("supName"));
		String supId = StringUtil.getInitString(request.getParameter("supId"));
		String[] date = StringUtil.getDataInit("1900-01-01", request.getParameter("date"));
		String adminloc = StringUtil.getInitString(request.getParameter("address"));
		try{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		try {
			List<SuppliesDispatch> list = supService.findAllSuppliesDispatch(adminloc, supId, supName, date[1], currentPage, lineSize);
			request.setAttribute("list",list);
			request.setAttribute("allRecorders", supService.getAllrecord(""));
			request.setAttribute("supName", supName);
			request.setAttribute("date", StringUtil.getReturnDate(date[1]) );
			request.setAttribute("supId", supId);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			pages = "model4_admin/model4_content_meterialDispatch.jsp";
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDispatchSupInsertPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("supId");
		SuppliesItem item = supService.findSuppliesItemById(SuppliesItem.class, id);
		request.setAttribute("item", item);
		request.getRequestDispatcher("model4_admin/model4_dispatch_insert.jsp").forward(request, response);
	}
	
	public void getDispatchSupInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String province = request.getParameter("cmbProvince2");
		String city = request.getParameter("cmbCity2");
		String country = request.getParameter("cmbArea2");
		String detail = request.getParameter("address");
		String date = request.getParameter("date");
		String number = request.getParameter("amount");
		String amount = request.getParameter("count");
		String num = (Integer.parseInt(amount)-Integer.parseInt(number))+"";
		String targerAddress = StringUtil.toChangeAddress(province, city, country)+detail;
		String itemId = request.getParameter("supId");
		SuppliesItem item = new SuppliesItem();
		item.setSuppliesId(itemId);
		SuppliesDispatch supDis = new SuppliesDispatch();
		supDis.setCity(city);
		supDis.setCountry(country);
		supDis.setDate(date);
		supDis.setItem(item);
		supDis.setNumber(number);
		supDis.setProvince(province);
		supDis.setTargerAddress(targerAddress);
		supDis.setDetail(detail);
		try {
			if(supService.doCreateOrUpdateSupItemDispatch(supDis, itemId,num )) {
				request.setAttribute("id", request.getParameter("id"));
				request.setAttribute("info", MessageUtil.get("suppliesItemDispatch.insert.true"));
				request.setAttribute("open", true);
			} else {
				request.setAttribute("info", MessageUtil.get("suppliesItemDispatch.insert.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("suppliesItemDispatch.insert.false"));
		}
		request.getRequestDispatcher("model4_admin/forwardToDis.jsp").forward(request, response);
	}
	
	public void getAllSuppliesNameList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = request.getParameter("address");
		List list = supService.findAllSuppliesName(address);
		request.setAttribute("list", list);
		request.getRequestDispatcher("model4_admin/model4_content_meterial.jsp").forward(request, response);
	}
	
	public void getAllSuppliesList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Supplies sup = supService.findAllSupplies(id);
		request.setAttribute("sup", sup);
		request.getRequestDispatcher("model4_admin/model4_content_meterialItem.jsp").forward(request, response);
	}
	
	public void getSuppliesItemInsertPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("reserveId", request.getParameter("reserveId"));
		request.setAttribute("address", request.getParameter("supAddress"));
		request.getRequestDispatcher("model2_admin/insert_meterial.jsp").forward(request, response);
	}
	
	public void getSuppliesItemUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		request.setAttribute("currentPage", request.getParameter("currentPage_D"));
		request.setAttribute("reserveId", request.getParameter("reserveId"));
		request.setAttribute("address", request.getParameter("supAddress"));
		SuppliesItem item = this.getSuppliesItemModel(request);
		try {
			if(supService.doCreateOrUpdateSupItem(item)) {
				request.setAttribute("info", MessageUtil.get("suppliesItem.update.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("suppliesItem.update.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("suppliesItem.update.false"));
		}
		pages = "model2_admin/forward_detail.jsp";
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	public void getSuppliesItemUpdatePre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		String suppliesId = request.getParameter("suppliesId");
		request.setAttribute("reserveId", request.getParameter("reserveId"));
		request.setAttribute("address", request.getParameter("supAddress"));
		try {
			SuppliesItem item = supService.findSuppliesItemById(SuppliesItem.class,suppliesId);
			if(item != null) {
				request.setAttribute("item", item);
				request.setAttribute("currentPage", request.getParameter("currentPage_D"));
				pages = "model2_admin/update_meterial.jsp";
			}
		} catch (Exception e) {
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	public void getSuppliesItemDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String suppliesId = request.getParameter("suppliesId");
			boolean flag = supService.doDeleteSuppliesItem(SuppliesItem.class,suppliesId);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
		}
	}
	
	public SuppliesItem getSuppliesItemModel(HttpServletRequest request){
		String suppliesId = request.getParameter("meterial_number");	
		String suppliesName = request.getParameter("meterial_name");	
		String suppliesPrice = request.getParameter("price");	
		String producter = request.getParameter("producer");		
		String number = request.getParameter("amount");			
		String validDate = request.getParameter("quality");	
		String failSitution = request.getParameter("valid");	
		String productDate = request.getParameter("date");
		String reserveId = request.getParameter("reserveId");
		Supplies supplies = new Supplies();
		supplies.setReserveId(reserveId);
		SuppliesItem item = new SuppliesItem();
		item.setFailSitution(failSitution);
		item.setNumber(number);
		item.setProductDate(productDate);
		item.setProducter(producter);
		item.setSupplies(supplies);
		item.setSuppliesId(suppliesId);
		item.setSuppliesName(suppliesName);
		item.setSuppliesPrice(suppliesPrice);
		item.setValidDate(validDate);
		return item;
	}
	
	public void getSuppliesItemInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		SuppliesItem item = this.getSuppliesItemModel(request);
		request.setAttribute("address", request.getParameter("supAddress"));
		request.setAttribute("reserveId", request.getParameter("reserveId"));
		System.out.println(item.getSuppliesId() + "  ===  " +item.getSupplies().getReserveId()); 
		try {
			if(supService.doCreateOrUpdateSupItem(item)) {
				request.setAttribute("info", MessageUtil.get("suppliesItem.insert.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("suppliesItem.insert.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("suppliesItem.insert.false"));
		}
		pages = "model2_admin/forward_detail.jsp";
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	public void getSuppliesDetaillList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		int currentPage = 1;
		int lineSize = 11;
		String address = request.getParameter("supAddress");
		String reserveId = request.getParameter("reserveId");
		String detail_number = StringUtil.getInitString(request.getParameter("detail_number"));
		String detail_name = StringUtil.getInitString(request.getParameter("detail_name"));
		String choose = StringUtil.getRadioButtonInit(request.getParameter("choose"));
		String[] date = StringUtil.getDataInit(request.getParameter("detail_start"), request.getParameter("detail_end"));
		try{
			currentPage = Integer.parseInt(request.getParameter("currentPage_D"));
		} catch (Exception e) {
		}
		try {
			List<SuppliesItem> list = supService.findAllSupItem(reserveId, detail_number, detail_name, date[0], date[1], choose, currentPage, lineSize);
			request.setAttribute("items_list",list);
			request.setAttribute("choose",choose);
			request.setAttribute("address",address);
			request.setAttribute("allRecorders", supService.getAllrecord(""));
			request.setAttribute("detail_number", detail_number);
			request.setAttribute("detail_start", StringUtil.getReturnDate(date[0]));
			request.setAttribute("detail_end", StringUtil.getReturnDate(date[1]) );
			request.setAttribute("detail_name", detail_name);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("reserveId", reserveId);
			pages = "model2_admin/material_detail.jsp";
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("suppliesItem.find.false"));
		}
	}
	
	public void getSuppliesDeleteAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] suppliesId = request.getParameterValues("check");
		try{
			if(supService.doBatchDelete(Supplies.class,suppliesId)){
				request.setAttribute("info",  MessageUtil.get("supplies.deleteAll.true"));
			} else {
				request.setAttribute("info",  MessageUtil.get("supplies.deleteAll.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info",  MessageUtil.get("supplies.deleteAll.false"));
		}
		this.getSuppliesList(request, response);
	}
	
	public void getSuppliesCheckId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	public void getSuppliesUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		Supplies sup = this.getSuppliesModel(request);
		try {
			if(supService.doCreateOrUpdate(sup)) {
				request.setAttribute("info", MessageUtil.get("supplies.update.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("supplies.update.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("supplies.update.false"));
		}
		pages = "model2_admin/forward.jsp";
		request.getRequestDispatcher(pages).forward(request, response);

	}
	public void getSuppliesUpdatePre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		String reserveId = request.getParameter("reserveId");
		try {
			Supplies sup = supService.findById(Supplies.class,reserveId);
			if(sup != null) {
				request.setAttribute("sup", sup);
				request.setAttribute("currentPage", request.getParameter("currentPage"));
				pages = "model2_admin/material_insert_update.jsp";
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
		}
	}
	
	public void getSuppliesDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String reserveId = request.getParameter("reserveId");
			boolean flag = supService.doDelete(Supplies.class,reserveId);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
		}
	}
	
	public Supplies getSuppliesModel(HttpServletRequest request){
		String reserveId = request.getParameter("reserve_number");
		String managementstation = request.getParameter("manager_unit");
		String head = request.getParameter("person");
		String phoneNum = request.getParameter("phone_number");
		String registDate = request.getParameter("date");
		String province = request.getParameter("cmbProvince");
		String city = request.getParameter("cmbCity");
		String county = request.getParameter("cmbArea");
		String dplace = request.getParameter("factor");
		String address = StringUtil.toChangeAddress(province, city, county)+dplace;
		String position = request.getParameter("job");
		String name = request.getParameter("supppliesName");
		Supplies sup = new Supplies();
		sup.setReserveId(reserveId);
		sup.setProvince(province);
		sup.setCity(city);
		sup.setCountry(county);
		sup.setDetail(dplace);
		sup.setAddress(address);
		sup.setHead(head);
		sup.setName(name);
		sup.setManagementstation(managementstation);
		sup.setPhoneNum(phoneNum);
		sup.setPosition(position);
		sup.setRegistDate(registDate);
		return sup;
	}
	
	public void getSuppliesInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		Supplies sup = this.getSuppliesModel(request);
		System.out.println(sup);
		try {
			if(supService.doCreateOrUpdate(sup)) {
				request.setAttribute("info", MessageUtil.get("supplies.insert.true"));
			} else {
				request.setAttribute("info", MessageUtil.get("supplies.insert.false"));
			}
		} catch (Exception e) {
			request.setAttribute("info", MessageUtil.get("supplies.insert.false"));
		}
		pages = "model2_admin/forward.jsp";
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	public String getCurrentDate() {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = new Date();
		 String current = sdf.format(date);
		 return current;
	}
	
	
	public void getSuppliesList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		int currentPage = 1;
		int lineSize = 11;
		String address = request.getParameter("address");
		String name = StringUtil.getInitString(request.getParameter("person"));
		String[] date = StringUtil.getDataInit(request.getParameter("date1"), request.getParameter("date2"));
		String supAddress = StringUtil.getInitString(request.getParameter("name"));
		try{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		try {
			List<Supplies> list = supService.findAll(address,supAddress, name, date[0], date[1], currentPage, lineSize);
			request.setAttribute("all",list);
			request.setAttribute("allRecorders", supService.getAllrecord(""));
			request.setAttribute("name", name);
			request.setAttribute("date1", StringUtil.getReturnDate(date[0]));
			request.setAttribute("date2", StringUtil.getReturnDate(date[1]) );
			request.setAttribute("supAddress", supAddress);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("URL", "SuppliesServlet");
			pages = "model2_admin/material_content.jsp";
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
		}
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		supService = (SuppliesService) context.getBean("suppliesService");
	}

}
