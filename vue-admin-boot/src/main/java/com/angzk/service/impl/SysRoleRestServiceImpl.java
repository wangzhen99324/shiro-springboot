package com.angzk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angzk.dao.mapper.SysRoleMapper;
import com.angzk.dao.model.SysRole;
import com.angzk.service.SysRoleRestService;

@Service
public class SysRoleRestServiceImpl implements SysRoleRestService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
//	@Autowired
//	private SysRoleMapper sysRoleMapper;

	@Override
	public List<SysRole> queryUserRole(Long userId) {
		return sysRoleMapper.queryUserRole(userId);
	}

}
