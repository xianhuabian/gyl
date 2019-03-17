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
import com.sdibt.gyl.business.xsgl.service.XsfhdService;
import com.sdibt.gyl.domain.business.xsgl.Xsfhdzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsfhdzhub;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.business.xsgl.XsfhdzhibQuery;
import com.sdibt.gyl.query.business.xsgl.XsfhdzhubQuery;

@Controller("xsfhdAction")
@Scope("prototype")
public class XsfhdAction {
	
	@Resource(name="xsfhdService")
	private XsfhdService xsfhdService;
	
	private List<Xsfhdzhib> xsfhdzhibs;
	
	private XsfhdzhubQuery query_zhub = new XsfhdzhubQuery();
	private XsfhdzhibQuery query_zhib = new XsfhdzhibQuery();
	
	
	
	public XsfhdzhubQuery getQuery_zhub() {
		return query_zhub;
	}
	public void setQuery_zhub(XsfhdzhubQuery query_zhub) {
		this.query_zhub = query_zhub;
	}
	public XsfhdzhibQuery getQuery_zhib() {
		return query_zhib;
	}
	public void setQuery_zhib(XsfhdzhibQuery query_zhib) {
		this.query_zhib = query_zhib;
	}
	public List<Xsfhdzhib> getXsfhdzhibs() {
		return xsfhdzhibs;
	}
	public void setXsfhdzhibs(List<Xsfhdzhib> xsfhdzhibs) {
		this.xsfhdzhibs = xsfhdzhibs;
	}
	
	public String addUI(){
		return "addUI";
	}
	
	public String add() throws IllegalAccessException, InvocationTargetException{
		Xsfhdzhub xsfhdzhub = new Xsfhdzhub();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		BeanUtils.copyProperties(xsfhdzhub, this.query_zhub);
		//建立销售预订单主表和子表的关系
		xsfhdzhub.setXsfhdzhibs(new HashSet<Xsfhdzhib>(this.xsfhdzhibs));
		//设置销售预订单的最新的订单号
		xsfhdzhub.setDdh(this.xsfhdService.getMax());
		this.xsfhdService.saveEntry_zhu(xsfhdzhub);
		return "chain";
	}
	
	public String showXsfhd(){
		PageResult<Xsfhdzhub> pageResult_zhu = this.xsfhdService.getEntrties_zhu(query_zhub);
		PageResult<Xsfhdzhib> pageResult_zhi = this.xsfhdService.getEntrties_zi(query_zhib);
		ActionContext.getContext().put("pageResult_zhub", pageResult_zhu);
		ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
		return "xsfhdList";
	}
	
	
}
