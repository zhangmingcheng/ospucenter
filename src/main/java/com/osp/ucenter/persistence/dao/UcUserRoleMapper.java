package com.osp.ucenter.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.osp.ucenter.persistence.model.UcUserRole;

public interface UcUserRoleMapper {

	int insert(UcUserRole record);

	int insertSelective(UcUserRole record);

	int deleteByUserId(@Param("id") Integer id);

	int deleteRoleByUserIds(Map<String, Object> resultMap);

	List<Integer> findUserIdByRoleId(Integer id);
}