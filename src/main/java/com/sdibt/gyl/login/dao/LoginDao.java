package com.sdibt.gyl.login.dao;

import com.sdibt.gyl.domain.basedata.User;

public interface LoginDao {

	public User authentication(String username, String password);
}
