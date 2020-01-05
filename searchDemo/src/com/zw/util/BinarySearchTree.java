package com.zw.util;

import javax.swing.tree.*;

import com.zw.board.*;

/*
 * �����������࣬�̳ж������࣬���ø��ຬ�ι�����
 */
public class BinarySearchTree extends BinaryTree {

	public BinarySearchTree(double data, int posFlag, BinaryTree father,DisplayingBoard db) {
		super(data, posFlag, father, db);
	}
	
	@Override
	public void add(double data, int posFlag) {
		if(this.getData() < data) {
			db.resultArea.append("	"+ data + "���� " + this.getData()+" �Ƚϣ����Ϊ����\n");
			if(rChild == null) {
				db.resultArea.append("	"+ "�Һ���Ϊ��,����" + data + "Ϊ�µĽ��\n");
				rChild = new BinarySearchTree(data, 1, this, db);
			} else {
				db.resultArea.append("	"+ "�Һ��Ӳ�Ϊ��,���뵽"+ this.getData()+ "��������\n");
				rChild.add(data, 1);
			}
		} else if(this.getData() > data) {
			db.resultArea.append("	"+ data + "���� " + this.getData()+" �Ƚϣ����ΪС��\n");
			if(lChild == null) {
				db.resultArea.append("	"+ "����Ϊ��,����" + data + "Ϊ�µĽ��\n");
				lChild = new BinarySearchTree(data, 0, this, db);
			} else {
				db.resultArea.append("	"+ "���Ӳ�Ϊ��,���뵽"+ this.getData()+ "��������\n");
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
