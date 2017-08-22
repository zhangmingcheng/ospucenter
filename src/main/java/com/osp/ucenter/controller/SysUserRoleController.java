package com.osp.ucenter.controller;

import java.util.HashMap;
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
	 * 用户角色权限分配
	 * 
	 * @param modelMap
	 * @param pageNo
	 * @param findContent
	 * @return
	 */
	@RequestMapping("/allocation")
	@ResponseBody
	public String allocation() {
		// @RequestBody Pagination<UserRoleAllocationBo>  
		ResponseObject ro = ResponseObject.getInstance();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			Pagination<UserRoleAllocationBo> boPage = ucUserService.findUserAndRole(new HashMap<String, Object>(), 1, 4);
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
}
