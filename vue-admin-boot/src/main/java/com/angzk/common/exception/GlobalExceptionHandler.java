package com.angzk.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuajx.common.base.ResponseCode;
import com.kuajx.common.base.ResponseData;
import com.kuajx.common.exception.GlobalException;

import net.sf.json.JSONObject;

/**
 * 统一异常处理
 * 
 * @author Angzk
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 自定义异常处理
	 * 
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = GlobalException.class)
	@ResponseBody
	public ResponseData jsonErrorHandler(HttpServletRequest req, GlobalException e) throws Exception {
		logger.error("", e);
		JSONObject data = new JSONObject();
		data.put("code", ResponseCode.BUSINESS_EXCEPTION_CODE.getCode());
		data.put("msg", e.getMessage());
		return ResponseData.ok(data, "业务异常");
	}

	/**
	 * 系统异常处理，比如：404,500
	 * 
	 * @param req
	 * @param resp
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseData defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		logger.error("", e);
		JSONObject data = new JSONObject();
		data.put("code", ResponseCode.BUSINESS_EXCEPTION_CODE.getCode());
		data.put("msg", e.getMessage());
		// 404
		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			data.put("code", ResponseCode.NOT_FOUND.getCode());
		} else if (e instanceof org.springframework.web.method.annotation.MethodArgumentTypeMismatchException) {
			data.put("code", ResponseCode.PARAM_ERROR_CODE.getCode());
		} else if (e instanceof org.apache.shiro.authz.UnauthorizedException) {
			data.put("code", ResponseCode.NOT_PERMISSION.getCode());
		} else if (e instanceof org.apache.shiro.authz.AuthorizationException) {
			return ResponseData.fail("暂未登录", ResponseCode.NOT_AUTH_CODE.getCode());

		} else {
			data.put("code", ResponseCode.SERVER_ERROR_CODE.getCode());
		}
		data.put("msg", e.getMessage());
		return ResponseData.ok(data, "业务异常");
	}

}
