package com.sdibt.gyl.privilege.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.base.service.impl.BaseServiceImpl;
import com.sdibt.gyl.domain.privilege.Privilege;
import com.sdibt.gyl.privilege.dao.PrivilegeDao;
import com.sdibt.gyl.privilege.service.PrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege> implements PrivilegeService{

	@Resource(name="privilegeDao")
	private PrivilegeDao privilegeDao;
	
	@Override
	public BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return this.privilegeDao;
	}

	@Override
	public Collection<Privilege> getPrivilegesByRoleid(Long rid) {
		// TODO Auto-generated method stub
		return this.privilegeDao.getPrivilegesByRid(rid);
	}
	
	@Override
	public Collection<Privilege> getMenuitemTreeByUid(Long uid) {
		// TODO Auto-generated method stub
		return this.privilegeDao.getMenuitemTreeByUid(uid);
	}
	
	@Override
	public Collection<Privilege> getFunctionTreeByUid(Long uid) {
		// TODO Auto-generated method stub
		return this.privilegeDao.getFunctionByUid(uid);
	}

}
