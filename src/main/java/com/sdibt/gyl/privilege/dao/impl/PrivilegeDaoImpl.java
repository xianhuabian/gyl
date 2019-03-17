package com.sdibt.gyl.privilege.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.domain.privilege.Privilege;
import com.sdibt.gyl.privilege.dao.PrivilegeDao;

@Repository("privilegeDao")
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao{

	@Override
	public Collection<Privilege> getPrivilegesByRid(Long rid) {
		/**
		 * 1、加载所有的权限
		 * 2、加载该角色能够访问到的权限
		 * 3、两次遍历，把所有的权限中用户能够访问到的权限的checked设置为true
		 */
		Collection<Privilege> allPrivileges = this.hibernateTemplate.find("from Privilege");
		Collection<Privilege> rolePrivileges = this.hibernateTemplate.
					find("from Privilege p inner join fetch p.roles r where r.rid=?",rid);
		for (Privilege privilege : allPrivileges) {//遍历所有的权限
			for (Privilege privilege2 : rolePrivileges) {//遍历角色的权限
				//如果当前正在遍历的所有的权限中的该项是角色能够访问到的
				if(privilege.getId().longValue()==privilege2.getId().longValue()){
					privilege.setChecked(true);
				}
			}
		}
		return allPrivileges;
	}

	@Override
	public Collection<Privilege> getMenuitemTreeByUid(Long uid) {
		if(uid.longValue()==1){//说明是管理员
			return this.hibernateTemplate.find("from Privilege");
		}else{//普通员工   根据uid查找对应的权限
			return this.hibernateTemplate.
				find("from Privilege p inner join fetch p.roles r " +
						"inner join fetch r.users u where u.uid=? ",uid);
		}
	}

	/**
	 * 得到一个用户能够访问的功能权限
	 */
	@Override
	public Collection<Privilege> getFunctionByUid(Long uid) {
		if(uid.longValue()==1){//说明是管理员
			return this.hibernateTemplate.find("from Privilege where type='2'");
		}else{//普通员工   根据uid查找对应的权限
			return this.hibernateTemplate.
				find("from Privilege p inner join fetch p.roles r " +
						"inner join fetch r.users u where u.uid=? and p.type='2'",uid);
		}
	}
	
}
