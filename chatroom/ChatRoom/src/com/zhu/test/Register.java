package com.zhu.test;

public class Register 					   			//ע����ģ�ʹ��� 
{
	String name;									//�û���
	String id;										//id	
	String password;								//����
	String birthday;								//����
	String sex;										//�Ա�
	String realname;								//����
	String telephone;								//�绰
	String occupation;								//ְλ
	String mintro;									//����ǩ��
	
	public void setName(String name){				//�����û��ǳ�
		this.name=name;
	}

	public void setID(String id){					//����id
		this.id=id;
	}
	
	public void setPassword(String password){		//��������
		this.password=password;
	}
		
	public void setBirth(String birthday){			//��������
		this.birthday=birthday;
	}

	public void setSex(String sex){					//�����Ա�
		this.sex=sex;
	}
	
	public void setRealname(String realname) {		//��������
		this.realname=realname;
	}
	
	public void setTelephone(String telephone) {	//���õ绰
		this.telephone=telephone;
	}
	
	public void setOccupation(String occupation) {	//����ְλ
		this.occupation=occupation;
	}
	
	public void setMintro(String mintro) {			//���ø���ǩ��
		this.mintro=mintro;
	}
	
	public String getName(){						//�õ��ǳ�
		return name;
	}
	
	public String getID(){							//�õ��˺�
		return id;
	}
	
	public String getPassword(){					//�õ�����
		return password;
	}

	public String getBirth(){							//�õ�����
		return birthday;
	}
	
	public String getSex(){								//�õ��Ա�
		return sex;	
	}
	
	public String getRealname() {						//�õ�����
		return realname;
	}
	
	public String getTelephone() {						//�õ��绰
		return telephone;
	}
	
	public String getOccupation() {						//�õ�ְλ
		return occupation;
	}
	
	public String getMintro() {							//�õ�����ǩ��
		return mintro;
	}
	
}
