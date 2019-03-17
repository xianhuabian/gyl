package com.sdibt.gyl.privilege.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.base.action.BaseAction;
import com.sdibt.gyl.basedata.service.UserService;
import com.sdibt.gyl.domain.basedata.User;
import com.sdibt.gyl.domain.privilege.Role;
import com.sdibt.gyl.privilege.service.RoleService;
import com.sdibt.gyl.utils.GylConstantKey;

@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	@Resource(name="userService")
	private UserService userService;
	
	private Long uid;//用户id   根据该用户id根据该用户能够访问到的角色
	
	private String checkedStr;
	
	public String getCheckedStr() {
		return checkedStr;
	}

	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String showRoleTree() {
		Collection<Role> roles = this.roleService.getEntries();
		System.out.println(roles);
		System.out.println("-------------------------------------");
		ActionContext.getContext().getValueStack().push(roles);
		return SUCCESS;
	}
	
	/**
	 * 在权限管理-->角色配置-->设置角色(超级链接)-->加载角色树，涉及到对角色树的回显
	 * @return
	 */
	public String showRoleByUid(){
		Collection<Role> roles = this.roleService.getRoleByUid(uid);
		ActionContext.getContext().getValueStack().push(roles);
		return SUCCESS;
	}
	
	public String add() throws IllegalAccessException, InvocationTargetException {
		Role role = new Role();
		BeanUtils.copyProperties(role, this.getModel());
		System.out.println(role.getPid() + " " + role.getPid());
		this.roleService.saveEntry(role);
		/**
		 * 把role回调到客户端，因为客户端要实用rid
		 */
		
		ActionContext.getContext().getValueStack().push(role);
		return SUCCESS;
	}
	
	public String showRoleByName() {
		Role role = this.roleService.getRoleByName(this.getModel().getName());
		if(role==null) {
			ActionContext.getContext().getValueStack().push(GylConstantKey.ROLE_NAME_FLAG_ABLE);
		}else {
			ActionContext.getContext().getValueStack().push(GylConstantKey.ROLE_NAME_FLAG_DISABLE);
		}
		return SUCCESS;
	}
	
	public String deleteRole() {
		this.roleService.deleteEntryById(this.getModel().getRid());
		return SUCCESS;
	}
	
	public String updateRole() throws IllegalAccessException, InvocationTargetException {
		Role role = this.roleService.getEntryById(this.getModel().getRid());
		role.setName(this.getModel().getName());
		this.roleService.updateEntry(role);
		return SUCCESS;
	}
	
	/**
	 * 在权限管理-->权限配置-->打开角色设置权限的页面的时候，显示角色列表，用该方法
	 */
	public String showRoles(){
		Collection<Role> roles = this.roleService.getEntries();
		ActionContext.getContext().put("roles", roles);
		return listAction;
	}
	
	/**
	 * 跳转到用户设置角色的页面
	 */
	public String showUserList(){
		Collection<User> users = this.userService.getEntries();
		ActionContext.getContext().put("users", users);
		return listAction;
	}
	
	/**
	 * 建立用户与角色之间的关系
	 */
	public String saveRole(){
		User user = this.userService.getEntryById(uid);
		Set<Role> roles = this.roleService.getEntriesByIds(this.checkedStr.split(","));
		user.setRoles(roles);//建立用户与角色之间的关系
		this.userService.updateEntry(user);
		return SUCCESS;
	}

}
