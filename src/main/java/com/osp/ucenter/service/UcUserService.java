package com.osp.ucenter.service;

import java.util.Map;

import com.osp.ucenter.mybatis.page.Pagination;
import com.osp.ucenter.persistence.model.UcUser;

/**
 * 用户业务接口
 * @author zhangmingcheng
 */
public interface UcUserService {
	
	Pagination<UcUser> findPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
	
	UcUser login(String username ,String password);
	
	int deleteByPrimaryKey(Long id);

	int insert(UcUser record);

	int insertSelective(UcUser record);

	UcUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UcUser record);

    int updateByPrimaryKey(UcUser record);

    UcUser findUser(int userId);

	Map<String, Object> deleteUserById(String ids);

	Map<String, Object> updateForbidUserById(Long id, Long status);

	Map<String, Object> addRole2User(Long userId, String ids);

	Map<String, Object> deleteRoleByUserIds(String userIds);
}
