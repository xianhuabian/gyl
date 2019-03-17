package com.sdibt.gyl.utils;

import java.util.Calendar;

import org.junit.Test;

public class GylUtils {
	
	public static String getDateToString(){
		 Calendar cal=Calendar.getInstance();//使用日历类
		  int year=cal.get(Calendar.YEAR);//得到年
		  int month=cal.get(Calendar.MONTH)+1; //得到月，因为从0开始的，所以要加1
		  int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
		  return ""+year+month+day;
	}
	
	public static String getDDH(String type){
		 Calendar cal=Calendar.getInstance();//使用日历类
		  int year=cal.get(Calendar.YEAR);//得到年
		  int month=cal.get(Calendar.MONTH)+1; //得到月，因为从0开始的，所以要加1
		  int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
		 return type+year+month+day;
	}
	
	@Test
	public void test(){
		
	}
}
