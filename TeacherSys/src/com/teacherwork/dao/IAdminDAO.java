package com.teacherwork.dao;

import com.teacherwork.domain.Admin;

public interface IAdminDAO extends IDAO<Admin>{

	Admin findLogin(String hql,Admin admin);
}
