package com.sdibt.gyl.business.cggl.dao;

import java.util.List;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhib;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhub;

public interface CgrkdzhubDao extends BaseDao<Cgrkdzhub>{

	public List<Cgrkdzhib> getCgrkdzhibByDDH(String ddh);
	public Cgrkdzhub getCgrkdzhubByDDH(String ddh);
}
