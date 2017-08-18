package com.osp.ucenter.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osp.ucenter.mybatis.BaseMybatisDao;
import com.osp.ucenter.persistence.dao.UcPermissionMapper;
import com.osp.ucenter.persistence.model.UcPermission;
import com.osp.ucenter.service.UcPermissionService;
/**
 * 权限管理
 * @author zhangmingcheng
 */
@Service
public class UcPermissionServiceImpl extends BaseMybatisDao<UcPermissionMapper> implements UcPermissionService{
 
	@Autowired
	UcPermissionMapper ucPermissionMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UcPermission insert(UcPermission record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UcPermission insertSelective(UcPermission record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UcPermission selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(UcPermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UcPermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> deletePermissionById(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UcPermission> selectPermissionById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> addPermission2Role(Long roleId, String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> deleteByRids(String roleIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> findPermissionByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
