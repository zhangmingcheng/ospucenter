package com.osp.ucenter.common.utils;

import java.text.SimpleDateFormat;

/**
 * 用户系统基础工具类
 * @author zhangmingcheng
 */
public class BaseUtils {

	/**
	 * 获取当前时间:格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		return sdf.format(date);
	}
}
