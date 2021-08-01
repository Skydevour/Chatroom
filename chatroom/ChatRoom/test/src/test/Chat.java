package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 在线聊天室：服客户端
 * 群聊，加入容器
 * 实现一个客户可以正常收发信息
 */
public class Chat 
{
	public static void main(String[] args) throws IOException 
	{
		System.out.println("服务端");
		//1.指定端口，使用ServerSocket创建服务器
		@SuppressWarnings("resource")
		ServerSocket server=new ServerSocket(8888);
		//2.阻塞式等待连接accept
		Socket client=server.accept();//client是一个客户端，管道  阻塞式
		System.out.println("一个客户端");
		//3.接受消息
		DataInputStream dis=new DataInputStream(client.getInputStream());
		String msg=dis.readUTF();
		//4.返回消息
		DataOutputStream dos =new DataOutputStream(client.getOutputStream());
		dos.writeUTF(msg);
		dos.flush();
	}
}
