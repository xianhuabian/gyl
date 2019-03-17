package com.sdibt.gyl.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdibt.gyl.domain.basedata.User;
import com.sdibt.gyl.login.dao.LoginDao;
import com.sdibt.gyl.login.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	@Resource(name="loginDao")
	private LoginDao loginDao;
	@Override
	public User authentication(String username, String password) {
		// TODO Auto-generated method stub
		return this.loginDao.authentication(username, password);
	}

}
