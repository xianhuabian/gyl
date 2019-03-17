package com.sdibt.gyl.basedata.test;

import org.junit.Test;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.basedata.test.utils.SpringUtils;
import com.sdibt.gyl.query.basedata.DepartmentQuery;


public class BaseDaoTest extends SpringUtils{
	@Test
	public void testBaseDao_Count(){
		BaseDao baseDao = (BaseDao)context.getBean("baseDao");
		DepartmentQuery baseQuery = new DepartmentQuery();
		baseQuery.setName("44");
		int count = baseDao.getCount(baseQuery);
		System.out.println(count);
	}
}
