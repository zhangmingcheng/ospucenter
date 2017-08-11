package com.osp.ucenter.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.osp.ucenter.common.tools.LoggerUtils;
import com.osp.ucenter.persistence.model.UcRole;
import com.osp.ucenter.service.UcRoleService;

/**
 * 用户角色管理
 * 
 * @author zhangmingcheng
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "/role")
public class SysRoleController extends BaseController {

	@Autowired
	UcRoleService ucRoleService;

	/**
	 * 取得所有角色
	 * 
	 * @return
	 */
	public ArrayList<UcRole> getAllRoles() {
		ArrayList<UcRole> ucRoles = null;
		try {
			ucRoles = ucRoleService.selectAllRoles();
		} catch (Exception e) {
			throw e;
		}
		return ucRoles;
	}

	/**
	 * 角色添加 //@RequestMapping(value = "addRole", method = RequestMethod.POST)
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/addRole")
	@ResponseBody
	public Map<String, Object> addRole(HttpServletRequest request, HttpSession session, RedirectAttributes attr) {
		UcRole testUcRole = new UcRole();
		try {
			testUcRole.setRoleName("管理员");
			int count = ucRoleService.insertSelective(testUcRole);
			resultMap.put("status", 200);
			resultMap.put("successCount", count);
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "添加失败，请刷新后再试！");
			LoggerUtils.fmtError(getClass(), e, "添加角色报错。source[%s]", testUcRole.toString());
		}
		return resultMap;
	}
}
