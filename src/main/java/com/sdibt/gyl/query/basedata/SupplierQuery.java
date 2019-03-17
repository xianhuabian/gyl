package com.sdibt.gyl.query.basedata;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sdibt.gyl.query.BaseQuery;

public class SupplierQuery extends BaseQuery {
	private String name;// 供应商的名称
	private String address;
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public Map<String, Object> buildWhere() {
		if (StringUtils.isNotBlank(this.name)) {// name属性的值不为空
			this.getKeyValues().put("name", this.name);
		}
		if (StringUtils.isNotBlank(this.address)) {// description属性的值不为空
			this.getKeyValues().put("address", this.address);
		}
		if (StringUtils.isNotBlank(this.phone)) {// description属性的值不为空
			this.getKeyValues().put("phone", this.phone);
		}
		return this.getKeyValues();
	}

}
