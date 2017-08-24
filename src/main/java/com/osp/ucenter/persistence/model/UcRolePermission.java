package com.osp.ucenter.persistence.model;

import java.io.Serializable;

public class UcRolePermission implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer roleId;
	private Integer permissionId;

	public UcRolePermission() {
		super();
	}

	public UcRolePermission(Integer roleid, Integer permissionId) {
		this.roleId = roleid;
		this.permissionId = permissionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

}