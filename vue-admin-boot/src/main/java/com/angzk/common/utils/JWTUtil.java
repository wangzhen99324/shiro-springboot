package com.angzk.common.utils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.angzk.common.exception.TokenAuthenticationException;
import com.kuajx.common.base.ResponseCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * JWT 工具类
 * 
 * @author Angzk
 *
 */
public class JWTUtil {

	/**
	 * 默认过期时间单位
	 */
	private static final long EXPIRE_SECOND = 60 * 1000;

	/**
	 * 默认过期时间5分钟
	 */
	private static final long EXPIRE_TIME = 60 * EXPIRE_SECOND;

	private static RSAPrivateKey priKey;
	private static RSAPublicKey pubKey;

	static {
		if (StringUtils.hasText(System.getProperty("rsa.modulus"))
				&& StringUtils.hasText(System.getProperty("rsa.privateExponent"))
				&& StringUtils.hasText(System.getProperty("rsa.publicExponent"))) {
			priKey = RSAUtils.getPrivateKey(System.getProperty("rsa.modulus"),
					System.getProperty("rsa.privateExponent"));
			pubKey = RSAUtils.getPublicKey(System.getProperty("rsa.modulus"), System.getProperty("rsa.publicExponent"));
		} else {
			// throw new RuntimeException("请配置RSA的公钥私钥信息");
			priKey = RSAUtils.getPrivateKey(RSAUtils.modulus, RSAUtils.private_exponent);
			pubKey = RSAUtils.getPublicKey(RSAUtils.modulus, RSAUtils.public_exponent);
		}
	}

	/**
	 * 创建/获取Token
	 * 
	 * @param uid
	 * 
	 * @param exp
	 *            失效时间，单位分钟
	 * @return
	 */
	public static String getToken(String uid, int exp) {
		long endTime = System.currentTimeMillis() + EXPIRE_SECOND * exp;
		return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
				.signWith(SignatureAlgorithm.RS512, priKey).compact();
	}

	/**
	 * 创建/获取Token
	 * 
	 * @param uid
	 * 
	 * @param exp
	 *            默认失效时间为15天
	 * @return
	 */
	public static String getToken(String uid) {
		Long endTime = System.currentTimeMillis() + EXPIRE_TIME;
		return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
				.signWith(SignatureAlgorithm.RS512, priKey).compact();
	}

	/**
	 * 检查Token是否合法
	 * 
	 * @param token
	 * @return JWTResult
	 */
	public static JWTResult checkToken(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody();
			String sub = claims.get("sub", String.class);
			return new JWTResult(true, sub, "合法请求", ResponseCode.SUCCESS_CODE.getCode());
		} catch (ExpiredJwtException e) {
			// 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
			return new JWTResult(false, null, "token已过期", ResponseCode.TOKEN_TIMEOUT_CODE.getCode());
		} catch (SignatureException e) {
			// 在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
			return new JWTResult(false, null, "非法请求", ResponseCode.NOT_AUTH_CODE.getCode());
		} catch (Exception e) {
			return new JWTResult(false, null, "非法请求", ResponseCode.NOT_AUTH_CODE.getCode());
		}
	}

	public static class JWTResult {
		private boolean status;
		private String uid;
		private String msg;
		private int code;

		public JWTResult() {
			super();
		}

		public JWTResult(boolean status, String uid, String msg, int code) {
			super();
			this.status = status;
			this.uid = uid;
			this.msg = msg;
			this.code = code;
		}

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

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		@Override
		public String toString() {
			return "JWTResult [status=" + status + ", uid=" + uid + ", msg=" + msg + ", code=" + code + "]";
		}
		
	}

	public static String getUserId(String token) throws TokenAuthenticationException {
		JWTResult jwtResult = checkToken(token);
		if (jwtResult.isStatus()) {
			return jwtResult.getUid();
		}else {
			throw new TokenAuthenticationException(jwtResult.getCode(),jwtResult.getMsg());
		}
	}

	/**
	 * 判断一个request 是否为 Ajax 请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(ServletRequest servletRequest) {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String requestType = request.getHeader("X-Requested-With");
		// 如果requestType能拿到值，并且值为 XMLHttpRequest
		// ,表示客户端的请求为异步请求，那自然是ajax请求了，反之如果为null,则是普通的请求
		if (requestType == null) {
			return false;
		}
		return true;

	}
}
