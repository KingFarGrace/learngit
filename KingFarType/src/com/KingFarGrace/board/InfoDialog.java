package com.KingFarGrace.board;

import java.awt.*;
import javax.swing.*;

public class InfoDialog {
	public JDialog infoDialog;
	public JTextArea infoArea;
	public JScrollPane scroller;
	
	private final String info = 	"���ڱ����" + "\n" +
									"������ϰ�� V1.0" + "\n" +
									"���ߣ����1802 �ſ���" + "\n" +
									"���ܣ�����ϰ�������º���Ϸ��" + "\n" +
									"��ʽ��ͬ��ߴ����ٶȣ�" + "\n" + 
									"������Ȥζ�Ժ���ϰЧ��." + "\n" +
									"���°汾�����ڣ�2019.12.30" + "\n";
	
	public InfoDialog() {
		infoDialog = new JDialog();
		infoDialog.setLayout(new BorderLayout());
		infoArea = new JTextArea(info);
		infoArea.setFont(new Font("����", Font.BOLD, 24));
		infoArea.setForeground(Color.BLACK);
		infoArea.setEnabled(false);
		scroller = new JScrollPane(infoArea);
		infoDialog.add(scroller);
		infoDialog.setVisible(true);
		infoDialog.setSize(400, 400);
	}
}
