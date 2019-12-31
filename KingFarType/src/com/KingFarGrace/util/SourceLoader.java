package com.KingFarGrace.util;

import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SourceLoader {
	public static final String CHI_WORD_PATH = "data/chi_word/词语.txt";
	public static final String CHI_ARTICLE_PATH = "data/chi_article/";
	public static final String ENG_WORD_PATH = "data/eng_word/单词.txt";
	public static final String ENG_ARTICLE_PATH = "data/eng_article/";
	public static final String USER_DATA_PATH = "data/user_data/";
	public static final String PIC_PATH = "image/pic/";
	public static final String MUS_PATH = "image/mus/";
	public static final String BASIC_INFO_PATH = "data/user_data/basic";
	
	public static int rowNum;
	public static int textLength;
	
	private char[] text = new char[2048];
	private String[] buf;
	private String url;
	private File file;
	private File basicFile = new File(BASIC_INFO_PATH);
	private ObjectOutputStream oow;
	private ObjectInputStream oiw;
	private DataInputStream dis;
	private DataOutputStream dos;
	private FileReader fr;
	
	private SourceLoader() {
		//Singleton Mode
	}
	
	private static SourceLoader sl;
	
	public static SourceLoader getInstance() {
		if(sl == null) {
			sl = new SourceLoader();
		}
		return sl;
	}
	
	public void setPath(String url) {
		this.url = url;
		file = new File(this.url);
	}
	
	public String getURL() {
		return url;
	}
	
	public void getText() {
		try {
			text = null;
			text = new char[(int) file.length()];
			
			fr = new FileReader(file);
			fr.read(text);
			fr.close();
			buf = null;
			buf = new String(text).split(" ");
			rowNum = buf.length;
			textLength = text.length;
		} catch(IOException ioe) {
			JOptionPane.showMessageDialog(null, "找不到文件！");
		}
	}
	
	public String getTextPart() {
		String str = new String(text);
		return str;
	}
	
	/*
	 * 重载方法
	 */
	public String getTextPart(int start, int length) {
		String str = new String(text);
		return str.substring(start, start + length);
	}
	
	public String[] getWord(int start, int length) {
		String[] bufPart = new String[length];
		for(int i = start, j = 0; i < length; i++, j++) {
			bufPart[j] = buf[i];
		}

		return bufPart;
	}
	
	public void getRankList(DefaultTableModel model) {
		try {
			System.out.println(url);
			oiw = new ObjectInputStream(new FileInputStream(file));
			User user;
			System.out.println("OKKKKKKK");
			dis = new DataInputStream(new FileInputStream(basicFile));
			int count = dis.readInt();
			for(int i = 0; i < count; i++) {
				user = (User) oiw.readObject();
				model.addRow(user.getInfoList());
			}
			oiw.close();
			dis.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "找不到文件！");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "读取信息失败！");
		}
	}
	
	/*
	 * 重载方法
	 */
	public void getRankList(DefaultTableModel model, String modeName, String typeName) {
		try {
			oiw = new ObjectInputStream(new FileInputStream(file));
			User user = null;
			dis = new DataInputStream(new FileInputStream(basicFile));
			int count = dis.readInt();
			for(int i = 0; i < count; i++) {	
				user = (User) oiw.readObject();
				if(modeName.equals(user.getModeName()) && typeName.equals(user.getTypeName())) {
					model.addRow(user.getInfoList());
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "找不到文件！");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "读取信息失败！");
		}
	}
	
	public void setUserInfo(User user) {
		try {
			oow = new ObjectOutputStream(new FileOutputStream(file));
			oow.writeObject(user);
			dis = new DataInputStream(new FileInputStream(basicFile));
			int count = dis.readInt();
			count++;
			dos = new DataOutputStream(new FileOutputStream(basicFile));
			dos.writeInt(count);
			dis.close();
			dos.close();
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "找不到文件！");
		}
	}
}
