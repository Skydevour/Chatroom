package com.zhu.user;

import java.io.Serializable;

public class User implements Serializable{
	//�û���
	private String userName;
	//ͷ��
	private int headIndex;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, int headIndex) {
		super();
		this.userName = userName;
		this.headIndex = headIndex;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getHeadIndex() {
		return headIndex;
	}
	public void setHeadIndex(int headIndex) {
		this.headIndex = headIndex;
	}

}
