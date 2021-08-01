package com.zhu.test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class edit extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l5,l6,l7;//标签
    JLabel pict;//头像
    JButton buttona,buttonb;//按钮
    String id;				//从主页面上接受过来的id
    public edit(String id){
    	this.id=id;				//传递id
    	setBackground(Color.GRAY);
    	setSize(300,500);
    	setLocation(300, 200);
    	setTitle("编辑个人信息");
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void init() {
        setLayout(new FlowLayout());
        l1 = new JLabel("昵称");
        l2 = new JLabel("个性签名");
        l3 = new JLabel("姓名");
        l4 = new JLabel("性别");
        l5 = new JLabel("出生日期");
        l6 = new JLabel("电话");
        l7 = new JLabel("职业");
        pict = new JLabel();
        buttona = new JButton("修改");
        buttonb = new JButton("保存");
        buttona.addActionListener(this);
        buttonb.addActionListener(this);
        add(l1);
        add(new TextField("该用户较懒没有起名",20));
        add(l2);
        add(new TextField("不要和我比懒，我懒得跟你比",20));
        add(l3);
        add(new TextField("刘一",20));
        add(l4);
        add(new TextField("男",20));
        add(l5);
        add(new TextField("2000.01.01",20));
        add(l6);
        add(new TextField("17824239651",20));
        add(l7);
        add(new TextField("学生",20));
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
