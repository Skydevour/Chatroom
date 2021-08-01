package com.zhu.chatframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.zhu.cellrenderer.CellRenderer;
import com.zhu.chatbean.ChatBean;
import com.zhu.tool.Tools;
import com.zhu.user.User;

public class ChatFrame extends JFrame {
	private JPanel contentPane;
	private Socket socket;
	//�û���
	private String userName;
	//��������� �������պͷ������ݰ�
	private ObjectInputStream oiStream;
	private ObjectOutputStream ooStream;
	private JScrollPane scrollPane;
	//��ʾ��Ϣ������
	private JTextArea messageArea;
	
	//�༭��Ϣ������
	private JTextArea inputArea;
	
	
	//������ʾ���ߵ��û�
	private JList list;



	/**
	 * Create the frame.
	 */
	public ChatFrame(final Socket socket,final String userName) {
		this.socket=socket;
		this.userName=userName;
		setResizable(false);
		this.setTitle(userName+"��������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 473);
		contentPane = new JPanel(){

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				//������ͼ
				g.drawImage(new ImageIcon("images\\background3.jpg").getImage(),0,0,getWidth(),getHeight(), null);
			}
			
		};
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton button = new JButton("\u53D1\u9001");
		button.addActionListener(new ActionListener() {
			//������Ϣ
			public void actionPerformed(ActionEvent e) {
				try {
					//������Ϣ
					sendMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton button_1 = new JButton("\u5173\u95ED");
		//�ر�������¼�
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					existChatRoom();
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button)
									.addGap(94)
									.addComponent(button_1))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1)
								.addComponent(button))))
					.addContainerGap())
		);
		list = new JList();
		list.setBackground(new Color(255,255,255));
		scrollPane_2.setViewportView(list);
		Border etch = BorderFactory.createEtchedBorder();
		list.setBorder(BorderFactory.createTitledBorder(etch, 
			"���߿ͻ�", TitledBorder.LEADING, TitledBorder.TOP, new Font(
				"sdf", Font.BOLD, 20), Color.black));

		list.setCellRenderer(new CellRenderer());
		 inputArea = new JTextArea();
		scrollPane_1.setViewportView(inputArea);
		inputArea.setLineWrap(true);//�����Զ����й��� 
		inputArea.setWrapStyleWord(true);//������в����ֹ��� 
		inputArea.setBorder(BorderFactory.createEmptyBorder());
		messageArea = new JTextArea();
		messageArea.setBackground(new Color(255,255,255));
		messageArea.setEditable(false);
		messageArea.setLineWrap(true);//�����Զ����й��� 
		messageArea.setWrapStyleWord(true);//������в����ֹ��� 
		messageArea.setBorder(BorderFactory.createTitledBorder(etch, 
			"��������:", TitledBorder.LEADING, TitledBorder.TOP, new Font(
				"sdf", Font.BOLD, 13), Color.black));
		scrollPane.setViewportView(messageArea);
		contentPane.setLayout(gl_contentPane);
		
		this.addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent e) {
				  try {
					existChatRoom();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
		});
		
		
		//����һ���߳�������ͨѶ
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true){
					ChatBean chatBean=null;
					try {
						if(socket==null){
							return ;
						}
						oiStream=new ObjectInputStream(socket.getInputStream());
						chatBean=(ChatBean) oiStream.readObject();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			
				switch(chatBean.getState()){
						
					case Tools.USER_LOGIN:  //���û�����
					{
						//�༭�û�������Ϣ����ʾ���������
						StringBuffer message=new StringBuffer();
						message.append(chatBean.getTime());
						message.append("\n");
						message.append("["+chatBean.getUserName()+"] ");
						message.append("�˸߲��ҵ������ˣ�\n");
						messageArea.append(message.toString());
						//ʹ��Ϣ��ʾ����Ĺ�������Զ��������
						setMax();
						//���������û��б�
						list.setListData(chatBean.getOnlineUsers().toArray());
						list.updateUI();
						break;
					}
					case Tools.USER_LOGOUT: //���û�����
					{
						//�༭�û�������Ϣ����ʾ���������
						StringBuffer message=new StringBuffer();
						message.append(chatBean.getTime());
						message.append("\n");
						message.append("["+chatBean.getUserName()+"] ");
						message.append("���������������!\n");
						messageArea.append(message.toString());
						//ʹ��Ϣ��ʾ����Ĺ�������Զ��������
						setMax();
						//���������û��б�
						
						list.setListData(chatBean.getOnlineUsers().toArray());
						list.updateUI();
						break;
					}
					case Tools.RECEIVE_MESSAGE:  //���ձ��˷�����Ϣ
					{
						StringBuffer message=new StringBuffer();
						message.append(chatBean.getTime());
						message.append("\n");
						message.append("["+chatBean.getUserName()+"] ");
						message.append("����˵��   ");
						message.append(chatBean.getMessage());
						message.append("\n");
						//����Ϣ��ʾ���������
						messageArea.append(message.toString());
						//ʹ��Ϣ��ʾ����Ĺ�������Զ��������
						setMax();
						break;
					}
					//��ȫ�˳�
					case Tools.CAN_LEAVE:
					{
						return;
					}
				}
			}
				
			}
		}).start();
	}
	
	
	protected void existChatRoom() throws IOException {
		// TODO Auto-generated method stub
		//����һ�����ݰ�
				ChatBean chatBean=new ChatBean();
				//��ȡ��ǰʱ��
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd�գ� hhʱmm��ss��");
				String time=sdf.format(date);
				chatBean.setTime(time);
				
				chatBean.setState(Tools.USER_LOGOUT);
				chatBean.setUserName(userName);
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(chatBean);
	}


	protected void sendMessage() throws IOException {
		// TODO Auto-generated method stub
				//��ȡҪ���͵Ķ���
				User to=(User) list.getSelectedValue();
				if(null==to){
					JOptionPane.showMessageDialog(this, "����ѡ��Ҫ���͵Ķ���");
					return;
				}
				if(to.getUserName().equals(userName)){
					JOptionPane.showMessageDialog(this, "�벻Ҫ���Լ�������Ϣ");
					return;
				}
				
				//����һ�����ݰ�
				ChatBean chatBean=new ChatBean();
				//��ȡ��ǰʱ��
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd�գ� hhʱmm��ss��");
				String time=sdf.format(date);
				chatBean.setTime(time);
				
				chatBean.setState(Tools.SEND_MESSAGE);
				chatBean.setUserName(userName);
				chatBean.setTo(to.getUserName());
				//��ȡ�û��������Ϣ
				String message=inputArea.getText();
				//��������
				inputArea.setText("");
				
				//�����͵���Ϣ��ʾ���Լ����������
				StringBuffer sb=new StringBuffer();
				sb.append(time);
				sb.append("\n");
				sb.append("[ �� ] ��");
				sb.append(" ["+to.getUserName()+"] ");
				sb.append("˵:  ");
				sb.append(message+"\n");
				messageArea.append(sb.toString());
				//ʹ��Ϣ��ʾ����Ĺ�������Զ��������
				setMax();
				chatBean.setMessage(message);
				ooStream=new ObjectOutputStream(socket.getOutputStream());
				ooStream.writeObject(chatBean);
	}

	//ʹ��ʾ��Ϣ��������Զ��������
	public void setMax(){
		scrollPane.doLayout();
		JScrollBar j=scrollPane.getVerticalScrollBar();
		
		j.setValue(j.getMaximum());
		
	}
		
}
