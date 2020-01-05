package com.zw.util;

import javax.swing.tree.DefaultMutableTreeNode;

import com.zw.board.*;

public class B_Tree {
	
	/*
	 * 	������
	 * 	���ֵ�
	 * 	���ֵ�
	 * 	����
	 * 	�ڵ���С����
	 */
	
	public static final int LCHILD_SIBLING = 0;
	public static final int RCHILD_SIBLING = 1;
	public static final int STEP = 4;	
	public static final int MIN_NODE_LENGTH = STEP / 2;
	
	/*
	 *	������
	 * 	B������ŵļ�¼����
	 * 	B�������
	 * 	B������������飨��Ҷ�Ӽ�¼������ʼλ�ã�Ҷ�Ӽ�¼���������ݼ���
	 * 	B������ָ������
	 * 	[��̬����] ����
	 */
	
	private int keyNum = 0;	
	private B_Tree father = null;
	/*
	 * 	record[0 -> STEP - 2] : data
	 * 	record[STEP - 1] : ���λ�����������
	 */
	private double[] record = new double[STEP];
	/*
	 * 	recordPtr[0] ��Ӧ record[1] ֮ǰ������
	 * 	recordPtr[n] ��Ӧ record[n] -> record[n + 1] ֮�������
	 * 	recordPtr[STEP - 1] ��Ӧ record[STEP - 1] ֮�������
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
	 * 	����¼�¼
	 */
	public void add(double key) {
		db.resultArea.append("����add����\n");
		B_Tree btp = getInsertPos(root, key);	
		btp.insertData(btp.record, key);
		
		if(btp.keyNum >= STEP) {
			db.resultArea.append("	�ѿ���\n");
			nodeSplit(btp);
		}
		db.resultArea.append("�˳�add����\n");
	}
	
	/*
	 * 	������
	 */
	public void nodeSplit(B_Tree anyNode) {
		db.resultArea.append("����nodeSplit����\n");
		while(anyNode.keyNum >= STEP) {
			// ��ǰ����¼����������ʱ
			if(anyNode.father == null) {
				//��Ϊ�����
				B_Tree rootNode = new B_Tree(null, db);
				anyNode.father = rootNode;
				getRoot(rootNode);
			} else {
				B_Tree father = anyNode.father;
				double midRecord = anyNode.record[anyNode.keyNum / 2];
				//��ǰ�ڵ��м��¼���������
				father.insertData(father.record, midRecord);			
				db.resultArea.append("	father�ļ�¼��Ϊ��" + father.keyNum + "\n");
				/*
				 * 	��÷��ѽ��record���飬�������������ӦkeyNum
				 * 	������B����㣬�Ӹ������������ָ�����ǰ�����½��
				 * 	�������鸳���½��
				 * 	������㸳����ǰ���
				 */
				double[] newRecord = new double[STEP];
				
				anyNode.keyNum = arrayMidSplit(anyNode.record, newRecord);
				db.resultArea.append("	anyNode��¼��Ϊ��" + anyNode.keyNum + "\n");
				int index = father.getIndexInArray(father.record, midRecord);
				db.resultArea.append("	father������¼������" + index + "\n");
				B_Tree newNode = new B_Tree(father, db);
				father.recordPtr[index] = anyNode;
				father.recordPtr[index + 1] = newNode;				
				newNode.setRecord(newRecord);
				newNode.keyNum = STEP - anyNode.keyNum - 1;
				db.resultArea.append("	newNode��¼��Ϊ��" + newNode.keyNum + "\n");
				anyNode = father;
			} // end_else			
		} // end_while
		db.resultArea.append("�˳�nodeSplit����\n");
	}
	
	/*
	 * 	��������
	 */
	public void insertData(double[] record, double key) {
		db.resultArea.append("����insertData����\n");
		int i = 0;
		db.resultArea.append("	�����һ���¼�¼��" + key + "\n");
		db.resultArea.append("	����ǰ�ļ�¼����" + keyNum + "\n");
		while(record[i] < key && record[i] > 0) {
			i++;
		}
		for(int j = keyNum; j > i; j--) {
			record[j] = record[j - 1];
		}
		record[i] = key;
		keyNum++;
		db.resultArea.append("	������¼����" + keyNum + "\n");
		db.resultArea.append("	������¼����Ϊ��");
		for(int j = 0; j < STEP; j++) {
			db.resultArea.append(record[j] + " ");
		}
		db.resultArea.append("\n");
		db.resultArea.append("�˳�insertData����\n");
	}
	
	/*
	 * 	��ǰһ������м���ѣ���ֵ������ǰ�ηָ�array1����ηָ�array2
	 * 	����ֵΪǰ������ĳ���
	 */
	public int arrayMidSplit(double[] array1, double[] array2) {
		db.resultArea.append("����arrayMidSpilt����\n");
		db.resultArea.append("	��������飺" + " ");
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
		
		db.resultArea.append("	����Ϊ��" + mid + "\n");
		db.resultArea.append("	array1��" + " ");
		for(double data : array1) {
			db.resultArea.append(data + " ");
		}
		db.resultArea.append("\n	array2��" + " ");
		for(double data : array2) {
			db.resultArea.append(data + " ");
		}
		db.resultArea.append("\n\n�˳�arrayMidSpilt����\n");
		return mid;
	}
	
	public void setRecord(double[] record) {
		this.record = record;
	}
	
	/*
	 *  get�෽����
	 *  	�õ����ݲ���λ�ã���������ָ��
	 *  	�õ������
	 *  	�õ���¼��
	 *  	�õ��ֵܽ��
	 *  	�õ�����Ԫ������
	 *  	[��̬����] Ѱ��������¼
	 */
	public B_Tree getInsertPos(B_Tree root, double key) {
		db.resultArea.append("����getInsertPos����\n");
		if(root.isRecordPtrEmpty(root.recordPtr)) {
			// ֻ����������
			db.resultArea.append("��ǰ����¼Ϊ�� ");
			for(double data : root.record) {
				db.resultArea.append(data + " ");
			}
			db.resultArea.append("\n");
			db.resultArea.append("�˳�getInsertPos����\n");
			
			return root;
		} else {
			B_Tree btp = root;
			int index = 0;
			db.resultArea.append("��ǰ����¼Ϊ�� ");
			for(double data : root.record) {
				db.resultArea.append(data + " ");
			}
			db.resultArea.append("\n");
			/*
			 * 	�� btp ��ΪҶ�ӽ��ʱ��������ֵ����½�������ӡ���������
			 */
			while(btp.isRecordPtrEmpty(btp.recordPtr) == false) {
				while(key >= btp.record[index] && btp.record[index] != 0) {
					index++;
				}																					
				db.resultArea.append("����>recordPtr[" + index + "]" + "\n");
				btp = btp.recordPtr[index];
			}
			db.resultArea.append("�˳�getInsertPos����\n");
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
				db.resultArea.append("û�����ֵܣ�");
				return null;
			}
			
		} else {
			int ptrIndex = this.father.getIndexInArray(recordPtr, this);
			if(ptrIndex < keyNum) {
				return this.father.recordPtr[ptrIndex + 1];
			} else {
				db.resultArea.append("û�����ֵܣ�");
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
		db.resultArea.append("No Such Element��");
		return -1;
	}
	
	/*
	 * 	���ط���
	 */
	public int getIndexInArray(double[] array, double key) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == key) {
				return i;
			}
		}
		db.resultArea.append("No Such Element��");
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
	 * 	boolean�෽����
	 * 	�ж��Ƿ�ΪҶ�ӣ�����ǰ�ڵ�����ָ�������һλΪ�գ����Ȼ��������������ΪҶ��
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
