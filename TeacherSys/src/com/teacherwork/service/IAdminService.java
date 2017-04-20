package com.teacherwork.service;

import com.teacherwork.domain.Admin;

public interface IAdminService extends IService<Admin>{
	boolean findLogin(Admin admin);
}
