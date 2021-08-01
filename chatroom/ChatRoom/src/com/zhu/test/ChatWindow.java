package com.zhu.test;

import com.zhu.test.TimePanel;													//日历
import com.zhu.test.imagePanel;													//头像	
import com.zhu.tool.Tools;
import com.zhu.chatbean.ChatBean;
import com.zhu.chatframe.*;
import com.zhu.test.edit;														//编辑个人信息
//设置面板大小
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.tree.DefaultMutableTreeNode;							//树结点
import javax.swing.event.TreeSelectionEvent;							//树的操作                
import javax.swing.event.TreeSelectionListener;                        //监听对树的操作

public class ChatWindow	implements ActionListener						//聊天界面
{
	JFrame fr;															//设置窗口
	JTabbedPane tabbedPane;												//选项卡
	JPanel panel1,contacts,news,infor;									//面板
	JButton addfriends,edit,jump;										//按钮
	JTree tree;															//树
	TimePanel timePanel;
	imagePanel image=null;
	String id;															//用于存储id的
	Connection con;
	ResultSet rs;
	Statement sql;

	public ChatWindow (String id){
		this.id=id;	
		fr=new JFrame();
		fr.setPreferredSize(new Dimension(350, 800));					//聊天室宽为：350，高为：800           
		fr.setResizable(false);											//聊天室大小固定
		fr.setLocation(700,90);											//聊天室位置
		fr.setBackground(Color.white);                                  //聊天室背景为白色
		fr.setLayout(new BorderLayout());								//设置布局为空
		fr.setTitle("聊天室");											//聊天室名称
		PerInformation();												//个人信息面板
		CreateTab();													//创建一个选项卡面板
		fr.pack();														//调整此窗口的大小，以适合其子组件的首选大小和布局。											
		fr.setVisible(true);											//显示窗口								
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//关闭窗口		
	}
	
	void PerInformation(){    //个人信息面板
			
		infor=new JPanel();										       	//创建一个面板
		edit=new JButton("编辑");										//创建编辑个人信息按钮
		edit.addActionListener(this);									//设置监听
		JPanel panel=new JPanel();										//用于承接一个按钮的
		panel.setPreferredSize(new Dimension(70,25)); 					//用于定义一个面板
		panel.add(edit);
		infor.setLayout(new BorderLayout());							//设置布局为BorderLayout
		image=new imagePanel();											//头像
		timePanel=new TimePanel();										//时间
		infor.add(panel,BorderLayout.NORTH);							//设置按钮在上面
		infor.add(image,BorderLayout.CENTER);							//头像面板在左边
		infor.add(timePanel.showtimePanel(),BorderLayout.EAST);			//设置日期面板在中间
		infor.setPreferredSize(new Dimension(350, 80));					//个人信息的面板大小
		fr.add(infor,BorderLayout.NORTH);					           	//设置个人信息面板在最上方
	}

	void CreateTab() {													//创建选项卡		
		tabbedPane=new JTabbedPane();									//创建选项卡面板对象
		tabbedPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));	//设置选项卡框距离左右的位置
		Contacts();
		News();
		tabbedPane.add("消息",news);									//将面板加入到选项卡面板对象上
		tabbedPane.add("联系人",contacts);
		fr.add(tabbedPane,BorderLayout.CENTER);							//直接添加选项卡
		}

	void News(){														//消息编设置
		news=new JPanel();												//消息面板
		news.setBackground(Color.white);								//消息面板为白色
		jump=new JButton("跳转");										//跳转按钮
		news.add(jump);													//跳转的按钮
		jump.addActionListener(this);									//增加一个监听
	}

	void Contacts(){
		contacts=new JPanel();														//联系人结构面板
		contacts.setLayout(new BorderLayout());										//设置面板布局为空	
				
		addfriends =new JButton("添加好友");											//增加一个添加好友按钮
		addfriends.addActionListener(this);											//添加好友的窗口
	

		contacts.add(addfriends,BorderLayout.NORTH);

		DefaultMutableTreeNode sort= new DefaultMutableTreeNode("好友分类");		//根结点
		DefaultMutableTreeNode friend= new DefaultMutableTreeNode("我的好友列表");	//结点
        DefaultMutableTreeNode family = new DefaultMutableTreeNode("家人");			//结点
        DefaultMutableTreeNode classmate = new DefaultMutableTreeNode("同学");		//结点
		sort.add(friend);
		sort.add(family);
		sort.add(classmate);

		tree = new JTree(sort);													// 使用根节点创建树组件
		
		tree.setShowsRootHandles(true);											// 设置树显示根节点句柄      
        tree.setEditable(true);													// 设置树节点可编辑	       
        tree.addTreeSelectionListener(new TreeSelectionListener() {				// 设置节点选中监听器
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                System.out.println("当前被选中的节点: " + e.getPath());
            }
        });

		JScrollPane scrollPane = new JScrollPane(tree);                         //创建滚动面板，包裹树
		contacts.add(scrollPane,BorderLayout.CENTER);							//把树加到面板中
	}
	
	String getID() {															//设置一个接口，使id可以传出去
		return id;
	}
	
	public void actionPerformed(ActionEvent e) {								//监听事件的发生有无
		if(e.getSource() ==  addfriends) {
			JOptionPane.showMessageDialog(null, "这是添加好友的按钮。");
		}
		if(e.getSource()==edit) {
			String id=getID();                                               //把id的值进行传递
			new edit(id);
		}
		if(e.getSource()==jump) {
			String id=getID();                                               //把id的值进行传递
			ChatBean chatBean=new ChatBean();
			//获取当前时间
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日： hh时mm分ss秒");
			String time=sdf.format(date);
			chatBean.setTime(time);
			
			chatBean.setState(Tools.USER_LOGIN);
			chatBean.setUserName(id);
			
			StringBuffer ip=new StringBuffer("127.0.0.1");
			Socket socket;
			try {
				socket = new Socket(ip.toString(),Tools.SERVER_PORT);
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(chatBean);
				oos.flush();
				
				
				
				//登陆成功后隐藏该登录界面并显示聊天界面
				
				ChatFrame chatFrame=new ChatFrame(socket,id);
				 chatFrame.setVisible(true);	 
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(addfriends, this,"请输入正确的服务器ip地址", 0);
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				
			}
		}
	}
}


