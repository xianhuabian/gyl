package com.sdibt.gyl.business.xsgl.dao.impl;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.business.xsgl.dao.XsyddzhibDao;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhib;

@Repository("xsyddzhibDao")
public class XsyddzhibDaoImpl extends BaseDaoImpl<Xsyddzhib> implements XsyddzhibDao{

	@Override
	public Xsyddzhib getXsyddzhibByCondition(String ytdjh, Long ythh) {
		// TODO Auto-generated method stub
		return (Xsyddzhib)this.hibernateTemplate.find("from Xsyddzhib where xsyddzhub.ddh=? and hh=?",ytdjh,ythh).get(0);
	}
}
