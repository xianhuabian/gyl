package com.sdibt.gyl.business.xsgl.service;

import com.sdibt.gyl.business.base.service.BaseBusinessService;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhub;

public interface XsyddService extends BaseBusinessService<Xsyddzhub, Xsyddzhib>{

	public Object getXsyddByDDH(String ddh);
	
	/**
	 * 
	 * @param item  要修改的字段
	 * @param textValue  字段的值
	 */
	public void updateXsydd(String item,String textValue,String ddh,Long hh) throws Exception;
}
