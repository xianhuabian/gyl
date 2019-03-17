package com.sdibt.gyl.business.xsgl.service;

import java.util.List;

import com.sdibt.gyl.business.base.service.BaseBusinessService;
import com.sdibt.gyl.domain.business.xsgl.Xsfhdzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsfhdzhub;

public interface XsfhdService extends BaseBusinessService<Xsfhdzhub, Xsfhdzhib>{
	public void saveXsfhd(Xsfhdzhub xsfhdzhub,List<Xsfhdzhib> xsfhdzhibs);
}
