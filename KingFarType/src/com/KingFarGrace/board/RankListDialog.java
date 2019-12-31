package com.KingFarGrace.board;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import com.KingFarGrace.util.SourceLoader;

public class RankListDialog {
	public JDialog listDialog;
	public JTable rankList;
	public DefaultTableModel model;
	public JScrollPane scroller;
	Object[] columnName = { "�ǳ�", "��ʼʱ��", "ģʽ��", "ƪĿ��", "��ϰʱ��", "���÷���" };
	String modeList[] = {"���Ĵ�����ϰ", "����������ϰ", "Ӣ�ĵ�����ϰ", "Ӣ��������ϰ", "������Ϸ" };
	
	public RankListDialog() {
		String mode = (String) JOptionPane.showInputDialog(null, "��ѡ��Ҫ�鿴��ģʽ", "",
													JOptionPane.INFORMATION_MESSAGE, 
														null, modeList, modeList[0]);
		if(mode == null) {
			return;
		}
		
		listDialog = new JDialog();
		listDialog.setLayout(new BorderLayout());
		SourceLoader sl = SourceLoader.getInstance();
		model = new DefaultTableModel(new Object[0][0], columnName);
		sl.setPath(SourceLoader.USER_DATA_PATH + mode + ".txt");
		sl.getRankList(model);
		rankList = new JTable(model);
		scroller = new JScrollPane(rankList);
		listDialog.add(rankList);
		listDialog.setVisible(true);
		listDialog.setSize(800, 800);
	}
}
