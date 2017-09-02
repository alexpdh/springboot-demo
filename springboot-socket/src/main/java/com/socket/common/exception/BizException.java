package com.socket.common.exception;

/**
 * 自定义异常
 *
 * @auther: pengdh
 * @create: 2017-08-25 14:50
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -2466703721851641645L;

	public BizException() {
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}
}
