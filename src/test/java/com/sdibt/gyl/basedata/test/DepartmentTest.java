package com.sdibt.gyl.basedata.test;

import org.junit.Test;

import com.sdibt.gyl.basedata.dao.DepartmentDao;
import com.sdibt.gyl.basedata.test.utils.SpringUtils;
import com.sdibt.gyl.domain.basedata.Department;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.basedata.DepartmentQuery;

public class DepartmentTest extends SpringUtils{
	@Test
	public void testGetCount(){
		DepartmentDao departmentDao = (DepartmentDao)context.getBean("departmentDao");
		DepartmentQuery baseQuery = new DepartmentQuery();
		int count = departmentDao.getCount(baseQuery);
		System.out.println(count);
	}
	
	@Test
	public void testPageResult() {
		DepartmentDao departmentDao = (DepartmentDao) context.getBean("departmentDao");
		DepartmentQuery baseQuery = new DepartmentQuery();
		PageResult<Department> pageResult = departmentDao.findPageResult(baseQuery);
		for (Department department : pageResult.getRows()) {
			System.out.println(department.getDid());
		}
	}
}
