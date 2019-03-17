package com.sdibt.gyl.business.xsgl.dao;

import java.util.List;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhub;

public interface XsddzhubDao extends BaseDao<Xsddzhub>{

	public List<Xsddzhib> getXsddzhibByDDH(String ddh);
	public Xsddzhub getXsddzhubByDDH(String ddh);
}
