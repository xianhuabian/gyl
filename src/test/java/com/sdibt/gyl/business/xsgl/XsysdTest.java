package com.sdibt.gyl.business.xsgl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.sdibt.gyl.basedata.test.utils.SpringUtils;
import com.sdibt.gyl.business.xsgl.dao.XsddzhibDao;
import com.sdibt.gyl.business.xsgl.dao.XsddzhubDao;
import com.sdibt.gyl.business.xsgl.service.XsysdService;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhub;
import com.sdibt.gyl.domain.business.xsgl.Xsysdzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsysdzhub;

public class XsysdTest extends SpringUtils{
	@Test
	public void testSaveXsysd(){
		XsysdService xsysdService = (XsysdService)context.getBean("xsysdService");
		Xsysdzhub xsysdzhub = new Xsysdzhub();
		List<Xsysdzhib> xsysdzhibs = new ArrayList<Xsysdzhib>();
		Xsysdzhib xsysdzhib = new Xsysdzhib();
		xsysdzhib.setSl(1L);//设置实发数量
		xsysdzhib.setYtdjh("20191140001");//源头单据号
		xsysdzhib.setYthh(1L);//源头行号
		xsysdzhibs.add(xsysdzhib);
		xsysdService.saveXsysd(xsysdzhub, xsysdzhibs);
		
		System.out.println("销售应收单子表是否关闭："+xsysdzhib.getIsysgb());
		XsddzhibDao xsddzhibDao = (XsddzhibDao)context.getBean("xsddzhibDao");
		Xsddzhib xsddzhib = xsddzhibDao.getXsddzhibByCondition("20191140001", 1L);
		System.out.println("销售订单子表累计发货数量:"+xsddzhib.getLjyssl());
		System.out.println("是否应收关闭:"+xsddzhib.getIsskjs());
		/**
		 * 提取销售订单主表
		 */
		XsddzhubDao xsddzhubDao = (XsddzhubDao)context.getBean("xsddzhubDao");
		Xsddzhub xsddzhub = xsddzhubDao.getXsddzhubByDDH("20191140001");
		//查看销售订单主表的状态
		System.out.println(xsddzhub.getState());
	}
	
	@Test
	public void testQuery(){
		XsddzhibDao xsddzhibDao = (XsddzhibDao)context.getBean("xsddzhibDao");
		Xsddzhib xsddzhib = xsddzhibDao.getXsddzhibByCondition("20191140001", 1L);
		System.out.println(xsddzhib.getIsskjs());
	}
	
	@Test
	public void testSetIsysgb(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Xsddzhib xsddzhib = (Xsddzhib)session.get(Xsddzhib.class, 1L);
		xsddzhib.setIsskjs(false);
		transaction.commit();
		session.close();
	}
}
