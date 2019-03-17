package com.sdibt.gyl.business.xsgl.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.sdibt.gyl.business.xsgl.dao.XsddzhibDao;
import com.sdibt.gyl.business.xsgl.dao.XskpzhibDao;
import com.sdibt.gyl.business.xsgl.dao.XskpzhubDao;
import com.sdibt.gyl.business.xsgl.service.XskpService;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;
import com.sdibt.gyl.domain.business.xsgl.Xskpzhib;
import com.sdibt.gyl.domain.business.xsgl.Xskpzhub;

@Service("xskpService")
public class XskpServiceImpl extends BaseBusinessServiceImpl<Xskpzhub, Xskpzhib> implements XskpService{
	@Resource(name="xskpzhubDao")
	private XskpzhubDao xskpzhubDao;
	@Resource(name="xskpzhibDao")
	private XskpzhibDao xskpzhibDao;
	@Resource(name="xsddzhibDao")
	private XsddzhibDao xsddzhibDao;
	@Override
	public BaseDao<Xskpzhub> getBaseDao_zhu() {
		// TODO Auto-generated method stub
		return this.xskpzhubDao;
	}

	@Override
	public BaseDao<Xskpzhib> getBaseDao_zhi() {
		// TODO Auto-generated method stub
		return this.xskpzhibDao;
	}

	@Transactional
	public void saveXskp(Xskpzhub xskpzhub, List<Xskpzhib> xskpzhibs) {
		/**
		 * 计算发票总金额
		 *    销售发票的子表的每一行的含税金额相加
		 */
		Float totalMoney = 0.0F;
		for (Xskpzhib xskpzhib : xskpzhibs) {
			String ytdjh = xskpzhib.getYtdjh();
			Long ythh = xskpzhib.getYthh();
			Xsddzhib xsddzhib = this.xsddzhibDao.getXsddzhibByCondition(ytdjh, ythh);
			//原来的开票的总和
			Long ljkpsl = xsddzhib.getLjkpsl();
			
			xskpzhib.setLjkpsl(ljkpsl+xskpzhib.getSl());//现在的总和写到销售开票子表中
			xsddzhib.setLjkpsl(ljkpsl+xskpzhib.getSl());//现在的总和写到销售订单子表中
			if(xsddzhib.getLjkpsl().longValue()==xsddzhib.getSl()){//开票的总数量和需要的总数量一致
				xskpzhib.setIskpjs(true);//设置销售开票子表的是否开票关闭为true
				xsddzhib.setIskpgb(true);//设置销售订单子表的是否开票关闭为true
			}
			totalMoney = xskpzhub.getFpzje() + xskpzhib.getHsje();
		}
		
		//把 totalMoney设置到销售开票主表中
		xskpzhub.setFpzje(totalMoney);
		xskpzhub.setXskpzhibs(new HashSet<Xskpzhib>(xskpzhibs));
		this.xskpzhubDao.saveEntry(xskpzhub);
	}	
}
