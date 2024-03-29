package com.sdibt.gyl.login.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sdibt.gyl.domain.basedata.User;
import com.sdibt.gyl.login.dao.LoginDao;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao{

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@Override
	public User authentication(String username, String password) {
		// TODO Auto-generated method stub
		List<User> users = this.hibernateTemplate.find("from User where username=? and password=?",username,password);
		if(users.size()==0) {
			return null;
		}else {
			return users.get(0);
		}
	}
	
	
}
