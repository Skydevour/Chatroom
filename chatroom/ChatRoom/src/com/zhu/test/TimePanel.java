package com.zhu.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimePanel extends JFrame
{
	JLabel displayArea;                                 //��ʾ����
	String DEFAULT_TIME_FORMAT = "yyyy-MM-dd"; //���ڱ�׼��ʽ
	String time;        
	int ONE_SECOND = 1000;
	public TimePanel() {
		displayArea = new JLabel();
		configTimeArea();								//ʵʱ����ʱ��
	}
	private void configTimeArea() {
		Timer tmr = new Timer();
		tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), ONE_SECOND);
	}
	protected class JLabelTimerTask extends TimerTask {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
		@Override
		public void run() {
			time = dateFormatter.format(Calendar.getInstance().getTime());
			displayArea.setText(time);
		}
	}
	public JLabel showtimePanel(){               //������׳�
		return displayArea;
	}
}
