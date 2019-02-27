package com.angzk.service;

import com.angzk.dao.model.SysUser;

import net.sf.json.JSONObject;

public interface SysUserRestService {
	
	/**
	 * 查询 后台系统用户信息
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	SysUser querySysUserInfo(Long uid);
	
	/**
	 * 用户名查询用户信息
	 * @param username
	 * @return
	 */
	SysUser querySysUserByUserName(String username);

	JSONObject logout();
}
