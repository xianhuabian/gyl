package com.sdibt.gyl.business.xsgl.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.sdibt.gyl.business.xsgl.dao.XsckdzhibDao;
import com.sdibt.gyl.business.xsgl.dao.XsckdzhubDao;
import com.sdibt.gyl.business.xsgl.dao.XsddzhibDao;
import com.sdibt.gyl.business.xsgl.service.XsckdService;
import com.sdibt.gyl.domain.business.xsgl.Xsckdzhib;
import com.sdibt.gyl.domain.business.xsgl.Xsckdzhub;
import com.sdibt.gyl.domain.business.xsgl.Xsddzhib;

@Service("xsckdService")
public class XsckdServiceImpl extends BaseBusinessServiceImpl<Xsckdzhub, Xsckdzhib> implements XsckdService{
	@Resource(name="xsckdzhubDao")
	private XsckdzhubDao xsckdzhubDao;
	@Resource(name="xsckdzhibDao")
	private XsckdzhibDao xsckdzhibDao;
	@Resource(name="xsddzhibDao")
	private XsddzhibDao xsddzhibDao;
	@Override
	public BaseDao<Xsckdzhub> getBaseDao_zhu() {
		// TODO Auto-generated method stub
		return this.xsckdzhubDao;
	}

	@Override
	public BaseDao<Xsckdzhib> getBaseDao_zhi() {
		// TODO Auto-generated method stub
		return this.xsckdzhibDao;
	}

	@Transactional
	public void saveXsckd(Xsckdzhub xsckdzhub, List<Xsckdzhib> xsckdzhibs) {
		/**
		 * 遍历每一个销售出库单子表表单的数据(该数据来自于页面)
		 */
		for (Xsckdzhib xsckdzhib : xsckdzhibs) {
			String ytdjh = xsckdzhib.getYtdjh();
			Long ythh = xsckdzhib.getYthh();
			/**
			 * 根据源头单据号和源头行号从销售订单的子表中查找
			 */
			Xsddzhib xsddzhib = this.xsddzhibDao.getXsddzhibByCondition(ytdjh, ythh);
			//到目前为止总的出库数量
			Long ljcksl = xsddzhib.getLjcksl();
			//把现在的出库数量和原来的出库的总的数量相加，回写到销售订单的子表中
			xsddzhib.setLjcksl(ljcksl+xsckdzhib.getSfsl());//xsckdzhib.getSfsl()为实际出库数量
			//把新的总的数量加到xsckdzhib的累计出库数量中
			xsckdzhib.setLjcksl(ljcksl+xsckdzhib.getSfsl());
			/**
			 * 判断是否出库关闭
			 * xsddzhib.getLjcksl() 累计出库的数量
			 * xsddzhib.getSl()是需要出库的数量
			 */
			if(xsddzhib.getLjcksl().longValue()==xsddzhib.getSl().longValue()){
				xsddzhib.setIsckgb(true);//把销售订单子表的是否出库关闭设置为true
				xsckdzhib.setIsckgb(true);//把销售出库单子表的是否出库关闭设置为true
			}
		}
		xsckdzhub.setXsckdzhibs(new HashSet<Xsckdzhib>(xsckdzhibs));
		this.xsckdzhubDao.saveEntry(xsckdzhub);
	}	
}
