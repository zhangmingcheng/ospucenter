package com.osp.ucenter.manager;

import com.osp.ucenter.common.utils.MathUtils;
import com.osp.ucenter.persistence.model.UcUser;

public class UserManager {

	/**
	 * 加工密码，和登录一致。
	 * 
	 * @param user
	 * @return
	 */
	public static UcUser md5Pswd(UcUser user) {
		user.setUserPwd(md5Pswd(user.getUserName(), user.getUserPwd()));
		return user;
	}

	/**
	 * MD5加密
	 * 32 位
	 * @param email
	 * @param pswd
	 * @return
	 */
	public static String md5Pswd(String username, String password) {	
		return  MathUtils.getMD5(String.format("%s#%s", username, password));
	}

}
