package com.sdibt.gyl.business.xsgl.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.business.xsgl.service.XskpService;
import com.sdibt.gyl.domain.business.xsgl.Xskpzhib;
import com.sdibt.gyl.domain.business.xsgl.Xskpzhub;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.business.xsgl.XskpzhibQuery;
import com.sdibt.gyl.query.business.xsgl.XskpzhubQuery;

@Controller("xskpAction")
@Scope("prototype")
public class XskpAction {
	
	@Resource(name="xskpService")
	private XskpService xskpService;
	
	private List<Xskpzhib> xskpzhibs;
	
	private XskpzhubQuery query_zhub = new XskpzhubQuery();
	private XskpzhibQuery query_zhib = new XskpzhibQuery();
	
	
	public XskpzhubQuery getQuery_zhub() {
		return query_zhub;
	}
	public void setQuery_zhub(XskpzhubQuery query_zhub) {
		this.query_zhub = query_zhub;
	}
	public List<Xskpzhib> getXskpzhibs() {
		return xskpzhibs;
	}
	public void setXskpzhibs(List<Xskpzhib> xskpzhibs) {
		this.xskpzhibs = xskpzhibs;
	}
	public XskpzhibQuery getQuery_zhib() {
		return query_zhib;
	}

	public void setQuery_zhib(XskpzhibQuery query_zhib) {
		this.query_zhib = query_zhib;
	}
	
	public String addUI(){
		return "addUI";
	}
	
	public String add() throws IllegalAccessException, InvocationTargetException{
		Xskpzhub xskpzhub = new Xskpzhub();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		BeanUtils.copyProperties(xskpzhub, this.query_zhub);
		//建立销售预订单主表和子表的关系
		xskpzhub.setXskpzhibs(new HashSet<Xskpzhib>(this.xskpzhibs));
		//设置销售预订单的最新的订单号
		//xskpzhub.setFph(this.xskpService.getMax());
		this.xskpService.saveEntry_zhu(xskpzhub);
		return "chain";
	}
	
	public String showXskp(){
		PageResult<Xskpzhub> pageResult_zhu = this.xskpService.getEntrties_zhu(query_zhub);
		PageResult<Xskpzhib> pageResult_zhi = this.xskpService.getEntrties_zi(query_zhib);
		ActionContext.getContext().put("pageResult_zhub", pageResult_zhu);
		ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
		return "xskpList";
	}
	
	
}
