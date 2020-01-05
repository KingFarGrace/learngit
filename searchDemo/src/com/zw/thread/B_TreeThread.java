package com.zw.thread;

import javax.swing.tree.*;

import com.zw.board.*;
import com.zw.util.*;

public class B_TreeThread implements Runnable {
	private DisplayingBoard db;
	public B_Tree root = null;
	public double[] data = new double[50];
	
	public B_TreeThread(DisplayingBoard db, double[] data) {
		this.db = db;
		for(int i = 0; i < data.length; i++) {
				this.data[i] = data[i];
		}
	}
	
	public void run() {
		db.resultArea.append("当前为B_TreeThread\n\n");
		for(int i = 0; data[i] != 0; i++) {
			db.resultArea.append("插入结点" + data[i] + "\n");
			if(root == null) {
				root = new B_Tree(null, db);	
				B_Tree.getRoot(root);
				root.add(data[i]);
			} else {
				root.add(data[i]);
			}
			if(data[i+1] == 0) {
				root.createTree(0);
			}
		}
		db.treeModel = new DefaultTreeModel(root.node);
		db.tree.setModel(db.treeModel);
		for(int row = 0; row < db.tree.getRowCount(); row++) {
			db.tree.expandRow(row);
		}
	}
}
