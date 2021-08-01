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
				Class.forName("com.mysql.cj.jdbc.Driver"); 	//com.mysql.jdbc.Driver已经弃用了，要加上cj
			}
			catch(Exception e)
			{
				System.out.println("forNameError:" + e);
			}
			String url = "jdbc:mysql://127.0.0.1:3306/chatroom?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";	//要加上serverTimezone=GMT，否则会报错
			String user = "root";
			String password = "123456";
			try
			{
				con = DriverManager.getConnection(url, user, password);
				System.out.println("连接数据库成功");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	}

	public Login queryVerify(Login loginModel){								//查询
		String id =loginModel.getID();
		String pw =loginModel.getPassword();
		try{
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT MNumber,MSecret FROM personinfor");
			if(id==null) {
				JOptionPane.showMessageDialog(null,"账号不能为空","注意!",JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			
			if(pw==null){
				JOptionPane.showMessageDialog(null,"密码不能为空","注意!",JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}			
			while(rs.next()) 
			{
				String number = rs.getString(1);
				String mpassword = rs.getString(2);
				if(id.equals(number) && pw.equals(mpassword)) {
					loginModel.setLoginSuccess(true);
					JOptionPane.showMessageDialog(null,"登陆成功","恭喜",JOptionPane.WARNING_MESSAGE);
				}
			}			
			con.close();
		}
		catch(SQLException e){}
		return loginModel;
	}
	
}