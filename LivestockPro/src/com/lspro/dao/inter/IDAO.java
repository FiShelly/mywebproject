package com.lspro.dao.inter;

import java.io.Serializable;
import java.util.List;
/**
 * Description:
 * 此接口为所有模块接口类的父类.用于提供基本的增删改查功能以及获取全部记录数的方法<br>
 * @author 谢福成
 * @param <T>  pojo类
 * @param <K>  数据库中主键的类型
 * @version 2.0（增加了数据库连接的关闭方法）
 */
public interface IDAO<T> {
	
	/**
	 * 将页面的数据增加到数据库中
	 * @param vo 所需要操作的pojo类
	 * @return void;
	 * @throws hibernate操作异常
	 */
	public void doCreateOrUpdate(T vo) ;

	/**
	 * 将数据库中相应的数据进行修改
	 * @param vo 所需要操作的pojo类
	 * @return	<tt>true</tt>如果vo被修改成功则返回true;
	 * @throws hibernate操作异常
	 */
	//public boolean doUpdate(T vo) throws Exception;

	/**
	 * 删除通过id查询得到的数据
	 * @param id 数据库的标识主键
	 * @return	void;
	 * @throws  hibernate操作异常
	 */
	public void doDelete(Class<T> entityClass,Serializable id) ;

	/**
	 * 通过id查询相应的数据.
	 * @param id 数据库的标识主键
	 * @return <tt>pojo对象实例</tt>如果通过id找到相应的数据则返回该数据;
	 * @throws hibernate操作异常
	 */
	public T findById(Class<T> entityClass,Serializable id) ;
	
	
	
	/**
	 * 分页查询获取数据
	 * @param keyWord 模糊查询的关键字
	 * @param currentPage  分页查询中当前页面页码
	 * @param lineSize	   分页查询中当前页面所需要显示的数据数目
	 * @return	<tt>List对象</tt> 模糊查询返回结果
	 * @throws hibernate操作异常
	 */

	public List<T> findAll(String hql,String keyWord, int currentPage, int lineSize);

	/**
	 * 获取符合关键字的全部数据的数目.
	 * @param keyWord 模糊查询的关键字
	 * @return  <tt>Integer</tt> 数据库中数据的总数
	 * @throws hibernate操作异常
	 */
	public Integer getAllrecord() ;
	
	public void setRecord(Serializable count) ;
	
	public Boolean doDeleteAboutFarm(String farmId) ;
	
	public void doBatchDelete(Class<T> entityClass,Serializable[] ids);

}
