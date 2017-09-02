package com.socket.common.exception;

import com.socket.common.response.BaseResult;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @auther: pengdh
 * @create: 2017-08-25 14:48
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = BizException.class)
	@ResponseBody
	public BaseResult<Object> defaultExceptionHandle(HttpServletRequest request, Exception e)
			throws IOException {
		logger.error("访问" + request.getRequestURI() + " 发生错误, 错误信息:" + e.getMessage());
		BaseResult<Object> result = new BaseResult<Object>(false, e.getMessage());
		return result;
	}
}
