package com.sdibt.gyl.basedata.test.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {

	public static ApplicationContext context;
	
	static {
		context = new ClassPathXmlApplicationContext("com/sdibt/gyl/spring/ApplicationContext.xml");
	}
}
