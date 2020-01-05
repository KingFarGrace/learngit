package com.zw.util;

import javax.swing.tree.DefaultMutableTreeNode;

import com.zw.board.*;
/*
 * 平衡二叉树类，继承二叉树类，采用父类含参构造器
 */
public class BalenceBinaryTree extends BinaryTree {
	private static int taller;			//表示子树高度有无变化
	public static double[] remember= new double[100]; 
	public static BalenceBinaryTree ROOT;
	int balenceFactor = 0; 		//结点的平衡因子
	public BalenceBinaryTree lChild = null;
	public BalenceBinaryTree rChild = null;
	
	public BalenceBinaryTree(double data, int posFlag, BalenceBinaryTree father, DisplayingBoard db){
		super(data, posFlag, father, db);
	}
	
	/*
	 * 平衡二叉树插入算法
	 */
	public int insert(double data, int posFlag) {
		if(this.getData() == data) {
			taller = 0;
			return 0;
		} else if(this.getData() > data) {
			db.resultArea.append("	"+ data + "与结点 " + this.getData()+" 比较，结果为小于\n");
			if(lChild == null) {
				db.resultArea.append("	"+ "左孩子为空,插入" + data + "为新的结点\n");
				lChild = new BalenceBinaryTree(data, 0, this, db);
				taller = 1;
			} else {
				db.resultArea.append("	"+ "左孩子不为空,插入到"+ this.getData()+ "的左子树\n");
				if(lChild.insert(data, 0) == 0) {
					return 0;
				}
			}
			if(taller == 1) {
				this.leftProcess();
			}
		} else {
			db.resultArea.append("	"+ data + "与结点 " + this.getData()+" 比较，结果为大于\n");
			if(rChild == null) {
				db.resultArea.append("	"+ "右孩子为空,插入" + data + "为新的结点\n");
				rChild = new BalenceBinaryTree(data, 1, this, db);
				taller = 1;
			} else {
				db.resultArea.append("	"+ "右孩子不为空,插入到"+ this.getData()+ "的右子树\n");
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
	 * 左处理，包括LL和LR处理
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
	 * 右处理，包括RR和RL处理
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
		 * 对this为根的二叉树进行LL调整
		 */
		db.resultArea.append("	对" + this.getData() + "为根的二叉树进行LL调整\n");
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
		 * 对this为根的二叉树进行LR调整
		 */
		db.resultArea.append("	对" + this.getData() + "为根的二叉树进行LR调整\n");
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
		 * 修正调整后的平衡因子
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
		 * 对this为根的二叉树进行RR调整
		 */
		db.resultArea.append("	对" + this.getData() + "为根的二叉树进行RR调整\n");
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
		 * 对this为根的二叉树进行RL调整
		 */
		db.resultArea.append("	对" + this.getData() + "为根的二叉树进行RL调整\n");
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
		 * 修正调整后的平衡因子
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