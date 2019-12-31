package com.KingFarGrace.board;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class GameBoard {
	public JFrame gameBoard;
	public JLabel gameName;
	public JPanel gamePanel;
	public JPanel buttonPanel;
	public JButton[] operateButton = new JButton[2];
	
	public GameBoard() {
		gameBoard = new JFrame("打字训练器 BY @KingFar");
		gameBoard.setLayout(new BorderLayout(20, 20));
		gameBoard.getContentPane().setBackground(Color.CYAN);
		
		gameName = new JLabel("游戏名", JLabel.CENTER);//此处放游戏名
		gameName.setFont(new Font("方正舒体", Font.ITALIC, 25));
		gameName.setBorder(BorderFactory.createDashedBorder(Color.BLUE));
		
		gamePanel = new JPanel();
		gamePanel.setSize(800, 800);
		gamePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		gamePanel.setBackground(Color.LIGHT_GRAY);
		
		operateButton[0] = new JButton("开始");
		operateButton[1] = new JButton("查看规则");
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(operateButton[0]);
		buttonPanel.add(operateButton[1]);
		
		gameBoard.add(gameName, BorderLayout.NORTH);
		gameBoard.add(gamePanel, BorderLayout.CENTER);
		gameBoard.add(buttonPanel, BorderLayout.SOUTH);
		gameBoard.setVisible(true);
		gameBoard.setSize(1000, 1000);
		gameBoard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
