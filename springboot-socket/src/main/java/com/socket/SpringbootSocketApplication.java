package com.socket;

import com.socket.socketio.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan
public class SpringbootSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSocketApplication.class, args);
		SocketServer server = new SocketServer();
		server.startServer();
	}
}
