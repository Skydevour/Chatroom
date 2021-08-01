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
	//�������ߵ��û�
	//ʹ���̰߳�ȫ��Map��Ϊ���ܻ��ж���߳�ͬʱ���ʸ�����
	public static ConcurrentMap<String,Client>onlines=new ConcurrentHashMap<String,Client>(); 
	private ServerSocket serverSocket;
	public ChatServer() throws IOException{
		serverSocket=new ServerSocket(Tools.SERVER_PORT);
	}
	public void start() throws IOException{
		//����ѭ���ȴ��û�����
		while(true){     
			Socket socket=serverSocket.accept();
			//�����⵽���Ӿʹ���һ���µ��߳�������
			System.out.println("���û���¼");
			Thread thread=new Thread(new ChatRunnable(socket));
			thread.start();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		//����������
		ChatServer server=new ChatServer();
		System.out.println("������������");
		server.start();
	}
}
