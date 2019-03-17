package com.sdibt.gyl.privilege.service;

import java.util.Collection;

import com.sdibt.gyl.base.service.BaseService;
import com.sdibt.gyl.domain.privilege.Privilege;

public interface PrivilegeService extends BaseService<Privilege>{

	public Collection<Privilege> getPrivilegesByRoleid(Long rid);
	
	public Collection<Privilege> getMenuitemTreeByUid(Long uid);
	
	public Collection<Privilege> getFunctionTreeByUid(Long uid);
}
