package com.osp.ucenter.jwt;

import java.util.HashMap;
import java.util.Map;

import com.osp.ucenter.persistence.model.UcUser;

/**
 * 暂存token
 * 
 * @author zhangmingcheng
 */
public class TokenAuth {
	// JWTToken
	public static Map<String, UcUser> jwtTokens = new HashMap<String, UcUser>();
	//全局map
	public static Map<Integer, UcUser> users = new HashMap<Integer, UcUser>();
	//只是登录验证时使用
	public static Map<String, UcUser> authUsers = new HashMap<String, UcUser>();
    /**
     * 登录验证时暂存用户信息
     * @param username
     * @param ucUser
     */
	public static void addAuthUser(String username,UcUser ucUser){
		if (authUsers.containsKey(username) == false) {
			authUsers.put(username, ucUser);
		}
	}
	
	/**
	 * 登录成功后即从map中移除
	 * @param username
	 */
	public static void removeAuthUser(String username) {
		if (authUsers.containsKey(username) == true) {
			authUsers.remove(username);
		}
	}
	
	/**
	 * 获取验证时暂存的用户信息
	 * @param userId
	 * @return
	 */
	public static UcUser getAuthUser(String username) {
		if (authUsers.containsKey(username) == false) {
			return null;
		}
		return authUsers.get(username);
	}
	
	
	public static void addUser(Integer userId, UcUser ucUser) {
		if (users.containsKey(userId) == false) {
			users.put(userId, ucUser);
		}
	}
	
	public static void updateUser(Integer userId, UcUser ucUser) {
		if (users.containsKey(userId) == true) {
			users.put(userId, ucUser);
		}
	}


	public static void removeUser(Integer userId) {
		if (users.containsKey(userId) == true) {
			users.remove(userId);
		}
	}

	public static UcUser getUser(Integer userId) {
		if (users.containsKey(userId) == false) {
			return null;
		}
		return users.get(userId);
	}
	

	public static void add(String token, UcUser ucUser) {
		jwtTokens.put(token, ucUser);
	}

	public static void remove(String token) {
		if (jwtTokens.containsKey(token)) {
			jwtTokens.remove(token);
		}
	}

	public static boolean hasToken(String token) {
		return jwtTokens.containsKey(token);
	}
}
