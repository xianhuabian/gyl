package com.sdibt.gyl.privilege.dao.impl;

import org.springframework.stereotype.Repository;

import com.sdibt.gyl.base.dao.impl.BaseDaoImpl;
import com.sdibt.gyl.domain.privilege.Menuitem;
import com.sdibt.gyl.privilege.dao.MenuitemDao;

@Repository("menuitemDao")
public class MenuitemDaoImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao{

}
