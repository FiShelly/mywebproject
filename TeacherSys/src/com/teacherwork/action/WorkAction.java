package com.teacherwork.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.teacherwork.domain.ClassIn;
import com.teacherwork.domain.DataCount;
import com.teacherwork.domain.Message;
import com.teacherwork.domain.Page;
import com.teacherwork.domain.PreReview;
import com.teacherwork.domain.Type;
import com.teacherwork.domain.User;
import com.teacherwork.domain.Work;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.service.IClassInService;
import com.teacherwork.service.IDataCountService;
import com.teacherwork.service.IMessageService;
import com.teacherwork.service.IPreReviewService;
import com.teacherwork.service.ITypeService;
import com.teacherwork.service.IUserService;
import com.teacherwork.service.IWorkService;
import com.teacherwork.service.IYearMsgService;
import com.teacherwork.util.BeanForInsertUtil;

@SuppressWarnings("serial")
public class WorkAction extends ActionSupport{

	private ApplicationContext context ;
	private List<Work> list ;
	private String result;
	private Page page;
	private IWorkService workService;
	private ITypeService typeService;
	private IClassInService icService;
	private IDataCountService dataService;
	private IYearMsgService ymService;
	private IPreReviewService ipService;
	private IUserService userService;
	private IMessageService msgService;
	private YearMsg cyear;
	private int rolee;
	private Work work;
	private int termChioce;
	private String queryParam = "";
	private String queryParamTn = "";
	public WorkAction(){
		list = new ArrayList<Work>();
		this.page = new Page();
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		workService = (IWorkService) context.getBean("wService");
		typeService = (ITypeService) context.getBean("typeService");
		ymService = (IYearMsgService) context.getBean("ymService");
		icService = (IClassInService) context.getBean("cService");
		dataService = (IDataCountService) context.getBean("dcService");
		userService = (IUserService) context.getBean("uService");
		msgService = (IMessageService) context.getBean("msgService");
		ipService = (IPreReviewService) context.getBean("prService");
	}

	public String updateUc(){
		ActionContext ct = ActionContext.getContext();
		User user = (User) ct.getSession().get("user");
		cyear = (YearMsg) ct.getSession().get("cyearmsg");
		work.setDuser(user);
		String view = update();
		if(result.equals("true")){
			if(!queryParamTn.equals("") && queryParamTn != null){
				Message msg = new Message();
				msg.setContent(queryParamTn);
				msg.setFromId(user.getLoginId());
				msg.setFromName(user.getUserName());
				msg.setToId(work.getUser().getLoginId());
				msg.setToName(work.getUser().getUserName());
				msg.setYears(cyear.getYears());
				msg.setFromRole(true);
				msg.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				msgService.doInsert(msg);
			}
		} 
		return view;
	}
	
	public String review(){
		ActionContext ct = ActionContext.getContext();
		try {
			User duser = (User) ct.getSession().get("user");
			result =  workService.doUpdateState(work.getId(), duser.getLoginId(),work.getState() , work.getFbContent())+"";
			if(work.getState()==2){
				addDataCount(queryParam);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String listFeedBack(){
		ActionContext ct = ActionContext.getContext();
		String view = "error";
		try {
			cyear = (YearMsg) ct.getSession().get("cyearmsg");
			User tuser = (User) ct.getSession().get("user");
			List<Work> workList = workService.findAllByState(tuser.getLoginId(),cyear.getLastTerm().getId() , cyear.getNextTerm().getId(), 1);
			List<Type> allType = typeService.findAllByYear(Type.class, getCyear().getYears());
			page.setAllRecord(workService.getAllrecord()); 
			ct.put("allWork",workList );
			ct.put("allType", allType);
			view = "teacher_feebBack";
		} catch (Exception e) {
		}
		return view;
	}
	
	public String listReview(){
		ActionContext ct = ActionContext.getContext();
		String view = "error";
		try {
			cyear = (YearMsg) ct.getSession().get("cyearmsg");
			List<Work> workList = workService.findAllByPage(page.getCurrentPage(), page.getLineSize(), "",queryParam,"",cyear.getLastTerm().getId() , cyear.getNextTerm().getId(), 0);
			page.setAllRecord(workService.getAllrecord()); 
			ct.put("page", getPage());
			ct.put("tuser", userService.findById(User.class, queryParam));
			ct.put("allWork",workList );
			DataCount dc = dataService.findViewById(queryParam, cyear.getYears(),false);
			ct.put("dc",dc);
			view = "dean_review";
		} catch (Exception e) {
		}
		return view;
	}
	
	public String listPreReview(){
		ActionContext ct = ActionContext.getContext();
		String view = "error";
		try {
			cyear = (YearMsg) ct.getSession().get("cyearmsg");
			List<PreReview> prList = ipService.findAllByPageAndYear(cyear.getYears(), page.getCurrentPage(), page.getLineSize());
			page.setAllRecord(ipService.getAllrecord()); 
			ct.put("page", getPage());
			ct.put("allPr",prList );
			view = "dean_preReview";
		} catch (Exception e) {
		}
		return view;
	}
	
	public void addPreReview(String loginId){
		ActionContext ct = ActionContext.getContext();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String fdate = format.format(date);
		User tuser = null;
		if(loginId==""){
			tuser  = (User) ct.getSession().get("user");
		} else {
			tuser = userService.findById(User.class,loginId);
		}
		cyear = (YearMsg) ct.getSession().get("cyearmsg");
		int num = workService.findWorkCount(tuser.getLoginId(), cyear.getLastTerm().getId(), cyear.getNextTerm().getId(), 0);
		PreReview count = new PreReview();
		count.setLoginId(tuser.getLoginId());
		count.setYears(cyear.getYears());
		count.setSubDate(fdate);
		count.setSubCount(num);
		count.setUser(tuser);
		ipService.doUpdate(count);
	}
	
	public String update(){
		try {
			result =  workService.doUpdate(work)+"";
			addDataCount(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
			result = false+"";
		}
		return SUCCESS;
	}

	public String delete(){
		try {
			result =  workService.doDelete(Work.class, getWork().getId())+"";
			addDataCount(queryParam);
			addPreReview(queryParam);
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updatePre(){
		ActionContext ct = ActionContext.getContext();
		String view = "error";
		try {
			User tuser = (User) ct.getSession().get("user");
			cyear = (YearMsg) ct.getSession().get("cyearmsg");
			List<Work> workList = workService.findAllByPage(page.getCurrentPage(), page.getLineSize(), tuser.getUserName(),tuser.getLoginId(),"",cyear.getLastTerm().getId() , cyear.getNextTerm().getId(), 0);
			List<Type> allType = typeService.findAllByYear(Type.class, getCyear().getYears());
			page.setAllRecord(workService.getAllrecord()); 
			DataCount dc = dataService.findViewById(tuser.getLoginId(), cyear.getYears(),false,0);
			ct.put("allType", allType);
			ct.put("page", getPage());
			ct.put("allWork",workList );
			ct.put("dc",dc);
			ct.put("cyear", cyear);
			view = "teacher_update";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	public String addWork(){
		try {
			list = BeanForInsertUtil.getBeanByWork(Work.class);
		} catch (Exception e1) {
			result = -1+"";
			return SUCCESS;
		}
		try {
			result = workService.doBatchInsert(list)+"";
			addDataCount(queryParam);
			addPreReview(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
			result = list.size()+"";
		}
		return SUCCESS;
	}
	
	public String allList(){
		String view = "error";
		ActionContext ct = ActionContext.getContext();
		cyear = (YearMsg) ct.getSession().get("cyearmsg");
		int lid = -1;
		int nid = -1;
		if(termChioce == 1){
			lid = cyear.getLastTerm().getId();
			nid = lid;
		} else if(termChioce == 2) {
			nid = cyear.getNextTerm().getId();
			lid = nid;
		} else {
			lid = cyear.getLastTerm().getId();
			nid = cyear.getNextTerm().getId();
		}
		try {
			List<Work> llist = workService.findAll( lid, nid, 2);
			ct.put("allWork", llist);
			view = "dean_copy";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	 
	public String dcList(){
		String view = "error";
		ActionContext ct = ActionContext.getContext();
		try {
			List<DataCount> dcList = dataService.findAllByPage(page.getCurrentPage(), page.getLineSize(), 
					Boolean.parseBoolean(queryParam), termChioce, cyear.getYears());
			page.setAllRecord(dataService.getAllrecord()); 
			ct.put("allDc", dcList);
			ct.put("page", getPage());
			view = "dean_dataCount";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}
	
	public String pssList(){
		String view = "error";
		ActionContext ct = ActionContext.getContext();
		User tuser = (User) ct.getSession().get("user"); 
		cyear = ymService.findById(YearMsg.class, cyear.getYears());
		int lid = -1;
		int nid = -1;
		if(termChioce == 1){
			lid = cyear.getLastTerm().getId();
			nid = lid;
		} else if(termChioce == 2) {
			nid = cyear.getNextTerm().getId();
			lid = nid;
		} else {
			lid = cyear.getLastTerm().getId();
			nid = cyear.getNextTerm().getId();
		}
		try {
			List<Work> llist = null;
			if(tuser.getRole()==1){
				llist = workService.findAllByPage(page.getCurrentPage(), page.getLineSize(), tuser.getUserName(),tuser.getLoginId(), 
							queryParam, lid, nid, 2);
				DataCount dc = dataService.findViewById(tuser.getLoginId(), cyear.getYears(),true,0);
				ct.put("dc",dc);
				view = "teacher_query";
			}  else {
				llist = workService.findAllByPage(page.getCurrentPage(), page.getLineSize(), queryParamTn,"", queryParam, lid, nid, 2);
				view = "dean_query";
			}
			page.setAllRecord(workService.getAllrecord()); 
			ct.put("allWork", llist);
			ct.put("page", getPage());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	public void addDataCount(String loginId){
		ActionContext ct = ActionContext.getContext();
		User tuser = null;
		List<Work> llist;
		DataCount count = new DataCount();
		cyear = (YearMsg) ct.getSession().get("cyearmsg");
		if(loginId==""){
			tuser = (User) ct.getSession().get("user");
			count.setUser(tuser);
			llist = workService.findAllByTrem(tuser.getLoginId(), cyear.getLastTerm().getId(), cyear.getNextTerm().getId());
		} else {
			tuser = userService.findById(User.class,loginId);
			count.setState(true);
			User user = new User();
			user.setLoginId(queryParam);
			count.setUser(user);
			llist = workService.findAllByState(tuser.getLoginId(), cyear.getLastTerm().getId(), cyear.getNextTerm().getId(),2);
		}
		ClassIn classIn = icService.findById(ClassIn.class, cyear.getYears());
		double clssinNum =classIn.getClassInNum();
		double titleNum = tuser.getTitle().getCoefficient();
		double postNum = tuser.getPost().getCoefficient();
		double clssCoeOut = classIn.getCoefficientOut();
		double allwork = 0;
		double nwork = 0;
		double lwork = 0;
		for(Work w : llist){
			if(w.getTerm().getIsLastTerm()){
				lwork += w.getAllWork();
			} else {
				nwork += w.getAllWork();
			}
			allwork += w.getAllWork();
		}
		count.setLoginId(tuser.getLoginId());
		try {
			setDataCount(count, allwork, clssinNum, titleNum, postNum, clssCoeOut,0);
			dataService.doUpdate(count);
			setDataCount(count, nwork, clssinNum, titleNum, postNum, clssCoeOut,1);
			dataService.doUpdate(count);
			setDataCount(count, lwork, clssinNum, titleNum, postNum, clssCoeOut,2);
			dataService.doUpdate(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDataCount(DataCount count,double result,double clssinNum,double titleNum,double postNum,double clssCoeOut,int term){
		result = result * titleNum; 
		if(result > clssinNum){
			count.setInWork(clssinNum);	//设置额内工作量
			double temp = (result - clssinNum) * clssCoeOut; //计算额外工作量
			count.setInOut(temp);	//设置额外工作量
			result = temp + clssinNum; //重设工作量
		}  else {
			count.setInWork(result);
		}
		result += postNum;
		count.setAllSal(result*cyear.getSal());
		count.setAllWork(result);
		count.setTerm(term);
		count.setYears(cyear.getYears());
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}

	public int getRolee() {
		return rolee;
	}

	public void setRolee(int rolee) {
		this.rolee = rolee;
	}
	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public YearMsg getCyear() {
		return cyear;
	}

	public void setCyear(YearMsg cyear) {
		this.cyear = cyear;
	}

	public int getTermChioce() {
		return termChioce;
	}

	public void setTermChioce(int termChioce) {
		this.termChioce = termChioce;
	}

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	public String getQueryParamTn() {
		return queryParamTn;
	}

	public void setQueryParamTn(String queryParamTn) {
		this.queryParamTn = queryParamTn;
	}

	
	
}
