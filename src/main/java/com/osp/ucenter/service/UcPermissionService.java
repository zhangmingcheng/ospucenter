package com.osp.ucenter.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.osp.ucenter.persistence.bo.UcPermissionBo;
import com.osp.ucenter.persistence.model.UcMenu;
import com.osp.ucenter.persistence.model.UcPermission;

public interface UcPermissionService {
	int deleteByPrimaryKey(Integer id);

	UcPermission insert(UcPermission record);

	Integer insertSelectiveMenu(UcMenu record);
	
	Map<String, Object> deleteByMenuIds(String menuIds);

    UcPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UcPermission record);

    int updateByPrimaryKey(UcPermission record);

	Map<String, Object> deletePermissionById(String ids);
	
	List<UcPermissionBo> selectPermissionByRoleId(Integer id);
	
	List<UcPermission> selectPermissionById(Integer id);

	Map<String, Object> addPermission2Role(Integer roleId,String ids);

	Map<String, Object> deleteByRids(String roleIds);
	//根据用户ID查询权限（permission），放入到Authorization里。
	Set<String> findPermissionByUserId(Integer userId);
}
