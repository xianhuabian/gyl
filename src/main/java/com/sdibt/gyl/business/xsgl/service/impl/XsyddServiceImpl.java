package com.sdibt.gyl.business.xsgl.service.impl;

import java.beans.PropertyDescriptor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.sdibt.gyl.business.xsgl.dao.XsyddzhibDao;
import com.sdibt.gyl.business.xsgl.dao.XsyddzhubDao;
import com.sdibt.gyl.business.xsgl.service.XsyddService;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsyddzhub;
import com.sdibt.gyl.utils.GylConstantKey;

@Service("xsyddService")
public class XsyddServiceImpl extends BaseBusinessServiceImpl<Xsyddzhub, Xsyddzhib> implements XsyddService{
	@Resource(name="xsyddzhubDao")
	private XsyddzhubDao xsyddzhubDao;
	@Resource(name="xsyddzhibDao")
	private XsyddzhibDao xsyddzhibDao;
	@Override
	public BaseDao<Xsyddzhub> getBaseDao_zhu() {
		// TODO Auto-generated method stub
		return this.xsyddzhubDao;
	}
	@Override
	public BaseDao<Xsyddzhib> getBaseDao_zhi() {
		// TODO Auto-generated method stub
		return this.xsyddzhibDao;
	}
	
	@Override
	public Object getXsyddByDDH(String ddh) {
		Xsyddzhub xsyddzhub = this.xsyddzhubDao.getXsyddzhubByDDH(ddh);
		String state = xsyddzhub.getState();
		if(GylConstantKey.XSDDZHUB_STATE_CLOSE.equals(state)){//该销售订单已经完成了
			return "1";
		}else{
			return xsyddzhub;
		}
	}
	
	@Transactional
	public void updateXsydd(String item, String textValue,String ddh,Long hh) throws Exception{
		String type = item.split("_")[1];
		String filedKey = item.split("_")[0];//filedKey的值"spmc"
		if("zhu".equals(type)){//要修改的是主表
			Xsyddzhub xsyddzhub = this.xsyddzhubDao.getXsyddzhubByDDH(ddh);
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(filedKey, xsyddzhub.getClass());
			propertyDescriptor.getWriteMethod().invoke(xsyddzhub, textValue);
		}else if("zhi".equals(type)){//要修改的是子表
			Xsyddzhib xsyddzhib = this.xsyddzhibDao.getXsyddzhibByCondition(ddh, hh);
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(filedKey, xsyddzhib.getClass());
			propertyDescriptor.getWriteMethod().invoke(xsyddzhib, textValue);
		}
	}
}
