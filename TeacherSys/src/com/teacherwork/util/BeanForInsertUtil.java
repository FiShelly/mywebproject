package com.teacherwork.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.teacherwork.domain.Post;
import com.teacherwork.domain.Title;
import com.teacherwork.domain.Type;
import com.teacherwork.domain.User;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.domain.YearTerm;

public class BeanForInsertUtil {
	public static <T> List<T>  getBean(Class<T> clazz){
		Map<String, String[]> map = ServletActionContext.getRequest().getParameterMap();
		int length = map.get("loginId").length;
		List<T> list = new ArrayList<T>();
		try {
			for(int i = 0;i<length;i++){
				T obj = clazz.newInstance();
				for(Map.Entry<String, String[]> me : map.entrySet()){
					String paramName = me.getKey();
					String[] paramValue = me.getValue();
					if(paramName.equals("title.id")){
						Title tile = new Title();
						tile.setId(Integer.parseInt(paramValue[i]));
						Method setTitle = clazz.getDeclaredMethod("setTitle", Title.class);
						setTitle.invoke(obj, tile);
					} else if(paramName.equals("post.id")){
						Post post = new Post();
						post.setId(Integer.parseInt(paramValue[i]));
						Method setPost = clazz.getDeclaredMethod("setPost", Post.class);
						setPost.invoke(obj, post);
					} else {
						BeanUtils.setProperty(obj, paramName, paramValue[i]);
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static <T> List<T>  getBeanByCoe(Class<T> clazz){
		Map<String, String[]> map = ServletActionContext.getRequest().getParameterMap();
		int length = map.get("yearMsg.years").length;
		List<T> list = new ArrayList<T>();
		try {
			for(int i = 0;i<length;i++){
				T obj = clazz.newInstance();
				for(Map.Entry<String, String[]> me : map.entrySet()){
					String paramName = me.getKey();
					String[] paramValue = me.getValue();
					if(paramName.equals("yearMsg.years")){
						YearMsg ym = new YearMsg();
						ym.setYears(Integer.parseInt(paramValue[i]));
						Method setYearMsg = clazz.getDeclaredMethod("setYearMsg", YearMsg.class);
						setYearMsg.invoke(obj, ym);
					} else {
						BeanUtils.setProperty(obj, paramName, paramValue[i]);
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static <T> List<T>  getBeanByWork(Class<T> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Map<String, String[]> map = ServletActionContext.getRequest().getParameterMap();
		int length = map.get("itemName").length;
		List<T> list = new ArrayList<T>();
		for(int i = 0;i<length;i++){
			T obj = clazz.newInstance();
			for(Map.Entry<String, String[]> me : map.entrySet()){
				String paramName = me.getKey();
				String[] paramValue = me.getValue();
				if(paramName.equals("type.id")){
					Type type = new Type();
					System.out.println();
					type.setId(Integer.parseInt(paramValue[i]));
					Method setType = clazz.getDeclaredMethod("setType", Type.class);
					setType.invoke(obj, type);
				} else if(paramName.equals("term")){
					YearTerm term = new YearTerm();
					term.setId(Integer.parseInt(paramValue[i]));
					Method setTerm = clazz.getDeclaredMethod("setTerm", YearTerm.class);
					setTerm.invoke(obj, term);
				} else if(paramName.equals("user.loginId")){
					User user = new User();
					user.setLoginId(paramValue[i]);
					Method setUser = clazz.getDeclaredMethod("setUser", User.class);
					setUser.invoke(obj, user);
				} else {
					BeanUtils.setProperty(obj, paramName, paramValue[i]);
				}
			}
			list.add(obj);
		}
		return list;
	}
}
