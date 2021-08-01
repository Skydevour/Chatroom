package com.zhu.test;
import com.zhu.test.Register;
import java.sql.*;
import javax.swing.JOptionPane;

public class HandleInsertData {
	Connection con;
	ResultSet rs;
	Statement sql;
	PreparedStatement persql;
	
	public HandleInsertData(){	
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
	
	public void writeRegister(Register register) {						//第一个注册界面用的
		String sqlstr="insert into personinfor values (?,?,?,null,null,null,null,null,null)";
		int ok=0;
		try {		
			persql=con.prepareStatement(sqlstr);
			String id=register.getID();
			String pw=register.getPassword();
			String name=register.getName();
			if(id==null) {
				JOptionPane.showMessageDialog(null,"账号不能为空","注意!",JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			if(pw==null){
				JOptionPane.showMessageDialog(null,"密码不能为空","注意!",JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}		
			persql.setString(1,id);
			persql.setString(2,pw);
			persql.setString(3,name);
			ok=persql.executeUpdate();
			con.close();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"id不能重复","警告",JOptionPane.WARNING_MESSAGE);
		}
		if(ok!=0) {
			JOptionPane.showMessageDialog(null,"基本信息注册成功","恭喜",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void writeRegister1(Register register) {								//第二个注册界面使用
		try{
			String sqlstr = "update personinfor set Name=?,MSex=?,birthday=?,Occupation=?,Telephone=? where MNumber=?";
			persql=con.prepareStatement(sqlstr);
			persql.setString(1,register.getRealname());
			persql.setString(2,register.getSex());
			persql.setString(3,register.getBirth());
			persql.setString(4,register.getOccupation());
			persql.setString(5,register.getTelephone());
			persql.setString(6,register.getID());
			persql.executeUpdate();
		    persql.close();
		    JOptionPane.showMessageDialog(null,"注册成功","恭喜",JOptionPane.WARNING_MESSAGE);
			con.close();
		}
		catch(SQLException e){
			System.out.println("信息插入失败");
			}
	}
}
