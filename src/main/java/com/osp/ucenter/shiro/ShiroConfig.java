package com.osp.ucenter.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 等价于beans
public class ShiroConfig {

	private static final Logger log = LoggerFactory.getLogger(ShiroFilterFactoryBean.class);

	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("myAuthorizingRealm") MyAuthorizingRealm myAuthorizingRealm) {
		log.info("securityManager()");
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myAuthorizingRealm);
		return securityManager;
	}

	/**
	 * realm
	 * 
	 * @return
	 */
	@Bean(name = "myAuthorizingRealm")
	public MyAuthorizingRealm myAuthorizingRealm() {
		MyAuthorizingRealm myAuthorizingRealm = new MyAuthorizingRealm();
		return myAuthorizingRealm;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
		log.info("shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		/*
		 * // 拦截器. Map<String, String> map = new LinkedHashMap<String,
		 * String>();
		 * 
		 * map.put("/**", "authc");
		 * 
		 * // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		 * shiroFilterFactoryBean.setLoginUrl("/login"); // 登录成功后要跳转的链接
		 * shiroFilterFactoryBean.setSuccessUrl("/index"); // 未授权界面;
		 * shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		 * 
		 * shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		 */
		return shiroFilterFactoryBean;
	}
}