package com.osp.ucenter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.osp.ucenter.persistence.model.UcRole;

public interface UcRoleService {
	int deleteByPrimaryKey(Long id);

	int insert(UcRole ucRole);

	// 插入一条数据,只插入不为null的字段,不会影响有默认值的字段,优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	int insertSelective(UcRole ucRole);
    
	ArrayList<UcRole> selectAllRoles();
	
	UcRole selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UcRole ucRole);

	int updateByPrimaryKey(UcRole ucRole);

	// Pagination<UcRole> findPage(Map<String, Object> resultMap, Integer
	// pageNo, Integer pageSize);

	Map<String, Object> deleteRoleById(String ids);

	// Pagination<RolePermissionAllocationBo>
	// findRoleAndPermissionPage(Map<String, Object> resultMap, Integer pageNo,
	// Integer pageSize);

	// 根据用户ID查询角色（role），放入到Authorization里。
	Set<String> findRoleByUserId(Long userId);

	List<UcRole> findNowAllPermission();

	// 初始化数据
	void initData();
}
