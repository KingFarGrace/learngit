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
		menu = new JFrame("���ݽṹ���� BY �ſ��� ���ڳ� С��");
		menu.setLayout(new GridLayout(4, 1, 10, 10));
		l1 = new JLabel("�����㷨��ʾ�� V1.0", JLabel.CENTER);
		l1.setFont(new Font("��Բ", Font.BOLD, 40));
		l1.setBorder(BorderFactory.createEtchedBorder());		
		l2 = new JLabel("���˵�", JLabel.CENTER);
		l2.setFont(new Font("����", Font.BOLD, 30));
		l2.setBorder(BorderFactory.createEtchedBorder());
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 2, 10, 10));
		menuButton[0] = new JButton("��ʾ��������������");
		menuButton[0].setFont(new Font("����", Font.ITALIC, 20));
		menuButton[1] = new JButton("��ʾƽ�����������");
		menuButton[1].setFont(new Font("����", Font.ITALIC, 20));
		p1.add(menuButton[0]);
		p1.add(menuButton[1]);
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 2, 10, 10));
		menuButton[2] = new JButton("��ʾB-������");
		menuButton[2].setFont(new Font("����", Font.ITALIC, 20));
		menuButton[3] = new JButton("�˳�");
		menuButton[3].setFont(new Font("����", Font.ITALIC, 20));
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
