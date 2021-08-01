package test1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable{
	private BufferedReader console;
	private DataOutputStream dos;
	private Socket client;
	private boolean isrunning;
	@SuppressWarnings("unused")
	private String name;
	public Send(Socket client,String name)
	{
		this.name=name;
		this.client=client;
		console=new BufferedReader(new InputStreamReader(System.in));
		try 
		{
			dos=new DataOutputStream (client.getOutputStream());
			send(name);
		} 
		catch (IOException e) 
		{
			release();
		}
		isrunning=true;
	}
	
	void release()
	{
		this.isrunning=false;
		Close.close(dos,client);
	}
	
	//·¢ËÍÏûÏ¢
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isrunning)
		{
			String msg=getStrFromConsole();
			if(!msg.equals(""))
			{
				send(msg);
				
			}
			
		}
	}
	private String getStrFromConsole()
	{
		try 
		{
			return console.readLine();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}
}
