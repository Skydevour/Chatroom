package com.zhu.test;

import com.zhu.test.Login;
import java.sql.*;
import javax.swing.JOptionPane;

public class  HandleLogin
{
	Connection con;
	ResultSet rs;
	Statement sql;
	
	public HandleLogin(){	
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver"); 	//com.mysql.jdbc.Driver�Ѿ������ˣ�Ҫ����cj
			}
			catch(Exception e)
			{
				System.out.println("forNameError:" + e);
			}
			String url = "jdbc:mysql://127.0.0.1:3306/chatroom?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";	//Ҫ����serverTimezone=GMT������ᱨ��
			String user = "root";
			String password = "123456";
			try
			{
				con = DriverManager.getConnection(url, user, password);
				System.out.println("�������ݿ�ɹ�");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	}

	public Login queryVerify(Login loginModel){								//��ѯ
		String id =loginModel.getID();
		String pw =loginModel.getPassword();
		try{
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT MNumber,MSecret FROM personinfor");
			if(id==null) {
				JOptionPane.showMessageDialog(null,"�˺Ų���Ϊ��","ע��!",JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			
			if(pw==null){
				JOptionPane.showMessageDialog(null,"���벻��Ϊ��","ע��!",JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}			
			while(rs.next()) 
			{
				String number = rs.getString(1);
				String mpassword = rs.getString(2);
				if(id.equals(number) && pw.equals(mpassword)) {
					loginModel.setLoginSuccess(true);
					JOptionPane.showMessageDialog(null,"��½�ɹ�","��ϲ",JOptionPane.WARNING_MESSAGE);
				}
			}			
			con.close();
		}
		catch(SQLException e){}
		return loginModel;
	}
	
}