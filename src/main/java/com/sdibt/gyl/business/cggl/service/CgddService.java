package com.sdibt.gyl.business.cggl.service;

import com.sdibt.gyl.business.base.service.BaseBusinessService;
import com.sdibt.gyl.domain.business.cggl.Cgddzhib;
import com.sdibt.gyl.domain.business.cggl.Cgddzhub;

public interface CgddService extends BaseBusinessService<Cgddzhub, Cgddzhib>{

	public Object getCgddByDDH(String ddh);
	
	/**
	 * 
	 * @param item  要修改的字段
	 * @param textValue  字段的值
	 */
	public void updateCgdd(String item,String textValue,String ddh,Long hh) throws Exception;
}
