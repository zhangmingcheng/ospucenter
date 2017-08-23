package com.osp.ucenter.service;

import java.util.List;
import java.util.Map;

import com.osp.ucenter.mybatis.page.Pagination;
import com.osp.ucenter.persistence.bo.UcRoleBo;
import com.osp.ucenter.persistence.bo.UserRoleAllocationBo;
import com.osp.ucenter.persistence.model.UcUser;

/**
 * 用户业务接口
 * @author zhangmingcheng
 */
public interface UcUserService {
	
	Pagination<UserRoleAllocationBo> findUserAndRole(Map modelMap,
			Integer pageNo, Integer pageSize);
	
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
	
	List<UcRoleBo> selectRoleByUserId(Integer id);

	Map<String, Object> addRole2User(Integer userId, String ids);

	Map<String, Object> deleteRoleByUserIds(String userIds);
	
}
