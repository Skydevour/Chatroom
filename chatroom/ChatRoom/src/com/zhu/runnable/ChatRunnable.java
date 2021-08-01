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
					case Tools.USER_LOGIN:  //���µ��û���¼
					{
						//�����û���ӵ������û��б�
						Client newClient=new Client(); 
						newClient.setSocket(socket);
						newClient.setUserName(chatBean.getUserName());
						newClient.setUser(new User(chatBean.getUserName(), chatBean.getHeadIndex()));
						ChatServer.onlines.put(chatBean.getUserName(), newClient);
						
						//֪ͨ���������û�����������
						ChatBean bean=new ChatBean();  //����һ���µ����ݰ��������͸����������û�
						bean.setTime(chatBean.getTime());
						bean.setState(Tools.USER_LOGIN);
						List<User>onlineUsers=new LinkedList<User>();
						for(String name:ChatServer.onlines.keySet()){
							User user=ChatServer.onlines.get(name).getUser();
							onlineUsers.add(user);
						}
						bean.setOnlineUsers(onlineUsers);
						bean.setUserName(chatBean.getUserName());
						//���͸����������û�
						sendAll(bean);
						break;
						
					}
					//���û��˳�
					case Tools.USER_LOGOUT:
					{
						
						String name=chatBean.getUserName();
						//�����û��������б���ɾ��
						ChatServer.onlines.remove(name);
						
						//֪ͨ���û����԰�ȫ�˳���
						ChatBean cb=new ChatBean();
						cb.setState(Tools.CAN_LEAVE);
						ooStream=new ObjectOutputStream(socket.getOutputStream());
						ooStream.writeObject(cb);
						//֪ͨ���������û�����������
						ChatBean bean=new ChatBean();  //����һ���µ����ݰ��������͸����������û�
						bean.setTime(chatBean.getTime());
						bean.setState(Tools.USER_LOGOUT);
						
						
						List<User>onlineUsers=new LinkedList<User>();
						for(String name1:ChatServer.onlines.keySet()){
							User user=ChatServer.onlines.get(name1).getUser();
							onlineUsers.add(user);
						}
						bean.setOnlineUsers(onlineUsers);
						
						bean.setUserName(chatBean.getUserName());
						//���͸����������û�
						sendAll(bean);
						//�ر�����
						socket.close();
						return ;
					}
					//���û�������Ϣ
					case Tools.SEND_MESSAGE:
					{
						
						ChatBean bean=new ChatBean();  
						bean.setTime(chatBean.getTime());
						bean.setState(Tools.RECEIVE_MESSAGE);
						bean.setUserName(chatBean.getUserName());
						bean.setTo(chatBean.getTo());
						bean.setMessage(chatBean.getMessage());
						//���͸����������û�
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
		//�������������û�
		while(it.hasNext()){
			String name=it.next();
			Client c=ChatServer.onlines.get(name);
			Socket s=c.getSocket();
			ooStream=new ObjectOutputStream(s.getOutputStream());
			ooStream.writeObject(bean);
		}
	}

}
