package com.teacherwork.action;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.teacherwork.domain.BaseCoe;
import com.teacherwork.domain.ClassIn;
import com.teacherwork.domain.Post;
import com.teacherwork.domain.SpecCoe;
import com.teacherwork.domain.Title;
import com.teacherwork.domain.Type;
import com.teacherwork.domain.User;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.service.IBaseCoeService;
import com.teacherwork.service.IClassInService;
import com.teacherwork.service.IPostService;
import com.teacherwork.service.ISpecCoeService;
import com.teacherwork.service.ITitleService;
import com.teacherwork.service.ITypeService;
import com.teacherwork.service.IUserService;
import com.teacherwork.service.IYearMsgService;
import com.teacherwork.util.BeanForInsertUtil;

@SuppressWarnings("serial")
public class CoefficientAction extends ActionSupport {
	private ApplicationContext context ;
	private ITypeService typeService;
	private IBaseCoeService bcService;
	private ITitleService titleService;
	private IPostService postService;
	private IClassInService icService;
	private ISpecCoeService specService;
	private IYearMsgService ymService;
	private IUserService userService;
	private BaseCoe bc;
	private Type type;
	private YearMsg msg;
	private Title title;
	private Post post;
	private SpecCoe spec;
	private ClassIn ic;
	private String result;
	private int id;
	public CoefficientAction() {
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		typeService = (ITypeService) context.getBean("typeService");
		bcService = (IBaseCoeService) context.getBean("bcService");
		titleService = (ITitleService) context.getBean("titleService");
		postService = (IPostService) context.getBean("pService");
		icService = (IClassInService) context.getBean("cService");
		specService = (ISpecCoeService) context.getBean("spService");
		ymService = (IYearMsgService) context.getBean("ymService");
		userService = (IUserService) context.getBean("uService");
	}
	
	
	public boolean useAcPutList(YearMsg msg){
		try {
			ActionContext ct = ActionContext.getContext();
			List<BaseCoe> bcList = bcService.findAllByYear(BaseCoe.class, msg.getYears());
			List<Type> typeList = typeService.findAllByYear(Type.class, msg.getYears());
			List<Title> titleList = titleService.findAllByYear(Title.class, msg.getYears());
			List<Post> postList = postService.findAllByYear(Post.class, msg.getYears());
			List<ClassIn> icList = icService.findAllByYear(ClassIn.class, msg.getYears());
			List<SpecCoe> specList = specService.findAllByYear(SpecCoe.class, msg.getYears());
			ct.put("bcList", bcList);
			ct.put("typeList", typeList);
			ct.put("titleList", titleList);
			ct.put("postList", postList);
			ct.put("icList", icList);
			ct.put("specList", specList);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String copyLastYearCoe(){
		ActionContext ct = ActionContext.getContext();
		if(ct.getSession().get("admin")==null){
			msg = (YearMsg) ct.getSession().get("cyearmsg");
		}
		int year = msg.getYears()-1;
		try {
			List<BaseCoe> bcList = updateList(bcService.findAllByYear(BaseCoe.class, year), BaseCoe.class);
			List<Type> typeList = updateList(typeService.findAllByYear(Type.class, year),Type.class);
			List<Title> titleList = updateList(titleService.findAllByYear(Title.class, year),Title.class);
			List<Post> postList = updateList(postService.findAllByYear(Post.class, year),Post.class);
			List<ClassIn> icList = updateList(icService.findAllByYear(ClassIn.class, year),ClassIn.class);
			List<SpecCoe> specList =updateList(specService.findAllByYear(SpecCoe.class, year),SpecCoe.class);
//		
			bcService.doBatchInsert(bcList);
			typeService.doBatchInsert(typeList);
			titleService.doBatchInsert(titleList);
			postService.doBatchInsert(postList);
			icService.doBatchInsert(icList);
			specService.doBatchInsert(specList);
//			ct.put("bcList", bcList);
//			ct.put("typeList", typeList);
//			ct.put("titleList", titleList);
//			ct.put("postList", postList);
//			ct.put("icList", icList);
//			ct.put("specList", specList);
			
			result = "true";
		} catch (Exception e) {
			e.printStackTrace();
			result = "false";
		}
		return SUCCESS;
	}
	
	public String updateUser(){
		try {
			List<User> list = userService.findAll(User.class);
			List<Post> postList = postService.findAllByYear(Post.class, msg.getYears());
			List<Title> titleList = titleService.findAllByYear(Title.class, msg.getYears());
			for(User user : list){
				Title title = user.getTitle();
				Post post = user.getPost();
				for(Title t : titleList){
					if(t.getTitleName().equals(title.getTitleName())){
						title = t;
						break;
					}
				}
				for(Post p : postList){
					if(p.getPostName().equals(post.getPostName())){
						post = p;
						break;
					}
				}
				user.setPost(post);
				user.setTitle(title);
				userService.doUpdate(user);
			}
			result = "true";
		} catch (Exception e) {
			e.printStackTrace();
			result = "false";
		}
		return SUCCESS;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List updateList(List list ,Class clazz) throws Exception{
		List reList = new ArrayList();
		for(Object obj : list){
			if(clazz.getName() == ClassIn.class.getName()){
				Method method = clazz.getMethod("setYears", int.class);
				method.invoke(obj, msg.getYears());
			}
			Method method = clazz.getMethod("setYearMsg", YearMsg.class);
			method.invoke(obj, msg);
			reList.add(obj);
		}
		return list;
	}
	
	public String addSal(){
		try {
			result = ymService.doInsertSal(msg)+"";
			ActionContext ct = ActionContext.getContext();
			List<YearMsg> ymList = ymService.findAll(YearMsg.class);
			ct.getSession().put("allYears", ymList);
		} catch (Exception e) {
			e.printStackTrace();
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String queryAllCoe(){
		String view = "error";
		ActionContext ct = ActionContext.getContext();
		try {
			if(id==-50){
				msg = (YearMsg) ct.getSession().get("cyearmsg");
			}
			List<YearMsg> ymList = ymService.findAll(YearMsg.class);
			ct.put("yearList", ymList);
			ct.put("msg", msg);
			boolean temp = useAcPutList(msg);
			if(temp){
				view = "dean_query_coe";
			} 
		} catch (Exception e) {
		}
		return view;
		
	}
	
	public String akfCoe(){
		ActionContext ct = ActionContext.getContext();
		String view = "error";
		msg = (YearMsg) ct.getSession().get("cyearmsg");
		try {
			boolean temp = useAcPutList(msg);
			if(temp){
				view = "dean_udq_coe";
			} 
		} catch (Exception e) {
		}
		return view;
	}
	
	public String addBaseCoe(){
		List<BaseCoe> list = null;
		try {
			list = BeanForInsertUtil.getBeanByCoe(BaseCoe.class);
			result = bcService.doBatchInsert(list)+"";
		} catch (Exception e) {
			result = list.size()+"";
		}
		return SUCCESS;
	}
	
	public String addTypeCoe(){
		List<Type> list = null;
		try {
			list = BeanForInsertUtil.getBeanByCoe(Type.class);
			result = typeService.doBatchInsert(list)+"";
		} catch (Exception e) {
			result = list.size()+"";
		}
		return SUCCESS;
	}
	
	public String addTitleCoe(){
		List<Title> list = null;
		try {
			list = BeanForInsertUtil.getBeanByCoe(Title.class);
			result = titleService.doBatchInsert(list)+"";
		} catch (Exception e) {
			result = list.size()+"";
		}
		return SUCCESS;
	}
	
	public String addPostCoe(){
		List<Post> list = null;
		try {
			list = BeanForInsertUtil.getBeanByCoe(Post.class);
			result = postService.doBatchInsert(list)+"";
		} catch (Exception e) {
			result = list.size()+"";
		}
		return SUCCESS;
	}
	
	
	public String addSpecCoe(){
		List<SpecCoe> list = null;
		try {
			list = BeanForInsertUtil.getBeanByCoe(SpecCoe.class);
			result = specService.doBatchInsert(list)+"";
		} catch (Exception e) {
			result = list.size()+"";
		}
		return SUCCESS;
	}
	
	public String deleteBaseCoe(){
		try {
			result =  bcService.doDelete(BaseCoe.class, id)+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String deleteTypeCoe(){
		try {
			result =  typeService.doDelete(Type.class, id)+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String deleteTitleCoe(){
		try {
			result =  titleService.doDelete(Title.class, id)+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String deletePostCoe(){
		try {
			result =  postService.doDelete(Post.class, id)+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String deleteClassInCoe(){
		try {
			result =  icService.doDelete(ClassIn.class, id)+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String deleteSpecCoe(){
		try {
			result =  specService.doDelete(SpecCoe.class, id)+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updateBaseCoe(){
		try {
			result =  bcService.doUpdate(bc)+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updateTypeCoe(){
		try {
			result =  typeService.doUpdate(getType())+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updateTitleCoe(){
		try {
			result =  titleService.doUpdate(getTitle())+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updatePostCoe(){
		try {
			result =  postService.doUpdate(getPost())+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updateClassInCoe(){
		try {
			result =  icService.doUpdate(getIc())+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String updateSpecCoe(){
		try {
			result =  specService.doUpdate(getSpec())+"";
		} catch (Exception e) {
			result = false+"";
		}
		return SUCCESS;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public YearMsg getMsg() {
		return msg;
	}

	public void setMsg(YearMsg msg) {
		this.msg = msg;
	}

	public BaseCoe getBc() {
		return bc;
	}

	public void setBc(BaseCoe bc) {
		this.bc = bc;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public SpecCoe getSpec() {
		return spec;
	}

	public void setSpec(SpecCoe spec) {
		this.spec = spec;
	}

	public ClassIn getIc() {
		return ic;
	}

	public void setIc(ClassIn ic) {
		this.ic = ic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
