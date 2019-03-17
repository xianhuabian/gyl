package com.sdibt.gyl.business.xsgl.service;

import com.sdibt.gyl.business.base.service.BaseBusinessService;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhub;

public interface XsddService extends BaseBusinessService<Xsddzhub, Xsddzhib>{

	public Object getXsddByDDH(String ddh);
	
	/**
	 * 
	 * @param item  要修改的字段
	 * @param textValue  字段的值
	 */
	public void updateXsdd(String item,String textValue,String ddh,Long hh) throws Exception;
}
