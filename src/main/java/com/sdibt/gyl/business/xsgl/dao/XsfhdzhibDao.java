package com.sdibt.gyl.business.xsgl.dao;

import java.util.List;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.domain.business.xsgl.Xsfhdzhib;

public interface XsfhdzhibDao extends BaseDao<Xsfhdzhib>{
	public List<Xsfhdzhib> getXsfhdzhibByCondition(String ytdjh,Long ythh);
}
