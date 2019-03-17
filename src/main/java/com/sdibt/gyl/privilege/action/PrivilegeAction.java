package com.sdibt.gyl.privilege.action;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.base.action.BaseAction;
import com.sdibt.gyl.domain.basedata.User;
import com.sdibt.gyl.domain.privilege.Privilege;
import com.sdibt.gyl.domain.privilege.Role;
import com.sdibt.gyl.privilege.service.PrivilegeService;
import com.sdibt.gyl.privilege.service.RoleService;

@Controller("privilegeAction")
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Privilege>{
	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	@Resource(name="roleService")
	private RoleService roleService;
	
	private Long rid;//角色id
	private String checkedStr;//被选中的权限节点的id的字符串  "1,2,3"
	
	
	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getCheckedStr() {
		return checkedStr;
	}

	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}

	/**
	 * 根据rid对对应的角色所有的权限树进行回显。
	 */
	public String showPrivilegeTree() throws Exception{
		Collection<Privilege> privileges = this.privilegeService.getPrivilegesByRoleid(this.getRid());
		ActionContext.getContext().getValueStack().push(privileges);
		Thread.sleep(1000L);
		return SUCCESS;
	}
	
	public String savePrivilege(){
		//根据rid获取到role
		Role role = this.roleService.getEntryById(rid);
		//获取到被选中的权限的集合
		if("".equals(this.checkedStr)){//页面上没有选择权限
			role.setPrivileges(null);
		}else{
			Set<Privilege> privileges = this.privilegeService.getEntriesByIds(this.checkedStr.split(","));
			//建立角色与权限之间的关系
			role.setPrivileges(privileges);
		}
		this.roleService.updateEntry(role);
		return SUCCESS;
	}
	
	/**
	 * 加载登录以后的左侧页面的菜单树
	 */
	public String showMenuitemTreeByUid(){
		User user = (User)this.getSession().getAttribute("user");
		Collection<Privilege> privileges = this.privilegeService.getMenuitemTreeByUid(user.getUid());
		ActionContext.getContext().getValueStack().push(new HashSet<Privilege>(privileges));
		return SUCCESS;
	}
}
