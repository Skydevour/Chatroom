package com.zhu.chatbean;

import java.io.Serializable;
import java.util.List;

import com.zhu.client.Client;
import com.zhu.user.User;

//���͵����ݰ���
public class ChatBean implements Serializable{
	//������Ϣ���û���
	private String userName;
	//���͵���Ϣ����
	private String message;
	//��Ϣ������
	private int state;
	//Ҫ���͵��û�
	private String to;
	
	//������Ϣ��ʱ��
	private String time;
	//�����û�
	private List<User>onlineUsers;
	//ͷ��
	private int headIndex;
	public int getHeadIndex() {
		return headIndex;
	}
	public void setHeadIndex(int headIndex) {
		this.headIndex = headIndex;
	}
	public List<User> getOnlineUsers() {
		return onlineUsers;
	}
	public void setOnlineUsers(List<User> onlineUsers) {
		this.onlineUsers = onlineUsers;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getState() {
		return state;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
}
