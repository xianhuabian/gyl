package com.sdibt.gyl.business.cggl.dao;

import java.util.List;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.domain.business.cggl.Cgddzhib;
import com.sdibt.gyl.domain.business.cggl.Cgddzhub;

public interface CgddzhubDao extends BaseDao<Cgddzhub>{

	public List<Cgddzhib> getCgddzhibByDDH(String ddh);
	public Cgddzhub getCgddzhubByDDH(String ddh);
}
