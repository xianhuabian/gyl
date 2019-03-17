package com.sdibt.gyl.basedata.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.base.service.impl.BaseServiceImpl;
import com.sdibt.gyl.basedata.dao.RepositoryDao;
import com.sdibt.gyl.basedata.service.RepositoryService;
import com.sdibt.gyl.domain.basedata.Repository;

@Service("repositoryService")
public class RepositoryServiceImpl extends  BaseServiceImpl<Repository> implements RepositoryService{
	@Resource(name="repositoryDao")
	private RepositoryDao repositoryDao;
	@Override
	public BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return this.repositoryDao;
	}	
}

