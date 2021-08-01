package com.zhu.test;
import com.zhu. test.RegisterView1;
import com.zhu.test.LoginView;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class RegisterView extends JFrame implements ActionListener{
	Register register;
    private JPanel contentPane;    								//����������
    private JTextField textField;
    private JTextField YtextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton button,button2;

    public RegisterView(){
        //���ô���
    	register=new Register();
        setTitle("ע��");                                		//���ڱ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    		//����ر�
        setBounds(200, 200, 500, 450);   						//����λ�ã����ڴ�С
        contentPane = new JPanel();                       		//����������
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		//�������е������߽�ľ���
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(8, 1, 5, 5));  	//����GridLayout�еĲ���
        contentPane.setBackground(Color.white);   				//����������ı���ɫ
        //������ʽ
        //����Welcome
        JPanel panel1 = new JPanel();   						//����Jpanel1���
        contentPane.add(panel1);        						//���Jpanel1���
        panel1.setBackground(Color.white);  					//����Jpanel1��屳��ɫ

        JLabel label1 = new JLabel("Welcome");  				//������ǩ���
        label1.setFont(new Font("Times New Roman", Font.ITALIC + Font.BOLD, 28));//���ñ�ǩ���弰��С
        panel1.add(label1);   									//��ӱ�ǩ

        //�����û���
        JPanel panel = new JPanel(); 							//����Jpanel3���
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));		//�������λ��
        contentPane.add(panel);  								//������
        panel.setBackground(Color.white); 						//������屳��ɫ

        JLabel label = new JLabel("��  ��  ��"); 					//������ǩ
        label.setFont(new Font("���ķ���", Font.PLAIN, 16));		//���ñ�ǩ���弰��С
        panel.add(label);										//��ӱ�ǩ

        YtextField = new JTextField(11);			 			//�����ı������
        panel.add(YtextField);									//����ı������
        YtextField.setColumns(18);								//���ÿ��


        //�����ǳ�
        JPanel panel2 = new JPanel(); 							//����Jpanel3���
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));	//�������λ��
        contentPane.add(panel2);  								//������
        panel2.setBackground(Color.white); 						//������屳��ɫ

        JLabel label2 = new JLabel("��       ��"); 					//������ǩ
        label2.setFont(new Font("���ķ���", Font.PLAIN, 16));	//���ñ�ǩ���弰��С
        panel2.add(label2);										//��ӱ�ǩ

        textField = new JTextField(10); 						//�����ı������
        panel2.add(textField);									//����ı������
        textField.setColumns(18);								//���ÿ��


        //����
        JPanel panel3 = new JPanel();							//����Jpanel3���
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER));	//�������λ��
        contentPane.add(panel3);								//������
        panel3.setBackground(Color.white);						//������屳��ɫ

        JLabel label3 = new JLabel("��       ��");					//������ǩ
        label3.setFont(new Font("���ķ���", Font.PLAIN, 16));	//���ñ�ǩ���弰��С
        panel3.add(label3);										//��ӱ�ǩ

        passwordField1 = new JPasswordField();					//������������
        passwordField1.setColumns(18);							//���ÿ��
        panel3.add(passwordField1);								//��������

        //ȷ������
        JPanel panel4 = new JPanel();							//����Jpanel4���
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER));	//�������λ��
        contentPane.add(panel4);								//������
        panel4.setBackground(Color.white);						//������屳��ɫ

        JLabel label4 = new JLabel("ȷ������");					//������ǩ
        label4.setFont(new Font("���ķ���", Font.PLAIN, 16));	//���ñ�ǩ�����ʽ
        panel4.add(label4);										//��ӱ�ǩ

        passwordField2 = new JPasswordField();					//������������
        passwordField2.setColumns(18);							//���ÿ��
        panel4.add(passwordField2);								//��������

        //����
        JPanel panel7 = new JPanel();							//�������7
        panel7.setLayout(new FlowLayout(FlowLayout.CENTER));	//�������λ��
        contentPane.add(panel7);								//������
        panel7.setBackground(Color.white);						//���ñ���ɫ

        JCheckBox checkBox1 = new JCheckBox("�����Ķ���ͬ��������������");//������ѡ�����
        checkBox1.setFont(new Font("����", Font.PLAIN, 12));		//���ø�ѡ�����ָ�ʽ
        panel7.add(checkBox1);									//��Ӹ�ѡ��
        checkBox1.setBackground(Color.white);					//���ø�ѡ�򱳾�ɫ

        //ע�ᰴť
        JPanel panel6 = new JPanel();							//�������6���
        contentPane.add(panel6);								//������
        panel6.setBackground(Color.white);						//������屳��ɫ

        button = new JButton("����ע��");						//������ť1���
        button.setFont(new Font("���ķ���", Font.PLAIN, 16));	//���ð�ť�����ʽ
        panel6.add(button);										//��Ӱ�ť
        button.setBackground(Color.LIGHT_GRAY);					//���ð�ť����ɫ
        button.setForeground(Color.BLACK);						//���ð�ťǰ��ɫ
        button.addActionListener(this);

        JPanel panel8 = new JPanel();							//�������8���
        panel8.setLayout(new FlowLayout(FlowLayout.RIGHT));		//�������λ��
        contentPane.add(panel8);								//������
        panel8.setBackground(Color.white);						//���ñ���ɫ

        //����
        button2 = new JButton("����");							//������ť2���
        button2.setFont(new Font("���ķ���", Font.PLAIN, 12));	//���ð�ť�����ʽ
        panel8.add(button2);									//��Ӱ�ť
        button2.setBackground(Color.lightGray);					//���ð�ť����ɫ
        button2.setForeground(Color.BLACK);						//���ð�ťǰ��ɫ
        button2.addActionListener(this);
        	
        setVisible(true);
        setBackground(Color.white);
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==button){
    		String id;
        	register.setID(YtextField.getText());						//�����˺Ŵ���
        	id=register.getID();										//Ϊ���ݵ���һ��������׼��
        	register.setName(textField.getText());						//�����ǳ�
        	char [] pw=passwordField1.getPassword();					//�ж��������������Ƿ����
        	char [] pw1=passwordField2.getPassword();
        	String s1=new String(pw);
        	String s2=new String(pw1);
        	if(!s1.equals(s2)) {										//������˳�
        		JOptionPane.showMessageDialog(null,"�����������벻���","ע��!",JOptionPane.WARNING_MESSAGE);
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

