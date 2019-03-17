package com.sdibt.gyl.basedata.dao.impl;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.basedata.dao.DepartmentDao;
import com.sdibt.gyl.domain.basedata.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao{

}
