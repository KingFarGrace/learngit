package com.zw.util;

import javax.swing.tree.DefaultMutableTreeNode;

import com.zw.board.DisplayingBoard;

/*
 * ��������������
 */
public class BinaryTree {
	/*
	 * ������λ�ó���������㣬���ӽ�㣬�Һ��ӽ��
	 */
	public static final String ROOT = "Root";
	public static final String L_NODE = "LNode";
	public static final String R_NODE = "RNode";
	
	public DefaultMutableTreeNode node;
	
	private double data = 0;
	/*
	 * posFlag = -1 : ��ǰ�ڵ�Ϊ���ڵ�
	 * posFlag = 0 : ��ǰ���Ϊ����
	 * posFlag = 1 : ��ǰ���Ϊ�Һ���
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
	 * ���ι����� : ������ݣ����λ�ã����ң������������ʵ���������ĸ����Ϊnull��
	 */
	public BinaryTree(double data, int posFlag, BinaryTree father, DisplayingBoard db) {
		this.data = data;
		this.posFlag = posFlag;
		this.father = father;
		this.db = db;
	}
	
	/*
	 * ��ӽ��
	 */
	public void add(double data, int posFlag) {
		if(posFlag == 0) {
			this.lChild = new BinaryTree(data, posFlag, this, db);
		} else {
			this.rChild = new BinaryTree(data, posFlag, this, db);
		}
	}
	
	/*
	 * ���ظ����ʵ������
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
	 * �ӵ�ǰ����������
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
	 * Ѱ����㣬����һ�����ڵ�ʵ������
	 */
	public BinaryTree getRoot() {
		BinaryTree father = this;
		while(father.posFlag != -1) {
			father = father.father;
		}
		return father;
	}
	
	/*
	 * ���Ҳ���ʾ��������̨��
	 */
	public void search(double key) {
		db.resultArea.append("����" + this.getData() + "�Ƚ�,");
		if(this.getData() == key) {
			db.resultArea.append("����" + key + "�ɹ�!\n");
		} else if(lChild == null && rChild == null){
			db.resultArea.append("����" + key + "ʧ��!\n");
		} else if (this.getData() < key) {
			db.resultArea.append("���" + this.getData() + "��С," + "������������\n");
			this.rChild.search(key);
		} else {
			db.resultArea.append("���" + this.getData() + "�ϴ�," + "������������\n");
			this.lChild.search(key);
		}
	}
}
