package com.sdibt.gyl.basedata.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.base.service.impl.BaseServiceImpl;
import com.sdibt.gyl.basedata.dao.SupplierDao;
import com.sdibt.gyl.basedata.service.SupplierService;
import com.sdibt.gyl.domain.basedata.Supplier;

@Service("supplierService")
public class SupplierServiceImpl extends  BaseServiceImpl<Supplier> implements SupplierService{
	@Resource(name="supplierDao")
	private SupplierDao supplierDao;
	@Override
	public BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return this.supplierDao;
	}	
}

