package com.sdibt.gyl.business.xsgl.dao;

import java.util.List;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhub;

public interface XsyddzhubDao extends BaseDao<Xsyddzhub>{

	public List<Xsyddzhib> getXsyddzhibByDDH(String ddh);
	public Xsyddzhub getXsyddzhubByDDH(String ddh);
}
