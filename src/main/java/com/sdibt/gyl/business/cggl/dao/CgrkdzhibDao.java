package com.sdibt.gyl.business.cggl.dao;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhib;

public interface CgrkdzhibDao extends BaseDao<Cgrkdzhib>{

	/**
	 * 根据源头单据号和源头行号查找销售订单的子表
	 * @param ytdjh
	 * @param ythh
	 * @return
	 */
	public Cgrkdzhib getCgrkdzhibByCondition(String ytdjh,Long ythh);
}
