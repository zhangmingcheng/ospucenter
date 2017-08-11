package com.osp.ucenter.service;

import java.util.Map;

import com.osp.ucenter.persistence.model.UcUser;

/**
 * 用户业务接口
 * @author zhangmingcheng
 */
public interface UcUserService {
	
	UcUser login(String username ,String password);
	
	int deleteByPrimaryKey(Long id);

	UcUser insert(UcUser record);

	UcUser insertSelective(UcUser record);

	UcUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UcUser record);

    int updateByPrimaryKey(UcUser record);

    UcUser findUserByEmail(String email);

	Map<String, Object> deleteUserById(String ids);

	Map<String, Object> updateForbidUserById(Long id, Long status);

	Map<String, Object> addRole2User(Long userId, String ids);

	Map<String, Object> deleteRoleByUserIds(String userIds);
}