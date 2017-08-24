package com.osp.ucenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osp.common.json.JsonUtil;
import com.osp.ucenter.common.exception.MyRuntimeException;
import com.osp.ucenter.common.model.ResponseObject;
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
	@RequestMapping(value = "/allocationLists",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String allocationLists(@RequestBody Pagination<UserRoleAllocationBo> pagination) {
		ResponseObject ro = ResponseObject.getInstance();
		Map<String,Object> findContent = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			findContent.put("findContent", pagination.getFindContent());
			Pagination<UserRoleAllocationBo> boPage = ucUserService.findUserAndRole(findContent, pagination.getFilterNo(),pagination.getPageSize());
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
	@RequestMapping(value = "/addRole2User",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addRole2User(Integer userId, String ids) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			Map<String, Object> data = ucUserService.addRole2User(userId,ids);
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
	@RequestMapping(value = "/selectRoleByUserId",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String selectRoleByUserId(Integer id) {
		ResponseObject ro = ResponseObject.getInstance();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			List<UcRoleBo> ucRoleBos = ucUserService.selectRoleByUserId(id);
			List<UcRoleBo> dataRoleBos = new ArrayList<>();
			for (UcRoleBo ucRoleBo : ucRoleBos) {
				if (ucRoleBo.isCheck() == true) {
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
	 * 根据用户id清空其所属角色。 用户ID 以','分隔
	 * 
	 * @param userIds
	 * 
	 * @return
	 */
	@RequestMapping(value = "clearRoleByUserIds",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String clearRoleByUserIds(String userIds) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			ro.setOspState(200);
			ro.setData(ucUserService.deleteRoleByUserIds(userIds.trim()));
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
}
