package com.sdibt.gyl.basedata.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.base.service.impl.BaseServiceImpl;
import com.sdibt.gyl.basedata.dao.ProductDao;
import com.sdibt.gyl.basedata.service.ProductService;
import com.sdibt.gyl.domain.basedata.Product;
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{
	@Resource(name="productDao")
	private ProductDao productDao;
	@Override
	public BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return this.productDao;
	}

}
