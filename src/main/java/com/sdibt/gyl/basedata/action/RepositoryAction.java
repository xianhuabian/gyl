package com.sdibt.gyl.basedata.action;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.base.action.BaseAction;
import com.sdibt.gyl.basedata.service.RepositoryService;
import com.sdibt.gyl.domain.basedata.Repository;
import com.sdibt.gyl.privilege.annotation.PrivilegeInfo;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.basedata.RepositoryQuery;

@Controller("repositoryAction")
@Scope("prototype")
public class RepositoryAction extends BaseAction<Repository> {

	@Resource(name = "repositoryService")
	private RepositoryService repositoryService;

	private RepositoryQuery baseQuery = new RepositoryQuery();

	public RepositoryQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(RepositoryQuery baseQuery) {
		this.baseQuery = baseQuery;
	}

	@PrivilegeInfo(name = "仓库查询")
	public String showPageResult() {
		baseQuery.setCurrentPage(this.getCurrentPage());
		PageResult<Repository> repositorys = this.repositoryService.getPageResult(baseQuery);
		ActionContext.getContext().put("repositorys", repositorys);
		return listAction;
	}

	public String deleteRepositorys() {
		// String[] ids = this.getCheckedStr().split(",");
		// System.out.println(this.getCheckedStr());
		this.repositoryService.deleteEntriesByIds(this.getIds());
		return action2action;
	}

	public String addUI() {
		return addUI;
	}

	public String add() throws IllegalAccessException, InvocationTargetException {
		Repository repository = new Repository();
		BeanUtils.copyProperties(repository, this.getModel());
		// System.out.println(this.getModel().getName() +
		// this.getModel().getDescription());
		this.repositoryService.saveEntry(repository);
		return action2action;
	}

	public String updateUI() {
		Repository repository = this.repositoryService.getEntryById(this.getModel().getRid());
		ActionContext.getContext().getValueStack().push(repository);
		return updateUI;
	}

	public String update() throws IllegalAccessException, InvocationTargetException {
		Repository repository = this.repositoryService.getEntryById(this.getModel().getRid());
		BeanUtils.copyProperties(repository, this.getModel());
		this.repositoryService.updateEntry(repository);
		return "chain";
	}

	/**
	 * 点击删除一个的方法
	 * 
	 * @return
	 */
	public String deleteOneRepository() {
		repositoryService.deleteEntryById(this.getModel().getRid());
		return "chain";
	}
	
	/**
	 * 删除多个的方法
	 */
	public String deleterepositories() {
		//String[] ids = this.getCheckedStr().split(",");
		//System.out.println(this.getCheckedStr());
		this.repositoryService.deleteEntriesByIds(this.getIds());
		return action2action;
	}

}
