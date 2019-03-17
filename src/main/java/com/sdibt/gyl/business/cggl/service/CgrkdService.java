package com.sdibt.gyl.business.cggl.service;

import com.sdibt.gyl.business.base.service.BaseBusinessService;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhib;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhub;

public interface CgrkdService extends BaseBusinessService<Cgrkdzhub, Cgrkdzhib>{

	public Object getCgrkdByDDH(String ddh);
	
	/**
	 * 
	 * @param item  要修改的字段
	 * @param textValue  字段的值
	 */
	public void updateCgrkd(String item,String textValue,String ddh,Long hh) throws Exception;
}
