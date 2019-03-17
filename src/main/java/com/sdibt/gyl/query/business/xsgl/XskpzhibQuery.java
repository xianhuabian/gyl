package com.sdibt.gyl.query.business.xsgl;

import java.util.Map;

import com.sdibt.gyl.query.BaseQuery;

/**
 * 销售开票子表
 * @author zd
 *
 */
public class XskpzhibQuery extends BaseQuery {
	private Long xskpzhubid;
	
	public Long getXskpzhubid() {
		return xskpzhubid;
	}

	public void setXskpzhubid(Long xskpzhubid) {
		this.xskpzhubid = xskpzhubid;
	}

	@Override
	public Map<String, Object> buildWhere() {
		if (xskpzhubid != null) {
			this.keyValues.put("xskpzhub.xskpzhubid", this.xskpzhubid);
		}
		return this.keyValues;
	}
}
