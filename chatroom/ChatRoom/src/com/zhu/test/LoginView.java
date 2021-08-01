package com.zhu.test;

import com.zhu.test.Login;
import com.zhu.test.HandleLogin;
import com.zhu.test.ChatWindow;
import com.zhu.test.RegisterView;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import java.awt.event.*;

public class LoginView extends JFrame implements ActionListener{
		Login login;								//设置id，密码
		JTextField inputID;							//账号框中文件
		JPasswordField inputPassword;				//密码框中文件
		JButton buttonLogin;						//登陆按钮
		JButton buttonregister;						//注册界面按钮
		boolean loginSeccess;                       //判断登陆是否成功
		Container c = getContentPane(); 			//获取JFrame面板
		
		LoginView(){
			login=new Login();					
	        setBak(); //调用背景方法
	        JPanel jp = new JPanel(); //创建个JPanel
	        jp.setOpaque(false); //把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
	        c.add(jp);
	        setSize(812, 581);
	        setVisible(true);
	        setTitle("登陆");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭规则
	        setLayout(new GridBagLayout());  //设置窗体网格组布局
	        setLocationRelativeTo(null); //居中展示窗体
	        setResizable(false);
	        init();  //调用组件方法
	        createJLable();
	        setVisible(true);  //强制刷新
	    }
		
		void init() {
	        //登陆按钮
	        GridBagConstraints g1 = new GridBagConstraints();
	        g1.gridx = 4;
	        g1.gridy = 9;
	        g1.gridheight = 1;
	        g1.gridwidth = 1;
	        buttonLogin=new JButton("登录");
	        c.add(buttonLogin, g1);
	        buttonLogin.addActionListener(this);

	        //账号标签
	        GridBagConstraints g2 = new GridBagConstraints();
	        g2.gridx = 2;
	        g2.gridy = 3;
	        g2.gridheight = 1;
	        g2.gridwidth = 1;
	        g2.fill = GridBagConstraints.BOTH;
	        c.add(new JLabel("帐号："), g2);

	        //密码标签
	        GridBagConstraints g3 = new GridBagConstraints();
	        g3.gridx = 2;
	        g3.gridy = 5;
	        g3.gridheight = 1;
	        g3.gridwidth = 1;
	        g3.fill = GridBagConstraints.BOTH;
	        c.add(new JLabel("密码："), g3);

	        //账号文本框
	        GridBagConstraints g4 = new GridBagConstraints();
	        g4.gridx = 3;
	        g4.gridy = 3;
	        g4.gridheight = 1;
	        g4.gridwidth = 1;
	        inputID=new JTextField(15);
	        c.add(inputID, g4);

	        //密码框
	        GridBagConstraints g5 = new GridBagConstraints();
	        g5.gridx = 3;
	        g5.gridy = 5;
	        g5.gridheight = 1;
	        g5.gridwidth = 1;
	        inputPassword=new JPasswordField(15);
	        c.add(inputPassword, g5);

	        //头像图片插入
	        GridBagConstraints g6 = new GridBagConstraints();
	        g6.gridx = 3;
	        g6.gridy = 0;
	        g6.gridheight = 2;
	        g6.gridwidth = 1;
	        JLabel tx = new JLabel();
	        URL url1 = LoginView.class.getResource("/touxiang.png");
	        Icon icon1 = new ImageIcon(url1);
	        tx.setIcon(icon1);
	        c.add(tx, g6);
	        
	        //注册按钮
	        GridBagConstraints g7 = new GridBagConstraints();
	        g7.gridx = 2;
	        g7.gridy = 9;
	        g7.gridheight = 1;
	        g7.gridwidth = 1;
	        buttonregister=new JButton("注册");
	        c.add(buttonregister, g7);
	        buttonregister.addActionListener(this);
	    }
			
	    public void setBak(){
	        ((JPanel)this.getContentPane()).setOpaque(false);
	        ImageIcon img = new ImageIcon("D:\\平时编程练习\\test\\src\\beijing.png"); //添加图片
	        JLabel background = new JLabel(img);
	        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
	        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	    }
	    
	    public void createJLable(){
	        for(int i = 0;i<10;i++) {
	            GridBagConstraints gl1 = new GridBagConstraints();
	            gl1.gridx = i;gl1.gridy = 0;
	            c.add(new JLabel(" "),gl1);

	            GridBagConstraints gl2 = new GridBagConstraints();
	            gl2.gridx = 0;gl2.gridy = i;
	            c.add(new JLabel(" "),gl2);
	        }
	    }
		
		public boolean isLoginSuccess() {            //判断登陆是否成功
			return loginSeccess;
		}
		
		public void actionPerformed(ActionEvent e) {             //监听输入数据 
			if(e.getSource() == buttonLogin)			//登陆按钮
			{
				String id;				
				login.setID(inputID.getText());			//输入账号传递
				char [] pw=inputPassword.getPassword(); //密码格式转化
				login.setPassword(new String(pw));      //输入的密码传递
				HandleLogin handleLogin=new HandleLogin();
				login=handleLogin.queryVerify(login);	//查询是否存在此人
				loginSeccess=login.getLoginSuccess();   //判断是否成功登陆
				id=login.getID();					//为传递到另一个界面做准备
				if(loginSeccess==true) {
					new ChatWindow(id);
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,"登陆失败","注意",JOptionPane.WARNING_MESSAGE);
				}
				
			} 
			if(e.getSource() == buttonregister)	         //注册按钮
			{
				new RegisterView();
				this.dispose();
			} 
		}
}
