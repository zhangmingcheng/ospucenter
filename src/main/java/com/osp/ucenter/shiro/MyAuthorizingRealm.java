package com.osp.ucenter.shiro;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.osp.ucenter.common.utils.BaseUtils;
import com.osp.ucenter.jwt.TokenAuth;
import com.osp.ucenter.persistence.model.UcUser;
import com.osp.ucenter.service.UcUserService;

/**
 * 基础Realm 认证+授权
 * 
 * @author zhangmingcheng
 */
public class MyAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private UcUserService ucUserService;

	public MyAuthorizingRealm() {
		super();
	}

	/**
	 * 用户登录认证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 将token转换成UsernamePasswordToken
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
		String username = (String) usernamePasswordToken.getPrincipal();
		String password = new String((char[]) usernamePasswordToken.getCredentials());
		UcUser user = ucUserService.login(username, password);
		if (null == user) {
			throw new AccountException("帐号或密码不正确！");
		} else {
			try {
				// 更新最后一次登录时间
				user.setLastLoginTime(BaseUtils.getCurrentTime());
				ucUserService.updateByPrimaryKeySelective(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		TokenAuth.addUser(username, user);
		return new SimpleAuthenticationInfo(username, user.getUserPwd(), getName());

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("==========================数据数据数据数据数据数据");
		return null;
	}
}
