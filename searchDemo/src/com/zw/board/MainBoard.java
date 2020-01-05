package com.zw.board;

import java.awt.*;
import javax.swing.*;
import com.zw.event.*;

public class MainBoard {
	public JFrame menu;
	
	private JLabel l1;
	private JLabel l2;
	
	private JPanel p1;
	private JPanel p2;
	
	public JButton[] menuButton = new JButton[4];
	
	public MainBoard() {
		menu = new JFrame("数据结构课设 BY 张凯恒 王亿成 小组");
		menu.setLayout(new GridLayout(4, 1, 10, 10));
		l1 = new JLabel("查找算法演示器 V1.0", JLabel.CENTER);
		l1.setFont(new Font("幼圆", Font.BOLD, 40));
		l1.setBorder(BorderFactory.createEtchedBorder());		
		l2 = new JLabel("主菜单", JLabel.CENTER);
		l2.setFont(new Font("宋体", Font.BOLD, 30));
		l2.setBorder(BorderFactory.createEtchedBorder());
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 2, 10, 10));
		menuButton[0] = new JButton("演示二叉排序树查找");
		menuButton[0].setFont(new Font("宋体", Font.ITALIC, 20));
		menuButton[1] = new JButton("演示平衡二叉树查找");
		menuButton[1].setFont(new Font("宋体", Font.ITALIC, 20));
		p1.add(menuButton[0]);
		p1.add(menuButton[1]);
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 2, 10, 10));
		menuButton[2] = new JButton("演示B-树查找");
		menuButton[2].setFont(new Font("宋体", Font.ITALIC, 20));
		menuButton[3] = new JButton("退出");
		menuButton[3].setFont(new Font("宋体", Font.ITALIC, 20));
		p2.add(menuButton[2]);
		p2.add(menuButton[3]);
		
		menu.add(l1);
		menu.add(l2);
		menu.add(p1);
		menu.add(p2);
		
		for(int i=0 ; i<4 ; i++) {
			menuButton[i].addActionListener(new MainBoardEvent(this));
		}
		menu.setVisible(true);
		menu.setSize(500, 600);
		menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
