package com.socket.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * 请求控制器
 *
 * @author pengdh
 * @date: 2017-09-02 16:16
 */
@Controller
public class WsController {

	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public ServerResponseMessage say(ClientRequestMessage message) {
		System.out.println(">>>>>>>>>>>>>> " + message.getName());
		return new ServerResponseMessage("Welcome," + message.getName() + "!");
	}
}
