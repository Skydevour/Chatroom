package com.zhu.test;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class imagePanel extends JPanel{	
	Image image=null;	
	public void paint(Graphics g){
		try {
			image=ImageIO.read(new File("D:\\∆Ω ±±‡≥Ã¡∑œ∞\\test\\src\\avatar2.png"));
			g.drawImage(image, 0, 0, 50, 50, null);
		}
		catch (Exception e) {							// TODO Auto-generated catch block			
			e.printStackTrace();
		}
	}
}
