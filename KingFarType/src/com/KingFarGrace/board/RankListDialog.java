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
	Object[] columnName = { "昵称", "开始时间", "模式名", "篇目名", "练习时长", "所得分数" };
	String modeList[] = {"中文词语练习", "中文文章练习", "英文单词练习", "英文文章练习", "打字游戏" };
	
	public RankListDialog() {
		String mode = (String) JOptionPane.showInputDialog(null, "请选择要查看的模式", "",
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
