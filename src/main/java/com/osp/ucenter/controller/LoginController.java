package com.osp.ucenter.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.osp.ucenter.common.utils.LoggerUtils;
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
public class LoginController extends BaseController{
	@Autowired
	UcUserService UcUserService;

	@ResponseBody
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, RedirectAttributes attr) {
		try {
			// String username = RequestUtil.getString(request, "username");
			// String password = RequestUtil.getString(request, "password");
			// String code = RequestUtil.getString(request, "verifyCode");
			// String vrifyCode = (String) session.getAttribute("vrifyCode");
			// if (code == null || !code.equals(vrifyCode)) {
			// if (!"a".equals(code)) {// 开发临时用
			// throw new MyRuntimeException("验证码错误");
			// }
			// }
			// LoginAuth.login("classpath:shiro-multi-realm.ini", username,
			// password);

			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken("zmcheng", "123456");
			currentUser.login(token);
			return "登录成功!";
		} catch (AuthenticationException e) {
			attr.addAttribute("errormsg", "用户或密码错误！！！");
			e.printStackTrace();
			return "redirect:/login";
		} catch (Exception e) {
			attr.addAttribute("errormsg", "验证码错误！！！");
			e.printStackTrace();
			return "redirect:/login";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/register")
	public Map<String, Object> register(HttpServletRequest request, RedirectAttributes attr) {
		resultMap.put("status", 400);
		UcUser ucUser = new UcUser();
		ucUser.setUserName("test1");
		ucUser.setUserPwd("123456");
		ucUser.setUserEmail("124973@qq.com");
		ucUser = UserManager.md5Pswd(ucUser);
			
		UcUser user = UcUserService.findUser(ucUser.getUserName(),ucUser.getSystemcode());
		if(null != user){
			resultMap.put("message", "帐号已经存在！");
			return resultMap;
		}
		int count = UcUserService.insert(ucUser);
		LoggerUtils.fmtDebug(getClass(), "注册插入完毕！",ucUser.toString());
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
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		return "{\"status\":1,\"data\":{\"user\":\"aaaa\",\"token\":\"sssssss\"}}";
	}

	
}
