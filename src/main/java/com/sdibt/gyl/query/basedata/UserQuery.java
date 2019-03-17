package com.sdibt.gyl.query.basedata;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sdibt.gyl.query.BaseQuery;

public class UserQuery extends BaseQuery {

	private String username;

	private String email;

	private String name;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Map<String, Object> buildWhere() {
		if (StringUtils.isNotBlank(this.username)) {
			this.getKeyValues().put("username", this.username);
		}
		if (StringUtils.isNotBlank(this.email)) {
			this.getKeyValues().put("email", this.email);
		}
		if (StringUtils.isNotBlank(this.name)) {
			this.getKeyValues().put("department.name", this.name);
		}
		return this.getKeyValues();
	}

}
