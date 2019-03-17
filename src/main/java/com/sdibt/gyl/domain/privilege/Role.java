package com.sdibt.gyl.domain.privilege;

import java.io.Serializable;
import java.util.Set;

import com.sdibt.gyl.domain.basedata.User;

public class Role implements Serializable{
	private Long rid;
	private Long pid;
	private String name;
	private Boolean isParent;//是否为父节点
	private Set<User> users;
	private Set<Privilege> privileges;
	private Boolean checked;//设置是否被选中
	
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
