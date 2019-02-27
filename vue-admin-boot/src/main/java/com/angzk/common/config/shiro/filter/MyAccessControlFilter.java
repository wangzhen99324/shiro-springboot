package com.angzk.common.config.shiro.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.kuajx.common.base.ResponseCode;
import com.kuajx.common.base.ResponseData;
import com.kuajx.common.util.JsonUtils;

/**
 * 由于默认shiro 不登录是跳转到 login.jsp 
 * 此拦截器是为了校验需要鉴权 http 请求中是否携带 Authorization
 * 参数，若未携带则返回自定义的 JSON 格式数据
 * 
 * @author Angzk
 *
 */
public class MyAccessControlFilter extends FormAuthenticationFilter {

	private static final String TOKEN = "Authorization";

	/**
	 * 如果isAccessAllowed返回false 则执行onAccessDenied
	 * 
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (request instanceof HttpServletRequest) {
			if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
				return true;
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}

	/**
	 * 在访问controller前判断是否登录，返回json，不进行重定向。
	 *
	 * @param request
	 * @param response
	 * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
	 * @throws Exception
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse response) throws IOException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String authorization = request.getHeader(TOKEN);
		if (StringUtils.isNotBlank(authorization)) {
			return true;
		} else {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			// 这里是个坑，如果不设置的接受的访问源，那么前端都会报跨域错误，因为这里还没到corsConfig里面
			httpServletResponse.setHeader("Access-Control-Allow-Origin",
					((HttpServletRequest) request).getHeader("Origin"));
			httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpServletResponse.setCharacterEncoding("UTF-8");
			httpServletResponse.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			writer.write(JsonUtils.toJson(ResponseData.fail("暂未登录", ResponseCode.NOT_AUTH_CODE.getCode())));
			writer.flush();
			return false;
		}
	}
}
