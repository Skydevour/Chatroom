package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ���������ң����ͻ���
 * Ⱥ�ģ���������
 * ʵ��һ���ͻ����������շ���Ϣ
 */
public class Chat 
{
	public static void main(String[] args) throws IOException 
	{
		System.out.println("�����");
		//1.ָ���˿ڣ�ʹ��ServerSocket����������
		@SuppressWarnings("resource")
		ServerSocket server=new ServerSocket(8888);
		//2.����ʽ�ȴ�����accept
		Socket client=server.accept();//client��һ���ͻ��ˣ��ܵ�  ����ʽ
		System.out.println("һ���ͻ���");
		//3.������Ϣ
		DataInputStream dis=new DataInputStream(client.getInputStream());
		String msg=dis.readUTF();
		//4.������Ϣ
		DataOutputStream dos =new DataOutputStream(client.getOutputStream());
		dos.writeUTF(msg);
		dos.flush();
	}
}
