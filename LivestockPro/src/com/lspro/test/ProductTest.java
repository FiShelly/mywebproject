package com.lspro.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lspro.pojo.AdminUsers;
import com.lspro.pojo.AnimalA;
import com.lspro.service.AdminUsersService;
import com.lspro.service.AnimalAService;
import com.lspro.util.MD5Code;

public class ProductTest {
	AdminUsersService aService = null;

	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		aService = (AdminUsersService) context.getBean("adminUsersService");
	}

	@Test
	public void testInsert3() {
		for (int i = 21; i < 31; i++) {
			AdminUsers ad = new AdminUsers();
			ad.setAddress("广东省");
			ad.setDetail("农业局"+i);
			ad.setGrade(3);
			ad.setCity("");
			ad.setCountry("");
			ad.setIsSuperAdmin(false);
			ad.setLoginId("user"+i);
			ad.setPassword(new MD5Code().getMD5ofStr("user"+i));
			ad.setProvince("广东省");
			if (i < 10) {
				ad.setRegistDate("2015-11-0" + i);
			} else {
				ad.setRegistDate("2015-11-" + i);
			}
			try {
				aService.doCreateOrUpdate(ad);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testInsert2() {
		for (int i = 11; i < 21; i++) {
			AdminUsers ad = new AdminUsers();
			ad.setAddress("广东省深圳市");
			ad.setDetail("农业局"+i);
			ad.setGrade(2);
			ad.setIsSuperAdmin(false);
			ad.setCountry("");
			ad.setCountry("");
			ad.setCity("深圳市");
			ad.setLoginId("user"+i);
			ad.setPassword(new MD5Code().getMD5ofStr("user"+i));
			ad.setProvince("广东省");
			if (i < 10) {
				ad.setRegistDate("2015-11-0" + i);
			} else {
				ad.setRegistDate("2015-11-" + i);
			}
			try {
				aService.doCreateOrUpdate(ad);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Test
	public void testInsert1() {
		for (int i = 1; i < 11; i++) {
			AdminUsers ad = new AdminUsers();
			ad.setAddress("广东省深圳市龙岗区");
			ad.setDetail("农业局"+i);
			ad.setGrade(1);
			ad.setCountry("龙岗区");
			ad.setCity("深圳市");
			ad.setIsSuperAdmin(false);
			ad.setLoginId("user"+i);
			ad.setPassword(new MD5Code().getMD5ofStr("user"+i));
			ad.setProvince("广东省");
			if (i < 10) {
				ad.setRegistDate("2015-11-0" + i);
			} else {
				ad.setRegistDate("2015-11-" + i);
			}
			try {
				aService.doCreateOrUpdate(ad);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Test
	public void findByPage() {
		List<AdminUsers> list = aService.findAll("广东省", "", "", "1900-01-01", "2200-12-31",1,5);
		System.out.println(list.size());
		for(AdminUsers aa : list){
			System.out.println(aa);
		}
	}
	
	@Test
	public void testFormat(){
		Integer[] a = {1,2,3}; 
		String[] way = {"dsa","das","ddas"};
		System.out.println(way);
	}
	
}
