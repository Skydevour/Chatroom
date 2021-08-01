package com.zhu.chatframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.zhu.cellrenderer.CellRenderer;
import com.zhu.chatbean.ChatBean;
import com.zhu.tool.Tools;
import com.zhu.user.User;

public class ChatFrame extends JFrame {
	private JPanel contentPane;
	private Socket socket;
	//用户名
	private String userName;
	//输入输出流 用来接收和发送数据包
	private ObjectInputStream oiStream;
	private ObjectOutputStream ooStream;
	private JScrollPane scrollPane;
	//显示消息的区域
	private JTextArea messageArea;
	
	//编辑消息的区域
	private JTextArea inputArea;
	
	
	//用来显示在线的用户
	private JList list;



	/**
	 * Create the frame.
	 */
	public ChatFrame(final Socket socket,final String userName) {
		this.socket=socket;
		this.userName=userName;
		setResizable(false);
		this.setTitle(userName+"的聊天室");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 473);
		contentPane = new JPanel(){

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				//画背景图
				g.drawImage(new ImageIcon("images\\background3.jpg").getImage(),0,0,getWidth(),getHeight(), null);
			}
			
		};
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton button = new JButton("\u53D1\u9001");
		button.addActionListener(new ActionListener() {
			//发送信息
			public void actionPerformed(ActionEvent e) {
				try {
					//发送信息
					sendMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton button_1 = new JButton("\u5173\u95ED");
		//关闭聊天框事件
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					existChatRoom();
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button)
									.addGap(94)
									.addComponent(button_1))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1)
								.addComponent(button))))
					.addContainerGap())
		);
		list = new JList();
		list.setBackground(new Color(255,255,255));
		scrollPane_2.setViewportView(list);
		Border etch = BorderFactory.createEtchedBorder();
		list.setBorder(BorderFactory.createTitledBorder(etch, 
			"在线客户", TitledBorder.LEADING, TitledBorder.TOP, new Font(
				"sdf", Font.BOLD, 20), Color.black));

		list.setCellRenderer(new CellRenderer());
		 inputArea = new JTextArea();
		scrollPane_1.setViewportView(inputArea);
		inputArea.setLineWrap(true);//激活自动换行功能 
		inputArea.setWrapStyleWord(true);//激活断行不断字功能 
		inputArea.setBorder(BorderFactory.createEmptyBorder());
		messageArea = new JTextArea();
		messageArea.setBackground(new Color(255,255,255));
		messageArea.setEditable(false);
		messageArea.setLineWrap(true);//激活自动换行功能 
		messageArea.setWrapStyleWord(true);//激活断行不断字功能 
		messageArea.setBorder(BorderFactory.createTitledBorder(etch, 
			"聊天区域:", TitledBorder.LEADING, TitledBorder.TOP, new Font(
				"sdf", Font.BOLD, 13), Color.black));
		scrollPane.setViewportView(messageArea);
		contentPane.setLayout(gl_contentPane);
		
		this.addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent e) {
				  try {
					existChatRoom();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
		});
		
		
		//创建一个线程来处理通讯
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true){
					ChatBean chatBean=null;
					try {
						if(socket==null){
							return ;
						}
						oiStream=new ObjectInputStream(socket.getInputStream());
						chatBean=(ChatBean) oiStream.readObject();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			
				switch(chatBean.getState()){
						
					case Tools.USER_LOGIN:  //有用户上线
					{
						//编辑用户上线信息并显示在聊天框上
						StringBuffer message=new StringBuffer();
						message.append(chatBean.getTime());
						message.append("\n");
						message.append("["+chatBean.getUserName()+"] ");
						message.append("兴高采烈的上线了！\n");
						messageArea.append(message.toString());
						//使消息显示区域的滚动条永远在最下面
						setMax();
						//更新在线用户列表
						list.setListData(chatBean.getOnlineUsers().toArray());
						list.updateUI();
						break;
					}
					case Tools.USER_LOGOUT: //有用户下线
					{
						//编辑用户下线信息并显示在聊天框上
						StringBuffer message=new StringBuffer();
						message.append(chatBean.getTime());
						message.append("\n");
						message.append("["+chatBean.getUserName()+"] ");
						message.append("依依不舍的下线了!\n");
						messageArea.append(message.toString());
						//使消息显示区域的滚动条永远在最下面
						setMax();
						//更新在线用户列表
						
						list.setListData(chatBean.getOnlineUsers().toArray());
						list.updateUI();
						break;
					}
					case Tools.RECEIVE_MESSAGE:  //接收别人发的消息
					{
						StringBuffer message=new StringBuffer();
						message.append(chatBean.getTime());
						message.append("\n");
						message.append("["+chatBean.getUserName()+"] ");
						message.append("对我说：   ");
						message.append(chatBean.getMessage());
						message.append("\n");
						//将信息显示在聊天框上
						messageArea.append(message.toString());
						//使消息显示区域的滚动条永远在最下面
						setMax();
						break;
					}
					//安全退出
					case Tools.CAN_LEAVE:
					{
						return;
					}
				}
			}
				
			}
		}).start();
	}
	
	
	protected void existChatRoom() throws IOException {
		// TODO Auto-generated method stub
		//创建一个数据包
				ChatBean chatBean=new ChatBean();
				//获取当前时间
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日： hh时mm分ss秒");
				String time=sdf.format(date);
				chatBean.setTime(time);
				
				chatBean.setState(Tools.USER_LOGOUT);
				chatBean.setUserName(userName);
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(chatBean);
	}


	protected void sendMessage() throws IOException {
		// TODO Auto-generated method stub
				//获取要发送的对象
				User to=(User) list.getSelectedValue();
				if(null==to){
					JOptionPane.showMessageDialog(this, "请先选择要发送的对象");
					return;
				}
				if(to.getUserName().equals(userName)){
					JOptionPane.showMessageDialog(this, "请不要给自己发送信息");
					return;
				}
				
				//创建一个数据包
				ChatBean chatBean=new ChatBean();
				//获取当前时间
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日： hh时mm分ss秒");
				String time=sdf.format(date);
				chatBean.setTime(time);
				
				chatBean.setState(Tools.SEND_MESSAGE);
				chatBean.setUserName(userName);
				chatBean.setTo(to.getUserName());
				//获取用户输入的消息
				String message=inputArea.getText();
				//清空输入框
				inputArea.setText("");
				
				//将发送的信息显示在自己的聊天框内
				StringBuffer sb=new StringBuffer();
				sb.append(time);
				sb.append("\n");
				sb.append("[ 我 ] 对");
				sb.append(" ["+to.getUserName()+"] ");
				sb.append("说:  ");
				sb.append(message+"\n");
				messageArea.append(sb.toString());
				//使消息显示区域的滚动条永远在最下面
				setMax();
				chatBean.setMessage(message);
				ooStream=new ObjectOutputStream(socket.getOutputStream());
				ooStream.writeObject(chatBean);
	}

	//使显示消息的区域永远在最下面
	public void setMax(){
		scrollPane.doLayout();
		JScrollBar j=scrollPane.getVerticalScrollBar();
		
		j.setValue(j.getMaximum());
		
	}
		
}
