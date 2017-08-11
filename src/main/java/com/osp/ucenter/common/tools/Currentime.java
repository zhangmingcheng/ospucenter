package com.osp.ucenter.common.tools;

import java.text.SimpleDateFormat;

/**
 * 获取当前时间:格式yyyy-MM-dd
 * 
 * @author zhangmingcheng
 */
public class Currentime {

	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		return sdf.format(date);
	}
}
