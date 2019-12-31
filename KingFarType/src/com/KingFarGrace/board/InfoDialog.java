package com.KingFarGrace.board;

import java.awt.*;
import javax.swing.*;

public class InfoDialog {
	public JDialog infoDialog;
	public JTextArea infoArea;
	public JScrollPane scroller;
	
	private final String info = 	"关于本软件" + "\n" +
									"打字练习器 V1.0" + "\n" +
									"作者：软件1802 张凯恒" + "\n" +
									"功能：用练习经典文章和游戏的" + "\n" +
									"方式共同提高打字速度，" + "\n" + 
									"增加了趣味性和练习效果." + "\n" +
									"最新版本发布于：2019.12.30" + "\n";
	
	public InfoDialog() {
		infoDialog = new JDialog();
		infoDialog.setLayout(new BorderLayout());
		infoArea = new JTextArea(info);
		infoArea.setFont(new Font("等线", Font.BOLD, 24));
		infoArea.setForeground(Color.BLACK);
		infoArea.setEnabled(false);
		scroller = new JScrollPane(infoArea);
		infoDialog.add(scroller);
		infoDialog.setVisible(true);
		infoDialog.setSize(400, 400);
	}
}
