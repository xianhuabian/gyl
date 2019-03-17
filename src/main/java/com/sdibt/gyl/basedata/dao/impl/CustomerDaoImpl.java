package com.sdibt.gyl.basedata.dao.impl;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.basedata.dao.CustomerDao;
import com.sdibt.gyl.domain.basedata.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

}
