package test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * ���������ң������
 */

public class Cliant 
{
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		System.out.println("�ͻ���");
		//1.�������ӣ�ʹ��Socket�����ͻ���+ָ����������ַ�Ͷ˿�
		Socket client=new Socket("localhost",8888);
		//2.�ͻ��˷�����Ϣ		
		BufferedReader console=new BufferedReader(new InputStreamReader(System.in));
		String msg=console.readLine();
		DataOutputStream dos =new DataOutputStream(client.getOutputStream());
		dos.writeUTF(msg);
		dos.flush();
		//3.���ܷ��ص���Ϣ
		DataInputStream dis=new DataInputStream(client.getInputStream());
		msg=dis.readUTF();
		System.out.println(msg);
		
		dos.close();
		dis.close();
		client.close();
	}
}
