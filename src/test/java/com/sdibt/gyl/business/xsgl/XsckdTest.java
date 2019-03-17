package com.sdibt.gyl.business.xsgl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.sdibt.gyl.basedata.test.utils.SpringUtils;
import com.sdibt.gyl.business.xsgl.dao.XsddzhibDao;
import com.sdibt.gyl.business.xsgl.service.XsckdService;
import com.sdibt.gyl.domain.business.xsgl.Xsckdzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsckdzhub;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;

public class XsckdTest extends SpringUtils{
	@Test
	public void testSaveXsckd(){
		XsckdService xsckdService = (XsckdService)context.getBean("xsckdService");
		Xsckdzhub xsckdzhub = new Xsckdzhub();
		List<Xsckdzhib> xsckdzhibs = new ArrayList<Xsckdzhib>();
		Xsckdzhib xsckdzhib = new Xsckdzhib();
		xsckdzhib.setSfsl(1L);//设置实发数量
		xsckdzhib.setYtdjh("20191140001");//源头单据号
		xsckdzhib.setYthh(1L);//源头行号
		xsckdzhibs.add(xsckdzhib);
		xsckdService.saveXsckd(xsckdzhub, xsckdzhibs);
		
		System.out.println("销售出库单子表是否关闭："+xsckdzhib.getIsckgb());
		XsddzhibDao xsddzhibDao = (XsddzhibDao)context.getBean("xsddzhibDao");
		Xsddzhib xsddzhib = xsddzhibDao.getXsddzhibByCondition("20191140001", 1L);
		System.out.println("销售订单子表累计发货数量:"+xsddzhib.getLjcksl());
		System.out.println("是否出库关闭:"+xsddzhib.getIsckgb());
	}
	
	@Test
	public void testQuery(){
		XsddzhibDao xsddzhibDao = (XsddzhibDao)context.getBean("xsddzhibDao");
		Xsddzhib xsddzhib = xsddzhibDao.getXsddzhibByCondition("20191140001", 1L);
		System.out.println(xsddzhib.getIsckgb());
	}
	
	@Test
	public void testSetIsckgb(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Xsddzhib xsddzhib = (Xsddzhib)session.get(Xsddzhib.class, 1L);
		xsddzhib.setIsckgb(false);
		transaction.commit();
		session.close();
	}
}
