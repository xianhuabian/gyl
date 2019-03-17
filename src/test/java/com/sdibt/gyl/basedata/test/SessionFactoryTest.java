package com.sdibt.gyl.basedata.test;

import org.junit.Test;

import com.sdibt.gyl.basedata.test.utils.SpringUtils;

public class SessionFactoryTest extends SpringUtils{

	@Test
	public void testSessionFactory() {
		context.getBean("sessionFactory");
	}
	
}
