package com.sdibt.gyl.query.basedata;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sdibt.gyl.query.BaseQuery;

public class ProductQuery extends BaseQuery {

	private String spmc; // 商品名称
	private String xh;// 型号
	private String spbm;// 商品编码
	// private Double shulv;//税率

	public String getSpmc() {
		return spmc;
	}

	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSpbm() {
		return spbm;
	}

	public void setSpbm(String spbm) {
		this.spbm = spbm;
	}

	@Override
	public Map<String, Object> buildWhere() {
		if (StringUtils.isNotBlank(this.spmc)) {
			this.getKeyValues().put("spmc", this.spmc);
		}
		if (StringUtils.isNotBlank(this.xh)) {
			this.getKeyValues().put("xh", this.xh);
		}
		if (StringUtils.isNotBlank(this.spbm)) {
			this.getKeyValues().put("spbm", this.spbm);
		}
		/*
		 * if(StringUtils.isNotBlank(Double.toString(this.shulv))) {
		 * this.getKeyValues().put("shulv", this.shulv); }
		 */
		return this.getKeyValues();
	}

}
