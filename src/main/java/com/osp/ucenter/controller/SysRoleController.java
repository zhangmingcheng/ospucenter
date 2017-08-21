package com.osp.ucenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.osp.ucenter.common.utils.LoggerUtils;
import com.osp.ucenter.mybatis.page.Pagination;
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
	 * 角色列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	@ResponseBody
	public Map<String, Object> index(String findContent) {
		try {
			Map<String,Object> modelMap = new HashMap<String,Object>();
			modelMap.put("findContent", findContent);
			Pagination<UcRole> role = ucRoleService.findPage(modelMap, pageNo, pageSize);
			//role.setTotalCount(ucRoleService.findCount());
			System.out.println("总共页数======"+role.getTotalPage());
			for (UcRole ucRole : role.getList()) {
				System.out.println("角色信息====" + ucRole.getRoleName());
			}
			resultMap.put("status", 200);
		} catch (Exception e) {

		}
		return resultMap;
	}

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
			resultMap.put("status", 500);
			resultMap.put("message", "获取当前页的角色列表失败，请刷新后再试！");
			LoggerUtils.fmtError(getClass(), e, "获取当前页的角色列表失败。source[%s]", ucRoles.toString());
		}
		return ucRoles;
	}

	/**
	 * 角色添加 @RequestMapping(value = "addRole", method = RequestMethod.POST)
	 * 规则：角色名和系统编号不能都相同
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/addRole")
	@ResponseBody
	public Map<String, Object> addRole(HttpServletRequest request, RedirectAttributes attr) {
		// String roleName = RequestUtil.getString(request, "roleName");
		UcRole testUcRole = new UcRole();
		testUcRole.setRoleName("普通用户");
		try {
			ArrayList<UcRole> ucRoles = ucRoleService.selectAllRoles();
			for (UcRole ucRole : ucRoles) {
				if (ucRole.getRoleName().equals(testUcRole.getRoleName())) {
					resultMap.put("status", 500);
					resultMap.put("message", "此角色名称已存在！");
					return resultMap;
				}
			}
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

	/**
	 * 删除角色，根据ID，系统管理员不能删除。
	 * 
	 * @RequestMapping(value="/deleteRole",method=RequestMethod.POST) @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteRole", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteRoleById(String ids) {
		System.out.println(SecurityUtils.getSubject().getPrincipal());
		return ucRoleService.deleteRoleById("1");
	}

	/**
	 * 我的权限
	 * 
	 * @return
	 */
	// @RequestMapping(value="getPermissionTree",method=RequestMethod.POST)
	// @ResponseBody
	// public List<Map<String, Object>> getPermissionTree(){
	// 查询我所有的角色 ---> 权限
	// List<UcRole> roles = ucRoleService.findNowAllPermission();
	// 把查询出来的roles 转换成bootstarp 的 tree数据
	// List<Map<String, Object>> data = UserManager.toTreeData(roles);
	// return data;
	// }
}
