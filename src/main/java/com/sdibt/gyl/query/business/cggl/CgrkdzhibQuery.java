package com.sdibt.gyl.query.business.cggl;

import java.util.Map;

import com.sdibt.gyl.query.BaseQuery;

public class CgrkdzhibQuery extends BaseQuery {

	private Long cgrkdzhubid;// 销售订单主表ID

	public Long getCgrkdzhubid() {
		return cgrkdzhubid;
	}

	public void setCgrkdzhubid(Long cgrkdzhubid) {
		this.cgrkdzhubid = cgrkdzhubid;
	}

	@Override
	public Map<String, Object> buildWhere() {
		if (cgrkdzhubid != null) {
			this.keyValues.put("cgrkdzhub.cgrkdzhibid", this.cgrkdzhubid);
		}

		return this.keyValues;
	}
}
