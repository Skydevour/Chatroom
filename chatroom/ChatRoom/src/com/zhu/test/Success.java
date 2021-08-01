package com.zhu.test;

import java.awt.event.*;
import javax.swing.*;
 
public class Success extends JFrame implements ActionListener{
	 JButton button;
	public Success() {
		
		JFrame frame = new JFrame();
		
		frame.setTitle("注册成功");
		frame.setSize(800,850);
		frame.setLocationRelativeTo(null);
		
		frame.setLayout(null);
		
	    button=new JButton();
		button.setBounds(10,10,60,20);
        button.setText("返回");
		frame.add(button);
		button.addActionListener(this);
 
		ImageIcon icon = new ImageIcon("D:\\平时编程练习\\test\\src\\success.png");		
		JLabel labIcon = new JLabel(icon);
		labIcon.setBounds(50,35,700,750);
		frame.add(labIcon);
 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//关闭窗口
	}
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		new LoginView();	
	}
}
