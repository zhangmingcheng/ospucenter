package com.osp.ucenter.persistence.bo;

import java.io.Serializable;

import com.osp.ucenter.common.utils.StringUtils;
import com.osp.ucenter.persistence.model.UcRole;

/**
 * 用户编号-用户角色 关系表
 * @author zhangmingcheng
 */
public class UcRoleBo extends UcRole implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID (用String， 考虑多个ID，现在只有一个ID)
	 */
	private String userId;
	/**
	 * 是否勾选
	 */
	private String marker;

	public boolean isCheck() {
		return StringUtils.equals(userId, marker);
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
