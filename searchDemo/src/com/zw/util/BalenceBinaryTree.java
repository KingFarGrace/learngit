package com.zw.util;

import javax.swing.tree.DefaultMutableTreeNode;

import com.zw.board.*;
/*
 * ƽ��������࣬�̳ж������࣬���ø��ຬ�ι�����
 */
public class BalenceBinaryTree extends BinaryTree {
	private static int taller;			//��ʾ�����߶����ޱ仯
	public static double[] remember= new double[100]; 
	public static BalenceBinaryTree ROOT;
	int balenceFactor = 0; 		//����ƽ������
	public BalenceBinaryTree lChild = null;
	public BalenceBinaryTree rChild = null;
	
	public BalenceBinaryTree(double data, int posFlag, BalenceBinaryTree father, DisplayingBoard db){
		super(data, posFlag, father, db);
	}
	
	/*
	 * ƽ������������㷨
	 */
	public int insert(double data, int posFlag) {
		if(this.getData() == data) {
			taller = 0;
			return 0;
		} else if(this.getData() > data) {
			db.resultArea.append("	"+ data + "���� " + this.getData()+" �Ƚϣ����ΪС��\n");
			if(lChild == null) {
				db.resultArea.append("	"+ "����Ϊ��,����" + data + "Ϊ�µĽ��\n");
				lChild = new BalenceBinaryTree(data, 0, this, db);
				taller = 1;
			} else {
				db.resultArea.append("	"+ "���Ӳ�Ϊ��,���뵽"+ this.getData()+ "��������\n");
				if(lChild.insert(data, 0) == 0) {
					return 0;
				}
			}
			if(taller == 1) {
				this.leftProcess();
			}
		} else {
			db.resultArea.append("	"+ data + "���� " + this.getData()+" �Ƚϣ����Ϊ����\n");
			if(rChild == null) {
				db.resultArea.append("	"+ "�Һ���Ϊ��,����" + data + "Ϊ�µĽ��\n");
				rChild = new BalenceBinaryTree(data, 1, this, db);
				taller = 1;
			} else {
				db.resultArea.append("	"+ "�Һ��Ӳ�Ϊ��,���뵽"+ this.getData()+ "��������\n");
				if(rChild.insert(data, 1) == 0) {
					return 0;
				}
			}
			if(taller == 1) {
				this.rightProcess();
			}
		}
		return 1;
	}
	
	/*
	 * ��������LL��LR����
	 */
	public void leftProcess() {
		BalenceBinaryTree p;
		if(this.balenceFactor == 0) {
			this.balenceFactor = 1;
			taller = 1;
		} else if(this.balenceFactor == -1) {
			this.balenceFactor = 0;
			taller = 0;
		} else {
			p = lChild;
			if(p.balenceFactor == 1) {
				this.change_LL();
			}
			else if(p.balenceFactor == -1) {
				this.change_LR();
			}
			taller = 0;
		}
	}
	
	/*
	 * �Ҵ�������RR��RL����
	 */
	public void rightProcess() {
		BalenceBinaryTree p;
		if(this.balenceFactor == 0) {
			this.balenceFactor = -1;
			taller = 1;
		} else if(this.balenceFactor == 1) {
			this.balenceFactor = 0;
			taller = 0;
		} else {
			p = this.rChild;
			if(p.balenceFactor == -1) {
				this.change_RR();
			}
			else if(p.balenceFactor == 1) {
				this.change_RL();
			}
			taller = 0;
		}
	}
	
	public void change_LL() {
		/*
		 * ��thisΪ���Ķ���������LL����
		 */
		db.resultArea.append("	��" + this.getData() + "Ϊ���Ķ���������LL����\n");
		BalenceBinaryTree p1, q;
		p1 = lChild;
		q = this;
		if(this == ROOT) {
			ROOT = p1;
		} else {
			BalenceBinaryTree x = ROOT;
			while(x != null) {
				if(x.getData() > this.getData()) {
					if(x.lChild == this) {
						x.lChild = p1;
						break;
					} else {
						x = x.lChild;
					}
				} else if(x.getData() < this.getData()) {
					if(x.rChild == this) {
						x.rChild = p1;
						break;
					} else {
						x = x.rChild;
					}
				}
			}
		}
		lChild = p1.rChild;
		p1.rChild = q;
		balenceFactor = p1.balenceFactor=0;
		q = p1;
	}
	
	public void change_LR() {
		/*
		 * ��thisΪ���Ķ���������LR����
		 */
		db.resultArea.append("	��" + this.getData() + "Ϊ���Ķ���������LR����\n");
		BalenceBinaryTree p1, p2, q;
		p1 = lChild;
		p2 = p1.rChild;
		q = this;
		if(this == ROOT) {
			ROOT = p2;
		} else {
			BalenceBinaryTree x = ROOT;
			while(x != null) {
				if(x.getData() > this.getData()) {
					if(x.lChild == this) {
						x.lChild = p2;
						break;
					} else {
						x = x.lChild;
					}
				} else if(x.getData() < this.getData()) {
					if(x.rChild == this) {
						x.rChild = p2;
						break;
					} else {
						x = x.rChild;
					}
				}
			}
		}
		p1.rChild = p2.lChild;
		q.lChild = p2.rChild;
		p2.lChild = p1;
		p2.rChild = q;
		/*
		 * �����������ƽ������
		 */
		if(p2.balenceFactor == 1) {
			q.balenceFactor = -1;
			p1.balenceFactor = 0;
		} else if(p2.balenceFactor == 0) {
			q.balenceFactor = 0;
			p1.balenceFactor = 0;
		} else {
			q.balenceFactor = 0;
			p1.balenceFactor = 1;
		}
		p2.balenceFactor = 0;
		q = p2;
	}
	
	public void change_RR() {
		/*
		 * ��thisΪ���Ķ���������RR����
		 */
		db.resultArea.append("	��" + this.getData() + "Ϊ���Ķ���������RR����\n");
		BalenceBinaryTree p1, q;
		p1 = rChild;
		q = this;
		if(this == ROOT) {
			ROOT = p1;
		} else {
			BalenceBinaryTree x = ROOT;
			while(x != null) {
				if(x.getData() > this.getData()) {
					if(x.lChild == this) {
						x.lChild = p1;
						break;
					} else {
						x = x.lChild;
					}
				} else if(x.getData() < this.getData()) {
					if(x.rChild == this) {
						x.rChild = p1;
						break;
					} else {
						x = x.rChild;
					}
				}
			}
		}	
		rChild = p1.lChild;
		p1.lChild = q;
		balenceFactor = p1.balenceFactor=0;
		q = p1;	
	}
	
	public void change_RL() {
		/*
		 * ��thisΪ���Ķ���������RL����
		 */
		db.resultArea.append("	��" + this.getData() + "Ϊ���Ķ���������RL����\n");
		BalenceBinaryTree p1, p2, q;
		p1 = rChild;
		p2 = p1.lChild;
		q = this;
		if(this == ROOT) {
			ROOT=p2;
		} else {
			BalenceBinaryTree x = ROOT;
			while(x != null) {
				if(x.getData() > this.getData()) {
					if(x.lChild == this) {
						x.lChild = p2;
						break;
					} else {
						x = x.lChild;
					}
				} else if(x.getData() < this.getData()) {
					if(x.rChild == this) {
						x.rChild = p2;
						break;
					} else {
						x = x.rChild;
					}
				}
			}
		}
		p1.lChild = p2.rChild;
		q.rChild = p2.lChild;
		p2.rChild = p1;
		p2.lChild = q;
		/*
		 * �����������ƽ������
		 */
		if(p2.balenceFactor == -1) {
			q.balenceFactor = 1;
			p1.balenceFactor = 0;
		} else if(p2.balenceFactor == 0) {
			q.balenceFactor = 0;
			p1.balenceFactor = 0;
		} else {
			q.balenceFactor = 0;
			p1.balenceFactor = -1;
		}
		p2.balenceFactor = 0;
		q = p2;
	}	
	
	public void preOrder(int pos) {
		node = new DefaultMutableTreeNode(this.getData());
		remember[pos] = this.getData();
		if(lChild != null) {
			lChild.preOrder(pos*2);
			this.node.add(lChild.node);
		} 
		if(rChild != null) {
			rChild.preOrder(pos*2+1);
			this.node.add(rChild.node);
		}	
	}
	
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