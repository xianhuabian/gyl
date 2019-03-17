package com.sdibt.gyl.business.kcgl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.base.service.impl.BaseServiceImpl;
import com.sdibt.gyl.business.kcgl.dao.KcglDao;
import com.sdibt.gyl.business.kcgl.service.KcglService;
import com.sdibt.gyl.domain.business.kcgl.Kc;

@Service("kcglService")
public class KcglServiceImpl extends BaseServiceImpl<Kc> implements KcglService{

	@Resource(name="kcglDao")
	private KcglDao kcglDao;
	
	@Override
	public BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return kcglDao;
	}

}
