package com.KingFarGrace.event;

import java.awt.event.*;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import java.util.Date;

import com.KingFarGrace.board.*;
import com.KingFarGrace.util.SourceLoader;
import com.KingFarGrace.util.User;

public class MainBoardEvent implements ActionListener{
	MainBoard mb;
	TypeBoard tb;
	GameBoard gb;
	InfoDialog id;
	RankListDialog rld;
	HelpDialog hd;
	TypeListDialog tld;
	User user;
	
	SourceLoader sl;
	
	public MainBoardEvent(MainBoard mb) {
		this.mb = mb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String startTime = sdf.format(date);
		sl = SourceLoader.getInstance();
		user = User.getInstance();
		
		if(e.getSource() == mb.mode[0]) {
			//���ĳ�����ϰ
			String name = JOptionPane.showInputDialog(null, "�������û�����", "", JOptionPane.INFORMATION_MESSAGE);
			if(name == null) {
				return;
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "�������û�����", "", JOptionPane.ERROR_MESSAGE);
			} else {
				String modeName = mb.mode[0].getText();
				String typeName = "������ϰ";
				user.setInfo(name, startTime, modeName, typeName);
				
				sl.setPath(SourceLoader.CHI_WORD_PATH);
				sl.getText();
				new TypeBoard();
				mb.menu.setVisible(false);
			}
			
			
		} else if(e.getSource() == mb.mode[1]) {
			//���Ķ�����ϰ
			String name = JOptionPane.showInputDialog(null, "�������û�����", "", JOptionPane.INFORMATION_MESSAGE);
			if(name == null) {
				return;
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "�������û�����", "", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String modeName = mb.mode[1].getText();
			String[] articleList = {"�ᱮ", "���º���", "������ϲ�", "�潭��ܽ��",
										"���겻����", "����ǣţ��", "����", "����������" };
			String articleName = (String) JOptionPane.showInputDialog(null, "��ѡ����ϰƪĿ��", "",
																JOptionPane.INFORMATION_MESSAGE, 
																	null, articleList, articleList[0]);
			if(articleName != null) {
				String typeName = "������ϰ��" + articleName;
				user.setInfo(name, startTime, modeName, typeName);
					
				sl.setPath(SourceLoader.CHI_ARTICLE_PATH + articleName + ".txt");
				sl.getText();
				tb = new TypeBoard();
				mb.menu.setVisible(false);
			}
			
			
		} else if(e.getSource() == mb.mode[2]) {
			//Ӣ�ĵ�����ϰ
			String name = JOptionPane.showInputDialog(null, "�������û�����", "", JOptionPane.INFORMATION_MESSAGE);
			if(name == null) {
				return;
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "�������û�����", "", JOptionPane.ERROR_MESSAGE);
			} else {
				String modeName = mb.mode[2].getText();
				String typeName = "������ϰ";
				user.setInfo(name, startTime, modeName, typeName);
				
				sl.setPath(SourceLoader.ENG_WORD_PATH);
				sl.getText();
				new TypeBoard();
				mb.menu.setVisible(false);
			}
			
			
		} else if(e.getSource() == mb.mode[3]) {
			//Ӣ�Ķ�����ϰ
			String name = JOptionPane.showInputDialog(null, "�������û�����", "", JOptionPane.INFORMATION_MESSAGE);
			if(name == null) {
				return;
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "�������û�����", "", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String modeName = mb.mode[3].getText();
			String[] articleList = {"Ӣ��1", "Ӣ��2", "Ӣ��3", "Ӣ��4" };
			String articleName = (String) JOptionPane.showInputDialog(null, "��ѡ����ϰƪĿ��", "",
																JOptionPane.INFORMATION_MESSAGE, 
																	null, articleList, articleList[0]);
			if(articleName != null) {
				String typeName = "������ϰ��" + articleName;
				user.setInfo(name, startTime, modeName, typeName);
					
				sl.setPath(SourceLoader.ENG_ARTICLE_PATH + articleName + ".txt");
				sl.getText();
				new TypeBoard();
				mb.menu.setVisible(false);
			}
			
			
		} else if(e.getSource() == mb.mode[4]) {
			//������Ϸ
			String name = JOptionPane.showInputDialog(null, "�������û�����", "", JOptionPane.INFORMATION_MESSAGE);
			if(name == null) {
				return;
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "�������û�����", "", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String modeName = mb.mode[4].getText();
			// TODO x x x
			new GameBoard();
			mb.menu.setVisible(false);
			
			
		} else if(e.getSource() == mb.mode[5]) {
			//�˳�
			int statu = JOptionPane.showConfirmDialog(	null, "�Ƿ�Ҫ�˳�����", "", 
															JOptionPane.OK_CANCEL_OPTION,
																JOptionPane.QUESTION_MESSAGE	);
			if(statu == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
			
			
		} else if(e.getSource() == mb.checkList) {
			// �鿴���
			tld = null;
			tld = new TypeListDialog();
			
			
		} else if(e.getSource() == mb.checkRank) {
			// �鿴���ְ�
			rld = null;
			rld = new RankListDialog();
			
			
		} else if(e.getSource() == mb.helpItem) {
			// �鿴����
			hd = null;
			hd = new HelpDialog();
			
			
		} else if(e.getSource() == mb.infoItem) {
			// ����...
			id = null;
			id = new InfoDialog();
		}
	}
}
