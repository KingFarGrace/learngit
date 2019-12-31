package com.KingFarGrace.board;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.KingFarGrace.util.SourceLoader;

public class TypeListDialog {
	JDialog listDialog;
	JComboBox<String> mode1;
	String[] list1 = {"丰碑", "明月何皎皎", "青青河畔草", "涉江采芙蓉",
						"生年不满百", "迢迢牵牛星", "橡树", "行行重行行"};
	JComboBox<String> mode2;
	String list2 = "词语";
	JComboBox<String> mode3;
	String[] list3 = {"英文1", "英文2", "英文3", "英文4"};
	JComboBox<String> mode4;
	String list4 = "单词";
	JComboBox<String> mode5;
	String list5 = "";
	
	JPanel p1;
	JTextArea area;
	JScrollPane scroller;
	
	Font f = new Font("楷体", Font.ITALIC, 32);
	
	public TypeListDialog() {
		listDialog = new JDialog();
		listDialog.setLayout(new GridLayout(1, 2, 10, 10));
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 2, 5, 5));
		
		mode1 = new JComboBox<>(list1);
		mode1.setFont(f);
		mode1.addActionListener(new ModeItemEvent(this));
		mode2 = new JComboBox<>();
		mode2.addItem(list2);
		mode2.setFont(f);
		mode2.addActionListener(new ModeItemEvent(this));
		mode3 = new JComboBox<>(list3);
		mode3.setFont(f);
		mode3.addActionListener(new ModeItemEvent(this));
		mode4 = new JComboBox<>();
		mode4.addItem(list4);
		mode4.setFont(f);
		mode4.addActionListener(new ModeItemEvent(this));
		mode5 = new JComboBox<>();
		mode5.addItem(list5);
		mode5.setFont(f);
		mode5.addActionListener(new ModeItemEvent(this));
		p1.add(mode1);
		p1.add(mode2);
		p1.add(mode3);
		p1.add(mode4);
		p1.add(mode5);
		
		area = new JTextArea();
		area.setEnabled(false);
		area.setFont(new Font("宋体", Font.BOLD, 28));
		scroller = new JScrollPane(area);
		
		listDialog.add(p1);
		listDialog.add(scroller);
		listDialog.setVisible(true);
		listDialog.setSize(800, 800);
	}
}

class ModeItemEvent implements ActionListener {
	TypeListDialog tld;
	
	public ModeItemEvent(TypeListDialog tld) {
		this.tld = tld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SourceLoader sl = SourceLoader.getInstance();
		if(e.getSource() == tld.mode1) {
			tld.area.setText("");
			sl.setPath(SourceLoader.CHI_ARTICLE_PATH + tld.list1[tld.mode1.getSelectedIndex()] + ".txt");
			sl.getText();
			tld.area.append(sl.getTextPart());
		} else if(e.getSource() == tld.mode2) {
			tld.area.setText("");
			sl.setPath(SourceLoader.CHI_WORD_PATH);
			sl.getText();
			tld.area.append(sl.getTextPart());
		} else if(e.getSource() == tld.mode3) {
			tld.area.setText("");
			sl.setPath(SourceLoader.ENG_ARTICLE_PATH + tld.list3[tld.mode3.getSelectedIndex()] + ".txt");
			System.out.println(SourceLoader.ENG_ARTICLE_PATH + tld.list3[tld.mode3.getSelectedIndex()] + ".txt");
			sl.getText();
			tld.area.append(sl.getTextPart());
		} else if(e.getSource() == tld.mode4) {
			tld.area.setText("");
			sl.setPath(SourceLoader.ENG_WORD_PATH);
			sl.getText();
			tld.area.append(sl.getTextPart());
		} else if(e.getSource() == tld.mode5) {
			tld.area.setText("");
			sl.setPath(SourceLoader.USER_DATA_PATH);
			sl.getText();
			tld.area.append(sl.getTextPart());
		}
	}
}
