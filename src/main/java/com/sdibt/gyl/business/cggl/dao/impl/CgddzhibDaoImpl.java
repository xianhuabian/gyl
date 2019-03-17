package com.sdibt.gyl.business.cggl.dao.impl;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.business.cggl.dao.CgddzhibDao;
import com.sdibt.gyl.domain.business.cggl.Cgddzhib;

@Repository("cgddzhibDao")
public class CgddzhibDaoImpl extends BaseDaoImpl<Cgddzhib> implements CgddzhibDao{

	@Override
	public Cgddzhib getCgddzhibByCondition(String ytdjh, Long ythh) {
		// TODO Auto-generated method stub
		return (Cgddzhib)this.hibernateTemplate.find("from Cgddzhib where cgddzhub.ddh=? and hh=?",ytdjh,ythh).get(0);
	}
}
