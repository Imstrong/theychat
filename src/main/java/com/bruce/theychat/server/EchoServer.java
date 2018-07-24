package com.bruce.theychat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;
    public EchoServer(int port) {
        this.port=port;
    }
    public static void main(String[] args)throws Exception{
        new EchoServer(8888).start();
    }
    public void start()throws Exception{
        NioEventLoopGroup group=new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)//指定使用nio的传输channel
                    .localAddress(new InetSocketAddress(port))//设置socket地址使用所选端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new EchoServerHandler());
                }
            });
            ChannelFuture future = bootstrap.bind().sync();//绑定的服务器，sync等待服务器关闭
            future.channel().closeFuture().sync();//调用channel和块的关闭动作，直到它被关闭
        }finally {
            group.shutdownGracefully();//关闭EventLoopGroup释放所有资源
        }
    }
}
