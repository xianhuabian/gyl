package com.sdibt.gyl.business.xsgl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.business.xsgl.dao.XsddzhubDao;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhub;

@Repository("xsddzhubDao")
public class XsddzhubDaoImpl extends BaseDaoImpl<Xsddzhub> implements XsddzhubDao{

	@Override
	public List<Xsddzhib> getXsddzhibByDDH(String ddh) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from Xsddzhib where xsddzhub.ddh=?",ddh);
	}

	@Override
	public Xsddzhub getXsddzhubByDDH(String ddh) {
		// TODO Auto-generated method stub
		return (Xsddzhub)this.hibernateTemplate.find("from Xsddzhub where ddh=?",ddh).get(0);
	}
}
