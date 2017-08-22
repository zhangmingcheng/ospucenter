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
import org.springframework.web.bind.annotation.ResponseBody;

import com.osp.ucenter.common.exception.MyRuntimeException;
import com.osp.ucenter.common.model.ResponseObject;
import com.osp.ucenter.common.utils.JsonUtil;
import com.osp.ucenter.jwt.TokenAuth;
import com.osp.ucenter.manager.UserManager;
import com.osp.ucenter.mybatis.page.Pagination;
import com.osp.ucenter.persistence.model.UcUser;
import com.osp.ucenter.service.UcUserService;

/**
 * 用户管理
 * 
 * @author zhangmingcheng
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/user")
public class SysUserController {
	@Autowired
	UcUserService ucUserService;

	/**
	 * 用户列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userLists")
	public String userLists() {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			//@RequestBody Pagination<UcUser> ucUser
			// Pagination<UcUser> ucUsers = ucUserService.findPage(new
			// HashMap<String, Object>(), ucUser.getPageNo(),
			// ucUser.getPageSize());
			Pagination<UcUser> ucUsers = ucUserService.findPage(new HashMap<String, Object>(), 1, 10);
			for(UcUser tempUcUser : ucUsers.getList()){
				tempUcUser.setUserPwd("");
			}
			System.out.println("总共页数======" + ucUsers.getTotalPage());
			for (UcUser user : ucUsers.getList()) {
				System.out.println("用户信息====" + user.getUserName());
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("ucUser", ucUsers.getList());
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
	 * 在线用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/onlineUsers")
	public String onlineUsers() {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			//@RequestBody Pagination<UcUser> ucUser
			Pagination<UcUser> ucUsers = new Pagination<UcUser>();
			ucUsers.setTotalCount(TokenAuth.jwtTokens.size());
			int start = (ucUsers.getPageNo()-1)*ucUsers.getPageSize();
			int end = start +ucUsers.getPageSize();
			int i=0;
			List<UcUser> lists = new ArrayList<>();
		    for (String jwtToken : TokenAuth.jwtTokens.keySet()) {
		    	if(i>=start&&i<end){
		    		lists.add(TokenAuth.jwtTokens.get(jwtToken));
		    	}    	
		    }
		    ucUsers.setList(lists);
			System.out.println("总共页数======" + ucUsers.getTotalPage());
			for (UcUser user : ucUsers.getList()) {
				System.out.println("在线用户信息====" + user.getUserName());
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("ucUser", ucUsers.getList());
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
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserInfo")
	public String updateUserInfo(@RequestBody UcUser user) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			int status = ucUserService.updateByPrimaryKeySelective(user);
			if (status > 0) {
				UcUser ucUser = ucUserService.findUser(user.getUserId());
				TokenAuth.updateUser(user.getUserId(), ucUser);// 用户信息修改，维护全局map
			}
			ro.setOspState(200);
			Map<String, Object> data = new HashMap<String, Object>();
			UcUser ucUser = TokenAuth.getUser(user.getUserId());
			data.put("ucUser", ucUser);
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
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserPsw")
	public String updateUserPsw(@RequestBody UcUser user) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			user = UserManager.md5Pswd(user);
			int status = ucUserService.updateByPrimaryKeySelective(user);
			if (status > 0) {
				UcUser ucUser = ucUserService.findUser(user.getUserId());
				TokenAuth.updateUser(user.getUserId(), ucUser);// 用户信息修改，维护全局map
			}
			ro.setOspState(200);
			Map<String, Object> data = new HashMap<String, Object>();
			UcUser ucUser = TokenAuth.getUser(user.getUserId());
			data.put("ucUser", ucUser);
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
	 * 用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userInfo")
	public String userInfo(@RequestBody UcUser user) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			int userId = user.getUserId();
			Map<String, Object> data = new HashMap<String, Object>();
			UcUser ucUser = TokenAuth.getUser(userId);
			data.put("ucUser", ucUser);
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
