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
import com.sdibt.gyl.business.xsgl.service.XsckdService;
import com.sdibt.gyl.domain.business.xsgl.Xsckdzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsckdzhub;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.business.xsgl.XsckdzhibQuery;
import com.sdibt.gyl.query.business.xsgl.XsckdzhubQuery;


@Controller("xsckdAction")
@Scope("prototype")
public class XsckdAction {
	
	@Resource(name="xsckdService")
	private XsckdService xsckdService;
	
	private List<Xsckdzhib> xsckdzhibs;
	
	private XsckdzhubQuery query_zhub = new XsckdzhubQuery();
	private XsckdzhibQuery query_zhib = new XsckdzhibQuery();
	
	
	
	public XsckdzhubQuery getQuery_zhub() {
		return query_zhub;
	}
	public void setQuery_zhub(XsckdzhubQuery query_zhub) {
		this.query_zhub = query_zhub;
	}
	public XsckdzhibQuery getQuery_zhib() {
		return query_zhib;
	}
	public void setQuery_zhib(XsckdzhibQuery query_zhib) {
		this.query_zhib = query_zhib;
	}
	public List<Xsckdzhib> getXsckdzhibs() {
		return xsckdzhibs;
	}
	public void setXsckdzhibs(List<Xsckdzhib> xsckdzhibs) {
		this.xsckdzhibs = xsckdzhibs;
	}
	
	public String addUI(){
		return "addUI";
	}
	
	public String add() throws IllegalAccessException, InvocationTargetException{
		Xsckdzhub xsckdzhub = new Xsckdzhub();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		BeanUtils.copyProperties(xsckdzhub, this.query_zhub);
		//建立销售预订单主表和子表的关系
		xsckdzhub.setXsckdzhibs(new HashSet<Xsckdzhib>(this.xsckdzhibs));
		//设置销售预订单的最新的订单号
		xsckdzhub.setDdh(this.xsckdService.getMax());
		this.xsckdService.saveEntry_zhu(xsckdzhub);
		return "chain";
	}
	
	public String showXsckd(){
		PageResult<Xsckdzhub> pageResult_zhu = this.xsckdService.getEntrties_zhu(query_zhub);
		PageResult<Xsckdzhib> pageResult_zhi = this.xsckdService.getEntrties_zi(query_zhib);
		ActionContext.getContext().put("pageResult_zhub", pageResult_zhu);
		ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
		return "xsfhdList";
	}
	
	
}
