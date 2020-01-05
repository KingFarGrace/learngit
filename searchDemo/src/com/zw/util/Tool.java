package com.zw.util;

import java.io.*;

import javax.swing.JOptionPane;

public class Tool {
	
	public static final String DATA_SET1_PATH = "test_set/set1.txt";
	public static final String DATA_SET2_PATH = "test_set/set2.txt";
	public static final String DATA_SET3_PATH = "test_set/set3.txt";
	
	private Tool() {
		// Singleton mode
	}
	
	private static Tool tool;
	
	public static Tool getInstance() {
		if(tool == null) {
			tool = new Tool();
		}
		return tool;
	}
	
	/*
	 * 从文件中提取double型数据
	 */
	public static double[] getArrayFromFile(String url) throws IOException {
		File file = new File(url);
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "找不到文件！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[(int) file.length()];
		for(int i = 0; i < file.length(); i++) {
			fis.read(buf);
		}
		fis.close();
		String str = new String(buf);
		String[] numStr = str.split(" ");
		double[] num = new double[numStr.length];
		for(int i = 0; i < numStr.length; i++) {
			num[i] = Double.parseDouble(numStr[i]);
		}
		return num;
	}
	
	/*
	 * 	重载方法
	 */
	public static double[] getArrayFromFile(File file) throws IOException {
		
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "找不到文件！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[(int) file.length()];
		for(int i = 0; i < file.length(); i++) {
			fis.read(buf);
		}
		fis.close();
		String str = new String(buf);
		String[] numStr = str.split(" ");
		double[] num = new double[numStr.length];
		for(int i = 0; i < numStr.length; i++) {
			num[i] = Double.parseDouble(numStr[i]);
		}
		return num;
	}
}
