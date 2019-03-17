package com.sdibt.gyl.basedata.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.base.action.BaseAction;
import com.sdibt.gyl.basedata.service.ProductService;
import com.sdibt.gyl.domain.basedata.Department;
import com.sdibt.gyl.domain.basedata.Product;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.basedata.ProductQuery;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product>{
	@Resource(name="productService")
	private ProductService productService;
	
	public ProductQuery baseQuery = new ProductQuery();
	
	
	public ProductQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(ProductQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	public String showProduct(){
		Collection<Product> products = this.productService.getEntries();
		ActionContext.getContext().getValueStack().push(products);
		return SUCCESS;
	}
	
	public String showPageResult(){
		baseQuery.setCurrentPage(this.getCurrentPage());
		PageResult<Product> products = this.productService.getPageResult(baseQuery);
		ActionContext.getContext().put("products", products);
		return listAction;
	}
	
	
	public String addUI() {
		return addUI;
	}
	
	public String add() throws IllegalAccessException, InvocationTargetException {
		Product product = new Product();
		BeanUtils.copyProperties(product, this.getModel());
		this.productService.saveEntry(product);
		return action2action;
	}
	
	public String updateUI() {
		Product product = this.productService.getEntryById(this.getModel().getPid());
		ActionContext.getContext().getValueStack().push(product);
		return updateUI;
	}
	
	public String update() throws IllegalAccessException, InvocationTargetException {
		Product product = this.productService.getEntryById(this.getModel().getPid());
		BeanUtils.copyProperties(product, this.getModel());
		this.productService.updateEntry(product);
		return "chain";
	}
	
	/**
	 * 点击删除一个的方法
	 * @return
	 */
	public String deleteOneProduct() {
		productService.deleteEntryById(this.getModel().getPid());
		return "chain";
	}
	
	/**
	 * 删除多个的方法
	 */
	public String deleteProducts() {
		//String[] ids = this.getCheckedStr().split(",");
		//System.out.println(this.getCheckedStr());
		this.productService.deleteEntriesByIds(this.getIds());
		return action2action;
	}

	
}	
