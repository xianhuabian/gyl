package com.sdibt.gyl.base.action;

import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private Class classt;
	private T t;

	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.classt = (Class) type.getActualTypeArguments()[0];
		try {
			this.t = (T) classt.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return this.t;
	}
	
	private int currentPage;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	private String checkedStr;
	
	private Long[] ids;
	
	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public String getCheckedStr() {
		return checkedStr;
	}

	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}

	public static final String ADDUI = "addUI";// 跳转到添加页面的字符串

	public String addUI = ADDUI;

	public static final String UPDATEUI = "updateUI";// 跳转到修改页面的字符串

	public String updateUI = UPDATEUI;

	public static final String LISTACTION = "listAction";// 跳转到列表页面的字符串

	public String listAction = LISTACTION;

	public static final String ACTION2ACTION = "action2action";// action跳转到action

	public String action2action = ACTION2ACTION;

	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

}
