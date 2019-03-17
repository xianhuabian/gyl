package com.sdibt.gyl.business.cggl.service.impl;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.basedata.service.ProductService;
import com.sdibt.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.sdibt.gyl.business.cggl.dao.CgrkdzhibDao;
import com.sdibt.gyl.business.cggl.dao.CgrkdzhubDao;
import com.sdibt.gyl.business.cggl.service.CgrkdService;
import com.sdibt.gyl.business.kcgl.service.KcglService;
import com.sdibt.gyl.domain.basedata.Product;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhib;
import com.sdibt.gyl.domain.business.cggl.Cgrkdzhub;
import com.sdibt.gyl.domain.business.kcgl.Kc;
import com.sdibt.gyl.query.basedata.ProductQuery;
import com.sdibt.gyl.query.business.kcgl.KcglQuery;

@Service("cgrkdService")
public class CgrkdServiceImpl extends BaseBusinessServiceImpl<Cgrkdzhub, Cgrkdzhib> implements CgrkdService{
	@Resource(name="cgrkdzhubDao")
	private CgrkdzhubDao cgrkdzhubDao;
	@Resource(name="cgrkdzhibDao")
	private CgrkdzhibDao cgrkdzhibDao;
	@Resource(name="kcglService")
	private KcglService kcglService;
	@Resource(name="productService")
	private ProductService productService;
	@Override
	public BaseDao<Cgrkdzhub> getBaseDao_zhu() {
		// TODO Auto-generated method stub
		return this.cgrkdzhubDao;
	}
	@Override
	public BaseDao<Cgrkdzhib> getBaseDao_zhi() {
		// TODO Auto-generated method stub
		return this.cgrkdzhibDao;
	}
	
	@Override
	public Object getCgrkdByDDH(String ddh) {
		Cgrkdzhub cgrkdzhub = this.cgrkdzhubDao.getCgrkdzhubByDDH(ddh);
		/*String state = 
		if(GylConstantKey.XSDDZHUB_STATE_CLOSE.equals(state)){//该销售订单已经完成了
			return "1";
		}else{
			return cgrkdzhub;
		}*/
		return cgrkdzhub;
	}
	
	@Transactional
	public void updateCgrkd(String item, String textValue,String ddh,Long hh) throws Exception{
		String type = item.split("_")[1];
		String filedKey = item.split("_")[0];//filedKey的值"spmc"
		if("zhu".equals(type)){//要修改的是主表
			Cgrkdzhub cgrkdzhub = this.cgrkdzhubDao.getCgrkdzhubByDDH(ddh);
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(filedKey, cgrkdzhub.getClass());
			propertyDescriptor.getWriteMethod().invoke(cgrkdzhub, textValue);
		}else if("zhi".equals(type)){//要修改的是子表
			Cgrkdzhib cgrkdzhib = this.cgrkdzhibDao.getCgrkdzhibByCondition(ddh, hh);
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(filedKey, cgrkdzhib.getClass());
			propertyDescriptor.getWriteMethod().invoke(cgrkdzhib, textValue);
		}
	}
	
	@Transactional
	public void saveEntry_zhu(Cgrkdzhub cgrkdzhub){
		this.getBaseDao_zhu().saveEntry(cgrkdzhub);
		Set<Cgrkdzhib> cgrkdzhibs = cgrkdzhub.getCgrkdzhibs();
		for(Cgrkdzhib cgrkdzhib : cgrkdzhibs) {
			Long count = cgrkdzhib.getSl();
			System.out.println(count);
			KcglQuery kcglQuery = new KcglQuery();
			kcglQuery.setCkspbh(cgrkdzhib.getCkbh());
			ProductQuery productQuery = new ProductQuery();
			productQuery.setSpbm(cgrkdzhib.getSpbm());
			productQuery.buildWhere();
			kcglQuery.setPid(productService.getPageResult(productQuery).getRows().get(0).getPid());
			kcglQuery.buildWhere();
			Kc kc = kcglService.getPageResult(kcglQuery).getRows().get(0);
			System.out.println(kc.getQuantity());
			kc.setQuantity(kc.getQuantity() + count);
			kcglService.updateEntry(kc);
		}
	}
}
