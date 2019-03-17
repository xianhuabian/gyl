package com.sdibt.gyl.business.cggl.service.impl;

import java.beans.PropertyDescriptor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.sdibt.gyl.business.cggl.dao.CgddzhibDao;
import com.sdibt.gyl.business.cggl.dao.CgddzhubDao;
import com.sdibt.gyl.business.cggl.service.CgddService;
import com.sdibt.gyl.business.xsgl.service.XsddService;
import com.sdibt.gyl.domain.business.cggl.Cgddzhib;
import com.sdibt.gyl.domain.business.cggl.Cgddzhub;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.utils.GylConstantKey;

@Service("cgddService")
public class CgddServiceImpl extends BaseBusinessServiceImpl<Cgddzhub, Cgddzhib> implements CgddService{
	@Resource(name="cgddzhubDao")
	private CgddzhubDao cgddzhubDao;
	@Resource(name="cgddzhibDao")
	private CgddzhibDao cgddzhibDao;
	@Override
	public BaseDao<Cgddzhub> getBaseDao_zhu() {
		// TODO Auto-generated method stub
		return this.cgddzhubDao;
	}
	@Override
	public BaseDao<Cgddzhib> getBaseDao_zhi() {
		// TODO Auto-generated method stub
		return this.cgddzhibDao;
	}
	
	@Override
	public Object getCgddByDDH(String ddh) {
		Cgddzhub cgddzhub = this.cgddzhubDao.getCgddzhubByDDH(ddh);
		String state = cgddzhub.getStatus();
		if(GylConstantKey.XSDDZHUB_STATE_CLOSE.equals(state)){//该销售订单已经完成了
			return "1";
		}else{
			return cgddzhub;
		}
	}
	
	@Transactional
	public void updateCgdd(String item, String textValue,String ddh,Long hh) throws Exception{
		String type = item.split("_")[1];
		String filedKey = item.split("_")[0];//filedKey的值"spmc"
		if("zhu".equals(type)){//要修改的是主表
			Cgddzhub cgddzhub = this.cgddzhubDao.getCgddzhubByDDH(ddh);
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(filedKey, cgddzhub.getClass());
			propertyDescriptor.getWriteMethod().invoke(cgddzhub, textValue);
		}else if("zhi".equals(type)){//要修改的是子表
			Cgddzhib cgddzhib = this.cgddzhibDao.getCgddzhibByCondition(ddh, hh);
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(filedKey, cgddzhib.getClass());
			propertyDescriptor.getWriteMethod().invoke(cgddzhib, textValue);
		}
	}
}
