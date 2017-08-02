package com.osp.ucenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.osp.ucenter.common.exception.MyRuntimeException;
import com.osp.ucenter.common.http.RequestUtil;


/**
 * 
 * @author fly
 *
 */
@Controller
@RequestMapping(value = "/")
public class LoginController {

	 @Autowired
	 DefaultWebSecurityManager defaultWebSecurityManager;
	
    @RequestMapping(value = "/loginDo")
    public String loginDo(HttpServletRequest request, HttpSession session, RedirectAttributes attr) {
        try {
            String username = RequestUtil.getString(request, "username");
            String password = RequestUtil.getString(request, "password");
//            String code = RequestUtil.getString(request, "verifyCode");
//            String vrifyCode = (String) session.getAttribute("vrifyCode");
//            if (code == null || !code.equals(vrifyCode)) {
//                if (!"a".equals(code)) {// 开发临时用
//                    throw new MyRuntimeException("验证码错误");
//                }
//            }
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            SecurityUtils.setSecurityManager(defaultWebSecurityManager);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            System.out.println("登录成功           principal =   " + subject.getPrincipal().toString());
            return "redirect:/index";
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
}
