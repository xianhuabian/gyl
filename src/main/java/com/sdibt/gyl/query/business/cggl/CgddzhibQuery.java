package com.sdibt.gyl.query.business.cggl;

import java.util.Map;

import com.sdibt.gyl.query.BaseQuery;

public class CgddzhibQuery extends BaseQuery {

	private Long cgddzhubid;// 销售订单主表ID

	private String hstatus;// 行状态 "1"表示关闭 "2"表示未关闭

	public String getHstatus() {
		return hstatus;
	}

	public void setHstatus(String hstatus) {
		this.hstatus = hstatus;
	}

	public Long getCgddzhubid() {
		return cgddzhubid;
	}

	public void setCgddzhubid(Long cgddzhubid) {
		this.cgddzhubid = cgddzhubid;
	}

	@Override
	public Map<String, Object> buildWhere() {
		if (cgddzhubid != null) {
			this.keyValues.put("cgddzhub.cgddzhubid", this.cgddzhubid);
		}
		if (hstatus != null) {
			this.keyValues.put("hstatus", this.hstatus);
		}
		return this.keyValues;
	}
}
