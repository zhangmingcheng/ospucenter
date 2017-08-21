package com.osp.ucenter.controller;

import java.util.HashMap;
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
