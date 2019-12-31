package com.KingFarGrace.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	
	private String name;
	private String startTime;	//用户开始练习游戏的时间			
	private String modeName;	//用户所选模式
	private String typeName;	//用户所选篇目/游戏名
	private String typeTime;	//用户练习所用时间
	private double curracy;		//正确率
	private double score;	
	/*
	 * 用户信息数组：
	 * 0.用户名
	 * 1.开始时间
	 * 2.练习模式
	 * 3.篇目/游戏名
	 * 4.练习耗时
	 * 5.正确率
	 * 6.得分
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
		return 	"用户姓名为：" + name + " 开始时间为：" + startTime + " 所选模式为：" + modeName +
					"篇目/游戏名为：" + typeName + " 练习用时为：" +typeTime + " 得分为："+ score + "\n";
	}
}
