package com.sdibt.gyl.business.cggl.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sdibt.gyl.business.cggl.service.CgddService;
import com.sdibt.gyl.domain.business.cggl.Cgddzhib;
import com.sdibt.gyl.domain.business.cggl.Cgddzhub;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhub;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.business.cggl.CgddzhibQuery;
import com.sdibt.gyl.query.business.cggl.CgddzhubQuery;

@Controller("cgddAction")
@Scope("prototype")
public class CgddAction {
	@Resource(name="cgddService")
	private CgddService cgddService;
	/**
	 * 接受主表中的数据
	 */
	private CgddzhubQuery query_zhub = new CgddzhubQuery();
	private CgddzhibQuery query_zhib = new CgddzhibQuery();
	/**
	 * 接受采购订单子表的数据
	 */
	private List<Cgddzhib> cgddzhibs;
	
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

	public CgddzhubQuery getQuery_zhub() {
		return query_zhub;
	}

	public void setQuery_zhub(CgddzhubQuery query_zhub) {
		this.query_zhub = query_zhub;
	}

	public CgddzhibQuery getQuery_zhib() {
		return query_zhib;
	}

	public void setQuery_zhib(CgddzhibQuery query_zhib) {
		this.query_zhib = query_zhib;
	}

	public List<Cgddzhib> getCgddzhibs() {
		return cgddzhibs;
	}

	public void setCgddzhibs(List<Cgddzhib> cgddzhibs) {
		this.cgddzhibs = cgddzhibs;
	}

	public String showCgdd(){
		PageResult<Cgddzhub> pageResult_zhu = this.cgddService.getEntrties_zhu(query_zhub);
		PageResult<Cgddzhib> pageResult_zhi = this.cgddService.getEntrties_zi(query_zhib);
		ActionContext.getContext().put("pageResult_zhub", pageResult_zhu);
		ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
		return "cgddList";
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
		Cgddzhub cgddzhub = new Cgddzhub();
		BeanUtils.copyProperties(this.query_zhub, cgddzhub);
		//产生订单号
		cgddzhub.setDdh(this.cgddService.getMax());
		//建立主表和子表的关联
		cgddzhub.setCgddzhibs(new HashSet<Cgddzhib>(this.cgddzhibs));
		this.cgddService.saveEntry_zhu(cgddzhub);
		return "chain";
	}
	
	public String updateUI(){
		return "updateUI";
	}
	
	public String update() throws Exception{
		System.out.println(item + " " + textValue + " " +  ddh + " " + hh);
		this.cgddService.updateCgdd(item, textValue, ddh, hh);
		return "success";
	}
	
	public String showCgddByDDH() throws IllegalAccessException, InvocationTargetException{
		Object obj = this.cgddService.getCgddByDDH(this.query_zhub.getDdh());
		if(obj instanceof String){//说明销售订单已经完成了
			ActionContext.getContext().getValueStack().push("该销售订单已经完成了，不能进行修改");
			return "business_error";
		}else{//没有完成
			System.out.println("+++++++++++++++++++++++++++++++++++++");
			BeanUtils.copyProperties(obj, this.query_zhub);
			Cgddzhub cgddzhub = (Cgddzhub)obj;
			this.query_zhib.setCgddzhubid(cgddzhub.getCgddzhubid());
			PageResult<Cgddzhib> pageResult_zhi = this.cgddService.getEntrties_zi(query_zhib);
			ActionContext.getContext().put("pageResult_zhib", pageResult_zhi);
			return "updateUI";
		}
	}
	
	
}
