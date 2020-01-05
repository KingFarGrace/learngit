package com.zw.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.zw.board.*;
import com.zw.util.*;

public class DisplayingBoardEvent implements ActionListener {
	private DisplayingBoard db;
	
	public DisplayingBoardEvent(DisplayingBoard db) {
		this.db = db;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == db.menuButton[0]) {
			if(db.modelChoosen == 0) {
				double elem = 0;
				if(db.dataVisible.getSelectedIndex() != 0) {
					db.resultArea.setText("");
					db.bst.root = null;
					elem = db.bst.data[db.dataVisible.getSelectedIndex()];
					db.bst.data[db.dataVisible.getSelectedIndex()] = 0;
				}
				db.bst.run();
				if(db.dataVisible.getSelectedIndex() != 0) {
					db.bst.data[db.dataVisible.getSelectedIndex()] = elem;
				}
			} else if (db.modelChoosen == 1) {
				double elem = 0;
				if(db.dataVisible.getSelectedIndex() != 0) {
					db.resultArea.setText("");
					BalenceBinaryTree.ROOT = null;
					elem = db.bt.data[db.dataVisible.getSelectedIndex()];
					db.bt.data[db.dataVisible.getSelectedIndex()] = 0;
				}
				db.bt.run();
				if(db.dataVisible.getSelectedIndex() != 0) {
					db.bt.data[db.dataVisible.getSelectedIndex()] = elem;
				}
			} else if (db.modelChoosen == 2) {
				double elem = 0;
				if(db.dataVisible.getSelectedIndex() != 0) {
					db.resultArea.setText("");
					db.btt.root = null;
					elem = db.btt.data[db.dataVisible.getSelectedIndex()];
					db.btt.data[db.dataVisible.getSelectedIndex()] = 0;
				}
				db.btt.run();
				if(db.dataVisible.getSelectedIndex() != 0) {
					db.btt.data[db.dataVisible.getSelectedIndex()] = elem;
				}
			}
		} else if(e.getSource() == db.menuButton[1]) {
			double key = Double.parseDouble(db.dataSearch.getText());
			db.resultArea.append("²éÕÒ¹Ø¼ü×Ö:"+db.dataSearch.getText()+"\n");
			if(db.modelChoosen == 0) {
				db.bst.root.search(key);
			} else if(db.modelChoosen == 1) {
				BalenceBinaryTree.ROOT.search(key);
			}
		} else if(e.getSource() == db.menuButton[2]) {
			new ModeSelectionBoard(db.modelChoosen);
			db.menu.setVisible(false);
			
		} else if(e.getSource() == db.menuButton[3]) {
			new MainBoard();
			db.menu.setVisible(false);
		}
	}
}
