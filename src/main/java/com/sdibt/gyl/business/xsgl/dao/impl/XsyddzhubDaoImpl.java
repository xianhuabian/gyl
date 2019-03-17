package com.sdibt.gyl.business.xsgl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.business.xsgl.dao.XsyddzhubDao;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhub;

@Repository("xsyddzhubDao")
public class XsyddzhubDaoImpl extends BaseDaoImpl<Xsyddzhub> implements XsyddzhubDao{

	@Override
	public List<Xsyddzhib> getXsyddzhibByDDH(String ddh) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from Xsyddzhib where xsyddzhub.ddh=?",ddh);
	}

	@Override
	public Xsyddzhub getXsyddzhubByDDH(String ddh) {
		// TODO Auto-generated method stub
		return (Xsyddzhub)this.hibernateTemplate.find("from Xsyddzhub where ddh=?",ddh).get(0);
	}
}
