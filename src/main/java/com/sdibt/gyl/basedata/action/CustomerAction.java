package com.sdibt.gyl.basedata.action;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.base.action.BaseAction;
import com.sdibt.gyl.basedata.service.CustomerService;
import com.sdibt.gyl.domain.basedata.Customer;
import com.sdibt.gyl.privilege.annotation.PrivilegeInfo;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.basedata.CustomerQuery;

@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends BaseAction<Customer>{
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	private CustomerQuery baseQuery = new CustomerQuery();
	
	@PrivilegeInfo(name="客户查询")
	public String showPageResult() {
		baseQuery.setCurrentPage(this.getCurrentPage());
		PageResult<Customer> customers = this.customerService.getPageResult(baseQuery);
		ActionContext.getContext().put("customers", customers);
		return listAction;
	}
	
	public String deleteCustoemrs() {
		//String[] ids = this.getCheckedStr().split(",");
		//System.out.println(this.getCheckedStr());
		this.customerService.deleteEntriesByIds(this.getIds());
		return action2action;
	}
	
	public String addUI() {
		return addUI;
	}
	
	public String add() throws IllegalAccessException, InvocationTargetException {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customer, this.getModel());
		//System.out.println(this.getModel().getName() + this.getModel().getDescription());
		this.customerService.saveEntry(customer);
		return action2action;
	}
	
	public String updateUI() {
		Customer customer = this.customerService.getEntryById(this.getModel().getCid());
		ActionContext.getContext().getValueStack().push(customer);
		return updateUI;
	}
	
	public String update() throws IllegalAccessException, InvocationTargetException {
		Customer customer = this.customerService.getEntryById(this.getModel().getCid());
		BeanUtils.copyProperties(customer, this.getModel());
		this.customerService.updateEntry(customer);
		return "chain";
	}
	
	/**
	 * 点击删除一个的方法
	 * @return
	 */
	public String deleteOneCustomer() {
		customerService.deleteEntryById(this.getModel().getCid());
		return "chain";
	}
	
}
