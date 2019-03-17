package com.sdibt.gyl.business.cggl.action;

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
import com.sdibt.gyl.business.cggl.service.CgrkdService;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhib;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhub;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.business.cggl.CgrkdzhibQuery;
import com.sdibt.gyl.query.business.cggl.CgrkdzhubQuery;

@Controller("cgrkdAction")
@Scope("prototype")
public class CgrkdAction {
	@Resource(name="cgrkdService")
	private CgrkdService cgrkdService;
	/**
	 * 接受主表中的数据
	 */
	private CgrkdzhubQuery query_zhub = new CgrkdzhubQuery();
	private CgrkdzhibQuery query_zhib = new CgrkdzhibQuery();
	/**
	 * 接受采购订单子表的数据
	 */
	private List<Cgrkdzhib> cgrkdzhibs;
	
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


	public CgrkdzhubQuery getQuery_zhub() {
		return query_zhub;
	}

	public void setQuery_zhub(CgrkdzhubQuery query_zhub) {
		this.query_zhub = query_zhub;
	}

	public CgrkdzhibQuery getQuery_zhib() {
		return query_zhib;
	}

	public void setQuery_zhib(CgrkdzhibQuery query_zhib) {
		this.query_zhib = query_zhib;
	}

	public List<Cgrkdzhib> getCgrkdzhibs() {
		return cgrkdzhibs;
	}

	public void setCgrkdzhibs(List<Cgrkdzhib> cgrkdzhibs) {
		this.cgrkdzhibs = cgrkdzhibs;
	}

	public String showCgrkd(){
		PageResult<Cgrkdzhub> pageResult_zhu = this.cgrkdService.getEntrties_zhu(query_zhub);
		PageResult<Cgrkdzhib> pageResult_zhi = this.cgrkdService.getEntrties_zi(query_zhib);
		ActionContext.getContext().put("pageResult_zhub", pageResult_zhu);
		ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
		return "cgrkdList";
	}
	
	
	public String addUI(){
		return "addUI";
	}
	
	/**
	 * 销售订单的添加
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String add() throws IllegalAccessException, InvocationTargetException{
		Cgrkdzhub cgrkdzhub = new Cgrkdzhub();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		BeanUtils.copyProperties(this.query_zhub, cgrkdzhub);
		//产生订单号
		//cgrkdzhub.setDjh(this.cgrkdService.getMax());
		//建立主表和子表的关联
		cgrkdzhub.setCgrkdzhibs(new HashSet<Cgrkdzhib>(this.cgrkdzhibs));
		this.cgrkdService.saveEntry_zhu(cgrkdzhub);
		return "chain";
	}
	
	public String updateUI(){
		return "updateUI";
	}
	
	public String update() throws Exception{
		System.out.println(item + " " + textValue + " " +  ddh + " " + hh);
		this.cgrkdService.updateCgrkd(item, textValue, ddh, hh);
		return "success";
	}
	
	public String showCgrkdByDDH() throws IllegalAccessException, InvocationTargetException{
		Object obj = this.cgrkdService.getCgrkdByDDH(this.query_zhub.getDjh());
		if(obj instanceof String){//说明销售订单已经完成了
			ActionContext.getContext().getValueStack().push("该销售订单已经完成了，不能进行修改");
			return "business_error";
		}else{//没有完成
			BeanUtils.copyProperties(obj, this.query_zhub);
			Cgrkdzhub cgrkdzhub = (Cgrkdzhub)obj;
			this.query_zhib.setCgrkdzhubid(cgrkdzhub.getCgrkdzhubid());
			PageResult<Cgrkdzhib> pageResult_zhi = this.cgrkdService.getEntrties_zi(query_zhib);
			ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
			return "updateUI";
		}
	}
	
	
}
