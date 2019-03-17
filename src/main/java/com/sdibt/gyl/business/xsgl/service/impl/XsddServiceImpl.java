package com.sdibt.gyl.business.xsgl.service.impl;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.basedata.dao.ProductDao;
import com.sdibt.gyl.basedata.service.ProductService;
import com.sdibt.gyl.basedata.service.RepositoryService;
import com.sdibt.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.sdibt.gyl.business.kcgl.dao.KcglDao;
import com.sdibt.gyl.business.kcgl.service.KcglService;
import com.sdibt.gyl.business.xsgl.dao.XsddzhibDao;
import com.sdibt.gyl.business.xsgl.dao.XsddzhubDao;
import com.sdibt.gyl.business.xsgl.service.XsddService;
import com.sdibt.gyl.domain.basedata.Product;
import com.sdibt.gyl.domain.business.kcgl.Kc;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhub;
import com.sdibt.gyl.query.PageResult;
import com.sdibt.gyl.query.basedata.ProductQuery;
import com.sdibt.gyl.query.basedata.RepositoryQuery;
import com.sdibt.gyl.query.business.kcgl.KcglQuery;
import com.sdibt.gyl.utils.GylConstantKey;

@Service("xsddService")
public class XsddServiceImpl extends BaseBusinessServiceImpl<Xsddzhub, Xsddzhib> implements XsddService{
	@Resource(name="xsddzhubDao")
	private XsddzhubDao xsddzhubDao;
	@Resource(name="xsddzhibDao")
	private XsddzhibDao xsddzhibDao;
	@Resource(name="kcglDao")
	private KcglDao kcglDao;
	@Resource(name="productDao")
	private ProductDao productDao;
	@Resource(name="productService")
	private ProductService productService;
	/*@Resource(name="repositoryService")
	private RepositoryService repositoryService;*/
	@Resource(name="kcglService")
	private KcglService kcglService;
	
	@Override
	public BaseDao<Xsddzhub> getBaseDao_zhu() {
		// TODO Auto-generated method stub
		return this.xsddzhubDao;
	}
	@Override
	public BaseDao<Xsddzhib> getBaseDao_zhi() {
		// TODO Auto-generated method stub
		return this.xsddzhibDao;
	}
	
	@Override
	public Object getXsddByDDH(String ddh) {
		Xsddzhub xsddzhub = this.xsddzhubDao.getXsddzhubByDDH(ddh);
		String state = xsddzhub.getState();
		if(GylConstantKey.XSDDZHUB_STATE_CLOSE.equals(state)){//该销售订单已经完成了
			return "1";
		}else{
			return xsddzhub;
		}
	}
	
	@Transactional
	public void updateXsdd(String item, String textValue,String ddh,Long hh) throws Exception{
		String type = item.split("_")[1];
		String filedKey = item.split("_")[0];//filedKey的值"spmc"
		if("zhu".equals(type)){//要修改的是主表
			Xsddzhub xsddzhub = this.xsddzhubDao.getXsddzhubByDDH(ddh);
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(filedKey, xsddzhub.getClass());
			propertyDescriptor.getWriteMethod().invoke(xsddzhub, textValue);
		}else if("zhi".equals(type)){//要修改的是子表
			Xsddzhib xsddzhib = this.xsddzhibDao.getXsddzhibByCondition(ddh, hh);
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(filedKey, xsddzhib.getClass());
			propertyDescriptor.getWriteMethod().invoke(xsddzhib, textValue);
		}
	}
	
	@Transactional
	public void saveEntry_zhu(Xsddzhub xsddzhub) {
		// TODO Auto-generated method stub
		Set<Xsddzhib>  xsddzhibs =   xsddzhub.getXsddzhibs();
		for(Xsddzhib xsddzhib :  xsddzhibs) {
			KcglQuery kcglQuery = new KcglQuery();
			ProductQuery productQuery = new ProductQuery();
			productQuery.setSpbm(xsddzhib.getSpbm());
			PageResult<Product> products =  productService.getPageResult(productQuery);
			kcglQuery.setPid(products.getRows().get(0).getPid());
			RepositoryQuery repositoryQuery = new RepositoryQuery();
			kcglQuery.setRepository(xsddzhib.getFhck());
	 		PageResult<Kc> kc =  kcglService.getPageResult(kcglQuery);
	 		Kc kc2 = kc.getRows().get(0);
	 		System.out.println(kc2.getCkspbh() + " " + kc2.getQuantity());
	 		
	 		kc2.setQuantity(kc2.getQuantity() - xsddzhib.getSl());
	 		
	 		kcglDao.updateEntry(kc2);
			
		}
		
		xsddzhubDao.saveEntry(xsddzhub);
		
	}
}
