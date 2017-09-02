package com.socket.web;

import com.socket.socketio.SocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务调度控制器
 *
 * @author pengdh
 * @date: 2017-09-03 1:53
 */
@RestController
public class ScheduledController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

 @Autowired
	private SocketClient client;

	/**
	 * 启动客户端任务
	 * @param id 任务id
	 * @param startTime 任务开始时间
	 * @return
	 */
	@RequestMapping("/start/{id}")
	public ResponseEntity<String> startClient(@PathVariable String id, @RequestParam String startTime) {
		client.startClient(id, startTime);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 取消消息任务
	 * @param id
	 * @return
	 */
	@RequestMapping("/end/{id}")
	public ResponseEntity<String> endClient(@PathVariable String id) {
		client.endClient(id);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 通过接口调用手动结束所有任务
	 *
	 * @return
	 */
	@RequestMapping("/end")
	public ResponseEntity<String> shutdown() {
		logger.info("手动结束所有任务");
		client.shutdown();
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 定时结束所有任务
	 */
	@Scheduled(cron = "0 0 2 * * ?")
	public void stopClient() {
		logger.info("定时结束所有任务");
		client.shutdown();
	}
}
