package com.angzk.rest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angzk.common.utils.JWTUtil;
import com.angzk.dao.model.SysRole;
import com.angzk.dao.model.SysUser;
import com.angzk.service.SysRoleRestService;
import com.angzk.service.SysUserRestService;
import com.kuajx.common.base.ResponseData;
import com.kuajx.common.exception.GlobalException;
import com.kuajx.common.tools.EncryptUtil;
import com.kuajx.common.util.DateUtils;

import net.sf.json.JSONObject;

/**
 * 授权控制器
 * 
 * @author Angzk
 *
 */
@RestController
@RequestMapping("auth")
public class AuthRestApi {

	@Autowired
	private SysUserRestService sysUserRestService;

	@Autowired
	private SysRoleRestService sysRoleRestService;

	@GetMapping("systemDate")
	public ResponseData getSystemDate() {
		JSONObject result = new JSONObject();
		result.put("date", DateUtils.getDateToString());
		return ResponseData.ok(result);
	}

	@PostMapping("/login")
	public ResponseData login(@NotBlank(message = "{required}") String username,
			@NotBlank(message = "{required}") String password, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		if (StringUtils.isBlank(username)) {
			throw new GlobalException("用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			throw new GlobalException("密码不能为空");
		}
		username = StringUtils.lowerCase(username);
		password = EncryptUtil.mD5(password);

		final String errorMessage = "用户名或密码错误";
		SysUser user = sysUserRestService.querySysUserByUserName(username);

		if (user == null)
			throw new GlobalException(errorMessage);
		if (!StringUtils.equals(user.getPassword(), password))
			throw new GlobalException(errorMessage);
		// if (User.STATUS_LOCK.equals(user.getStatus()))
		// throw new GlobalException("账号已被锁定,请联系管理员！");
		List<SysRole> role = sysRoleRestService.queryUserRole(user.getUserId());
		if (role == null || role.isEmpty()) {
			result.put("code", 0);
			result.put("msg", "用户暂未分配角色，无法认证登录");
			return ResponseData.ok(result, "认证失败");
		}
		Set<String> roles = role.stream().map(record -> record.getAliasName()).collect(Collectors.toSet());
		String token = JWTUtil.getToken(user.getUserId().toString());

		result.put("token", token);
		result.put("roles", roles);
		result.put("code", 200);
		return ResponseData.ok(result);
	}

	@GetMapping("test")
	@RequiresPermissions("test:test")
	public ResponseData testAuthor() throws Exception {
		return ResponseData.ok(DateUtils.getDateToString(), "请求成功");
	}

	/**
	 * 登出
	 */
	@PostMapping("/logout")
	public JSONObject logout() {
		return sysUserRestService.logout();
	}
}
