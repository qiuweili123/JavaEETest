package com.ns.netty.server;

import io.netty.channel.ChannelFuture;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@SpringBootApplication
public class SpringBootNettyServerApplication implements CommandLineRunner {
    

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNettyServerApplication.class, args);
    }

    @Resource
    private NettyServer nettyServer;

    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9001);
        ChannelFuture future = nettyServer.run(address);
        // 优雅关闭连接，在JVM销毁前执行的一个线程.
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                nettyServer.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}

