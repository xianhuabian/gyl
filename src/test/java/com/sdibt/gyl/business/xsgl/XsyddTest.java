package com.sdibt.gyl.business.xsgl;

import org.junit.Test;

import com.sdibt.gyl.basedata.test.utils.SpringUtils;
import com.sdibt.gyl.business.xsgl.service.XsyddService;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhib;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.business.xsgl.XsyddzhibQuery;

public class XsyddTest extends SpringUtils{
	@Test
	public void testQuery(){
		XsyddService xsyddService = (XsyddService)context.getBean("xsyddService");
//		XsyddzhubQuery baseQuery = new XsyddzhubQuery();
//		baseQuery.setKhmc("fasaf");
//		//baseQuery.setCurrentPage(2);
//		PageResult<Xsyddzhub> pageResult_zhu = xsyddService.getEntrties_zhu(baseQuery);
//		System.out.println(pageResult_zhu.getRows().size());
		
		XsyddzhibQuery xsyddzhibQuery = new XsyddzhibQuery();
		xsyddzhibQuery.setXsyddzhubid(1L);
		//xsyddzhibQuery.setCurrentPage(3);
		PageResult<Xsyddzhib> pageResult = xsyddService.getEntrties_zi(xsyddzhibQuery);
		System.out.println(pageResult.getRows().size());
	}
}
