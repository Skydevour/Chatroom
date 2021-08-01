package test1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * 在线聊天室：服客户端
 * 群聊，加入容器
 * 实现一个客户可以正常收发信息
 */
public class Chat 
{
	private static CopyOnWriteArrayList<Channel> all=new CopyOnWriteArrayList <Channel>();
	public static void main(String[] args) throws IOException 
	{
		///修改和遍历同时进行的容器CopyOnWriteArrayList
		
		//ArrayList无法实现并发性
		//private List<Channel> all=new ArrayList<Channel>();
		System.out.println("服务端");
		//1.指定端口，使用ServerSocket创建服务器
		@SuppressWarnings("resource")
		ServerSocket server=new ServerSocket(8888);
		//2.阻塞式等待连接accept
		while(true)
		{
			Socket client=server.accept();//client是一个客户端，管道  阻塞式
			System.out.println("一个客户端");
			Channel c=new Channel(client);
			all.add(c);//管理所有成员
			new Thread(c).start();
		}
	}
	
	static class Channel implements Runnable
	{
		private String name;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private Socket client;
		private boolean isrunning=true;
		
		public Channel(Socket client)
		{
			this.client=client;
			try 
			{
				dis = new DataInputStream (client.getInputStream());
				dos=new DataOutputStream (client.getOutputStream());
				this.name=receive();
				this.send("欢迎回来");
				sendOther(this.name+"进入聊天室",true);
			} 
			catch (IOException e) 
			{
				release();
			}
		}
		//接收消息
		String receive()
		{
			String msg="";
			try 
			{
				msg=dis.readUTF();
			} 
			catch (IOException e) 
			{
				release();
			}
			return msg;
		}
		//发送消息(群聊)
		void sendOther(String msg,boolean a)
		{
			
			for(Channel other: all)
			{
				if(other==this)
					continue;
				if(a)
					other.send(msg);
				else
				other.send(this.name+":"+msg);
			}
			
			
		}
		
		void send(String msg)
		{
			try 
			{
				dos.writeUTF(msg);
				dos.flush();
			} 
			catch (IOException e) 
			{
				release();
			}
		}
		//释放资源
		void release()
		{
			this.isrunning=false;
			Close.close(dis,dos,client);
		}
		@Override
		public void run() 
		{
			while(isrunning)
			{
				String msg=receive();
				if(msg.equals(""))
				{
					continue;
				}
				else
				{
					sendOther(msg,false);
				}
			}
			// TODO Auto-generated method stub
			
		}
	}
}
