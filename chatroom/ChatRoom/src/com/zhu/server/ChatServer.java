package com.zhu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.zhu.client.Client;
import com.zhu.runnable.ChatRunnable;
import com.zhu.tool.Tools;

public class ChatServer {
	//用来在线的用户
	//使用线程安全的Map因为可能会有多个线程同时访问该容器
	public static ConcurrentMap<String,Client>onlines=new ConcurrentHashMap<String,Client>(); 
	private ServerSocket serverSocket;
	public ChatServer() throws IOException{
		serverSocket=new ServerSocket(Tools.SERVER_PORT);
	}
	public void start() throws IOException{
		//无线循环等待用户连接
		while(true){     
			Socket socket=serverSocket.accept();
			//如果检测到连接就创建一个新的线程来处理
			System.out.println("有用户登录");
			Thread thread=new Thread(new ChatRunnable(socket));
			thread.start();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		//启动服务器
		ChatServer server=new ChatServer();
		System.out.println("服务器已启动");
		server.start();
	}
}
