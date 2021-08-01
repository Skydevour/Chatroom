package com.zhu.clientrunnable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.zhu.chatbean.ChatBean;
import com.zhu.tool.Tools;

public class ClientRunnable implements Runnable{
	private Socket socket;
	
	
	public ClientRunnable(Socket socket){
		this.socket=socket;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	
	}

}
