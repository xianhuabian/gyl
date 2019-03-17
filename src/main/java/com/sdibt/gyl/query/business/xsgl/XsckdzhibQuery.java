package com.sdibt.gyl.query.business.xsgl;

import java.util.Map;

import com.sdibt.gyl.query.BaseQuery;

public class XsckdzhibQuery extends BaseQuery {
	private Long xsckdzhubid;//主键
	
	public Long getXsckdzhubid() {
		return xsckdzhubid;
	}

	public void setXsckdzhubid(Long xsckdzhubid) {
		this.xsckdzhubid = xsckdzhubid;
	}


	@Override
	public Map<String, Object> buildWhere() {
		if (xsckdzhubid != null) {
			this.keyValues.put("xsckdzhub.xsckdzhubid", this.xsckdzhubid);
		}
		return this.keyValues;
	}
}
