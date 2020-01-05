package com.zw.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.zw.board.*;

public class MainBoardEvent implements ActionListener{
	private MainBoard mb;
	
	public MainBoardEvent(MainBoard mb){
		this.mb = mb;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mb.menuButton[0]) {
			new ModeSelectionBoard(0);
			mb.menu.setVisible(false);
			
		} else if(e.getSource() == mb.menuButton[1]) {
			new ModeSelectionBoard(1);
			mb.menu.setVisible(false);
			
		} else if(e.getSource() == mb.menuButton[2]) {
			new ModeSelectionBoard(2);
			mb.menu.setVisible(false);
			
		} else if(e.getSource() == mb.menuButton[3]) {
			int statu = JOptionPane.showConfirmDialog(null, "确认要退出吗？", "", JOptionPane.OK_CANCEL_OPTION);
			if(statu == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}		
	}
}
