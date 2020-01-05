package com.zw.util;

import javax.swing.tree.DefaultMutableTreeNode;

import com.zw.board.DisplayingBoard;

/*
 * 二叉树基本操作
 */
public class BinaryTree {
	/*
	 * 定义结点位置常量：根结点，左孩子结点，右孩子结点
	 */
	public static final String ROOT = "Root";
	public static final String L_NODE = "LNode";
	public static final String R_NODE = "RNode";
	
	public DefaultMutableTreeNode node;
	
	private double data = 0;
	/*
	 * posFlag = -1 : 当前节点为根节点
	 * posFlag = 0 : 当前结点为左孩子
	 * posFlag = 1 : 当前结点为右孩子
	 */
    public int posFlag;
	public BinaryTree lChild = null;
	public BinaryTree rChild = null;
	public BinaryTree father = null;
	public DisplayingBoard db;
	
	public BinaryTree() {
		// Empty constructor
	}
	
	/*
	 * 含参构造器 : 结点数据，结点位置（左，右，根），父结点实例（根结点的父结点为null）
	 */
	public BinaryTree(double data, int posFlag, BinaryTree father, DisplayingBoard db) {
		this.data = data;
		this.posFlag = posFlag;
		this.father = father;
		this.db = db;
	}
	
	/*
	 * 添加结点
	 */
	public void add(double data, int posFlag) {
		if(posFlag == 0) {
			this.lChild = new BinaryTree(data, posFlag, this, db);
		} else {
			this.rChild = new BinaryTree(data, posFlag, this, db);
		}
	}
	
	/*
	 * 返回父结点实例对象
	 */
	public BinaryTree getFatherNode() {		
		return father;
	}
	
	public double getData() {
		return data;
	}
	
	public String getPosition() {
		if(posFlag == -1) {
			return ROOT;
		} else if(posFlag == 0) {
			return L_NODE;
		} else {
			return R_NODE;
		}
	}
	
	/*
	 * 从当前结点先序遍历
	 */
	public void preOrder() {
		System.out.println(data + "  " + posFlag);
		if(lChild != null) {
			lChild.preOrder();
		} 
		if(rChild != null) {
			rChild.preOrder();
		}		
	}
	
	/*
	 * 寻根结点，返回一个根节点实例对象
	 */
	public BinaryTree getRoot() {
		BinaryTree father = this;
		while(father.posFlag != -1) {
			father = father.father;
		}
		return father;
	}
	
	/*
	 * 查找并显示在面板控制台区
	 */
	public void search(double key) {
		db.resultArea.append("与结点" + this.getData() + "比较,");
		if(this.getData() == key) {
			db.resultArea.append("查找" + key + "成功!\n");
		} else if(lChild == null && rChild == null){
			db.resultArea.append("查找" + key + "失败!\n");
		} else if (this.getData() < key) {
			db.resultArea.append("结果" + this.getData() + "较小," + "查找其右子树\n");
			this.rChild.search(key);
		} else {
			db.resultArea.append("结果" + this.getData() + "较大," + "查找其左子树\n");
			this.lChild.search(key);
		}
	}
}
