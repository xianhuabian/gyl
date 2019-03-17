package com.sdibt.gyl.business.xsgl.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.basedata.service.CustomerService;
import com.sdibt.gyl.business.xsgl.service.XsyddService;
import com.sdibt.gyl.domain.basedata.Customer;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhub;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.business.xsgl.XsyddzhibQuery;
import com.sdibt.gyl.query.business.xsgl.XsyddzhubQuery;

@Controller("xsyddAction")
@Scope("prototype")
public class XsyddAction{
	/**
	 * 主表的查询条件
	 */
	private XsyddzhubQuery query_zhub = new XsyddzhubQuery();
	
	/**
	 * 子表的查询条件
	 */
	private XsyddzhibQuery query_zhib = new XsyddzhibQuery();
	
	/**
	 * 在增加的时候，接受页面上子表的表格中的值
	 */
	private List<Xsyddzhib> xsyddzhibs;
	
	private String item;//得到要修改的字段
	private String textValue;//得到修改之后的值
	private String ddh;//订单号
	private Long hh;//行号  修改子表的时候，需要传递行号
	

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
	public List<Xsyddzhib> getXsyddzhibs() {
		return xsyddzhibs;
	}
	public void setXsyddzhibs(List<Xsyddzhib> xsyddzhibs) {
		this.xsyddzhibs = xsyddzhibs;
	}
	public XsyddzhubQuery getQuery_zhub() {
		return query_zhub;
	}
	public void setQuery_zhub(XsyddzhubQuery query_zhub) {
		this.query_zhub = query_zhub;
	}
	public XsyddzhibQuery getQuery_zhib() {
		return query_zhib;
	}
	public void setQuery_zhib(XsyddzhibQuery query_zhib) {
		this.query_zhib = query_zhib;
	}
	@Resource(name="xsyddService")
	private XsyddService xsyddService;
	
	@Resource(name="customerService")
	private CustomerService customerService;
	/**
	 * 查询销售预订单
	 * @return
	 */
	public String showXsydd(){
		PageResult<Xsyddzhub> pageResult_zhu = this.xsyddService.getEntrties_zhu(query_zhub);
		PageResult<Xsyddzhib> pageResult_zhi = this.xsyddService.getEntrties_zi(query_zhib);
		
		ActionContext.getContext().put("pageResult_zhub", pageResult_zhu);
		ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
		
		return "xsyddList";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String addUI(){
		Collection<Customer> customers = customerService.getEntries();
		ActionContext.getContext().put("customers", customers);
		return "xsyddAddUI";
	}
	
	/**
	 * 增加
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String add() throws IllegalAccessException, InvocationTargetException{
		Xsyddzhub xsyddzhub = new Xsyddzhub();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		BeanUtils.copyProperties(xsyddzhub, this.query_zhub);
		//建立销售预订单主表和子表的关系
		xsyddzhub.setXsyddzhibs(new HashSet<Xsyddzhib>(this.xsyddzhibs));
		//设置销售预订单的最新的订单号
		xsyddzhub.setDdh(this.xsyddService.getMax());
		this.xsyddService.saveEntry_zhu(xsyddzhub);
		return "chain";
	}
	
	public String updateUI(){
		return "updateUI";
	}
	
	public String update() throws Exception{
		this.xsyddService.updateXsydd(item, textValue, ddh, hh);
		return "success";
	}
	
	public String showXsyddByDDH() throws IllegalAccessException, InvocationTargetException{
		Object obj = this.xsyddService.getXsyddByDDH(this.query_zhub.getDdh());
		if(obj instanceof String){//说明销售订单已经完成了
			ActionContext.getContext().getValueStack().push("该销售订单已经完成了，不能进行修改");
			return "business_error";
		}else{//没有完成
			ConvertUtils.register(new DateConverter(null), java.util.Date.class);
			BeanUtils.copyProperties(this.query_zhub,  obj);
			Xsyddzhub xsyddzhub = (Xsyddzhub)obj;
			this.query_zhib.setXsyddzhubid(xsyddzhub.getXsyddzhubid());
			PageResult<Xsyddzhib> pageResult_zhi = this.xsyddService.getEntrties_zi(query_zhib);
			ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
			return "updateUI";
		}
	}
	
}
