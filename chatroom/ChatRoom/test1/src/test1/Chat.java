package test1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * ���������ң����ͻ���
 * Ⱥ�ģ���������
 * ʵ��һ���ͻ����������շ���Ϣ
 */
public class Chat 
{
	private static CopyOnWriteArrayList<Channel> all=new CopyOnWriteArrayList <Channel>();
	public static void main(String[] args) throws IOException 
	{
		///�޸ĺͱ���ͬʱ���е�����CopyOnWriteArrayList
		
		//ArrayList�޷�ʵ�ֲ�����
		//private List<Channel> all=new ArrayList<Channel>();
		System.out.println("�����");
		//1.ָ���˿ڣ�ʹ��ServerSocket����������
		@SuppressWarnings("resource")
		ServerSocket server=new ServerSocket(8888);
		//2.����ʽ�ȴ�����accept
		while(true)
		{
			Socket client=server.accept();//client��һ���ͻ��ˣ��ܵ�  ����ʽ
			System.out.println("һ���ͻ���");
			Channel c=new Channel(client);
			all.add(c);//�������г�Ա
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
				this.send("��ӭ����");
				sendOther(this.name+"����������",true);
			} 
			catch (IOException e) 
			{
				release();
			}
		}
		//������Ϣ
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
		//������Ϣ(Ⱥ��)
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
		//�ͷ���Դ
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
