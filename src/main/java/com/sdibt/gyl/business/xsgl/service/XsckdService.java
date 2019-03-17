package com.sdibt.gyl.business.xsgl.service;

import java.util.List;

import com.sdibt.gyl.business.base.service.BaseBusinessService;
import com.sdibt.gyl.domain.business.xsgl.Xsckdzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsckdzhub;

public interface XsckdService extends BaseBusinessService<Xsckdzhub, Xsckdzhib>{
	public void saveXsckd(Xsckdzhub xsckdzhub,List<Xsckdzhib> xsckdzhibs);
}
