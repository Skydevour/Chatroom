package test1;

import java.io.BufferedReader;
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
		String name;
		System.out.println("�û���");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		name=br.readLine();
		new Thread(new Send(client,name)).start();
		new Thread(new Receive(client)).start();
	
	}
}
