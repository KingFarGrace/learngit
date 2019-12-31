package com.KingFarGrace.board;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class EndBoard {
	public JFrame endBoard;
	
	public JTable rankTable;
	public DefaultTableModel model;
	Object[] columnName = { "昵称", "开始时间", "模式名", "篇目名", "练习时长", "所得分数" };
	public JScrollPane scroller;
	
	public JLabel l1;
	public JButton backButton;
	
	public EndBoard() {
		endBoard = new JFrame("打字训练器 BY @KingFar");
		endBoard.setLayout(new BorderLayout(20, 20));
		endBoard.getContentPane().setBackground(Color.CYAN);
		l1 = new JLabel("练习结束！排行榜如下", JLabel.CENTER);
		l1.setFont(new Font("楷体", Font.BOLD, 50));
		
		model = new DefaultTableModel(new Object[0][0], columnName);
		rankTable = new JTable(model);
		scroller = new JScrollPane(rankTable);
		
		backButton = new JButton("返回主菜单");
		
		endBoard.add(l1, BorderLayout.NORTH);
		endBoard.add(scroller, BorderLayout.CENTER);
		endBoard.add(backButton, BorderLayout.SOUTH);
		endBoard.setVisible(true);
		endBoard.setSize(800, 800);
		endBoard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
