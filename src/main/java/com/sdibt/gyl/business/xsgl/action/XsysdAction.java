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
import com.sdibt.gyl.business.xsgl.service.XsysdService;
import com.sdibt.gyl.domain.business.xsgl.Xsysdzhub;
import com.sdibt.gyl.domain.business.xsgl.Xsysdzhib;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.business.xsgl.XsysdzhibQuery;
import com.sdibt.gyl.query.business.xsgl.XsysdzhubQuery;

@Controller("xsysdAction")
@Scope("prototype")
public class XsysdAction {

	@Resource(name = "xsysdService")
	private XsysdService xsysdService;

	private List<Xsysdzhib> xsysdzhibs;

	private XsysdzhubQuery query_zhub = new XsysdzhubQuery();
	private XsysdzhibQuery query_zhib = new XsysdzhibQuery();

	public List<Xsysdzhib> getXsysdzhibs() {
		return xsysdzhibs;
	}

	public void setXsysdzhibs(List<Xsysdzhib> xsysdzhibs) {
		this.xsysdzhibs = xsysdzhibs;
	}

	public XsysdzhubQuery getQuery_zhub() {
		return query_zhub;
	}

	public void setQuery_zhub(XsysdzhubQuery query_zhub) {
		this.query_zhub = query_zhub;
	}

	public XsysdzhibQuery getQuery_zhib() {
		return query_zhib;
	}

	public void setQuery_zhib(XsysdzhibQuery query_zhib) {
		this.query_zhib = query_zhib;
	}

	public String addUI() {
		return "addUI";
	}

	public String add() throws IllegalAccessException, InvocationTargetException {
		Xsysdzhub xsysdzhub = new Xsysdzhub();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		BeanUtils.copyProperties(xsysdzhub, this.query_zhub);
		// 建立销售预订单主表和子表的关系
		xsysdzhub.setXsysdzhibs(new HashSet<Xsysdzhib>(this.xsysdzhibs));
		// 设置销售预订单的最新的订单号
		xsysdzhub.setDdh(this.xsysdService.getMax());
		this.xsysdService.saveEntry_zhu(xsysdzhub);
		return "chain";
	}

	public String showXsysd() {
		PageResult<Xsysdzhub> pageResult_zhu = this.xsysdService.getEntrties_zhu(query_zhub);
		PageResult<Xsysdzhib> pageResult_zhi = this.xsysdService.getEntrties_zi(query_zhib);
		ActionContext.getContext().put("pageResult_zhub", pageResult_zhu);
		ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
		return "xsysdList";
	}

}
