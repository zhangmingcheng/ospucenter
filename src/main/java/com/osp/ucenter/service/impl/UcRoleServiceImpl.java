package com.osp.ucenter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osp.ucenter.persistence.dao.UcRoleMapper;
import com.osp.ucenter.persistence.model.UcRole;
import com.osp.ucenter.service.UcRoleService;

/**
 * 角色业务类
 * @author zhangmingcheng
 */
@Service
public class UcRoleServiceImpl implements UcRoleService {

	@Autowired
	UcRoleMapper ucRoleMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(UcRole ucRole) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(UcRole ucRole) {
		return ucRoleMapper.insertSelective(ucRole);
	}

	@Override
	public UcRole selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(UcRole ucRole) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UcRole ucRole) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> deleteRoleById(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> findRoleByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UcRole> findNowAllPermission() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<UcRole> selectAllRoles() {
		return ucRoleMapper.selectAllRoles();		
	}

}
