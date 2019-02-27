package com.angzk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angzk.dao.mapper.SysMenuMapper;
import com.angzk.dao.model.SysMenu;
import com.angzk.service.SysMentRestService;

@Service
public class SysMentRestServiceImpl implements SysMentRestService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public List<SysMenu> getUserSysMenu(Long userId) {
		return sysMenuMapper.getUserSysMenu(userId);
	}

}
