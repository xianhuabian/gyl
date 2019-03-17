package com.sdibt.gyl.basedata.dao.impl;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.basedata.dao.ProductDao;
import com.sdibt.gyl.domain.basedata.Product;

@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao{

}
