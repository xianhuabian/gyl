package com.sdibt.gyl.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.query.BaseQuery;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.utils.GylUtils;

public class BaseDaoImpl<T> implements BaseDao<T>{
	private final Class classt;
	private ClassMetadata classMetadata;//元数据  描述持久化类的对象
	/**
	 * 把泛型的参数提取出来的过程放入到构造函数中写，因为
	 * 当子类创建对象的时候，直接调用父类的构造函数
	 */
	public BaseDaoImpl(){
		/**
		 * this代表子类
		 * this.getClass().getGenericSuperclass()就是父类:BaseDaoImpl<T> 泛型
		 * 如果不带T,this.getClass().getGenericSuperclass()返回的是class类型，而不是ParameterizedType
		 * spring(2.x和3.x)容器不支持带泛型的创建对象
		 */
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		//得到t的实现类型
		this.classt = (Class)type.getActualTypeArguments()[0];
	}
	
	@PostConstruct
	public void init(){//初始化方法
		this.classMetadata = this.hibernateTemplate.getSessionFactory()
				.getClassMetadata(this.classt);
	}
	
	@Resource(name="hibernateTemplate")
	public HibernateTemplate hibernateTemplate;

	@Override
	public PageResult<T> findPageResult(final BaseQuery baseQuery) {
		//返回根据查询条件查询的总的记录数
		int totleRows = this.getCount(baseQuery);
		//创建PageResult对象
		final PageResult<T> pageResult = new PageResult<T>(baseQuery.getCurrentPage(),baseQuery.getPageSize(),totleRows);
		/**
		 * 拼接where条件语句
		 */
		final StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from "+this.classt.getSimpleName());
		stringBuffer.append(" where 1=1 ");
		//在map中封装的查询条件
		final Map<String, Object> keyValues = baseQuery.buildWhere();
		for (Entry<String, Object> entry : keyValues.entrySet()) {//把查询条件放到where的后面
			/**
			 * 在一对多的情况下，例如Xsyddzhib
			 *    entry.getKey()="xsyddzhub.xsyddzhubid"
			 *    "where xsyddzhub.xsyddzhubid=:xsyddzhubid"
			 */
			if(entry.getKey().contains(".")){
				stringBuffer.append(" and "+entry.getKey()+"=:"+entry.getKey().split("\\.")[1]);
			}else{
				stringBuffer.append(" and "+entry.getKey()+"=:"+entry.getKey());
			}
		}
		
		return this.hibernateTemplate.execute(new HibernateCallback<PageResult<T>>() {

			@Override
			public PageResult<T> doInHibernate(Session session) throws HibernateException,
					SQLException {
				//根据拼接的hql语句产生一个query对象
				Query query = session.createQuery(stringBuffer.toString());
				/**
				 * 给hql语句的参数赋值
				 */
				for (Entry<String, Object> entry : keyValues.entrySet()) {
					if(entry.getKey().contains(".")){
						/**
						 * "where xsyddzhub.xsyddzhubid=:xsyddzhubid"的=:后面的赋值
						 */
						query.setParameter(entry.getKey().split("\\.")[1], entry.getValue());
					}else{
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				//设置当前页的第一行在集合中的位置
				int firstResult = (baseQuery.getCurrentPage()-1)*baseQuery.getPageSize();
				//设置每页显示的做多的行数
				int maxResult = baseQuery.getPageSize();
				//用hibernate的方式设置分页
				query.setFirstResult(firstResult).setMaxResults(maxResult);
				//返回分页后的结果集
				List<T> rows = query.list();
				//把结果设置到pageResult
				pageResult.setRows(rows);
				return pageResult;
			}
		});
	}

	@Override
	public Collection<T> findEntry() {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from "+this.classt.getSimpleName());
	}

	@Override
	public void saveEntry(T t) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.save(t);
	}

	@Override
	public void updateEntry(T t) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.update(t);
	}

	@Override
	public void deleteEntriesByIDS(Serializable[] ids) {
		/**
		 * [1,2,3,4]-->"1,2,3,4"
		 */
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			if(i==ids.length-1){
				stringBuffer.append(ids[i]);
			}else{
				stringBuffer.append(ids[i]+",");
			}
		}
		StringBuffer hql = new StringBuffer();
		/**
		 * this.classt.getSimpleName()是持久化类的名称
		 */
		hql.append("from "+this.classt.getSimpleName());
		/**
		 * this.classMetadata.getIdentifierPropertyName()持久化类id的名称
		 */
		hql.append(" where "+this.classMetadata.getIdentifierPropertyName());
		hql.append(" in(");
		hql.append(stringBuffer.toString());
		hql.append(")");
		List<T> list = this.hibernateTemplate.find(hql.toString());
		this.hibernateTemplate.deleteAll(list);
	}

	@Override
	public void deleteEntry(Serializable id) {
		// TODO Auto-generated method stub
		T t = (T)this.hibernateTemplate.get(this.classt, id);
		this.hibernateTemplate.delete(t);
	}

	@Override
	public T getEntryById(Serializable id) {
		// TODO Auto-generated method stub
		return (T)this.hibernateTemplate.get(this.classt, id);
	}

	@Override
	public Set<T> getEntriesByIds(Serializable[] ids) {
		/**
		 * [1,2,3,4]-->"1,2,3,4"
		 */
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			if(i==ids.length-1){
				stringBuffer.append(ids[i]);
			}else{
				stringBuffer.append(ids[i]+",");
			}
		}
		StringBuffer hql = new StringBuffer();
		/**
		 * this.classt.getSimpleName()是持久化类的名称
		 */
		hql.append("from "+this.classt.getSimpleName());
		/**
		 * this.classMetadata.getIdentifierPropertyName()持久化类id的名称
		 */
		hql.append(" where "+this.classMetadata.getIdentifierPropertyName());
		hql.append(" in(");
		hql.append(stringBuffer.toString());
		hql.append(")");
		List<T> list = this.hibernateTemplate.find(hql.toString());
		return new HashSet<T>(list);//从list到set的转化
	}

	@Override
	public int getCount(final BaseQuery baseQuery) {
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("select count("+classMetadata.getIdentifierPropertyName()+") from "+classt.getSimpleName());
				stringBuffer.append(" where 1=1 ");
				
				//获取所有的查询条件
				Map<String, Object> keyValues = baseQuery.buildWhere();
				
				/**
				 * 拼接where条件的过程
				 */
				for (Entry<String, Object> entry : keyValues.entrySet()) {
					/**
					 * 在一对多的情况下，例如Xsyddzhib
					 *    entry.getKey()="xsyddzhub.xsyddzhubid"
					 *    "where xsyddzhub.xsyddzhubid=:xsyddzhubid"
					 */
					if(entry.getKey().contains(".")){
						stringBuffer.append(" and "+entry.getKey()+"=:"+entry.getKey().split("\\.")[1]);
					}else{
						stringBuffer.append(" and "+entry.getKey()+"=:"+entry.getKey());
					}
				}
				
				System.out.println("hql语句:"+stringBuffer.toString());
				
				Query query = session.createQuery(stringBuffer.toString());//存放一个hql语句
				
				/**
				 * 把where条件中的参数传递值的过程
				 */
				for (Entry<String, Object> entry : keyValues.entrySet()) {
					if(entry.getKey().contains(".")){
						/**
						 * "where xsyddzhub.xsyddzhubid=:xsyddzhubid"的=:后面的赋值
						 */
						query.setParameter(entry.getKey().split("\\.")[1], entry.getValue());
					}else{
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				
				Long count  = (Long)query.uniqueResult();
				return count.intValue();
			}
		});
	}
	
	@Override
	public String getMax() {
		/**
		 * 把订单号的前8为数字提取到，然后和今天的时间作对比，如果一样，说明今天已经产生订单号了
		 * 如果没有，说明今天没有订单号
		 */
		List list = this.hibernateTemplate.
				find("select max(ddh) from " +this.classt.getSimpleName()+" where ddh like '%"+GylUtils.getDateToString()+"%'");
		if(list.get(0)==null){
			return GylUtils.getDateToString()+"0001";
		}else{
			String temp = (String)list.get(0);//得到的是日期与GylUtils.getDateToString()相同的最大的订单号
			return  ""+(Long.parseLong(temp)+1);
		}
	}
}
