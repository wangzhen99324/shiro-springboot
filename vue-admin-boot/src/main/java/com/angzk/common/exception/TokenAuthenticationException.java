package com.angzk.common.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 自定义异常处理。 Token 不合法/或已过期
 * 
 * @author Angzk
 *
 */
public class TokenAuthenticationException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3107502076964446396L;

	private int code;

	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public TokenAuthenticationException(int code, String msg) {
		super(msg);
	}

	public TokenAuthenticationException() {
		super();
	}

}
