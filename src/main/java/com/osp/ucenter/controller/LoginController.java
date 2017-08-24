package com.osp.ucenter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.osp.common.json.JsonUtil;
import com.osp.ucenter.common.exception.MyRuntimeException;
import com.osp.ucenter.common.model.ResponseObject;
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
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			String username = user.getUserName();
			String password = user.getUserPwd();
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, UserManager.md5Pswd(username, password));
			currentUser.login(token);
			// currentUser.hasRole("admin");

			// 获取 菜单

			UcUser ucUser = TokenAuth.getAuthUser(username);
			if (ucUser != null) {
				ucUser.setUserPwd("");
				// 拼装accessToken MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=
				String accessToken = JwtHelper.createJWT(username, 1800 * 1000);
				//暂存jwtToken信息，不存签名部分
				ucUser.setJwtToken(accessToken.substring(0, accessToken.lastIndexOf('.')));
				TokenAuth.add(accessToken, ucUser);
				ro.setOspState(200);
				ro.setToken(accessToken);
				data.put("ucUser", ucUser);
				ro.setData(data);
				TokenAuth.removeAuthUser(username);
			}else{
				ro.setOspState(500);
			}
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

		UcUser user = ucUserService.findUser(ucUser.getUserId());
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
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String noauth() {
		ResponseObject ro = ResponseObject.getInstance();
		ro.setOspState(402);
		return JsonUtil.beanToJson(ro);
	}
}
