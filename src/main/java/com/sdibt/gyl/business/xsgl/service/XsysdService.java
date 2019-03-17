package com.sdibt.gyl.business.xsgl.service;

import java.util.List;

import com.sdibt.gyl.business.base.service.BaseBusinessService;
import com.sdibt.gyl.domain.business.xsgl.Xsysdzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsysdzhub;

public interface XsysdService extends BaseBusinessService<Xsysdzhub, Xsysdzhib>{
	public void saveXsysd(Xsysdzhub xsysdzhub,List<Xsysdzhib> xsysdzhibs);
}
