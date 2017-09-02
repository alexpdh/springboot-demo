package com.socket.websocket;

/**
 * 服务端像浏览器端发送消息封装类
 *
 * @author pengdh
 * @date: 2017-09-02 16:13
 */
public class ServerResponseMessage {

	private String responseMessage;

	public ServerResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {

		return responseMessage;
	}
}
