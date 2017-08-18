package com.osp.ucenter.jwt;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.osp.ucenter.persistence.model.UcUser;

/**
 * 暂存token
 * 
 * @author zhangmingcheng
 */
public class TokenAuth {
	// JWTToken
	public static Map<String, String> jwtTokens = new HashMap<String, String>();
	// User 仅供登录认证时使用
	public static Map<String, UcUser> users = new HashMap<String, UcUser>();

	public static void addUser(String username, UcUser ucUser) {
		if (users.containsKey(username) == false) {
			users.put(username, ucUser);
		}
	}

	public static void removeUser(String username) {
		if (users.containsKey(username) == true) {
			users.remove(username);
		}
	}

	public static UcUser getUser(String username) {
		if (users.containsKey(username) == false) {
			return new UcUser();
		}
		return users.get(username);
	}

	public static void add(String token) {
		jwtTokens.put(token, "");
	}

	public static void remove(String token) {
		jwtTokens.remove(token);
	}

	public static boolean hasToken(String token) {
		return jwtTokens.containsKey(token);
	}
}
