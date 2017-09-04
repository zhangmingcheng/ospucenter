package com.osp.ucenter.persistence.dao;

import java.util.List;

import com.osp.ucenter.persistence.bo.UcPermissionBo;
import com.osp.ucenter.persistence.bo.UcPermissionMenuActionBo;
import com.osp.ucenter.persistence.model.UcPermission;

public interface UcPermissionMapper {
   
    int deleteByPrimaryKey(Integer permissionId);

    int insert(UcPermission record);
  
    int insertSelective(UcPermission record);

    UcPermission selectByPrimaryKey(Integer permissionId);
    
    int selectByMenuId(Integer menuId);

    int updateByPrimaryKeySelective(UcPermission record);

    int updateByPrimaryKey(UcPermission record);
    
    List<UcPermissionMenuActionBo> selectPermissions();
    
    List<UcPermissionBo> selectPermissionByRoleId(Integer id);
    
    
}