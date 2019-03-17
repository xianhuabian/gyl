package com.sdibt.gyl.login.service;

import com.sdibt.gyl.domain.basedata.User;

public interface LoginService {

	public User authentication(String username, String password);
}
