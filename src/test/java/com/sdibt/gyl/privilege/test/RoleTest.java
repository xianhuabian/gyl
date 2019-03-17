package com.sdibt.gyl.privilege.test;

import java.util.Arrays;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;

import com.sdibt.gyl.basedata.test.utils.SpringUtils;
import com.sdibt.gyl.domain.privilege.Privilege;
import com.sdibt.gyl.domain.privilege.Role;
import com.sdibt.gyl.privilege.dao.RoleDao;
import com.sdibt.gyl.privilege.service.PrivilegeService;
import com.sdibt.gyl.privilege.service.RoleService;
import com.sdibt.gyl.privilege.service.impl.PrivilegeServiceImpl;

public class RoleTest extends SpringUtils{
	@Test
	public void testSaveRole(){
		RoleService roleService = (RoleService)context.getBean("roleService");
		Role t = new Role();
		t.setIsParent(true);
		t.setName("CEO");
		t.setPid(0L);
		roleService.saveEntry(t);
	}
	
	/*@Test
	public void testQueryRoleByName(){
		RoleDao roleDao = (RoleDao)context.getBean("roleDao");
		Role role = roleDao.getRoleByName("aaa");
		System.out.println(role);
	}*/
	
	@Resource(name="privilegeService")
	PrivilegeService privilegeService;
	@Test
	public void test() {
		String string = "1,2,3";
		Set<Privilege> privileges = new PrivilegeServiceImpl().getEntriesByIds(string.split(","));
		System.out.println(privileges);
	}
}
