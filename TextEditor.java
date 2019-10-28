package practice10;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor {
	JFrame f;
	
	JMenuBar operateBoard;
	JMenu fileOperate;
	JMenuItem open;
	JMenuItem save;
	JMenuItem close;
	JMenuItem exit;
	
	JTextArea paper;
	JScrollPane scroller;
	
	JFileChooser fc;
	
	File file;
	
	String current = System.getProperty("user.sir");
	
	public TextEditor() {
		f = new JFrame("简易记事本");
		operateBoard = new JMenuBar();
		f.setJMenuBar(operateBoard);
		fileOperate = new JMenu("文件");
		operateBoard.add(fileOperate);
		open = new JMenuItem("打开");
		open.addActionListener(new OpenEvent(this));
		save = new JMenuItem("保存");
		save.addActionListener(new SaveEvent(this));
		close = new JMenuItem("关闭文件");
		close.addActionListener(new CloseEvent(this));
		exit = new JMenuItem("退出");
		exit.addActionListener(new ExitEvent(this));
		fileOperate.add(open);
		fileOperate.add(save);
		fileOperate.add(close);
		fileOperate.add(exit);
		
		paper = new JTextArea("");
		scroller = new JScrollPane(paper);
		f.add(scroller);
		
		f.setSize(800, 800);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TextEditor();
	}
}

class OpenEvent implements ActionListener {
	TextEditor te;
	
	public OpenEvent(TextEditor te) {
		this.te = te;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		te.fc = new JFileChooser(te.current);		
		int statu = te.fc.showOpenDialog(null);
		if (statu == JFileChooser.APPROVE_OPTION) {
			te.file = te.fc.getSelectedFile();
			try {
				FileReader fr = new FileReader(te.file);
				char[] ch = new char[1024];
				for(int i = 0 ; i < te.file.length(); i++) {
					ch[i] = (char) fr.read();
				}
				te.paper.append(new String(ch));
				fr.close();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		} else if (statu == JFileChooser.CANCEL_OPTION ||
				statu == JFileChooser.ERROR_OPTION) {
			te.fc.setVisible(false);
		}
		te.fc = null;
	}
}

class SaveEvent implements ActionListener {
	TextEditor te;
	
	public SaveEvent(TextEditor te) {
		this.te = te;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		te.fc = new JFileChooser(te.current);
		int statu = te.fc.showSaveDialog(null);
		if (statu == JFileChooser.APPROVE_OPTION) {
			te.file = te.fc.getSelectedFile();
			try {
				FileWriter fw = new FileWriter(te.file);
				String text = te.paper.getText();
				char[] ch = new char[1024];
				ch = text.toCharArray();
				for(char c : ch) {
					fw.write(c);
				}
				fw.close();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		} else if (statu == JFileChooser.CANCEL_OPTION ||
				statu == JFileChooser.ERROR_OPTION) {
			te.fc.setVisible(false);
		}
		
		te.fc.setVisible(false);
	}
}

class CloseEvent implements ActionListener {
	TextEditor te;
	
	public CloseEvent(TextEditor te) {
		this.te = te;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		te.paper.setText("");
	}
}

class ExitEvent implements ActionListener {
	TextEditor te;
	
	public ExitEvent(TextEditor te) {
		this.te = te;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);;
	}
}