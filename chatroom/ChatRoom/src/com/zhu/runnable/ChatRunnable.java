package com.zhu.runnable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.zhu.chatbean.ChatBean;
import com.zhu.client.Client;
import com.zhu.server.ChatServer;
import com.zhu.tool.Tools;
import com.zhu.user.User;

public class ChatRunnable implements Runnable{
	private Socket socket;
	private ObjectInputStream oiStream;
	private ObjectOutputStream ooStream;
	public ChatRunnable(Socket socket) throws IOException {
		// TODO Auto-generated constructor stub
		this.socket=socket;
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				oiStream=new ObjectInputStream(socket.getInputStream());
				
				ChatBean chatBean=(ChatBean)oiStream.readObject();
				switch(chatBean.getState()){
					case Tools.USER_LOGIN:  //有新的用户登录
					{
						//将该用户添加到在线用户列表
						Client newClient=new Client(); 
						newClient.setSocket(socket);
						newClient.setUserName(chatBean.getUserName());
						newClient.setUser(new User(chatBean.getUserName(), chatBean.getHeadIndex()));
						ChatServer.onlines.put(chatBean.getUserName(), newClient);
						
						//通知所有在线用户有人上线了
						ChatBean bean=new ChatBean();  //创建一个新的数据包用来发送给所有在线用户
						bean.setTime(chatBean.getTime());
						bean.setState(Tools.USER_LOGIN);
						List<User>onlineUsers=new LinkedList<User>();
						for(String name:ChatServer.onlines.keySet()){
							User user=ChatServer.onlines.get(name).getUser();
							onlineUsers.add(user);
						}
						bean.setOnlineUsers(onlineUsers);
						bean.setUserName(chatBean.getUserName());
						//发送给所有在线用户
						sendAll(bean);
						break;
						
					}
					//有用户退出
					case Tools.USER_LOGOUT:
					{
						
						String name=chatBean.getUserName();
						//将该用户从在线列表中删除
						ChatServer.onlines.remove(name);
						
						//通知该用户可以安全退出了
						ChatBean cb=new ChatBean();
						cb.setState(Tools.CAN_LEAVE);
						ooStream=new ObjectOutputStream(socket.getOutputStream());
						ooStream.writeObject(cb);
						//通知所有在线用户有人下线了
						ChatBean bean=new ChatBean();  //创建一个新的数据包用来发送给所有在线用户
						bean.setTime(chatBean.getTime());
						bean.setState(Tools.USER_LOGOUT);
						
						
						List<User>onlineUsers=new LinkedList<User>();
						for(String name1:ChatServer.onlines.keySet()){
							User user=ChatServer.onlines.get(name1).getUser();
							onlineUsers.add(user);
						}
						bean.setOnlineUsers(onlineUsers);
						
						bean.setUserName(chatBean.getUserName());
						//发送给所有在线用户
						sendAll(bean);
						//关闭连接
						socket.close();
						return ;
					}
					//有用户发送消息
					case Tools.SEND_MESSAGE:
					{
						
						ChatBean bean=new ChatBean();  
						bean.setTime(chatBean.getTime());
						bean.setState(Tools.RECEIVE_MESSAGE);
						bean.setUserName(chatBean.getUserName());
						bean.setTo(chatBean.getTo());
						bean.setMessage(chatBean.getMessage());
						//发送给所有在线用户
						send(bean);
						break;
					}
					
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void send(ChatBean bean) throws IOException {
		// TODO Auto-generated method stub
		String name=bean.getTo();
		Client c=ChatServer.onlines.get(name);
		Socket s=c.getSocket();
		ooStream=new ObjectOutputStream(s.getOutputStream());
		ooStream.writeObject(bean);
	}
	private void sendAll(ChatBean bean) throws IOException {
		// TODO Auto-generated method stub
		Iterator<String>it=ChatServer.onlines.keySet().iterator();
		//遍历所有在线用户
		while(it.hasNext()){
			String name=it.next();
			Client c=ChatServer.onlines.get(name);
			Socket s=c.getSocket();
			ooStream=new ObjectOutputStream(s.getOutputStream());
			ooStream.writeObject(bean);
		}
	}

}
