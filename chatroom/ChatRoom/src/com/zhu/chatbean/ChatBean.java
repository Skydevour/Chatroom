package com.zhu.chatbean;

import java.io.Serializable;
import java.util.List;

import com.zhu.client.Client;
import com.zhu.user.User;

//发送的数据包类
public class ChatBean implements Serializable{
	//发送信息的用户名
	private String userName;
	//发送的消息内容
	private String message;
	//消息的类型
	private int state;
	//要发送的用户
	private String to;
	
	//发送消息的时间
	private String time;
	//在线用户
	private List<User>onlineUsers;
	//头像
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
