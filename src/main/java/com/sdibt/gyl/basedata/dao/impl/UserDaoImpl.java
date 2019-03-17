package com.sdibt.gyl.basedata.dao.impl;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.action.BaseAction;
import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.basedata.dao.UserDao;
import com.sdibt.gyl.domain.basedata.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

}
