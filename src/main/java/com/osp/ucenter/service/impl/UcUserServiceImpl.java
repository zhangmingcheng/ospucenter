package com.osp.ucenter.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osp.ucenter.persistence.dao.UcUserMapper;
import com.osp.ucenter.persistence.model.UcUser;
import com.osp.ucenter.service.UcUserService;
/**
 * 用户业务实现类
 * @author zhangmingcheng
 */
@Service
public class UcUserServiceImpl  implements UcUserService{
	
	@Autowired
    private UcUserMapper ucUserMapper;
   
	@Override
	public UcUser login(String username, String password) {
		return ucUserMapper.login(username,password);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UcUser insert(UcUser record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UcUser insertSelective(UcUser record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UcUser selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(UcUser ucUser) {
		ucUserMapper.updateByPrimaryKeySelective(ucUser);
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UcUser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UcUser findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> deleteUserById(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateForbidUserById(Long id, Long status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> addRole2User(Long userId, String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> deleteRoleByUserIds(String userIds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UcUserMapper getUcUserMapper() {
		return ucUserMapper;
	}

	public void setUcUserMapper(UcUserMapper ucUserMapper) {
		this.ucUserMapper = ucUserMapper;
	}


}