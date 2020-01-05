package com.zw.board;

import java.awt.*;
import javax.swing.*;
import com.zw.event.*;

public class ModeSelectionBoard {
	public int modelChoosen;
	
	public JFrame menu;
	
	private JLabel l1;
	
	private JPanel p1;
	
	public JButton[] menuButton = new JButton[3];
	
	public JFileChooser fileChooser;
	
	public ModeSelectionBoard(int modelChoosen) {
		this.modelChoosen = modelChoosen;
		menu = new JFrame("数据结构课设 BY 张凯恒 王亿成 小组");
		menu.setLayout(new BorderLayout());
		l1 = new JLabel("请选择演示模式：", JLabel.CENTER);
		l1.setFont(new Font("楷体", Font.BOLD, 20));
		l1.setBorder(BorderFactory.createEtchedBorder());
		p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 2, 10, 10));
		p1.setBorder(BorderFactory.createEtchedBorder());
		menuButton[0] = new JButton("使用默认数据集");
		menuButton[0].setFont(new Font("楷体", Font.BOLD, 20));
		menuButton[0].setBorder(BorderFactory.createEtchedBorder());
		menuButton[1] = new JButton("使用自定义数据集");
		menuButton[1].setFont(new Font("楷体", Font.BOLD, 20));
		menuButton[1].setBorder(BorderFactory.createEtchedBorder());
		menuButton[2] = new JButton("返回上一个页面");
		menuButton[2].setFont(new Font("楷体", Font.BOLD, 16));
		p1.add(menuButton[0]);
		p1.add(menuButton[1]);
		menu.add(l1, BorderLayout.NORTH);
		menu.add(p1, BorderLayout.CENTER);
		menu.add(menuButton[2], BorderLayout.SOUTH);
		
		for(int i=0 ; i<3 ; i++) {
			menuButton[i].addActionListener(new ModeSelectionBoardEvent(this));
		}
		menu.setVisible(true);
		menu.setSize(400, 150);
		menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
