package com.socket.domain;

import java.io.Serializable;

/**
 * 获取当前时间消息实体类
 *
 * @author pengdh
 * @date: 2017-09-03 2:14
 */
public class TimeMessage implements Serializable {

	private static final long serialVersionUID = 4098680584856164475L;
	private String id ;
 private String currentTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	@Override
	public String toString() {
		return "TimeMessage{" +
				"id='" + id + '\'' +
				", currentTime='" + currentTime + '\'' +
				'}';
	}
}
