package com.sdibt.gyl.business.xsgl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.sdibt.gyl.basedata.test.utils.SpringUtils;
import com.sdibt.gyl.business.xsgl.dao.XsddzhibDao;
import com.sdibt.gyl.business.xsgl.service.XsfhdService;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsfhdzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsfhdzhub;

public class XsfhdTest extends SpringUtils{
	@Test
	public void testSaveXsfhd(){
		XsfhdService xsfhdService = (XsfhdService)context.getBean("xsfhdService");
		Xsfhdzhub xsfhdzhub = new Xsfhdzhub();
		List<Xsfhdzhib> xsfhdzhibs = new ArrayList<Xsfhdzhib>();
		Xsfhdzhib xsfhdzhib = new Xsfhdzhib();
		xsfhdzhib.setSfsl(1L);//设置实发数量
		xsfhdzhib.setYtdjh("20191140001");//源头单据号
		xsfhdzhib.setYthh(1L);//源头行号
		xsfhdzhibs.add(xsfhdzhib);
		xsfhdService.saveXsfhd(xsfhdzhub, xsfhdzhibs);
		
		System.out.println("销售发货单子表是否关闭："+xsfhdzhib.getIsfhgb());
		XsddzhibDao xsddzhibDao = (XsddzhibDao)context.getBean("xsddzhibDao");
		Xsddzhib xsddzhib = xsddzhibDao.getXsddzhibByCondition("20191140001", 1L);
		System.out.println("销售订单子表累计发货数量:"+xsddzhib.getLjfhsl());
		System.out.println("是否发货关闭:"+xsddzhib.getIsfhgb());
	}
	
	@Test
	public void testQuery(){
		XsddzhibDao xsddzhibDao = (XsddzhibDao)context.getBean("xsddzhibDao");
		Xsddzhib xsddzhib = xsddzhibDao.getXsddzhibByCondition("201410240001", 1L);
		System.out.println(xsddzhib.getIsfhgb());
	}
	
	@Test
	public void testSetIsfhgb(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Xsddzhib xsddzhib = (Xsddzhib)session.get(Xsddzhib.class, 1L);
		xsddzhib.setIsfhgb(false);
		transaction.commit();
		session.close();
	}
}
