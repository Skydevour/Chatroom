package com.zhu.test;

public class Register 					   			//注册码模型代码 
{
	String name;									//用户名
	String id;										//id	
	String password;								//密码
	String birthday;								//生日
	String sex;										//性别
	String realname;								//姓名
	String telephone;								//电话
	String occupation;								//职位
	String mintro;									//个性签名
	
	public void setName(String name){				//设置用户昵称
		this.name=name;
	}

	public void setID(String id){					//设置id
		this.id=id;
	}
	
	public void setPassword(String password){		//设置密码
		this.password=password;
	}
		
	public void setBirth(String birthday){			//设置生日
		this.birthday=birthday;
	}

	public void setSex(String sex){					//设置性别
		this.sex=sex;
	}
	
	public void setRealname(String realname) {		//设置姓名
		this.realname=realname;
	}
	
	public void setTelephone(String telephone) {	//设置电话
		this.telephone=telephone;
	}
	
	public void setOccupation(String occupation) {	//设置职位
		this.occupation=occupation;
	}
	
	public void setMintro(String mintro) {			//设置个性签名
		this.mintro=mintro;
	}
	
	public String getName(){						//得到昵称
		return name;
	}
	
	public String getID(){							//得到账号
		return id;
	}
	
	public String getPassword(){					//得到密码
		return password;
	}

	public String getBirth(){							//得到生日
		return birthday;
	}
	
	public String getSex(){								//得到性别
		return sex;	
	}
	
	public String getRealname() {						//得到姓名
		return realname;
	}
	
	public String getTelephone() {						//得到电话
		return telephone;
	}
	
	public String getOccupation() {						//得到职位
		return occupation;
	}
	
	public String getMintro() {							//得到个性签名
		return mintro;
	}
	
}
