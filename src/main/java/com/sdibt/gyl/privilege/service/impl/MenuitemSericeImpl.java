package com.sdibt.gyl.privilege.service.impl;

import javax.annotation.Resource;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import com.sdibt.gyl.base.dao.BaseDao;
import com.sdibt.gyl.base.service.impl.BaseServiceImpl;
import com.sdibt.gyl.domain.privilege.Menuitem;
import com.sdibt.gyl.privilege.dao.MenuitemDao;
import com.sdibt.gyl.privilege.service.MenuitemService;

@Service("menuitemService")
public class MenuitemSericeImpl extends BaseServiceImpl<Menuitem> implements MenuitemService{

	@Resource(name="menuitemDao")
	private MenuitemDao menuiteDao;
	
	@Override
	public BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return this.menuiteDao;
	}

}
