package com.KingFarGrace.board;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class EndBoard {
	public JFrame endBoard;
	
	public JTable rankTable;
	public DefaultTableModel model;
	Object[] columnName = { "�ǳ�", "��ʼʱ��", "ģʽ��", "ƪĿ��", "��ϰʱ��", "���÷���" };
	public JScrollPane scroller;
	
	public JLabel l1;
	public JButton backButton;
	
	public EndBoard() {
		endBoard = new JFrame("����ѵ���� BY @KingFar");
		endBoard.setLayout(new BorderLayout(20, 20));
		endBoard.getContentPane().setBackground(Color.CYAN);
		l1 = new JLabel("��ϰ���������а�����", JLabel.CENTER);
		l1.setFont(new Font("����", Font.BOLD, 50));
		
		model = new DefaultTableModel(new Object[0][0], columnName);
		rankTable = new JTable(model);
		scroller = new JScrollPane(rankTable);
		
		backButton = new JButton("�������˵�");
		
		endBoard.add(l1, BorderLayout.NORTH);
		endBoard.add(scroller, BorderLayout.CENTER);
		endBoard.add(backButton, BorderLayout.SOUTH);
		endBoard.setVisible(true);
		endBoard.setSize(800, 800);
		endBoard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
