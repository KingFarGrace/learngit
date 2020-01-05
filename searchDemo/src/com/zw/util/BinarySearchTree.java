package com.zw.util;

import javax.swing.tree.*;

import com.zw.board.*;

/*
 * 二叉排序树类，继承二叉树类，采用父类含参构造器
 */
public class BinarySearchTree extends BinaryTree {

	public BinarySearchTree(double data, int posFlag, BinaryTree father,DisplayingBoard db) {
		super(data, posFlag, father, db);
	}
	
	@Override
	public void add(double data, int posFlag) {
		if(this.getData() < data) {
			db.resultArea.append("	"+ data + "与结点 " + this.getData()+" 比较，结果为大于\n");
			if(rChild == null) {
				db.resultArea.append("	"+ "右孩子为空,插入" + data + "为新的结点\n");
				rChild = new BinarySearchTree(data, 1, this, db);
			} else {
				db.resultArea.append("	"+ "右孩子不为空,插入到"+ this.getData()+ "的右子树\n");
				rChild.add(data, 1);
			}
		} else if(this.getData() > data) {
			db.resultArea.append("	"+ data + "与结点 " + this.getData()+" 比较，结果为小于\n");
			if(lChild == null) {
				db.resultArea.append("	"+ "左孩子为空,插入" + data + "为新的结点\n");
				lChild = new BinarySearchTree(data, 0, this, db);
			} else {
				db.resultArea.append("	"+ "左孩子不为空,插入到"+ this.getData()+ "的左子树\n");
				lChild.add(data, 0);
			}
		}
	}
	
	public void preOrder() {
		this.createTree();
		if(lChild != null) {
			lChild.preOrder();
			this.node.add(lChild.node);
		} 
		if(rChild != null) {
			rChild.preOrder();
			this.node.add(rChild.node);
		}	
	}
	
	public void createTree() {
		if(this.posFlag == -1) {
			node = new DefaultMutableTreeNode(this.getData()+" "+BinaryTree.ROOT);
		} else if(this.posFlag == 0) {
			node = new DefaultMutableTreeNode(this.getData()+" "+BinaryTree.L_NODE);
		} else if(this.posFlag == 1) {
			node =new DefaultMutableTreeNode(this.getData()+" "+BinaryTree.R_NODE);
		}
	}
}
