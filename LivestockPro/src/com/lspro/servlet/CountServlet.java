package com.lspro.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.CountMsg;
import com.lspro.service.AnimalAService;
import com.lspro.service.AnimalBService;
import com.lspro.service.DisinfectionRecordService;
import com.lspro.service.EpidemicMonitoringService;
import com.lspro.service.EpidemicReportService;
import com.lspro.service.FarmService;
import com.lspro.service.ImmuneRecordService;
import com.lspro.service.MedicalRecordService;
import com.lspro.service.ProductAService;
import com.lspro.service.ProductBService;
import com.lspro.service.ProductRecordService;
import com.lspro.service.SuppliesService;
import com.lspro.util.GrayModel;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class CountServlet extends HttpServlet {
	private ApplicationContext context;
	private AnimalAService aService;
	private AnimalBService bService;
	private DisinfectionRecordService disService;
	private EpidemicMonitoringService epiService;
	private EpidemicReportService service;
	private ImmuneRecordService immService;
	private MedicalRecordService medService;
	private ProductRecordService proService;
	private SuppliesService supService;
	private FarmService farmService;
	private ProductAService paService;
	private ProductBService pbService;

	public CountServlet() {
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
		String type = request.getParameter("type");
		String pages = "error.jsp";
		if (type.equals("count")) {
			request.setAttribute("msg", getCountMsg(request));
			pages = "model5_admin/file_open.jsp";
		} else {
			request.setAttribute("rate", forecast(request));
			pages = "model5_admin/warning_open.jsp";
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}

	private double forecast(HttpServletRequest request) {
		CountMsg msg = getCountMsg(request);
		double[] data = new double[] { msg.getDdr(), msg.getAvgDisfCount(),
				msg.getImmRate(), msg.getAvgSickCount(), msg.getAvgSupCount(),
				msg.getAvgPreCount(), msg.getDaethr(), msg.getAvgRerRate(), };
		double rate = GrayModel.forecast(data,4);
		if((rate+"").equals("NaN")){
			request.setAttribute("advices", "数据不足，无法预警。");
			rate = 0;
		} else {
			request.setAttribute("advices", getAdvice(msg,rate));
		}
		
		return rate;
	}

	private List<String> getAdvice(CountMsg msg,double rate) {
		List<String> advices = new ArrayList<String>();
		String advice = "";
		if (msg.getDdr() > 0.051 && msg.getDdr() < 0.251) {
			advice = MessageUtil.get("ddr.more.msg");
		} else if (msg.getDdr() < 0.051) {
			advice = MessageUtil.get("ddr.less.msg");
		} else {
			advice = MessageUtil.get("ddr.moremore.msg");
		}
		advices.add(advice);
		if (msg.getAvgSickCount() > 0.051 && msg.getAvgSickCount() < 0.251) {
			advice = MessageUtil.get("sick.more.msg");
		} else if (msg.getAvgSickCount() < 0.051) {
			advice = MessageUtil.get("sick.less.msg");
		} else {
			advice = MessageUtil.get("sick.moremore.msg");
		}
		advices.add(advice);
		if (msg.getImmRate() < 0.951 && msg.getImmRate() > 0.701) {
			advice = MessageUtil.get("imm.less.msg");
		} else if (msg.getImmRate() > 0.951) {
			advice = MessageUtil.get("imm.more.msg");
		} else {
			advice = MessageUtil.get("imm.lessless.msg");
		}
		advices.add(advice);
		if (msg.getAvgDisfCount() < 0.998 && msg.getAvgDisfCount() > 0.75) {
			advice = MessageUtil.get("disf.less.msg");
		} else if (msg.getAvgDisfCount() > 0.998) {
			advice = MessageUtil.get("disf.more.msg");
		} else {
			advice = MessageUtil.get("disf.lessless.msg");
		}
		advices.add(advice);
		if (msg.getAvgerCount() > 0 && msg.getAvgerCount() < 0.251) {
			advice = MessageUtil.get("sdis.more.msg");
		} else {
			advice = MessageUtil.get("sdis.moremore.msg");
		}
		advices.add(advice);
		if (msg.getAvgRerRate() > 0) {
			advice = MessageUtil.get("rdis.more.msg");
		}  
		advices.add(advice);
		if(rate > 0.11 && rate < 0.251){
			advice = MessageUtil.get("dish.more.msg");
		} else if( rate < 0.11) {
			advice = MessageUtil.get("dish.less.msg");
		} else {
			advice = MessageUtil.get("dish.moremore.msg");
		}
		advices.add(advice);
		return advices;
	}

	public CountMsg getCountMsg(HttpServletRequest request) {
		String farmId = request.getParameter("farmId");
		String address = request.getParameter("local");
		String date[] = StringUtil.getDate(request.getParameter("months"),request.getParameter("years"));
		double ddr = proService.finddcr(farmId, date[0], date[1]);
		double avgDisfCount = disService.findCount(farmId, date[0], date[1],Integer.parseInt(date[2]));
		double immRate = immService.findImmRate(farmId, date[0], date[1]);
		double avgSickCount = medService.findMedRate(farmId, date[0], date[1]);
		double avgPreCount = epiService.findCount(farmId, date[0], date[1],Integer.parseInt(date[2]));
		double daethr = ddr;
		double avgSupCount = supService.findCount(address, date[0], date[1],Integer.parseInt(date[2]));
		double avgAaCount = aService.findCount(address, date[0], date[1]);
		double avgAbCount = bService.findCount(address, date[0], date[1]);
		double avgPaCount = paService.findCount(address, date[0], date[1]);
		double avgPbCount = pbService.findCount(address, date[0], date[1]);
		double avgerCount = service.findCount(farmId, date[0], date[1], 0);
		double temp = service.findCount(farmId, date[0], date[1], 1);
		double avgRerRate = 0;
		if (avgerCount == 0) {
			avgRerRate = 0;
		} else {
			avgRerRate = temp / avgerCount;
		}
		CountMsg msg = new CountMsg();
		msg.setAvgDisfCount(avgDisfCount);
		msg.setAvgPreCount(avgPreCount);
		msg.setAvgSickCount(avgSickCount);
		msg.setDaethr(daethr);
		msg.setDdr(ddr);
		msg.setImmRate(immRate);
		msg.setAvgAaCount(avgAaCount);
		msg.setAvgAbCount(avgAbCount);
		msg.setAvgerCount(avgerCount);
		msg.setAvgPaCount(avgPaCount);
		msg.setAvgPbCount(avgPbCount);
		msg.setAvgRerRate(avgRerRate);
		msg.setAvgRerCount(temp);
		msg.setAvgSupCount(avgSupCount);
		request.setAttribute("date", date[1]);
		request.setAttribute("name", farmService.findName(farmId));
		request.setAttribute("farmId", farmId);
		request.setAttribute("local", address);
		request.setAttribute("flag", request.getParameter("time"));
		return msg;
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		aService = (AnimalAService) context.getBean("animalAService");
		bService = (AnimalBService) context.getBean("animalBService");
		disService = (DisinfectionRecordService) context.getBean("disinfectionRecordService");
		epiService = (EpidemicMonitoringService) context.getBean("epidemicMonitoringService");
		service = (EpidemicReportService) context.getBean("epidemicReportService");
		immService = (ImmuneRecordService) context.getBean("immuneRecordService");
		medService = (MedicalRecordService) context.getBean("medicalRecordService");
		proService = (ProductRecordService) context.getBean("productRecordService");
		supService = (SuppliesService) context.getBean("suppliesService");
		farmService = (FarmService) context.getBean("farmService");
		paService = (ProductAService) context.getBean("productAService");
		pbService = (ProductBService) context.getBean("productBService");
	}

}
