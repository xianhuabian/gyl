package com.sdibt.gyl.business.cggl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.business.cggl.dao.CgddzhubDao;
import com.sdibt.gyl.domain.business.cggl.Cgddzhib;
import com.sdibt.gyl.domain.business.cggl.Cgddzhub;

@Repository("cgddzhubDao")
public class CgddzhubDaoImpl extends BaseDaoImpl<Cgddzhub> implements CgddzhubDao{

	@Override
	public List<Cgddzhib> getCgddzhibByDDH(String ddh) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from Cgddzhib where cgddzhub.ddh=?",ddh);
	}

	@Override
	public Cgddzhub getCgddzhubByDDH(String ddh) {
		// TODO Auto-generated method stub
		return (Cgddzhub)this.hibernateTemplate.find("from Cgddzhub where ddh=?",ddh).get(0);
	}
}
