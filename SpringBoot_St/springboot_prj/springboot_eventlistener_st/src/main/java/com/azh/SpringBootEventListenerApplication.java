package com.azh;

import com.azh.common.MyBanner;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * netty-socketio是一个开源的Socket.io服务器端的一个java的实现，它基于Netty框架，可用于服务端推送消息给客户端
 * 
 */
@SpringBootApplication
@Slf4j
public class SpringBootEventListenerApplication implements CommandLineRunner {
    
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootEventListenerApplication.class);
        application.setBanner(new MyBanner());
        application.run(args);
    }

    @Autowired
    private SocketIOServer socketIOServer;
    
    @Override
    public void run(String... args) throws Exception {
        socketIOServer.start();
        log.info("socket.io 启动成功！");
    }
}
