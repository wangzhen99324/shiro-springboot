package com.angzk.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angzk.dao.mapper.SysUserMapper;
import com.angzk.dao.model.SysUser;
import com.angzk.dao.model.SysUserExample;
import com.angzk.service.SysUserRestService;

import net.sf.json.JSONObject;

@Service
public class SysUserRestServiceImpl implements SysUserRestService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUser querySysUserInfo(Long uid) {
		return sysUserMapper.selectByPrimaryKey(uid);
	}

	@Override
	public SysUser querySysUserByUserName(String username) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<SysUser> list = sysUserMapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public JSONObject logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
		}
		return null;
	}

}
