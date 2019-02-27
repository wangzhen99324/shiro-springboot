package com.angzk.service;

import java.util.List;

import com.angzk.dao.model.SysMenu;

/**
 * 菜单服务
 * 
 * @author Angzk
 *
 */
public interface SysMentRestService {

	List<SysMenu> getUserSysMenu(Long userId);
}
