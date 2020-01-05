package com.zw.util;

import javax.swing.tree.DefaultMutableTreeNode;

import com.zw.board.*;

public class B_Tree {
	
	/*
	 * 	常量：
	 * 	左兄弟
	 * 	右兄弟
	 * 	阶数
	 * 	节点最小长度
	 */
	
	public static final int LCHILD_SIBLING = 0;
	public static final int RCHILD_SIBLING = 1;
	public static final int STEP = 4;	
	public static final int MIN_NODE_LENGTH = STEP / 2;
	
	/*
	 *	变量：
	 * 	B树结点存放的记录个数
	 * 	B树父结点
	 * 	B树结点数据数组（非叶子记录子树起始位置，叶子记录区间内数据集）
	 * 	B树子树指针数组
	 * 	[静态变量] 树根
	 */
	
	private int keyNum = 0;	
	private B_Tree father = null;
	/*
	 * 	record[0 -> STEP - 2] : data
	 * 	record[STEP - 1] : 溢出位，不存放数据
	 */
	private double[] record = new double[STEP];
	/*
	 * 	recordPtr[0] 对应 record[1] 之前的区间
	 * 	recordPtr[n] 对应 record[n] -> record[n + 1] 之间的区间
	 * 	recordPtr[STEP - 1] 对应 record[STEP - 1] 之后的区间
	 */
	public B_Tree[] recordPtr = new B_Tree[STEP + 1];
	public static B_Tree root;
	public DisplayingBoard db;
	public DefaultMutableTreeNode node;
	
	public B_Tree() {
		// Empty constructor
	}
	
	public B_Tree(B_Tree father, DisplayingBoard db) {
		this.father = father;
		this.db = db;
	}
	
	/*
	 * 	添加新记录
	 */
	public void add(double key) {
		db.resultArea.append("进入add方法\n");
		B_Tree btp = getInsertPos(root, key);	
		btp.insertData(btp.record, key);
		
		if(btp.keyNum >= STEP) {
			db.resultArea.append("	裂开了\n");
			nodeSplit(btp);
		}
		db.resultArea.append("退出add方法\n");
	}
	
	/*
	 * 	结点分裂
	 */
	public void nodeSplit(B_Tree anyNode) {
		db.resultArea.append("进入nodeSplit方法\n");
		while(anyNode.keyNum >= STEP) {
			// 当前结点记录数超过阶数时
			if(anyNode.father == null) {
				//若为根结点
				B_Tree rootNode = new B_Tree(null, db);
				anyNode.father = rootNode;
				getRoot(rootNode);
			} else {
				B_Tree father = anyNode.father;
				double midRecord = anyNode.record[anyNode.keyNum / 2];
				//当前节点中间记录交给父结点
				father.insertData(father.record, midRecord);			
				db.resultArea.append("	father的记录数为：" + father.keyNum + "\n");
				/*
				 * 	获得分裂结点record数组，设置两新数组对应keyNum
				 * 	构造新B树结点，从父类区间分配两指针给当前结点和新结点
				 * 	将新数组赋给新结点
				 * 	将父结点赋给当前结点
				 */
				double[] newRecord = new double[STEP];
				
				anyNode.keyNum = arrayMidSplit(anyNode.record, newRecord);
				db.resultArea.append("	anyNode记录数为：" + anyNode.keyNum + "\n");
				int index = father.getIndexInArray(father.record, midRecord);
				db.resultArea.append("	father新增记录索引：" + index + "\n");
				B_Tree newNode = new B_Tree(father, db);
				father.recordPtr[index] = anyNode;
				father.recordPtr[index + 1] = newNode;				
				newNode.setRecord(newRecord);
				newNode.keyNum = STEP - anyNode.keyNum - 1;
				db.resultArea.append("	newNode记录数为：" + newNode.keyNum + "\n");
				anyNode = father;
			} // end_else			
		} // end_while
		db.resultArea.append("退出nodeSplit方法\n");
	}
	
	/*
	 * 	插入数据
	 */
	public void insertData(double[] record, double key) {
		db.resultArea.append("进入insertData方法\n");
		int i = 0;
		db.resultArea.append("	添加了一个新纪录：" + key + "\n");
		db.resultArea.append("	插入前的记录数：" + keyNum + "\n");
		while(record[i] < key && record[i] > 0) {
			i++;
		}
		for(int j = keyNum; j > i; j--) {
			record[j] = record[j - 1];
		}
		record[i] = key;
		keyNum++;
		db.resultArea.append("	插入后记录数：" + keyNum + "\n");
		db.resultArea.append("	插入后记录数组为：");
		for(int j = 0; j < STEP; j++) {
			db.resultArea.append(record[j] + " ");
		}
		db.resultArea.append("\n");
		db.resultArea.append("退出insertData方法\n");
	}
	
	/*
	 * 	将前一数组从中间分裂，中值舍弃，前段分给array1，后段分给array2
	 * 	返回值为前段数组的长度
	 */
	public int arrayMidSplit(double[] array1, double[] array2) {
		db.resultArea.append("进入arrayMidSpilt方法\n");
		db.resultArea.append("	被拆分数组：" + " ");
		for(double data : array1) {
			db.resultArea.append(data + " ");
		}
		db.resultArea.append("\n");
		
		int mid = STEP / 2;
		array1[mid] = 0;
		for(int i = mid + 1, j = 0; i < STEP; i++, j++) {
			db.resultArea.append("OK");
			array2[j] = array1[i];
			array1[i] = 0;
		}
		
		db.resultArea.append("	长度为：" + mid + "\n");
		db.resultArea.append("	array1：" + " ");
		for(double data : array1) {
			db.resultArea.append(data + " ");
		}
		db.resultArea.append("\n	array2：" + " ");
		for(double data : array2) {
			db.resultArea.append(data + " ");
		}
		db.resultArea.append("\n\n退出arrayMidSpilt方法\n");
		return mid;
	}
	
	public void setRecord(double[] record) {
		this.record = record;
	}
	
	/*
	 *  get类方法：
	 *  	得到数据插入位置，返回区间指针
	 *  	得到父结点
	 *  	得到记录数
	 *  	得到兄弟结点
	 *  	得到数组元素索引
	 *  	[静态方法] 寻树根并记录
	 */
	public B_Tree getInsertPos(B_Tree root, double key) {
		db.resultArea.append("进入getInsertPos方法\n");
		if(root.isRecordPtrEmpty(root.recordPtr)) {
			// 只含树根的树
			db.resultArea.append("当前结点记录为： ");
			for(double data : root.record) {
				db.resultArea.append(data + " ");
			}
			db.resultArea.append("\n");
			db.resultArea.append("退出getInsertPos方法\n");
			
			return root;
		} else {
			B_Tree btp = root;
			int index = 0;
			db.resultArea.append("当前结点记录为： ");
			for(double data : root.record) {
				db.resultArea.append(data + " ");
			}
			db.resultArea.append("\n");
			/*
			 * 	当 btp 不为叶子结点时，按索引值逐层下降，并打印进入的区间
			 */
			while(btp.isRecordPtrEmpty(btp.recordPtr) == false) {
				while(key >= btp.record[index] && btp.record[index] != 0) {
					index++;
				}																					
				db.resultArea.append("——>recordPtr[" + index + "]" + "\n");
				btp = btp.recordPtr[index];
			}
			db.resultArea.append("退出getInsertPos方法\n");
			return btp;
		} // end_else
	}
	
	public B_Tree getFatherNode() {
		return father;
	}
	
	public int getKeyNum() {
		return keyNum;
	}	
	
	public B_Tree getSibling(int position) {
		if(position == LCHILD_SIBLING) {
			int ptrIndex = this.father.getIndexInArray(recordPtr, this);
			if(ptrIndex > 0) {
				return this.father.recordPtr[ptrIndex - 1];
			} else {
				db.resultArea.append("没有左兄弟！");
				return null;
			}
			
		} else {
			int ptrIndex = this.father.getIndexInArray(recordPtr, this);
			if(ptrIndex < keyNum) {
				return this.father.recordPtr[ptrIndex + 1];
			} else {
				db.resultArea.append("没有右兄弟！");
				return null;
			}
		}
	}
	
	public int getIndexInArray(Object[] array, Object key) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == key) {
				return i;
			}
		}
		db.resultArea.append("No Such Element！");
		return -1;
	}
	
	/*
	 * 	重载方法
	 */
	public int getIndexInArray(double[] array, double key) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == key) {
				return i;
			}
		}
		db.resultArea.append("No Such Element！");
		return -1;
	}
	
	public static void getRoot(B_Tree anyNode) {
		B_Tree btp = anyNode;
		while(anyNode.father != null) {
			btp = anyNode.father;
		}
		root = btp;
	}
	
	/*
	 * 	boolean类方法：
	 * 	判断是否为叶子：若当前节点子树指针数组第一位为空，则必然不存在子树，即为叶子
	 */
	public boolean isRecordPtrEmpty(B_Tree[] recordPtr) {
		if(recordPtr[0] == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void createTree(int pos) {
		String nodeData = "";
		for(int i = 0; i < STEP-1; i++) {
			nodeData = nodeData + this.record[i];
			if(i < STEP-2) {
				nodeData = nodeData + " , ";
			}
		}
		nodeData = nodeData + " --"+pos;
		node = new DefaultMutableTreeNode(nodeData);
		for(int i = 0; i < STEP; i++) {
			if(recordPtr[i] != null) {
				recordPtr[i].createTree(i+1);
				this.node.add(recordPtr[i].node);
			}
		}
	}
	
}
