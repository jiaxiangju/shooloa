package com.oa.common.dao;


/**
 * @author zorro
 * @date 2014年6月3日
 */
public interface IBaseDao {
	/**
	 * 增加对象
	 * 
	 * @param o
	 * @return 
	 */
	public <T> void add(T t) ;

	/**
	 * 根据对象删除对象
	 * 
	 * @param o
	 */
	public <T> void delete(T t);

	/**
	 * 根据对象更改对象
	 * 
	 * @param o
	 */
	public <T> void update(T t) ;

	/**
	 * 根据id获得单个对象
	 * 
	 * @param claxx
	 * @param id
	 * @return
	 */
	public <T> T queryObjectById(Class<? extends Object> class1, Object id);
}
