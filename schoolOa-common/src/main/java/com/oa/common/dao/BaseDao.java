package com.oa.common.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;




import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.oa.common.util.StringUtil;

import javax.annotation.Resource;


@SuppressWarnings("restriction")
public abstract class BaseDao {
	
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 增加对象
	 * 
	 * @param o
	 * @return 
	 */
	public <T> void add(T t) {
		getSession().save(t);
	}

	/**
	 * 根据对象删除对象
	 * 
	 * @param o
	 */
	public <T> void delete(T t) {
		getSession().delete(t);

	}

	/**
	 * 根据对象更改对象
	 * 
	 * @param o
	 */
	public <T> void update(T t) {
		getSession().update(t);

	}

	/**
	 * 根据id获得单个对象
	 * 
	 * @param claxx
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T queryObjectById(Class<? extends Object> class1, Object id) {
		return (T) getSession().get(class1,(Serializable)id);
	}
	

	/**
	 * 根据参数获得对象list
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryListByParam(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		if(params!=null){
			for(int i=0; i<params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (List<T>)query.list();
	}
	
	
	/**
	 * 
	 * @param hql
	 * @param rowNum 每页显示多少
	 * @param page 当前页
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  HashMap<String,Object> queryMiniListByPage(String hql,int rowNum,int page, Object... params){
		String hc = new String();
		if(hql.toLowerCase().startsWith("select")){
			int i = hql.toLowerCase().indexOf("from");
			hc = "select count(*) " + hql.substring(i, hql.length());
		}else{
			hc = "select count(*) " + hql;
		}
		Query countQ = getSession().createQuery(hc);
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		Query query = getSession().createQuery(hql);
		if (!StringUtil.isNullOrEmpty(params)) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
				countQ.setParameter(i, params[i]);
			}
		}
		hashMap.put("total", countQ.list().get(0));//总记录
		query.setFirstResult((page)*rowNum);
		query.setMaxResults(rowNum);
		List<Object> list = query.list();
		hashMap.put("rowNum", rowNum);
		hashMap.put("page", page);
		hashMap.put("data", list);
		return hashMap;	
	}
	
	/**
	 * 大数据量查询优化
	 * @param cl
	 * @param whereHql
	 * @param rowNum
	 * @param page
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  HashMap<String,Object> queryMiniListByPage(Class cl,String whereHql,int rowNum,int page, Object... params){
		String className = cl.getName();
		String countHql = "select count(id) from "+className+" where 1=1 "+whereHql;
		String baseHql = "from "+className+" where 1=1 "+whereHql;
		
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		
		Query countQ = getSession().createQuery(countHql);
		Query query = getSession().createQuery(baseHql);
		if (!StringUtil.isNull(params)) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
				countQ.setParameter(i, params[i]);
			}
		}
		hashMap.put("total", countQ.list().get(0));//总记录
		query.setFirstResult((page)*rowNum);
		query.setMaxResults(rowNum);
		List<Object> list = query.list();
		hashMap.put("rowNum", rowNum);
		hashMap.put("page", page);
		hashMap.put("data", list);
		return hashMap;	
	}

	/**
	 * @return sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory 要设置的 sessionFactory
	 */
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


}
