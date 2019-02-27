package com.angzk.service;

import java.util.List;

import com.angzk.dao.model.SysRole;

/**
 * 角色类
 * @author Angzk
 *
 */
public interface SysRoleRestService {
	
	List<SysRole> queryUserRole(Long userId);
}
