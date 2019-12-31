package com.KingFarGrace.board;

import java.awt.*;
import javax.swing.*;

import com.KingFarGrace.event.MainBoardEvent;

public class MainBoard {	
	public JFrame menu;
	
	public JMenuBar userOption;
	public JMenu file;
	public JMenu help;
	public JMenu info;
	public JMenuItem checkRank;
	public JMenuItem checkList;
	public JMenuItem helpItem;
	public JMenuItem infoItem;
	
	public JLabel l1;
	public JLabel l2;
	public JLabel l3;
	public JLabel bgp = new JLabel();
	
	public JPanel buttonPanel;
	public JButton[] mode = new JButton[6];
	String[] modeName = { "���Ĵ�����ϰ", "����������ϰ", "Ӣ�ĵ�����ϰ", "Ӣ��������ϰ", "������Ϸ", "�˳�" };

	public MainBoard() {
		menu = new JFrame("����ѵ���� BY @KingFar");
		menu.setLayout(new BorderLayout());
		
		userOption = new JMenuBar();	
		help = new JMenu("����");
		help.addSeparator();
		helpItem = new JMenuItem("����");
		helpItem.addActionListener(new MainBoardEvent(this));
		help.add(helpItem);
		
		info = new JMenu("����");
		info.addSeparator();
		infoItem = new JMenuItem("����");
		infoItem.addActionListener(new MainBoardEvent(this));
		info.add(infoItem);
		
		file = new JMenu("�ļ�");
		file.addSeparator();
		checkRank = new JMenuItem("�鿴���ְ�");
		checkRank.addActionListener(new MainBoardEvent(this));
		checkList = new JMenuItem("�鿴���");
		checkList.addActionListener(new MainBoardEvent(this));
		file.add(checkRank);
		file.add(checkList);
		userOption.add(file);
		userOption.add(help);
		userOption.add(info);
		menu.setJMenuBar(userOption);
		
		l1 = new JLabel("����ѵ����", JLabel.CENTER);
		l1.setFont(new Font("��Բ", Font.ITALIC, 52));
		l1.setBorder(BorderFactory.createEtchedBorder());
		menu.add(l1, BorderLayout.NORTH);
		
		l2 = new JLabel("��ѡ����ϰģʽ", JLabel.CENTER);
		l2.setFont(new Font("����", Font.BOLD, 22));
		l2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		buttonPanel.setLayout(new GridLayout(7, 1, 10, 10));
		buttonPanel.add(l2);
		
		for(int i = 0; i < modeName.length; i++) {
			mode[i] = new JButton(modeName[i]);
			mode[i].setFont(new Font("����", Font.PLAIN, 20));
			mode[i].setForeground(Color.RED);
			mode[i].setBorder(BorderFactory.createEtchedBorder());
			mode[i].addActionListener(new MainBoardEvent(this));
			buttonPanel.add(mode[i]);
		}
		menu.add(buttonPanel, BorderLayout.CENTER);
		
		l3 = new JLabel("���ߣ�KingFar", JLabel.RIGHT);
		menu.add(l3, BorderLayout.SOUTH);
		
		menu.setVisible(true);
		menu.setSize(800, 800);
		menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
