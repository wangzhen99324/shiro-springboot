package com.angzk.common.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Shiro login 时需要的参数Model 见 JWTFilter executeLogin 函数
 * 
 * @author Angzk
 *
 */
public class JWTToken implements AuthenticationToken {

	private static final long serialVersionUID = 4052944912823373040L;
	// 密钥
	private String token;

	public JWTToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return token;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return token;
	}

}
