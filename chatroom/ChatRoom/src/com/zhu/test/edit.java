package com.zhu.test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class edit extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l5,l6,l7;//��ǩ
    JLabel pict;//ͷ��
    JButton buttona,buttonb;//��ť
    String id;				//����ҳ���Ͻ��ܹ�����id
    public edit(String id){
    	this.id=id;				//����id
    	setBackground(Color.GRAY);
    	setSize(300,500);
    	setLocation(300, 200);
    	setTitle("�༭������Ϣ");
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void init() {
        setLayout(new FlowLayout());
        l1 = new JLabel("�ǳ�");
        l2 = new JLabel("����ǩ��");
        l3 = new JLabel("����");
        l4 = new JLabel("�Ա�");
        l5 = new JLabel("��������");
        l6 = new JLabel("�绰");
        l7 = new JLabel("ְҵ");
        pict = new JLabel();
        buttona = new JButton("�޸�");
        buttonb = new JButton("����");
        buttona.addActionListener(this);
        buttonb.addActionListener(this);
        add(l1);
        add(new TextField("���û�����û������",20));
        add(l2);
        add(new TextField("��Ҫ���ұ����������ø����",20));
        add(l3);
        add(new TextField("��һ",20));
        add(l4);
        add(new TextField("��",20));
        add(l5);
        add(new TextField("2000.01.01",20));
        add(l6);
        add(new TextField("17824239651",20));
        add(l7);
        add(new TextField("ѧ��",20));
        add(buttona);
        add(buttonb);
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==buttona) {
    		
    	}
    	else if(e.getSource()==buttonb) {
    		
    		this.dispose();
    	}
    }
 }
