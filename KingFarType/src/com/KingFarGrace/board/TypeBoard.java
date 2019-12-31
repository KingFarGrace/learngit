package com.KingFarGrace.board;

import java.awt.*;
import javax.swing.*;

import com.KingFarGrace.event.TypeBoardEvent;
import com.KingFarGrace.util.TimeTool;

public class TypeBoard {
	public JFrame typeBoard;
	
	public JLabel clock;
	public JTextArea typeArea;
	public JPanel mixPanel;
	public JScrollPane scroller1;
	public JScrollPane scroller2;
	public JTextArea textArea;
	
	public JButton[] operateButton = new JButton[2];
	public JPanel buttonPanel;
	
	public TypeBoard() {
		typeBoard = new JFrame("����ѵ���� BY @KingFar");
		typeBoard.setLayout(new GridLayout(1, 2, 10, 10));				
		typeBoard.getContentPane().setBackground(Color.CYAN);	
		mixPanel = new JPanel();
		mixPanel.setLayout(new BorderLayout(10, 10));
		
		clock = new JLabel("", JLabel.CENTER);
		TimeTool.addClock(clock);
		clock.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "��ǰʱ��"));
		clock.setFont(new Font("΢���ź�", Font.ITALIC, 15));
		
		typeArea = new JTextArea();
		typeArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "��������������Ҳ���ͬ���ı�"));
		typeArea.setFont(new Font("����", Font.PLAIN, 22));
		typeArea.setLineWrap(true);
		scroller1 = new JScrollPane(typeArea);
		scroller1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		
		operateButton[0] = new JButton("��ʼ");
		operateButton[0].addActionListener(new TypeBoardEvent(this));
		operateButton[1] = new JButton("��ͣ");
		operateButton[1].addActionListener(new TypeBoardEvent(this));
		buttonPanel = new JPanel();

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(operateButton[0]);
		buttonPanel.add(operateButton[1]);
		
		mixPanel.add(clock, BorderLayout.NORTH);
		mixPanel.add(scroller1, BorderLayout.CENTER);
		mixPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("����", Font.BOLD, 22));
		scroller2 = new JScrollPane(textArea);
		scroller2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		
		typeBoard.add(mixPanel);
		typeBoard.add(scroller2);
		typeBoard.setVisible(true);
		typeBoard.setSize(1000, 500);
		typeBoard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
