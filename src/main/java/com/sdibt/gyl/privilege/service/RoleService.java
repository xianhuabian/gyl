package com.sdibt.gyl.privilege.service;

import java.util.Collection;

import com.sdibt.gyl.base.service.BaseService;
import com.sdibt.gyl.domain.privilege.Role;

public interface RoleService extends BaseService<Role>{
	
	public Role getRoleByName(String name);
	public Collection<Role> getRoleByUid(Long uid);

}
