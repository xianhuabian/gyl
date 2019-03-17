package com.sdibt.gyl.basedata.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.base.service.impl.BaseServiceImpl;
import com.sdibt.gyl.basedata.dao.DepartmentDao;
import com.sdibt.gyl.basedata.service.DepartmentService;
import com.sdibt.gyl.domain.basedata.Department;

@Service("departmentService")
public class DepartmentServiceImpl extends  BaseServiceImpl<Department> implements DepartmentService{
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	@Override
	public BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return this.departmentDao;
	}	
}

