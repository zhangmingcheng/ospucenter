package com.osp.ucenter.persistence.dao;

import com.osp.ucenter.persistence.model.UcMenu;

public interface UcMenuMapper {
	
	int deleteByPrimaryKey(Integer menuId);
	
	int insert(UcMenu record);
	
	int insertSelective(UcMenu record);
	
	UcMenu selectByPrimaryKey(Integer menuId);
	
	int updateByPrimaryKeySelective(UcMenu record);

	int updateByPrimaryKey(UcMenu record);
}