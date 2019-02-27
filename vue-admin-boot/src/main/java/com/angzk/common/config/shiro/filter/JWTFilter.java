package com.angzk.common.config.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.angzk.common.config.shiro.JWTToken;

import lombok.extern.slf4j.Slf4j;

/**
 * 登录授权过滤器
 * 
 * @author Angzk
 *
 */
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

	private static final String TOKEN = "Authorization";
	private static final String TOKEN_TOP = "Bearer ";

	// public JWTFilter() {
	// log.error("------------------------------------------JWTFilter.shirFilter()");
	// }

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		log.error("------------------------------------------JWTFilter.isAccessAllowed()");
		if (isLoginAttempt(request, response)) {
			try {
				executeLogin(request, response);
			} catch (Exception e) {
				log.error(e.getMessage());
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断用户是否想要登入。 检测header里面是否包含Authorization字段即可
	 */
	@Override
	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
		// log.error("------------------------------------------JWTFilter.isLoginAttempt()");
		HttpServletRequest req = (HttpServletRequest) request;
		String authorization = req.getHeader(TOKEN);
		return authorization != null;
	}

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		// log.error("------------------------------------------JWTFilter.executeLogin()");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String authorization = httpServletRequest.getHeader(TOKEN);
		int indexOf = authorization.indexOf(TOKEN_TOP);
		if (indexOf > -1) {
			authorization = authorization.substring(indexOf + TOKEN_TOP.length(), authorization.length());
		}
		JWTToken token = new JWTToken(authorization);
		// 提交给realm进行登入，如果错误他会抛出异常并被捕获
		try {
			getSubject(request, response).login(token);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		// 如果没有抛出异常则代表登入成功，返回true
	}

	/**
	 * 对跨域提供支持
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		// log.error("------------------------------------------JWTFilter.preHandle()");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
		httpServletResponse.setHeader("Access-Control-Allow-Headers",
				httpServletRequest.getHeader("Access-Control-Request-Headers"));
		// 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
		if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			httpServletResponse.setStatus(HttpStatus.OK.value());
			return false;
		}
		return super.preHandle(request, response);
	}

}
