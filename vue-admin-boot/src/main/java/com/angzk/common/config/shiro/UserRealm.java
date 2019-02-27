package com.angzk.common.config.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.angzk.common.utils.JWTUtil;
import com.angzk.dao.model.SysMenu;
import com.angzk.dao.model.SysRole;
import com.angzk.dao.model.SysUser;
import com.angzk.service.SysMentRestService;
import com.angzk.service.SysRoleRestService;
import com.angzk.service.SysUserRestService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private SysUserRestService sysUserService;

	@Autowired
	private SysRoleRestService sysRoleService;

	@Autowired
	private SysMentRestService sysMentService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	/**
	 * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
	 * 
	 * 授权模块，获取用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
		String uid = JWTUtil.getUserId(token.toString());
		Long userId = Long.valueOf(uid);
		// // 查询用户信息
		// SysUser sysUserInfo = sysUserService.querySysUserInfo(userId);
		// 查询用户角色
		List<SysRole> userRole = sysRoleService.queryUserRole(userId);
		Set<String> roleSet = new HashSet<String>();
		if (userRole != null && !userRole.isEmpty()) {
			roleSet = userRole.stream().map(pred -> pred.getAliasName()).collect(Collectors.toSet());
		}
		List<SysMenu> userMenus = sysMentService.getUserSysMenu(userId);

		Set<String> menusSet = new HashSet<String>();
		if (userMenus != null && !userMenus.isEmpty()) {
			menusSet = userMenus.stream().map(pred -> pred.getPerms()).collect(Collectors.toSet());
		}

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(roleSet);
		simpleAuthorizationInfo.addStringPermissions(menusSet);
		return simpleAuthorizationInfo;
	}

	/**
	 * 用户（token）认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		String token = (String) authenticationToken.getCredentials();
//		log.error("[---------- shiro token----------] ======>" + token);
		String uid = JWTUtil.getUserId(token);
		if (StringUtils.isBlank(uid)) {
//			log.error("[---------- shiro ----------] ======>" + "token校验不通过");
			throw new AuthenticationException("token校验不通过");
		}
		// 查询用户信息
		SysUser sysUserInfo = sysUserService.querySysUserInfo(Long.valueOf(uid));

		if (sysUserInfo == null) {
//			log.error("[---------- shiro ----------] ======>" + "用户名或密码错误");
			throw new AuthenticationException("用户名或密码错误");
		}

		// http 无状态 工程 到此即可结束
		// http 有状态 工程， 可加入 redis 模块，将登录之后颁发的Token 序列化到redis中，过期时间和jwt 过期时间一致。
		// 然后在此处加入redis校验，若redis中不存在此token，说明 此token 是失效的。

		return new SimpleAuthenticationInfo(token, token, "my_realm");
	}

	public static void main(String[] args) {
		String token = "eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiIyIiwiZXhwIjoxNTUxMjQ0ODYzfQ.kdljzQWD7Q_-7T7HzdwppjXVf48GQnLOe4O5AzlWay5wWxXoyh3xbC9Y88iTrtVX8_H88LJvDUKFghmFJOCi_hTYSXm6i4i5-A3JgkEIkSXs3-PdLJrd4JdLW-S3Z-hLHdj3NQEftpuy0SVT86Ki1AE_jGSHggDS86pYLg8KSHU";
		String uid = JWTUtil.getUserId(token);
		System.err.println(uid);
	}

}
