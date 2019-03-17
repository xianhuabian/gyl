package com.sdibt.gyl.business.xsgl.action;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.basedata.service.RepositoryService;
import com.sdibt.gyl.business.xsgl.service.XsddService;
import com.sdibt.gyl.domain.basedata.Repository;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhub;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.business.xsgl.XsddzhibQuery;
import com.sdibt.gyl.query.business.xsgl.XsddzhubQuery;

@Controller("xsddAction")
@Scope("prototype")
public class XsddAction {
	@Resource(name="xsddService")
	private XsddService xsddService;
	
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;
	/**
	 * 接受主表中的数据
	 */
	private XsddzhubQuery query_zhub = new XsddzhubQuery();
	private XsddzhibQuery query_zhib = new XsddzhibQuery();
	/**
	 * 接受销售订单子表的数据
	 */
	private List<Xsddzhib> xsddzhibs;
	
	private String item;//得到要修改的字段
	private String textValue;//得到修改之后的值
	private String ddh;//订单号
	private Long hh;//行号  修改子表的时候，需要传递行号
	

	public String getDdh() {
		return ddh;
	}

	public void setDdh(String ddh) {
		this.ddh = ddh;
	}

	public Long getHh() {
		return hh;
	}

	public void setHh(Long hh) {
		this.hh = hh;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getTextValue() {
		return textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public XsddzhibQuery getQuery_zhib() {
		return query_zhib;
	}

	public void setQuery_zhib(XsddzhibQuery query_zhib) {
		this.query_zhib = query_zhib;
	}

	public XsddzhubQuery getQuery_zhub() {
		return query_zhub;
	}

	public void setQuery_zhub(XsddzhubQuery query_zhub) {
		this.query_zhub = query_zhub;
	}

	public List<Xsddzhib> getXsddzhibs() {
		return xsddzhibs;
	}

	public void setXsddzhibs(List<Xsddzhib> xsddzhibs) {
		this.xsddzhibs = xsddzhibs;
	}
	
	public String showXsdd(){
		PageResult<Xsddzhub> pageResult_zhu = this.xsddService.getEntrties_zhu(query_zhub);
		PageResult<Xsddzhib> pageResult_zhi = this.xsddService.getEntrties_zi(query_zhib);
		ActionContext.getContext().put("pageResult_zhub", pageResult_zhu);
		ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
		return "xsddList";
	}
	
	public String addUI(){
		Collection<Repository> repositories = repositoryService.getEntries();
		ActionContext.getContext().put("repositories", repositories);
		return "addUI";
	}
	
	public String updateUI(){
		return "updateUI";
	}
	
	public String update() throws Exception{
		System.out.println(item + " " + textValue + " " +  ddh + " " + hh);
		this.xsddService.updateXsdd(item, textValue, ddh, hh);
		return "success";
	}
	
	public String showXsddByDDH(){
		Object obj = this.xsddService.getXsddByDDH(this.query_zhub.getDdh());
		if(obj instanceof String){//说明销售订单已经完成了
			ActionContext.getContext().getValueStack().push("该销售订单已经完成了，不能进行修改");
			return "business_error";
		}else{//没有完成
			BeanUtils.copyProperties(obj, this.query_zhub);
			Xsddzhub xsddzhub = (Xsddzhub)obj;
			this.query_zhib.setXsddzhubid(xsddzhub.getXsddzhubid());
			PageResult<Xsddzhib> pageResult_zhi = this.xsddService.getEntrties_zi(query_zhib);
			ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
			return "updateUI";
		}
	}
	
	/**
	 * 销售订单的添加
	 * @return
	 */
	public String add(){
		Xsddzhub xsddzhub = new Xsddzhub();
		BeanUtils.copyProperties(this.query_zhub, xsddzhub);
		//产生订单号
		xsddzhub.setDdh(this.xsddService.getMax());
		//建立主表和子表的关联
		xsddzhub.setXsddzhibs(new HashSet<Xsddzhib>(this.xsddzhibs));
		this.xsddService.saveEntry_zhu(xsddzhub);
		return "chain";
	}
}
