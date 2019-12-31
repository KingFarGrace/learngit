package com.KingFarGrace.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	
	private String name;
	private String startTime;	//�û���ʼ��ϰ��Ϸ��ʱ��			
	private String modeName;	//�û���ѡģʽ
	private String typeName;	//�û���ѡƪĿ/��Ϸ��
	private String typeTime;	//�û���ϰ����ʱ��
	private double curracy;		//��ȷ��
	private double score;	
	/*
	 * �û���Ϣ���飺
	 * 0.�û���
	 * 1.��ʼʱ��
	 * 2.��ϰģʽ
	 * 3.ƪĿ/��Ϸ��
	 * 4.��ϰ��ʱ
	 * 5.��ȷ��
	 * 6.�÷�
	 */
	private Object[] infoList;
	
	private User() {
		//Singleton mode
		infoList = new Object[7];
	}
	
	private static User user;
	
	public static User getInstance() {
		if(user == null) {
			user = new User();
		}
		return user;
	}
	
	public void setInfo(String name, String startTime, String modeName, String typeName) {
		this.name = name;
		this.startTime = startTime;
		this.modeName = modeName;
		this.typeName = typeName;
		
		infoList[0] = name;
		infoList[1] = startTime;
		infoList[2] = modeName;
		infoList[3] = typeName;
	}
	
	public void setTypeTime(String typeTime) {
		this.typeTime = typeTime;
		infoList[4] = typeTime;
	}
	
	public void setCurracy(double curracy) {
		this.curracy = curracy;
		infoList[5] = curracy;
	}
	
	public void setScore(double score) {
		this.score = score;
		infoList[6] = score;
	}
		
	public Object[] getInfoList() {
		infoList[0] = name;
		infoList[1] = startTime;
		infoList[2] = modeName;
		infoList[3] = typeName;
		infoList[4] = typeTime;
		infoList[5] = curracy;
		infoList[6] = score;
		return infoList;
	}
	
	public String getModeName() {
		return modeName;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public double getCurracy(int length, int falseType) {
		return (length - falseType) / length;
	}
	
	public double getScore() {
		return curracy * 3 * SourceLoader.textLength / Double.parseDouble(typeTime);
	}
	
	@Override
	public String toString() {
		return 	"�û�����Ϊ��" + name + " ��ʼʱ��Ϊ��" + startTime + " ��ѡģʽΪ��" + modeName +
					"ƪĿ/��Ϸ��Ϊ��" + typeName + " ��ϰ��ʱΪ��" +typeTime + " �÷�Ϊ��"+ score + "\n";
	}
}
