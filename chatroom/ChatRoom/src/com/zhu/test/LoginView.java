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
		Login login;								//����id������
		JTextField inputID;							//�˺ſ����ļ�
		JPasswordField inputPassword;				//��������ļ�
		JButton buttonLogin;						//��½��ť
		JButton buttonregister;						//ע����水ť
		boolean loginSeccess;                       //�жϵ�½�Ƿ�ɹ�
		Container c = getContentPane(); 			//��ȡJFrame���
		
		LoginView(){
			login=new Login();					
	        setBak(); //���ñ�������
	        JPanel jp = new JPanel(); //������JPanel
	        jp.setOpaque(false); //��JPanel����Ϊ͸�� �����Ͳ�����ס����ı��� �����������JPanel����������
	        c.add(jp);
	        setSize(812, 581);
	        setVisible(true);
	        setTitle("��½");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رչ���
	        setLayout(new GridBagLayout());  //���ô��������鲼��
	        setLocationRelativeTo(null); //����չʾ����
	        setResizable(false);
	        init();  //�����������
	        createJLable();
	        setVisible(true);  //ǿ��ˢ��
	    }
		
		void init() {
	        //��½��ť
	        GridBagConstraints g1 = new GridBagConstraints();
	        g1.gridx = 4;
	        g1.gridy = 9;
	        g1.gridheight = 1;
	        g1.gridwidth = 1;
	        buttonLogin=new JButton("��¼");
	        c.add(buttonLogin, g1);
	        buttonLogin.addActionListener(this);

	        //�˺ű�ǩ
	        GridBagConstraints g2 = new GridBagConstraints();
	        g2.gridx = 2;
	        g2.gridy = 3;
	        g2.gridheight = 1;
	        g2.gridwidth = 1;
	        g2.fill = GridBagConstraints.BOTH;
	        c.add(new JLabel("�ʺţ�"), g2);

	        //�����ǩ
	        GridBagConstraints g3 = new GridBagConstraints();
	        g3.gridx = 2;
	        g3.gridy = 5;
	        g3.gridheight = 1;
	        g3.gridwidth = 1;
	        g3.fill = GridBagConstraints.BOTH;
	        c.add(new JLabel("���룺"), g3);

	        //�˺��ı���
	        GridBagConstraints g4 = new GridBagConstraints();
	        g4.gridx = 3;
	        g4.gridy = 3;
	        g4.gridheight = 1;
	        g4.gridwidth = 1;
	        inputID=new JTextField(15);
	        c.add(inputID, g4);

	        //�����
	        GridBagConstraints g5 = new GridBagConstraints();
	        g5.gridx = 3;
	        g5.gridy = 5;
	        g5.gridheight = 1;
	        g5.gridwidth = 1;
	        inputPassword=new JPasswordField(15);
	        c.add(inputPassword, g5);

	        //ͷ��ͼƬ����
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
	        
	        //ע�ᰴť
	        GridBagConstraints g7 = new GridBagConstraints();
	        g7.gridx = 2;
	        g7.gridy = 9;
	        g7.gridheight = 1;
	        g7.gridwidth = 1;
	        buttonregister=new JButton("ע��");
	        c.add(buttonregister, g7);
	        buttonregister.addActionListener(this);
	    }
			
	    public void setBak(){
	        ((JPanel)this.getContentPane()).setOpaque(false);
	        ImageIcon img = new ImageIcon("D:\\ƽʱ�����ϰ\\test\\src\\beijing.png"); //���ͼƬ
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
		
		public boolean isLoginSuccess() {            //�жϵ�½�Ƿ�ɹ�
			return loginSeccess;
		}
		
		public void actionPerformed(ActionEvent e) {             //������������ 
			if(e.getSource() == buttonLogin)			//��½��ť
			{
				String id;				
				login.setID(inputID.getText());			//�����˺Ŵ���
				char [] pw=inputPassword.getPassword(); //�����ʽת��
				login.setPassword(new String(pw));      //��������봫��
				HandleLogin handleLogin=new HandleLogin();
				login=handleLogin.queryVerify(login);	//��ѯ�Ƿ���ڴ���
				loginSeccess=login.getLoginSuccess();   //�ж��Ƿ�ɹ���½
				id=login.getID();					//Ϊ���ݵ���һ��������׼��
				if(loginSeccess==true) {
					new ChatWindow(id);
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,"��½ʧ��","ע��",JOptionPane.WARNING_MESSAGE);
				}
				
			} 
			if(e.getSource() == buttonregister)	         //ע�ᰴť
			{
				new RegisterView();
				this.dispose();
			} 
		}
}
