package com.KingFarGrace.board;

import java.awt.*;
import javax.swing.*;

public class HelpDialog {
	public JDialog helpDialog;
	public JTextArea area1;
	public JTextArea area2;
	JScrollPane scroller1;
	JScrollPane scroller2;
	
	private final String help1 = 	"本软件有哪些功能？" + "\n" +
									"1.练习中文打字速度，包括成语和经典篇目的练习；" + "\n" +
									"2.练习英文打字速度，包括单词和经典篇目的练习；" + "\n" +
									"3.通过趣味游戏练习打字速度；" + "\n" +
									"4.记录每一次打字的用时，直观看到自己的进步；" + "\n" +
									"5.后续功能留待以后补充。"; 
	
	private final String help2 =	"如何使用本软件？" + "\n" + 
									"1.在主页面选择自己需要的练习模式，根据弹出对话框的内容操作；" + "\n" +
									"2.在打字页面根据给出的文本打字，可以随时暂停或结束；" + "\n" +
									"3.打字完毕后根据时长和正确率得出得分，计入排行榜。";
	Font f = new Font("宋体", Font.BOLD, 24);
	
	public HelpDialog() {
		helpDialog = new JDialog();
		helpDialog.setLayout(new GridLayout(2, 1, 10, 10));
		area1 = new JTextArea(help1);
		area2 = new JTextArea(help2);
		area1.setEnabled(false);
		area1.setFont(f);
		area2.setEnabled(false);
		area2.setFont(f);
		scroller1 = new JScrollPane(area1);
		scroller2 = new JScrollPane(area2);
		helpDialog.add(scroller1);
		helpDialog.add(scroller2);
		helpDialog.setVisible(true);
		helpDialog.setSize(600, 600);
	}
}
