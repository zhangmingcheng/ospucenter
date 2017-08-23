package com.osp.ucenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osp.ucenter.common.utils.StringUtils;
import com.osp.ucenter.mybatis.BaseMybatisDao;
import com.osp.ucenter.mybatis.page.Pagination;
import com.osp.ucenter.persistence.bo.UcRoleBo;
import com.osp.ucenter.persistence.bo.UserRoleAllocationBo;
import com.osp.ucenter.persistence.dao.UcUserMapper;
import com.osp.ucenter.persistence.dao.UcUserRoleMapper;
import com.osp.ucenter.persistence.model.UcUser;
import com.osp.ucenter.persistence.model.UcUserRole;
import com.osp.ucenter.service.UcUserService;

/**
 * 用户业务实现类
 * 
 * @author zhangmingcheng
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class UcUserServiceImpl extends BaseMybatisDao<UcUserMapper> implements UcUserService {

	@Autowired
	private UcUserMapper ucUserMapper;

	@Autowired
	private UcUserRoleMapper ucUserRoleMapper;

	@Override
	public Pagination<UserRoleAllocationBo> findUserAndRole(Map<String, Object> modelMap, Integer pageNo,
			Integer pageSize) {
		return super.findPage("findUserAndRole", "findCount", modelMap, pageNo, pageSize);
	}

	@Override
	public Pagination<UcUser> findPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}

	@Override
	public UcUser login(String username, String password) {
		return ucUserMapper.login(username, password);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(UcUser record) {
		return ucUserMapper.insert(record);
	}

	@Override
	public int insertSelective(UcUser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UcUser selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(UcUser ucUser) {
		return ucUserMapper.updateByPrimaryKeySelective(ucUser);
	}

	@Override
	public int updateByPrimaryKey(UcUser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UcUser findUser(int userId) {
		return ucUserMapper.findUser(userId);
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
	public Map<String, Object> addRole2User(Integer userId, String ids) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			// 先删除原有的。
			ucUserRoleMapper.deleteByUserId(userId);
			// 如果ids(role的id)有值，那么就添加。没值象征着：把这个用户（userId）所有角色取消。
			if (StringUtils.isNotBlank(ids)) {
				String[] idArray = null;

				if (StringUtils.contains(ids, ",")) {
					idArray = ids.split(",");
				} else {
					idArray = new String[] { ids };
				}
				// 添加新的。
				for (String rid : idArray) {
					if (StringUtils.isNotBlank(rid)) {
						UcUserRole ucUserRole = new UcUserRole(userId, new Integer(rid));
						ucUserRoleMapper.insertSelective(ucUserRole);
					}
				}
			}
			data.put("ucUserRole", "维护用户角色关系成功");
			return data;
		} catch (Exception e) {
			data.put("ucUserRole", "操作失败，请重试！");
			e.printStackTrace();
		}
		// 清空用户的权限，迫使再次获取权限的时候，得重新加载
		// TokenManager.clearUserAuthByUserId(userId);
		return data;
	}

	/**
	 * 根据用户ID删除其绑定的角色信息
	 */
	@Override
	public Map<String, Object> deleteRoleByUserIds(String userIds) {
		Map<String, Object> data = new HashMap<String, Object>();
		String[] idArray = null;
		try {
			if (StringUtils.isNotBlank(userIds)) {
				if (StringUtils.contains(userIds, ",")) {
					idArray = userIds.split(",");
				} else {
					idArray = new String[] { userIds };
				}
			}
			ucUserRoleMapper.deleteRoleByUserIds(idArray);
			data.put("ucUserRole", "操作成功");
		} catch (Exception e) {
			data.put("ucUserRole", "操作失败，请重试！");
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public List<UcRoleBo> selectRoleByUserId(Integer id) {
		return ucUserMapper.selectRoleByUserId(id);
	}

}
