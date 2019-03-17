package com.sdibt.gyl.business.cggl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.business.cggl.dao.CgrkdzhubDao;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhib;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhub;

@Repository("cgrkdzhubDao")
public class CgrkdzhubDaoImpl extends BaseDaoImpl<Cgrkdzhub> implements CgrkdzhubDao{

	@Override
	public List<Cgrkdzhib> getCgrkdzhibByDDH(String ddh) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from Cgrkdzhib where cgrkdzhub.djh=?",ddh);
	}

	@Override
	public Cgrkdzhub getCgrkdzhubByDDH(String ddh) {
		// TODO Auto-generated method stub
		return (Cgrkdzhub)this.hibernateTemplate.find("from Cgrkdzhub where djh=?",ddh).get(0);
	}
}
