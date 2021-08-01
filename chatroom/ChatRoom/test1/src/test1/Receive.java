package test1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{
	private DataInputStream dis;
	private Socket client;
	private boolean isrunning;
	public Receive(Socket client)
	{
		this.client=client;
		isrunning=true;
		try {
			dis=new DataInputStream (client.getInputStream());
		} catch (IOException e) {
			release();
		}
	}

	//接收消息
	private String receive()
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
	@Override
	public void run() 
	{
		while(isrunning)
		{
			String msg=receive();
			if(!msg.equals(""))
			{
				System.out.println(msg);
			}
		}
		
	}
	
	void release()
	{
		this.isrunning=false;
		Close.close(dis,client);
	}

}
