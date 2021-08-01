package test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * 在线聊天室：服务端
 */

public class Cliant 
{
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		System.out.println("客户端");
		//1.建立连接：使用Socket创建客户端+指定服务器地址和端口
		Socket client=new Socket("localhost",8888);
		//2.客户端发送消息		
		BufferedReader console=new BufferedReader(new InputStreamReader(System.in));
		String msg=console.readLine();
		DataOutputStream dos =new DataOutputStream(client.getOutputStream());
		dos.writeUTF(msg);
		dos.flush();
		//3.接受返回的消息
		DataInputStream dis=new DataInputStream(client.getInputStream());
		msg=dis.readUTF();
		System.out.println(msg);
		
		dos.close();
		dis.close();
		client.close();
	}
}
