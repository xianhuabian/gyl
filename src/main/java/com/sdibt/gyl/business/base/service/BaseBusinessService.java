package com.sdibt.gyl.business.base.service;

import java.io.Serializable;

import com.sdibt.gyl.query.BaseQuery;
import com.sdibt.gyl.query.PageResult;

/**
 * 关于业务逻辑的抽象接口
 * @author zd
 *  参数T代表主表
 *  参数E代表子表
 */
public interface BaseBusinessService<T,E extends Serializable>{
	public PageResult<T> getEntrties_zhu(BaseQuery baseQuery);  //返回主表的分页
	public PageResult<E> getEntrties_zi(BaseQuery baseQuery);  //返回子表的分页
	
	/**
	 * 得到订单号的最大值+1
	 * @return
	 */
	public String getMax();
	
	/**
	 * 保存主表，级联保存子表
	 */
	public void saveEntry_zhu(T t);
}
