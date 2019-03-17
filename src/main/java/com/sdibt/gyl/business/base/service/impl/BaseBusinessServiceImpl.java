package com.sdibt.gyl.business.base.service.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.business.base.service.BaseBusinessService;
import com.sdibt.gyl.query.BaseQuery;
import com.sdibt.gyl.query.PageResult;

public abstract class BaseBusinessServiceImpl<T, E extends Serializable> implements BaseBusinessService<T, E>{
	public abstract BaseDao<T> getBaseDao_zhu();//得到主表的dao
	public abstract BaseDao<E> getBaseDao_zhi();//得到子表的dao
	/**
	 * 得到主表的分页
	 */
	@Override
	public PageResult<T> getEntrties_zhu(BaseQuery baseQuery) {
		// TODO Auto-generated method stub
		return this.getBaseDao_zhu().findPageResult(baseQuery);
	}

	/**
	 * 得到子表的分页
	 */
	@Override
	public PageResult<E> getEntrties_zi(BaseQuery baseQuery) {
		// TODO Auto-generated method stub
		return this.getBaseDao_zhi().findPageResult(baseQuery);
	}
	

	public String getMax(){
		return this.getBaseDao_zhu().getMax();
	}
	
	/**
	 * 保存主表，级联保存子表
	 */
	@Transactional
	public void saveEntry_zhu(T t){
		this.getBaseDao_zhu().saveEntry(t);
	}

}
