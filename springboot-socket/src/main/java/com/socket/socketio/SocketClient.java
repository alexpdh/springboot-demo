package com.socket.socketio;

import com.socket.common.date.DateUtils;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter.Listener;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * socketio 客户端实现
 *
 * @author pengdh
 * @date: 2017-09-03 1:06
 */
@Component
public class SocketClient {
	// 初始化连接
	private static Socket socket = initSocket();
	// 初始化连接池
	private static ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(
			10);

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 连接标识
	private boolean isConnected;

	private String onMessageContent = null;
	/**
	 * 用于存放每个进来任务的 future ，key：为任务id，value：future，
	 * 目的是为了可以通过条件控制任务，通过接口调用可以 cancel 对应的 future
	 */
	private Map<String, Future> futureMap = new HashMap<String, Future>();

	/**
	 * 连接监听事件
	 * 实现消息回调接口
	 */
	private Listener onConnect = new Listener() {
		@Override
		public void call(Object... objects) {
			logger.info("client 连接服务端成功：");
			if (!isConnected) {
				socket.emit("connect message", "hello");
			}
			isConnected = true;
		}
	};
	/**
	 * 断开连接端口监听
	 */
	private Listener onDisconnect = new Listener() {
		@Override
		public void call(Object... objects) {
			logger.info("client 断开服务端连接：" + objects[0]);
			isConnected = false;
		}
	};
	/**
	 * 连接错误监听
	 */
	private Listener onConnetError = new Listener() {
		@Override
		public void call(Object... objects) {
			logger.info("client 连接服务端错误：" + objects[0]);
		}
	};
	/**
	 * 连接超时监听
	 */
	private Listener onConnetTimeout = new Listener() {
		@Override
		public void call(Object... objects) {
			logger.info("client 连接服务端超时：" + objects[0]);
		}
	};

	/**
	 * 消息监听事件
	 */
	private Listener onMessage = new Listener() {
		@Override
		public void call(Object... objects) {
			logger.info("收到返回监听事件：" + objects[0]);
			onMessageContent = (String) objects[0];
		}
	};

	/**
	 * 初始化 socket 连接
	 */
	public static Socket initSocket() {
		try {
			socket = IO.socket("http://localhost:9090");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return socket;
	}

	/**
	 * 连接处理
	 */
	public void connectSocket() {
		// 连接
		socket.connect();

		// 添加监听事件
		addConnectListenerEvent();
	}

	/**
	 * 断开连接
	 */
	public void disConnectSocket() {
		// 断开连接
		socket.disconnect();
		// 添加监听事件
		addDisConnectListenerEvent();
	}

	/**
	 * 添加连接监听事件
	 */
	private void addConnectListenerEvent() {
		socket.on(Socket.EVENT_CONNECT, onConnect);  // 连接成功
		socket.on(Socket.EVENT_DISCONNECT, onDisconnect);  // 断开连接
		socket.on(Socket.EVENT_CONNECT_ERROR, onConnetError);// 连接错误
		socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnetTimeout);  // 连接超时
	}

	/**
	 * 添加断开连接监听事件
	 */
	public void addDisConnectListenerEvent() {
		socket.off(Socket.EVENT_CONNECT, onConnect);  // 连接成功
		socket.off(Socket.EVENT_DISCONNECT, onDisconnect);  // 断开连接
		socket.off(Socket.EVENT_CONNECT_ERROR, onConnetError);// 连接错误
		socket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnetTimeout);  // 连接超时
	}

	/**
	 * 启动客户端并指定发送消息任务开始时间
	 * @param id 指定的任务id
	 * @param startTime 任务开始时间
	 */
	public void startClient(String id, String startTime) {
		try {
			this.judgeConnection();
			this.judgeHandleExistFuture(id);
			this.emitJoined(id);
			// 指定从当前时间延迟多久后开始执行定时的任务，时间单位可以在调用方法时指定
			long delay = 0;
			// 指定每次执行任务的时间间隔
			long period = 10000;
			Date date = DateUtils.parseShortDateTime(startTime);
			// 计算任务开始时间到当前时间的毫秒差
			long targetTimeMillis = date.getTime();
			long initDelay = targetTimeMillis - System.currentTimeMillis();
			delay = initDelay > 0 ? initDelay : delay;
			logger.info("启动定时任务：delay=" + delay + " period=" + period);
			// 因为每天会定时结束所有服务，当再次有任务进来时要先初始化线程池
			if (scheduledExecutorService.isShutdown()) {
				scheduledExecutorService = new ScheduledThreadPoolExecutor(10);
			}
			Future future = scheduledExecutorService
					.scheduleAtFixedRate(new HandleMessageRunnable(id), delay, period,
							TimeUnit.MILLISECONDS);
			// 将 future 放入map
			futureMap.put(id, future);
			logger.info("已有客户端：" + futureMap.keySet());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加入事件监听
	 * @param id
	 */
	private void emitJoined(String id) {
		socket.emit("join", id).on("joinSuccess", onMessage);
	}

	/**
	 * 判断连接
	 */
	private void judgeConnection() {
		if (!isConnected) {
			this.initSocket();
			this.connectSocket();
		}
	}

	/**
	 * cancel 任务
	 * @param id
	 */
	public void judgeHandleExistFuture (String id) {
		if (futureMap.containsKey(id)) {
			Future future = futureMap.get(id);
			future.cancel(true);
			futureMap.remove(id);
			logger.info(id + " task cancelled!");
			logger.info("剩余客户端：" + futureMap.keySet());
		}
	}

	/**
	 * cancel 掉 id 对应的任务
	 */
	public void endClient(String id) {
		try {
			this.judgeHandleExistFuture(id);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
	}


	/**
	 * 结束所有任务
	 */
	public void shutdown() {
		scheduledExecutorService.shutdown();
		futureMap.clear();
		logger.info("task have shutdown!");
	}

	/**
	 * 处理任务
	 * 通过固定频率发送消息到服务端
	 */
	private class HandleMessageRunnable implements Runnable {

		private String id;

		public HandleMessageRunnable(String id) {
			this.id = id;
		}

		@Override
		public void run() {
			JSONObject jsonObject = new JSONObject();
			try {
				// 先发送 joined 事件，当收到回执后再发送 chatMessage 消息
				if (null != onMessageContent && onMessageContent.equals("join success")) {
					String currentTime = DateUtils.formatStandardDateTime(new Date());
					jsonObject.put("id", id);
					jsonObject.put("currentTime", currentTime);
					logger.info("客户端发送消息：" + jsonObject.toString());
					socket.emit("chatMessage", jsonObject);
				} else {
					logger.info("未收到加入返回事件");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
