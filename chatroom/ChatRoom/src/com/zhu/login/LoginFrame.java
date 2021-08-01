package com.zhu.login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.zhu.chatbean.ChatBean;
import com.zhu.chatframe.ChatFrame;
import com.zhu.tool.Tools;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameField;
	private JTextField ip1;
	private JTextField ip2;
	private JTextField ip3;
	private JTextField ip4;
	private JComboBox headIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 374, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u59D3\u540D\uFF1A");
		label.setBounds(23, 22, 97, 21);
		contentPane.add(label);
		
		userNameField = new JTextField();
		userNameField.setBounds(115, 22, 78, 21);
		contentPane.add(userNameField);
		userNameField.setColumns(10);
		
		JLabel lblip = new JLabel("\u8BF7\u8F93\u5165\u670D\u52A1\u5668ip\uFF1A");
		lblip.setBounds(10, 88, 97, 15);
		contentPane.add(lblip);
		
		ip1 = new JTextField();
		ip1.setBounds(109, 85, 40, 21);
		contentPane.add(ip1);
		ip1.setColumns(10);
		
		ip2 = new JTextField();
		ip2.setColumns(10);
		ip2.setBounds(159, 85, 40, 21);
		contentPane.add(ip2);
		
		ip3 = new JTextField();
		ip3.setColumns(10);
		ip3.setBounds(213, 85, 40, 21);
		contentPane.add(ip3);
		
		ip4 = new JTextField();
		ip4.setColumns(10);
		ip4.setBounds(272, 85, 40, 21);
		contentPane.add(ip4);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					login();
				} 
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(42, 161, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u9000\u51FA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//退出登录界面
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(190, 161, 93, 23);
		contentPane.add(btnNewButton_1);
		
		headIndex = new JComboBox();
		headIndex.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		headIndex.setBounds(280, 22, 45, 21);
		contentPane.add(headIndex);
		
		JLabel label_1 = new JLabel("\u9009\u62E9\u5934\u50CF\uFF1A");
		label_1.setBounds(205, 22, 65, 21);
		contentPane.add(label_1);
	}

	protected void login() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String name=userNameField.getText();
		if("".equals(name)||null==name){
			JOptionPane.showMessageDialog(this, "请输入用户名!");
			return;
		}
		//创建一个数据包
		ChatBean chatBean=new ChatBean();
		//获取当前时间
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日： hh时mm分ss秒");
		String time=sdf.format(date);
		chatBean.setTime(time);
		
		chatBean.setState(Tools.USER_LOGIN);
		chatBean.setUserName(name);
		
		StringBuffer ip=new StringBuffer();
		ip.append(ip1.getText());
		ip.append(".");
		ip.append(ip2.getText());
		ip.append(".");
		ip.append(ip3.getText());
		ip.append(".");
		ip.append(ip4.getText());
		
		int index=Integer.parseInt((String)headIndex.getSelectedItem());
		chatBean.setHeadIndex(index);
		Socket socket;
		try {
			socket = new Socket(ip.toString(),Tools.SERVER_PORT);
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(chatBean);
			oos.flush();
			
			
			
			//登陆成功后隐藏该登录界面并显示聊天界面
			
			ChatFrame chatFrame=new ChatFrame(socket,name);
			 chatFrame.setVisible(true);
			 this.setVisible(false);
			 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"请输入正确的服务器ip地址");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
	}
}
