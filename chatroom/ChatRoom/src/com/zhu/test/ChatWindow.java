package com.zhu.test;

import com.zhu.test.TimePanel;													//����
import com.zhu.test.imagePanel;													//ͷ��	
import com.zhu.tool.Tools;
import com.zhu.chatbean.ChatBean;
import com.zhu.chatframe.*;
import com.zhu.test.edit;														//�༭������Ϣ
//��������С
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.tree.DefaultMutableTreeNode;							//�����
import javax.swing.event.TreeSelectionEvent;							//���Ĳ���                
import javax.swing.event.TreeSelectionListener;                        //���������Ĳ���

public class ChatWindow	implements ActionListener						//�������
{
	JFrame fr;															//���ô���
	JTabbedPane tabbedPane;												//ѡ�
	JPanel panel1,contacts,news,infor;									//���
	JButton addfriends,edit,jump;										//��ť
	JTree tree;															//��
	TimePanel timePanel;
	imagePanel image=null;
	String id;															//���ڴ洢id��
	Connection con;
	ResultSet rs;
	Statement sql;

	public ChatWindow (String id){
		this.id=id;	
		fr=new JFrame();
		fr.setPreferredSize(new Dimension(350, 800));					//�����ҿ�Ϊ��350����Ϊ��800           
		fr.setResizable(false);											//�����Ҵ�С�̶�
		fr.setLocation(700,90);											//������λ��
		fr.setBackground(Color.white);                                  //�����ұ���Ϊ��ɫ
		fr.setLayout(new BorderLayout());								//���ò���Ϊ��
		fr.setTitle("������");											//����������
		PerInformation();												//������Ϣ���
		CreateTab();													//����һ��ѡ����
		fr.pack();														//�����˴��ڵĴ�С�����ʺ������������ѡ��С�Ͳ��֡�											
		fr.setVisible(true);											//��ʾ����								
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			//�رմ���		
	}
	
	void PerInformation(){    //������Ϣ���
			
		infor=new JPanel();										       	//����һ�����
		edit=new JButton("�༭");										//�����༭������Ϣ��ť
		edit.addActionListener(this);									//���ü���
		JPanel panel=new JPanel();										//���ڳн�һ����ť��
		panel.setPreferredSize(new Dimension(70,25)); 					//���ڶ���һ�����
		panel.add(edit);
		infor.setLayout(new BorderLayout());							//���ò���ΪBorderLayout
		image=new imagePanel();											//ͷ��
		timePanel=new TimePanel();										//ʱ��
		infor.add(panel,BorderLayout.NORTH);							//���ð�ť������
		infor.add(image,BorderLayout.CENTER);							//ͷ����������
		infor.add(timePanel.showtimePanel(),BorderLayout.EAST);			//��������������м�
		infor.setPreferredSize(new Dimension(350, 80));					//������Ϣ������С
		fr.add(infor,BorderLayout.NORTH);					           	//���ø�����Ϣ��������Ϸ�
	}

	void CreateTab() {													//����ѡ�		
		tabbedPane=new JTabbedPane();									//����ѡ�������
		tabbedPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));	//����ѡ���������ҵ�λ��
		Contacts();
		News();
		tabbedPane.add("��Ϣ",news);									//�������뵽ѡ���������
		tabbedPane.add("��ϵ��",contacts);
		fr.add(tabbedPane,BorderLayout.CENTER);							//ֱ�����ѡ�
		}

	void News(){														//��Ϣ������
		news=new JPanel();												//��Ϣ���
		news.setBackground(Color.white);								//��Ϣ���Ϊ��ɫ
		jump=new JButton("��ת");										//��ת��ť
		news.add(jump);													//��ת�İ�ť
		jump.addActionListener(this);									//����һ������
	}

	void Contacts(){
		contacts=new JPanel();														//��ϵ�˽ṹ���
		contacts.setLayout(new BorderLayout());										//������岼��Ϊ��	
				
		addfriends =new JButton("��Ӻ���");											//����һ����Ӻ��Ѱ�ť
		addfriends.addActionListener(this);											//��Ӻ��ѵĴ���
	

		contacts.add(addfriends,BorderLayout.NORTH);

		DefaultMutableTreeNode sort= new DefaultMutableTreeNode("���ѷ���");		//�����
		DefaultMutableTreeNode friend= new DefaultMutableTreeNode("�ҵĺ����б�");	//���
        DefaultMutableTreeNode family = new DefaultMutableTreeNode("����");			//���
        DefaultMutableTreeNode classmate = new DefaultMutableTreeNode("ͬѧ");		//���
		sort.add(friend);
		sort.add(family);
		sort.add(classmate);

		tree = new JTree(sort);													// ʹ�ø��ڵ㴴�������
		
		tree.setShowsRootHandles(true);											// ��������ʾ���ڵ���      
        tree.setEditable(true);													// �������ڵ�ɱ༭	       
        tree.addTreeSelectionListener(new TreeSelectionListener() {				// ���ýڵ�ѡ�м�����
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                System.out.println("��ǰ��ѡ�еĽڵ�: " + e.getPath());
            }
        });

		JScrollPane scrollPane = new JScrollPane(tree);                         //����������壬������
		contacts.add(scrollPane,BorderLayout.CENTER);							//�����ӵ������
	}
	
	String getID() {															//����һ���ӿڣ�ʹid���Դ���ȥ
		return id;
	}
	
	public void actionPerformed(ActionEvent e) {								//�����¼��ķ�������
		if(e.getSource() ==  addfriends) {
			JOptionPane.showMessageDialog(null, "������Ӻ��ѵİ�ť��");
		}
		if(e.getSource()==edit) {
			String id=getID();                                               //��id��ֵ���д���
			new edit(id);
		}
		if(e.getSource()==jump) {
			String id=getID();                                               //��id��ֵ���д���
			ChatBean chatBean=new ChatBean();
			//��ȡ��ǰʱ��
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd�գ� hhʱmm��ss��");
			String time=sdf.format(date);
			chatBean.setTime(time);
			
			chatBean.setState(Tools.USER_LOGIN);
			chatBean.setUserName(id);
			
			StringBuffer ip=new StringBuffer("127.0.0.1");
			Socket socket;
			try {
				socket = new Socket(ip.toString(),Tools.SERVER_PORT);
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(chatBean);
				oos.flush();
				
				
				
				//��½�ɹ������ظõ�¼���沢��ʾ�������
				
				ChatFrame chatFrame=new ChatFrame(socket,id);
				 chatFrame.setVisible(true);	 
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(addfriends, this,"��������ȷ�ķ�����ip��ַ", 0);
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				
			}
		}
	}
}


