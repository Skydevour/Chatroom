package com.zhu.test;

import java.awt.event.*;
import javax.swing.*;
 
public class Success extends JFrame implements ActionListener{
	 JButton button;
	public Success() {
		
		JFrame frame = new JFrame();
		
		frame.setTitle("ע��ɹ�");
		frame.setSize(800,850);
		frame.setLocationRelativeTo(null);
		
		frame.setLayout(null);
		
	    button=new JButton();
		button.setBounds(10,10,60,20);
        button.setText("����");
		frame.add(button);
		button.addActionListener(this);
 
		ImageIcon icon = new ImageIcon("D:\\ƽʱ�����ϰ\\test\\src\\success.png");		
		JLabel labIcon = new JLabel(icon);
		labIcon.setBounds(50,35,700,750);
		frame.add(labIcon);
 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//�رմ���
	}
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		new LoginView();	
	}
}
