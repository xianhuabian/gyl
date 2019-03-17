package com.sdibt.gyl.basedata.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.base.action.BaseAction;
import com.sdibt.gyl.basedata.service.DepartmentService;
import com.sdibt.gyl.basedata.service.UserService;
import com.sdibt.gyl.domain.basedata.Department;
import com.sdibt.gyl.domain.basedata.User;
import com.sdibt.gyl.privilege.annotation.PrivilegeInfo;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.basedata.UserQuery;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	private Long did;
	
	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public UserQuery baseQuery = new UserQuery();
	
	public UserQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(UserQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	@PrivilegeInfo(name="用户查询")
	public String showPageResult() {
		baseQuery.setCurrentPage(this.getCurrentPage());
		PageResult<User> users = this.userService.getPageResult(baseQuery);
		ActionContext.getContext().put("users", users);
		return listAction;
	}
	
	public String addUI() {
		/**
		 * 列出所有部门
		 */
		Collection<Department> departments = this.departmentService.getEntries();
		ActionContext.getContext().put("departments", departments);
		return addUI;
	}
	
	public String add() throws IllegalAccessException, InvocationTargetException {
		/**
		 * 1、获取用户的一般属性
		 * 2、获取页面上的did的值，根据did的值把department对象提取出来
		 * 3、建立用户与部门之间的关系
		 */
		User user = new User();
		BeanUtils.copyProperties(user, this.getModel());
		
		Department department = this.departmentService.getEntryById(did);
		user.setDepartment(department);
		this.userService.saveEntry(user);
		
		return action2action;
	}
	
	public String updateUI() {
		/**
		 * 回显用户的一般属性
		 */
		User user = this.userService.getEntryById(this.getModel().getUid());
		ActionContext.getContext().getValueStack().push(user);
		
		/**
		 * 回显部门
		 * action 中的属性 did 在对象栈中，所以只要给did复制就可以了
		 */
		this.did = user.getDepartment().getDid();
		
		/**
		 * 把部门取出来
		 */
		Collection<Department> departments = this.departmentService.getEntries();
		ActionContext.getContext().put("departments", departments);
		return updateUI;
	}
	
	public String update() throws IllegalAccessException, InvocationTargetException {
		/**
		 * 用户的一般属性
		 */
		User user = this.userService.getEntryById(this.getModel().getUid());
		BeanUtils.copyProperties(user, this.getModel());
		
		Department department = this.departmentService.getEntryById(this.did);
		
		user.setDepartment(department);
		
		this.userService.updateEntry(user);
		
		return action2action;
		
	}
	
	/**
	 * 点击删除一个的方法
	 * @return
	 */
	public String deleteOneUser() {
		userService.deleteEntryById(this.getModel().getUid());
		return "chain";
	}
	
	/**
	 * 删除多个的方法
	 */
	public String deleteUsers() {
		//String[] ids = this.getCheckedStr().split(",");
		//System.out.println(this.getCheckedStr());
		this.userService.deleteEntriesByIds(this.getIds());
		return action2action;
	}
	

}
