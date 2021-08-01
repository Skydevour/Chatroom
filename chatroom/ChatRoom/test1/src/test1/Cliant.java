package test1;

import java.io.BufferedReader;
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
		String name;
		System.out.println("用户名");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		name=br.readLine();
		new Thread(new Send(client,name)).start();
		new Thread(new Receive(client)).start();
	
	}
}
