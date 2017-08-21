package com.osp.ucenter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.osp.ucenter.common.exception.MyRuntimeException;
import com.osp.ucenter.common.http.RequestUtil;
import com.osp.ucenter.common.model.ResponseObject;
import com.osp.ucenter.common.utils.JsonUtil;
import com.osp.ucenter.common.utils.LoggerUtils;
import com.osp.ucenter.jwt.JwtHelper;
import com.osp.ucenter.jwt.TokenAuth;
import com.osp.ucenter.manager.UserManager;
import com.osp.ucenter.persistence.model.UcUser;
import com.osp.ucenter.service.UcUserService;

/**
 * 用户登录注册
 * 
 * @author zhangmingcheng
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "/user")
public class LoginController extends BaseController {

	@Autowired
	UcUserService ucUserService;

	@ResponseBody
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@RequestBody UcUser user) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			String username = user.getUserName();
			String password = user.getUserPwd();
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, UserManager.md5Pswd(username, password));
			currentUser.login(token);
			//currentUser.hasRole("admin");

			// 获取 菜单

			// 拼装accessToken MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=
			String accessToken = JwtHelper.createJWT(username, 1800 * 1000);
			TokenAuth.add(accessToken);
			ro.setOspState(200);
			ro.setToken(accessToken);	
			Map<String , Object> data = new HashMap<String,Object>();
		    data.put("ucUser", TokenAuth.getUser(username));
		    TokenAuth.remove(username);
			ro.setData(data);
			return JsonUtil.beanToJson(ro);
		} catch (MyRuntimeException e) {
			ro.setOspState(400);
			return JsonUtil.beanToJson(ro);
		} catch (AuthenticationException e) {
			ro.setOspState(401);
			e.printStackTrace();
			return JsonUtil.beanToJson(ro);
		} catch (Exception e) {
			ro.setOspState(402);
			e.printStackTrace();
			return JsonUtil.beanToJson(ro);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/register")
	public Map<String, Object> register(HttpServletRequest request, RedirectAttributes attr) {
		resultMap.put("status", 400);
		UcUser ucUser = new UcUser();
		ucUser.setUserName("test111");
		ucUser.setUserPwd("123456");
		ucUser.setUserEmail("124973@qq.com");
		ucUser = UserManager.md5Pswd(ucUser);

		UcUser user = ucUserService.findUser(ucUser.getUserName(), ucUser.getSystemcode());
		if (null != user) {
			resultMap.put("message", "帐号已经存在！");
			return resultMap;
		}
		int count = ucUserService.insert(ucUser);
		LoggerUtils.fmtDebug(getClass(), "注册插入完毕！", ucUser.toString());
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(ucUser.getUserName(), ucUser.getUserPwd());
		currentUser.login(token);
		LoggerUtils.fmtDebug(getClass(), "注册后，登录完毕！", ucUser.toString());
		resultMap.put("message", "注册成功！");
		resultMap.put("successCount", count);
		resultMap.put("status", 200);
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/loginTest.json")
	public String loginTest(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		System.out.println("{a:aa}");
		String username = RequestUtil.getString(request, "username");

		// response.setHeader("Access-Control-Allow-Origin", "*");
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getSession().getAttribute("");
		ResponseObject ro = ResponseObject.getInstance();
		ro.setOspState(200);
		String accessToken = JwtHelper.createJWT(username, 1800 * 1000);
		ro.setToken(accessToken);
		ro.setValue("danwei", "IT");

		String json = JsonUtil.beanToJson(ro);
		return json;
		// return
		// "{\"status\":1,\"data\":{\"user\":\"aaaa\",\"token\":\"sssssss\"}}";
	}

	@ResponseBody
	@RequestMapping(value = "/userInfo")
	public String userInfo(@RequestBody UcUser user) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			String username = user.getUserName();
			String systemcode = user.getSystemcode();
			return JsonUtil.beanToJson(ucUserService.findUser(username, systemcode));
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
	@RequestMapping(value = "/updateUserInfo")
	public String updateUserInfo(@RequestBody UcUser user) {
		ResponseObject ro = ResponseObject.getInstance();
		try {
			ucUserService.updateByPrimaryKeySelective(user);
			ro.setOspState(200);
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
			ucUserService.updateByPrimaryKeySelective(user);
			ro.setOspState(200);
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
