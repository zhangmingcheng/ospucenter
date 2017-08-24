package com.osp.ucenter.controller;

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
import com.osp.ucenter.persistence.model.UcMenu;
import com.osp.ucenter.service.UcPermissionService;

/**
 * 权限控制类
 * 
 * @author zhangmingcheng
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/permission")
public class SysPermissionController {

	@Autowired
	UcPermissionService ucPermissionService;

	/**
	 * 添加菜单权限
	 * 
	 * @param ucMenu
	 * @return
	 */
	@RequestMapping(value = "/addMenu", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addMenu(@RequestBody UcMenu ucMenu) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			if (ucPermissionService.insertSelectiveMenu(ucMenu) > 0) {
				ro.setOspState(200);
			} else {
				ro.setOspState(500);
			}
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
	 * 删除菜单权限:(menuIds:id以','分割)，规则同删除权限
	 * 
	 * @param menuIds
	 * @return
	 */
	@RequestMapping(value = "/deleteMenu", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteMenu(String menuIds) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			if (ucPermissionService.deleteByMenuIds(menuIds).get("ucRolePermission").equals("操作成功")) {
				ro.setOspState(200);
				ro.setData(ucPermissionService.deleteByMenuIds(menuIds));
			} else {
				ro.setOspState(500);
			}
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
	 * 删除权限，根据ID，但是删除权限的时候，需要查询是否有赋予给角色，如果有角色在使用，那么就不能删除。注意同时维护操作表，菜单表。
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deletePermission", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deletePermissionById(String permissionIds) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			if (ucPermissionService.deleteByMenuIds(permissionIds).get("ucRolePermission").equals("操作成功")) {
				ro.setOspState(200);
				ro.setData(ucPermissionService.deleteByMenuIds(permissionIds));
			} else {
				ro.setOspState(500);
			}
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
