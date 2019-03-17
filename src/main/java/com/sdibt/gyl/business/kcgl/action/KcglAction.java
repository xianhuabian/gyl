package com.sdibt.gyl.business.kcgl.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.base.action.BaseAction;
import com.sdibt.gyl.basedata.service.ProductService;
import com.sdibt.gyl.basedata.service.RepositoryService;
import com.sdibt.gyl.business.kcgl.service.KcglService;
import com.sdibt.gyl.domain.basedata.Department;
import com.sdibt.gyl.domain.basedata.Product;
import com.sdibt.gyl.domain.basedata.Repository;
import com.sdibt.gyl.domain.business.kcgl.Kc;
import com.sdibt.gyl.privilege.annotation.PrivilegeInfo;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.business.kcgl.KcglQuery;

@Controller("kcglAction")
@Scope("prototype")
public class KcglAction extends BaseAction<Kc> {

	@Resource(name="kcglService")
	KcglService kcglService;
	
	@Resource(name="repositoryService")
	RepositoryService repositoryService;
	
	@Resource(name="productService")
	ProductService productService;
	

	KcglQuery baseQuery = new KcglQuery();
	
	private Long pid;
	
	private Long rid;
	
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public KcglQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(KcglQuery baseQuery) {
		this.baseQuery = baseQuery;
	}
	
	public String showCkMap() {
		return "map";
	}
	
	@PrivilegeInfo(name = "库存查询")
	public String showPageResult() {
		baseQuery.setCurrentPage(this.getCurrentPage());
		PageResult<Kc> kcs = this.kcglService.getPageResult(baseQuery);
		ActionContext.getContext().put("kcs", kcs);
		return listAction;
	}
	
	public String addUI() {
		Collection<Repository> repositories = repositoryService.getEntries();
		Collection<Product> products = productService.getEntries();
		ActionContext.getContext().put("repositories", repositories);
		ActionContext.getContext().put("products", products);
		return addUI;
	}
	
	public String add() throws IllegalAccessException, InvocationTargetException {
		Kc kc = new Kc();
		Repository repository = repositoryService.getEntryById(this.getRid());
		Product product = productService.getEntryById(this.getPid());
		BeanUtils.copyProperties(kc, this.getModel());
		kc.setProduct(product);
		kc.setRepository(repository);
		//System.out.println(this.getModel().getName() + this.getModel().getDescription());
		this.kcglService.saveEntry(kc);
		return action2action;
	}
	
	public String deleteKcs() {
		//String[] ids = this.getCheckedStr().split(",");
		//System.out.println(this.getCheckedStr());
		this.kcglService.deleteEntriesByIds(this.getIds());
		return action2action;
	}
	
	
	public String deleteOneKc() {
		kcglService.deleteEntryById(this.getModel().getCkspbh());
		return "chain";
	}
	
	
	
	public String updateUI() {
		Collection<Repository> repositories = repositoryService.getEntries();
		Collection<Product> products = productService.getEntries();
		ActionContext.getContext().put("repositories", repositories);
		ActionContext.getContext().put("products", products);
		Kc kc = this.kcglService.getEntryById(this.getModel().getCkspbh());
		ActionContext.getContext().getValueStack().push(kc);
		return updateUI;
	}
	
	public String update() throws IllegalAccessException, InvocationTargetException {
		Kc kc = this.kcglService.getEntryById(this.getModel().getCkspbh());
		BeanUtils.copyProperties(kc, this.getModel());
		Repository repository = repositoryService.getEntryById(this.getRid());
		Product product = productService.getEntryById(this.getPid());
		kc.setProduct(product);
		kc.setRepository(repository);
		this.kcglService.updateEntry(kc);
		return "chain";
	}
}
