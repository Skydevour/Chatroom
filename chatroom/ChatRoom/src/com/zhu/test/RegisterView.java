package com.zhu.test;
import com.zhu. test.RegisterView1;
import com.zhu.test.LoginView;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class RegisterView extends JFrame implements ActionListener{
	Register register;
    private JPanel contentPane;    								//声明组件面板
    private JTextField textField;
    private JTextField YtextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton button,button2;

    public RegisterView(){
        //设置窗口
    	register=new Register();
        setTitle("注册");                                		//窗口标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    		//点击关闭
        setBounds(200, 200, 500, 450);   						//窗口位置，窗口大小
        contentPane = new JPanel();                       		//定义组件面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		//组件面板中的组件离边界的距离
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(8, 1, 5, 5));  	//定义GridLayout中的布局
        contentPane.setBackground(Color.white);   				//定义组件面板的背景色
        //设置样式
        //设置Welcome
        JPanel panel1 = new JPanel();   						//创建Jpanel1面板
        contentPane.add(panel1);        						//添加Jpanel1面板
        panel1.setBackground(Color.white);  					//设置Jpanel1面板背景色

        JLabel label1 = new JLabel("Welcome");  				//创建标签组件
        label1.setFont(new Font("Times New Roman", Font.ITALIC + Font.BOLD, 28));//设置标签字体及大小
        panel1.add(label1);   									//添加标签

        //设置用户名
        JPanel panel = new JPanel(); 							//创建Jpanel3面板
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));		//设置面板位置
        contentPane.add(panel);  								//添加面板
        panel.setBackground(Color.white); 						//设置面板背景色

        JLabel label = new JLabel("用  户  名"); 					//创建标签
        label.setFont(new Font("华文仿宋", Font.PLAIN, 16));		//设置标签字体及大小
        panel.add(label);										//添加标签

        YtextField = new JTextField(11);			 			//创建文本框组件
        panel.add(YtextField);									//添加文本框组件
        YtextField.setColumns(18);								//设置宽度


        //设置昵称
        JPanel panel2 = new JPanel(); 							//创建Jpanel3面板
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));	//设置面板位置
        contentPane.add(panel2);  								//添加面板
        panel2.setBackground(Color.white); 						//设置面板背景色

        JLabel label2 = new JLabel("昵       称"); 					//创建标签
        label2.setFont(new Font("华文仿宋", Font.PLAIN, 16));	//设置标签字体及大小
        panel2.add(label2);										//添加标签

        textField = new JTextField(10); 						//创建文本框组件
        panel2.add(textField);									//添加文本框组件
        textField.setColumns(18);								//设置宽度


        //密码
        JPanel panel3 = new JPanel();							//创建Jpanel3面板
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER));	//设置面板位置
        contentPane.add(panel3);								//添加面板
        panel3.setBackground(Color.white);						//设置面板背景色

        JLabel label3 = new JLabel("密       码");					//创建标签
        label3.setFont(new Font("华文仿宋", Font.PLAIN, 16));	//设置标签字体及大小
        panel3.add(label3);										//添加标签

        passwordField1 = new JPasswordField();					//创建密码框组件
        passwordField1.setColumns(18);							//设置宽度
        panel3.add(passwordField1);								//添加密码框

        //确认密码
        JPanel panel4 = new JPanel();							//创建Jpanel4面板
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER));	//设置面板位置
        contentPane.add(panel4);								//添加面板
        panel4.setBackground(Color.white);						//设置面板背景色

        JLabel label4 = new JLabel("确认密码");					//创建标签
        label4.setFont(new Font("华文仿宋", Font.PLAIN, 16));	//设置标签字体格式
        panel4.add(label4);										//添加标签

        passwordField2 = new JPasswordField();					//创建密码框组件
        passwordField2.setColumns(18);							//设置宽度
        panel4.add(passwordField2);								//添加密码框

        //条款
        JPanel panel7 = new JPanel();							//创建面板7
        panel7.setLayout(new FlowLayout(FlowLayout.CENTER));	//设置面板位置
        contentPane.add(panel7);								//添加面板
        panel7.setBackground(Color.white);						//设置背景色

        JCheckBox checkBox1 = new JCheckBox("我已阅读并同意相关条款和政策");//创建复选框组件
        checkBox1.setFont(new Font("黑体", Font.PLAIN, 12));		//设置复选框文字格式
        panel7.add(checkBox1);									//添加复选框
        checkBox1.setBackground(Color.white);					//设置复选框背景色

        //注册按钮
        JPanel panel6 = new JPanel();							//创建面板6组件
        contentPane.add(panel6);								//添加面板
        panel6.setBackground(Color.white);						//设置面板背景色

        button = new JButton("立即注册");						//创建按钮1组件
        button.setFont(new Font("华文仿宋", Font.PLAIN, 16));	//设置按钮字体格式
        panel6.add(button);										//添加按钮
        button.setBackground(Color.LIGHT_GRAY);					//设置按钮背景色
        button.setForeground(Color.BLACK);						//设置按钮前景色
        button.addActionListener(this);

        JPanel panel8 = new JPanel();							//创建面板8组件
        panel8.setLayout(new FlowLayout(FlowLayout.RIGHT));		//设置面板位置
        contentPane.add(panel8);								//添加面板
        panel8.setBackground(Color.white);						//设置背景色

        //返回
        button2 = new JButton("返回");							//创建按钮2组件
        button2.setFont(new Font("华文仿宋", Font.PLAIN, 12));	//设置按钮字体格式
        panel8.add(button2);									//添加按钮
        button2.setBackground(Color.lightGray);					//设置按钮背景色
        button2.setForeground(Color.BLACK);						//设置按钮前景色
        button2.addActionListener(this);
        	
        setVisible(true);
        setBackground(Color.white);
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==button){
    		String id;
        	register.setID(YtextField.getText());						//输入账号传递
        	id=register.getID();										//为传递到另一个界面做准备
        	register.setName(textField.getText());						//传递昵称
        	char [] pw=passwordField1.getPassword();					//判断两次输入密码是否相等
        	char [] pw1=passwordField2.getPassword();
        	String s1=new String(pw);
        	String s2=new String(pw1);
        	if(!s1.equals(s2)) {										//不相等退出
        		JOptionPane.showMessageDialog(null,"两次输入密码不相等","注意!",JOptionPane.WARNING_MESSAGE);
    			System.exit(0);
        	}
        	else {
        		register.setPassword(s1);
        		HandleInsertData handlereister=new HandleInsertData();
        		handlereister.writeRegister(register);
        		new RegisterView1(id);
        		this.dispose();
        	}
    	}
    	else if(e.getSource()==button2){
    		new LoginView();
			this.dispose();
    	}
    }
    
}

