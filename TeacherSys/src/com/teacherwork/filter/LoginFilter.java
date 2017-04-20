package com.teacherwork.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teacherwork.domain.Admin;
import com.teacherwork.domain.User;



public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		User user = (User) request.getSession().getAttribute("user");
		String currentURL = request.getRequestURI();
		String[] urlarray = currentURL.split("/");
		String filterUrl = urlarray[urlarray.length-1];
		if(filterUrl.contains("login")){
			arg2.doFilter(arg0, arg1) ;
		} else if (admin != null || user != null) {	// 已经登陆过了
			arg2.doFilter(arg0, arg1) ;
		} else {
			response.sendRedirect(request.getContextPath()+"/error/NoLoginError.jsp");
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
