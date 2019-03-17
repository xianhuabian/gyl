package com.sdibt.gyl.privilege.dao;

import java.util.Collection;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.domain.privilege.Role;

public interface RoleDao extends BaseDao<Role>{

	public Role getRoleByName(final String name);
	public Collection<Role> getRoleByUid(Long uid);
}
