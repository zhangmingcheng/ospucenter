package com.osp.ucenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osp.ucenter.common.exception.MyRuntimeException;
import com.osp.ucenter.common.model.ResponseObject;
import com.osp.ucenter.common.utils.JsonUtil;
import com.osp.ucenter.mybatis.page.Pagination;
import com.osp.ucenter.persistence.bo.UcRoleBo;
import com.osp.ucenter.persistence.bo.UserRoleAllocationBo;
import com.osp.ucenter.service.UcPermissionService;
import com.osp.ucenter.service.UcUserService;

@Controller
@Scope(value = "prototype")
@RequestMapping("/userRole")
public class SysUserRoleController {
	@Autowired
	UcUserService ucUserService;
	@Autowired
	UcPermissionService ucPermissionService;

	/**
	 * 用户角色关系列表
	 * 
	 * @param modelMap
	 * @param pageNo
	 * @param findContent
	 * @return
	 */
	@RequestMapping("/allocationLists")
	@ResponseBody
	public String allocationLists() {
		// @RequestBody Pagination<UserRoleAllocationBo>
		ResponseObject ro = ResponseObject.getInstance();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			Pagination<UserRoleAllocationBo> boPage = ucUserService.findUserAndRole(new HashMap<String, Object>(), 1,
					4);
			data.put("ucUserRole", boPage.getList());
			ro.setOspState(200);
			ro.setData(data);
			return JsonUtil.beanToJson(ro);
		} catch (MyRuntimeException e) {
			ro.setOspState(400);
			return JsonUtil.beanToJson(ro);
		} catch (Exception e) {
			ro.setOspState(402);
			e.printStackTrace();
			return JsonUtil.beanToJson(ro);
		}
	}

	/**
	 * 操作用户的角色
	 * 
	 * @param userId
	 *            用户ID
	 * @param ids
	 *            角色ID，以‘,’间隔
	 * @return
	 */
	@RequestMapping("/addRole2User")
	@ResponseBody
	public String addRole2User(Integer userId, String ids) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			Map<String, Object> data = ucUserService.addRole2User(2, "");
			ro.setOspState(200);
			ro.setData(data);
			return JsonUtil.beanToJson(ro);
		} catch (MyRuntimeException e) {
			ro.setOspState(400);
			return JsonUtil.beanToJson(ro);
		} catch (Exception e) {
			ro.setOspState(402);
			e.printStackTrace();
			return JsonUtil.beanToJson(ro);
		}
	}

	/**
	 * 根据用户ID查询角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectRoleByUserId")
	@ResponseBody
	public String selectRoleByUserId(Integer id) {
		ResponseObject ro = ResponseObject.getInstance();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			List<UcRoleBo> ucRoleBos = ucUserService.selectRoleByUserId(1);
			List<UcRoleBo> dataRoleBos = new ArrayList<>();
			for(UcRoleBo ucRoleBo : ucRoleBos){
				if(ucRoleBo.isCheck()==true){
					dataRoleBos.add(ucRoleBo);
				}
			}
			ro.setOspState(200);
			data.put("ucUserRole", dataRoleBos);
			ro.setData(data);
			return JsonUtil.beanToJson(ro);
		} catch (MyRuntimeException e) {
			ro.setOspState(400);
			return JsonUtil.beanToJson(ro);
		} catch (Exception e) {
			ro.setOspState(402);
			e.printStackTrace();
			return JsonUtil.beanToJson(ro);
		}
	}

	/**
	 * 根据用户id清空角色。
	 * 
	 * @param userIds
	 *            用户ID ，以‘,’间隔
	 * @return
	 */
	@RequestMapping(value = "clearRoleByUserIds")
	@ResponseBody
	public String clearRoleByUserIds(String userIds) {
		// return userService.deleteRoleByUserIds(userIds);
		return "";
	}
}
