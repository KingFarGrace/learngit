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
			//中文成语练习
			String name = JOptionPane.showInputDialog(null, "请输入用户名：", "", JOptionPane.INFORMATION_MESSAGE);
			if(name == null) {
				return;
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入用户名！", "", JOptionPane.ERROR_MESSAGE);
			} else {
				String modeName = mb.mode[0].getText();
				String typeName = "成语练习";
				user.setInfo(name, startTime, modeName, typeName);
				
				sl.setPath(SourceLoader.CHI_WORD_PATH);
				sl.getText();
				new TypeBoard();
				mb.menu.setVisible(false);
			}
			
			
		} else if(e.getSource() == mb.mode[1]) {
			//中文短文练习
			String name = JOptionPane.showInputDialog(null, "请输入用户名：", "", JOptionPane.INFORMATION_MESSAGE);
			if(name == null) {
				return;
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入用户名！", "", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String modeName = mb.mode[1].getText();
			String[] articleList = {"丰碑", "明月何皎皎", "青青河畔草", "涉江采芙蓉",
										"生年不满百", "迢迢牵牛星", "橡树", "行行重行行" };
			String articleName = (String) JOptionPane.showInputDialog(null, "请选择练习篇目：", "",
																JOptionPane.INFORMATION_MESSAGE, 
																	null, articleList, articleList[0]);
			if(articleName != null) {
				String typeName = "文章练习：" + articleName;
				user.setInfo(name, startTime, modeName, typeName);
					
				sl.setPath(SourceLoader.CHI_ARTICLE_PATH + articleName + ".txt");
				sl.getText();
				tb = new TypeBoard();
				mb.menu.setVisible(false);
			}
			
			
		} else if(e.getSource() == mb.mode[2]) {
			//英文单词练习
			String name = JOptionPane.showInputDialog(null, "请输入用户名：", "", JOptionPane.INFORMATION_MESSAGE);
			if(name == null) {
				return;
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入用户名！", "", JOptionPane.ERROR_MESSAGE);
			} else {
				String modeName = mb.mode[2].getText();
				String typeName = "单词练习";
				user.setInfo(name, startTime, modeName, typeName);
				
				sl.setPath(SourceLoader.ENG_WORD_PATH);
				sl.getText();
				new TypeBoard();
				mb.menu.setVisible(false);
			}
			
			
		} else if(e.getSource() == mb.mode[3]) {
			//英文短文练习
			String name = JOptionPane.showInputDialog(null, "请输入用户名：", "", JOptionPane.INFORMATION_MESSAGE);
			if(name == null) {
				return;
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入用户名！", "", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String modeName = mb.mode[3].getText();
			String[] articleList = {"英文1", "英文2", "英文3", "英文4" };
			String articleName = (String) JOptionPane.showInputDialog(null, "请选择练习篇目：", "",
																JOptionPane.INFORMATION_MESSAGE, 
																	null, articleList, articleList[0]);
			if(articleName != null) {
				String typeName = "文章练习：" + articleName;
				user.setInfo(name, startTime, modeName, typeName);
					
				sl.setPath(SourceLoader.ENG_ARTICLE_PATH + articleName + ".txt");
				sl.getText();
				new TypeBoard();
				mb.menu.setVisible(false);
			}
			
			
		} else if(e.getSource() == mb.mode[4]) {
			//打字游戏
			String name = JOptionPane.showInputDialog(null, "请输入用户名：", "", JOptionPane.INFORMATION_MESSAGE);
			if(name == null) {
				return;
			} else if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "请输入用户名！", "", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String modeName = mb.mode[4].getText();
			// TODO x x x
			new GameBoard();
			mb.menu.setVisible(false);
			
			
		} else if(e.getSource() == mb.mode[5]) {
			//退出
			int statu = JOptionPane.showConfirmDialog(	null, "是否要退出程序？", "", 
															JOptionPane.OK_CANCEL_OPTION,
																JOptionPane.QUESTION_MESSAGE	);
			if(statu == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
			
			
		} else if(e.getSource() == mb.checkList) {
			// 查看题库
			tld = null;
			tld = new TypeListDialog();
			
			
		} else if(e.getSource() == mb.checkRank) {
			// 查看积分榜
			rld = null;
			rld = new RankListDialog();
			
			
		} else if(e.getSource() == mb.helpItem) {
			// 查看帮助
			hd = null;
			hd = new HelpDialog();
			
			
		} else if(e.getSource() == mb.infoItem) {
			// 关于...
			id = null;
			id = new InfoDialog();
		}
	}
}
