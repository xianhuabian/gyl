package com.sdibt.gyl.basedata.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.base.service.impl.BaseServiceImpl;
import com.sdibt.gyl.basedata.dao.UserDao;
import com.sdibt.gyl.basedata.service.UserService;
import com.sdibt.gyl.domain.basedata.User;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	public BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return this.userDao;
	}

}
