package com.sdibt.gyl.basedata.action;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.base.action.BaseAction;
import com.sdibt.gyl.basedata.service.SupplierService;
import com.sdibt.gyl.domain.basedata.Supplier;
import com.sdibt.gyl.privilege.annotation.PrivilegeInfo;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.basedata.SupplierQuery;

@Controller("supplierAction")
@Scope("prototype")
public class SupplierAction extends BaseAction<Supplier>{
	
	@Resource(name="supplierService")
	private SupplierService supplierService;
	
	private SupplierQuery baseQuery = new SupplierQuery();
	
	@PrivilegeInfo(name="供应商查询")
	public String showPageResult() {
		baseQuery.setCurrentPage(this.getCurrentPage());
		PageResult<Supplier> suppliers = this.supplierService.getPageResult(baseQuery);
		ActionContext.getContext().put("suppliers", suppliers);
		return listAction;
	}
	
	public String deleteSuppliers() {
		//String[] ids = this.getCheckedStr().split(",");
		//System.out.println(this.getCheckedStr());
		this.supplierService.deleteEntriesByIds(this.getIds());
		return action2action;
	}
	
	public String addUI() {
		return addUI;
	}
	
	public String add() throws IllegalAccessException, InvocationTargetException {
		Supplier supplier = new Supplier();
		BeanUtils.copyProperties(supplier, this.getModel());
		//System.out.println(this.getModel().getName() + this.getModel().getDescription());
		this.supplierService.saveEntry(supplier);
		return action2action;
	}
	
	public String updateUI() {
		Supplier supplier = this.supplierService.getEntryById(this.getModel().getSid());
		ActionContext.getContext().getValueStack().push(supplier);
		return updateUI;
	}
	
	public String update() throws IllegalAccessException, InvocationTargetException {
		Supplier supplier = this.supplierService.getEntryById(this.getModel().getSid());
		BeanUtils.copyProperties(supplier, this.getModel());
		this.supplierService.updateEntry(supplier);
		return "chain";
	}
	
	/**
	 * 点击删除一个的方法
	 * @return
	 */
	public String deleteOneSupplier() {
		supplierService.deleteEntryById(this.getModel().getSid());
		return "chain";
	}

}
