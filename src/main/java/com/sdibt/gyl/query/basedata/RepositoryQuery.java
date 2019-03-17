package com.sdibt.gyl.query.basedata;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sdibt.gyl.query.BaseQuery;

public class RepositoryQuery extends BaseQuery {
	private String name;
	private String area;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Map<String, Object> buildWhere() {
		if (StringUtils.isNotBlank(this.name)) {// name属性的值不为空
			this.getKeyValues().put("name", this.name);
		}
		if (StringUtils.isNotBlank(this.area)) {// description属性的值不为空
			this.getKeyValues().put("area", this.area);
		}
		if (StringUtils.isNotBlank(this.address)) {// description属性的值不为空
			this.getKeyValues().put("address", this.address);
		}
		return this.getKeyValues();
	}

}
