package com.osp.ucenter.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.osp.ucenter.persistence.model.UcPermission;

public interface UcPermissionService {
	int deleteByPrimaryKey(Long id);

	UcPermission insert(UcPermission record);

    UcPermission insertSelective(UcPermission record);

    UcPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UcPermission record);

    int updateByPrimaryKey(UcPermission record);

	Map<String, Object> deletePermissionById(String ids);

	List<UcPermission> selectPermissionById(Long id);

	Map<String, Object> addPermission2Role(Long roleId,String ids);

	Map<String, Object> deleteByRids(String roleIds);
	//根据用户ID查询权限（permission），放入到Authorization里。
	Set<String> findPermissionByUserId(Long userId);
}
