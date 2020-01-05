package com.zw.thread;

import javax.swing.tree.*;

import com.zw.board.*;
import com.zw.util.*;

public class BinarySearchThread implements Runnable{
	private DisplayingBoard db;
	public BinarySearchTree root = null;
	public double[] data = new double[30];
	
	public BinarySearchThread(DisplayingBoard db, double[] data) {
		this.db = db;
		for(int i = 0; i < data.length; i++) {
				this.data[i] = data[i];
		}
	}
	
	public void run() {
		for(int i = 0; data[i] != 0; i++) {
			if(root == null) {
				db.resultArea.append("树为空,创建根结点" + data[i] + "\n\n");
				root = new BinarySearchTree(data[0], -1, null, db);	
			} else {
				db.resultArea.append("插入结点" + data[i] + "\n");
				root.add(data[i], 1);
				db.resultArea.append("\n");
			}
		}
		db.bst.root.preOrder();
		db.treeModel = new DefaultTreeModel(root.node);
		db.tree.setModel(db.treeModel);
		for(int row = 0; row < db.tree.getRowCount(); row++) {
			db.tree.expandRow(row);
		}
	}
}
