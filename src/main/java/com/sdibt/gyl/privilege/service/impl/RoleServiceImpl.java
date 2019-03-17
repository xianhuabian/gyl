package com.sdibt.gyl.privilege.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.base.service.impl.BaseServiceImpl;
import com.sdibt.gyl.domain.privilege.Role;
import com.sdibt.gyl.privilege.dao.RoleDao;
import com.sdibt.gyl.privilege.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	@Resource(name="roleDao")
	private RoleDao roleDao;
	@Override
	public BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return this.roleDao;
	}
	
	@Override
	public Role getRoleByName(String name) {
		// TODO Auto-generated method stub
		return this.roleDao.getRoleByName(name);
	}
	
	@Override
	public Collection<Role> getRoleByUid(Long uid) {
		// TODO Auto-generated method stub
		return this.roleDao.getRoleByUid(uid);
	}	
	

}
