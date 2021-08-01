package com.zhu.test;

import com.zhu.test.Success;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
 
public class RegisterView1 extends JFrame implements ActionListener{
		JTextField textName,textName1,textName2,textName3,textName4;
		JButton button;
		Register register;
	
	public RegisterView1(String id) {							//接收前一个界面的id，不然查询不了		
		register=new Register();
		register.setID(id);                                    //先把之前一个界面的数据存储，到时候要用
		
		JFrame frame = new JFrame();	
		frame.setTitle("注册具体信息");
		frame.setSize(400,650);
		frame.setLocationRelativeTo(null);
 
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
		
		frame.setLayout(fl);
 	
		ImageIcon icon = new ImageIcon("D:\\平时编程练习\\test\\src\\ashu.gif");		
		JLabel labIcon = new JLabel(icon);
		Dimension dim = new Dimension(400,300);
		labIcon.setPreferredSize(dim);
		frame.add(labIcon);
 	
		JLabel labName = new JLabel("姓名:");
		frame.add(labName);
 
		textName = new JTextField();
		Dimension dim1 = new Dimension(300,30);
		textName.setPreferredSize(dim1);
		frame.add(textName);
			
		JLabel labName1 = new JLabel("性别:");
		frame.add(labName1);
 
		textName1 = new JTextField();
		textName1.setPreferredSize(dim1);
		frame.add(textName1);
		
		JLabel labName2 = new JLabel("出生日期:");
		frame.add(labName2);
 
		textName2 = new JTextField();
		textName2.setPreferredSize(dim1);
		frame.add(textName2);
				
		JLabel labName3 = new JLabel("职业:");
		frame.add(labName3);
 
		textName3 = new JTextField();
		textName3.setPreferredSize(dim1);
		frame.add(textName3);
		
		JLabel labName4 = new JLabel("联系方式:");
		frame.add(labName4);
 
		textName4 = new JTextField();
		textName4.setPreferredSize(dim1);
		frame.add(textName4);
	
		button=new JButton();
		Dimension dim2 = new Dimension(100,30);
		button.setText("提交");	
		button.setSize(dim2);
		frame.add(button);
		button.addActionListener(this);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//关闭窗口
	}
	
	 public void actionPerformed(ActionEvent e) {
		 register.setRealname(textName.getText());				//姓名
		 register.setSex(textName1.getText());					//性别
		 register.setBirth(textName2.getText());				//生日
		 register.setOccupation(textName3.getText());  			//职业
		 register.setTelephone(textName4.getText()); 			//电话
		 HandleInsertData handlereister=new HandleInsertData();
 		 handlereister.writeRegister1(register);
 		 new Success();
 		 this.dispose();
	 }
}
