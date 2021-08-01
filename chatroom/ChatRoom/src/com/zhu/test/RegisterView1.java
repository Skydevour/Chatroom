package com.zhu.test;

import com.zhu.test.Success;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
 
public class RegisterView1 extends JFrame implements ActionListener{
		JTextField textName,textName1,textName2,textName3,textName4;
		JButton button;
		Register register;
	
	public RegisterView1(String id) {							//����ǰһ�������id����Ȼ��ѯ����		
		register=new Register();
		register.setID(id);                                    //�Ȱ�֮ǰһ����������ݴ洢����ʱ��Ҫ��
		
		JFrame frame = new JFrame();	
		frame.setTitle("ע�������Ϣ");
		frame.setSize(400,650);
		frame.setLocationRelativeTo(null);
 
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
		
		frame.setLayout(fl);
 	
		ImageIcon icon = new ImageIcon("D:\\ƽʱ�����ϰ\\test\\src\\ashu.gif");		
		JLabel labIcon = new JLabel(icon);
		Dimension dim = new Dimension(400,300);
		labIcon.setPreferredSize(dim);
		frame.add(labIcon);
 	
		JLabel labName = new JLabel("����:");
		frame.add(labName);
 
		textName = new JTextField();
		Dimension dim1 = new Dimension(300,30);
		textName.setPreferredSize(dim1);
		frame.add(textName);
			
		JLabel labName1 = new JLabel("�Ա�:");
		frame.add(labName1);
 
		textName1 = new JTextField();
		textName1.setPreferredSize(dim1);
		frame.add(textName1);
		
		JLabel labName2 = new JLabel("��������:");
		frame.add(labName2);
 
		textName2 = new JTextField();
		textName2.setPreferredSize(dim1);
		frame.add(textName2);
				
		JLabel labName3 = new JLabel("ְҵ:");
		frame.add(labName3);
 
		textName3 = new JTextField();
		textName3.setPreferredSize(dim1);
		frame.add(textName3);
		
		JLabel labName4 = new JLabel("��ϵ��ʽ:");
		frame.add(labName4);
 
		textName4 = new JTextField();
		textName4.setPreferredSize(dim1);
		frame.add(textName4);
	
		button=new JButton();
		Dimension dim2 = new Dimension(100,30);
		button.setText("�ύ");	
		button.setSize(dim2);
		frame.add(button);
		button.addActionListener(this);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//�رմ���
	}
	
	 public void actionPerformed(ActionEvent e) {
		 register.setRealname(textName.getText());				//����
		 register.setSex(textName1.getText());					//�Ա�
		 register.setBirth(textName2.getText());				//����
		 register.setOccupation(textName3.getText());  			//ְҵ
		 register.setTelephone(textName4.getText()); 			//�绰
		 HandleInsertData handlereister=new HandleInsertData();
 		 handlereister.writeRegister1(register);
 		 new Success();
 		 this.dispose();
	 }
}
