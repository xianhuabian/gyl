package com.sdibt.gyl.basedata.action;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.base.action.BaseAction;
import com.sdibt.gyl.basedata.service.DepartmentService;
import com.sdibt.gyl.domain.basedata.Department;
import com.sdibt.gyl.privilege.annotation.PrivilegeInfo;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.basedata.DepartmentQuery;

@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	@Resource(name = "departmentService")
	private DepartmentService departmentService;

	private DepartmentQuery baseQuery = new DepartmentQuery();

	public DepartmentQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(DepartmentQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	@PrivilegeInfo(name = "部门查询")
	public String showPageResult() {
		baseQuery.setCurrentPage(this.getCurrentPage());
		PageResult<Department> departments = this.departmentService.getPageResult(baseQuery);
		ActionContext.getContext().put("departments", departments);
		return listAction;
	}


	public String addUI() {
		return addUI;
	}

	public String add() throws IllegalAccessException, InvocationTargetException {
		Department department = new Department();
		BeanUtils.copyProperties(department, this.getModel());
		System.out.println(this.getModel().getName() + this.getModel().getDescription());
		this.departmentService.saveEntry(department);
		return action2action;
	}

	public String updateUI() {
		Department department = this.departmentService.getEntryById(this.getModel().getDid());
		ActionContext.getContext().getValueStack().push(department);
		return updateUI;
	}

	public String update() throws IllegalAccessException, InvocationTargetException {
		Department department = this.departmentService.getEntryById(this.getModel().getDid());
		BeanUtils.copyProperties(department, this.getModel());
		this.departmentService.updateEntry(department);
		return "chain";
	}

	/**
	 * 点击删除一个的方法
	 * 
	 * @return
	 */
	public String deleteOneDepartment() {
		departmentService.deleteEntryById(this.getModel().getDid());
		return "chain";
	}
	
	/**
	 * 删除多个的方法
	 */
	public String deleteDepartments() {
		//String[] ids = this.getCheckedStr().split(",");
		//System.out.println(this.getCheckedStr());
		this.departmentService.deleteEntriesByIds(this.getIds());
		return action2action;
	}

}
