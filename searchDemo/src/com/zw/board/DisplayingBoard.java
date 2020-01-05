package com.zw.board;

import java.awt.*;

import javax.swing.*;
import javax.swing.tree.*;

import com.zw.event.*;
import com.zw.thread.*;

public class DisplayingBoard {
	public JFrame menu;
	
	public DefaultTreeModel treeModel;
	public JTree tree;
	private JScrollPane scroller1;
	
	public JTextField dataLabel;
	public JTextArea resultArea;
	private JScrollPane scroller3;
	public JTextField dataSearch;
	
	public JButton[] menuButton = new JButton[4];
	public JComboBox<String> dataVisible;
	
	public int modelChoosen;
	public BinarySearchThread bst;
	public BalenceThread bt;
	public B_TreeThread btt;
	
	public DisplayingBoard db = this;
	
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	private JPanel p6;
	private JLabel l2;
	
	public DisplayingBoard(double[] dataSet, int modelChoosen) {
		this.modelChoosen = modelChoosen;
		if(modelChoosen == 0) {
			bst = new BinarySearchThread(this, dataSet);
		} else if (modelChoosen == 1){
			bt = new BalenceThread(this, dataSet);
		} else if (modelChoosen == 2) {
			btt = new B_TreeThread(this, dataSet);
		}
		
		menu = new JFrame("数据结构课设 BY 张凯恒 王亿成 小组");
		menu.setLayout(new GridLayout(1, 2, 10, 10));
		
		String dataString=" ";
		for(int i = 0; i < dataSet.length ; i++) {
			dataString=dataString+dataSet[i]+"  ";
		}
		dataLabel = new JTextField(25);
		dataLabel.setEditable(false);
		dataLabel.setText(dataString);
		dataLabel.setFont(new Font("Arial", Font.ITALIC, 18));
		dataLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "数据集"));
		resultArea = new JTextArea();
		resultArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "查询结果"));
		resultArea.setFont(new Font("等线", Font.BOLD, 16));
		scroller3 = new JScrollPane(resultArea);
		menuButton[0] = new JButton("开始");
		menuButton[0].setFont(new Font("宋体", Font.BOLD, 20));
		menuButton[1] = new JButton("查找");
		menuButton[1].setFont(new Font("宋体", Font.BOLD, 20));
		dataSearch = new JTextField(5);
		String[] data = new String[dataSet.length+1];
		data[0]=" ";
		for(int i = 1; i<= dataSet.length; i++) {
			data[i] = String.valueOf(dataSet[i-1]);
		}
		dataVisible = new JComboBox<String>(data);
		dataVisible.setFont(new Font("宋体", Font.BOLD, 20));
		
		p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(dataLabel);
		p3.add(dataVisible);
		
		p6 = new JPanel();
		p6.setLayout(new FlowLayout());
		p6.add(menuButton[0]);
		p6.add(dataSearch);
		p6.add(menuButton[1]);
		
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(scroller3, BorderLayout.CENTER);
		
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(p3, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		p1.add(p6, BorderLayout.SOUTH);
		
		l2 = new JLabel("树状图演示如下", JLabel.LEFT);
		tree = new JTree(treeModel);
		scroller1 = new JScrollPane(tree);
		
		menuButton[2] = new JButton("返回上一级页面");
		menuButton[2].setFont(new Font("宋体", Font.BOLD, 20));
		menuButton[3] = new JButton("返回主菜单");
		menuButton[3].setFont(new Font("宋体", Font.BOLD, 20));
		p5 = new JPanel();
		p5.setLayout(new FlowLayout());
		p5.add(menuButton[2]);
		p5.add(menuButton[3]);
		
		p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		p4.add(l2, BorderLayout.NORTH);
		p4.add(scroller1, BorderLayout.CENTER);
		p4.add(p5, BorderLayout.SOUTH);
		
		menu.add(p1);
		menu.add(p4);
		
		menuButton[0].addActionListener(new DisplayingBoardEvent(this));
		menuButton[1].addActionListener(new DisplayingBoardEvent(this));
		menuButton[2].addActionListener(new DisplayingBoardEvent(this));
		menuButton[3].addActionListener(new DisplayingBoardEvent(this));
		
		menu.setVisible(true);
		menu.setSize(1000, 800);
		menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
