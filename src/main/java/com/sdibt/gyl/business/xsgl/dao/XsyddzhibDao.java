package com.sdibt.gyl.business.xsgl.dao;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhib;

public interface XsyddzhibDao extends BaseDao<Xsyddzhib>{

	/**
	 * 根据源头单据号和源头行号查找销售订单的子表
	 * @param ytdjh
	 * @param ythh
	 * @return
	 */
	public Xsyddzhib getXsyddzhibByCondition(String ytdjh,Long ythh);
}
