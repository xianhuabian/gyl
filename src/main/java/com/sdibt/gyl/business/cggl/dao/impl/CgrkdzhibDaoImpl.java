package com.sdibt.gyl.business.cggl.dao.impl;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.business.cggl.dao.CgrkdzhibDao;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhib;

@Repository("cgrkdzhibDao")
public class CgrkdzhibDaoImpl extends BaseDaoImpl<Cgrkdzhib> implements CgrkdzhibDao{

	@Override
	public Cgrkdzhib getCgrkdzhibByCondition(String ytdjh, Long ythh) {
		// TODO Auto-generated method stub
		return (Cgrkdzhib)this.hibernateTemplate.find("from Cgrkdzhib where cgrkdzhub.djh=? and hh=?",ytdjh,ythh).get(0);
	}
}
