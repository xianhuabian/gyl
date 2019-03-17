package com.sdibt.gyl.business.xsgl.service;

import java.util.List;

import com.sdibt.gyl.business.base.service.BaseBusinessService;
import com.sdibt.gyl.domain.business.xsgl.Xskpzhib;
import com.sdibt.gyl.domain.business.xsgl.Xskpzhub;

public interface XskpService extends BaseBusinessService<Xskpzhub, Xskpzhib>{
	public void saveXskp(Xskpzhub xskpzhub,List<Xskpzhib> xskpzhibs);
}
