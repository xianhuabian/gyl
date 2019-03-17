package com.sdibt.gyl.query.basedata;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sdibt.gyl.query.BaseQuery;

public class CustomerQuery extends BaseQuery {
	private String name;
	private String phone;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Map<String, Object> buildWhere() {
		if (StringUtils.isNotBlank(this.name)) {// name属性的值不为空
			this.getKeyValues().put("name", this.name);
		}
		if (StringUtils.isNotBlank(this.phone)) {// description属性的值不为空
			this.getKeyValues().put("phone", this.phone);
		}
		if (StringUtils.isNotBlank(this.email)) {// description属性的值不为空
			this.getKeyValues().put("email", this.email);
		}
		return this.getKeyValues();
	}

}
