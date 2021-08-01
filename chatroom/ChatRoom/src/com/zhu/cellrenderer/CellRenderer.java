package com.zhu.cellrenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.zhu.user.User;
 
public  class CellRenderer extends JLabel implements ListCellRenderer {
	public CellRenderer(){
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));// 加入宽度为5的空白边框

		if (value != null) {
			setFont(new Font("sdf", Font.ROMAN_BASELINE, 13));
			User user=(User)value;
			setText(user.getUserName());
			setIcon(new ImageIcon("images//head"+user.getHeadIndex()+".jpg"));
		}
		if (isSelected) {
			setBackground(new Color(255, 127, 39));// 设置背景色
			setForeground(Color.black);
		} else {
			// 设置选取与取消选取的前景与背景颜色.
			setBackground(Color.white); // 设置背景色
			setForeground(Color.black);
		}
		setEnabled(list.isEnabled());
		setFont(new Font("sdf", Font.ROMAN_BASELINE, 13));
		setOpaque(true);
		return this;
	}
}
