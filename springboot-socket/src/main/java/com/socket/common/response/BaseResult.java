package com.socket.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 请求返回封装类
 *
 * @auther: pengdh
 * @create: 2017-08-22 9:20
 */
@JsonInclude(Include.NON_EMPTY)
public class BaseResult<T> implements Serializable {

	private static final long serialVersionUID = 175010385239641820L;
	private boolean success;
	private String errorDesc;
	private T data;

	public BaseResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public BaseResult(boolean success, String errorDesc) {
		this.success = success;
		this.errorDesc = errorDesc;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BaseResult{" +
				"success=" + success +
				", errorDesc='" + errorDesc + '\'' +
				", data=" + data +
				'}';
	}
}
