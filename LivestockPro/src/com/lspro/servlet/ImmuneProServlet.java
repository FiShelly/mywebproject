package com.lspro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.FarmMes;
import com.lspro.pojo.ImmunePro;
import com.lspro.service.ImmuneProgramService;
import com.lspro.util.MessageUtil;
import com.lspro.util.StringUtil;

public class ImmuneProServlet extends HttpServlet {

	private ApplicationContext context ;
	private ImmuneProgramService immService;
	
	public ImmuneProServlet() {
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
			this.getImmuneProList(request,response);
		} else if (action.equals("insert")) {
			this.getImmuneProInsert(request, response);
		} else if (action.equals("delete")) {
			this.getImmuneProDelete(request, response);
		} else if (action.equals("updatePre")) {
			this.getImmuneProUpdatePre(request, response);
		} else if (action.equals("update")) {
			this.getImmuneProUpdate(request,response);
		} else if (action.equals("species")) {
			this.getImmuneProSpecies(request, response);
		} else if (action.equals("checkId")) {
			this.getImmuneProCheck(request,response);
		}
		
	}
	public void getImmuneProUpdatePre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = "../error.jsp";
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ImmunePro pro = immService.findById(ImmunePro.class,id);
			request.setAttribute("pro", pro);
			page = "model1_user/update_immune_program.jsp";
			request.getRequestDispatcher(page).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getImmuneProUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		String farmId = request.getParameter("farmId");
		String name = request.getParameter("immuneSpecies");
		String age = request.getParameter("immuneDate");
		String vaccine = request.getParameter("series");
		String dose = request.getParameter("dose");
		String part = request.getParameter("part");
		String note = request.getParameter("remark");
		int sequeueNum = Integer.parseInt(request.getParameter("sequeueNum"));
		int id = Integer.parseInt(request.getParameter("id"));
		ImmunePro imm = null;
		FarmMes farm = new FarmMes();
		farm.setFarmId(farmId);
		try{
				imm = new ImmunePro();
				imm.setDateAge(age);
				imm.setDose(dose);
				imm.setFarm(farm);
				imm.setName(name);
				imm.setNote(note);
				imm.setSequeueNum(sequeueNum);
				imm.setVaccine(vaccine);
				imm.setWay(part);
				imm.setId(id);
				if(immService.doCreateOrUpdate(imm)) {
					request.setAttribute("info", MessageUtil.get("immunePro.update.true"));			
				} else {
					request.setAttribute("info", MessageUtil.get("immunePro.update.false"));
				}
				pages = "model1_user/forward.jsp";
				request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getImmuneProDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			boolean flag = false;
			flag = immService.doDelete(ImmunePro.class,id);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getImmuneProCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String species = request.getParameter("species");
		String farmId = request.getParameter("farmId");
		try {
			boolean flag = false;
			flag = immService.checkName(species, farmId);
			response.setContentType("text/html;charset=GBK") ;
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getImmuneProInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		String farmId = request.getParameter("farmId");
		String name = request.getParameter("immuneSpecies");
		String[] age = request.getParameterValues("immuneDate");
		String[] vaccine = request.getParameterValues("series");
		String[] dose = request.getParameterValues("dose");
		String[] part = request.getParameterValues("part");
		String[] note = request.getParameterValues("remark");
		ImmunePro imm = null;
		FarmMes farm = new FarmMes();
		farm.setFarmId(farmId);
		int temp = 0;
		try{
			for(int i = 0;i<age.length;i++) {
				imm = new ImmunePro();
				imm.setDateAge(age[i]);
				imm.setDose(dose[i]);
				imm.setFarm(farm);
				imm.setName(name);
				imm.setNote(note[i]);
				imm.setSequeueNum(i);
				imm.setVaccine(vaccine[i]);
				imm.setWay(part[i]);
				if(immService.doCreateOrUpdate(imm)) {
					temp++;
				}
			}
			if(temp == age.length) {
				request.setAttribute("info", MessageUtil.get("immunePro.insert.true"));
				pages = "model1_user/model1_user_index.jsp";
			} else {
				request.setAttribute("info", MessageUtil.get("immunePro.insert.false"));
			}
			request.getRequestDispatcher(pages).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getImmuneProSpecies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		String farmId = request.getParameter("farmId");
		String role = request.getParameter("role");
		if(role == null || role.equals("")) {
			role = "";
		}
		try {
			List<String> species = immService.findSpecies(farmId);
			request.setAttribute("species", species);
			request.setAttribute("farmId", farmId);
			if(role.equals("user")) {
				pages = "model1_user/immune_program_leftMenu.jsp";
			} else {
				pages = "model1_admin/immune_program_leftMenu.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();	 
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	public void getImmuneProList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../error.jsp";
		String farmId = request.getParameter("farmId");
		String species = request.getParameter("species");
		String role = StringUtil.getInitString(request.getParameter("role"));
		try {
			List<ImmunePro> list = immService.findImmunePro(species, farmId);
			request.setAttribute("species", species);
			request.setAttribute("all", list);
			if(role.equals("user")) {
				pages = "model1_user/farm_immune_program.jsp";
			} else {
				pages = "model1_admin/farm_immune_program.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();	 
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}

	public void init() throws ServletException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		immService = (ImmuneProgramService) context.getBean("immuneProgramService");
	}

}
