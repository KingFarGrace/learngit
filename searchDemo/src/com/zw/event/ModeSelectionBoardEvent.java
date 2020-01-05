package com.zw.event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.zw.board.*;
import com.zw.util.Tool;

public class ModeSelectionBoardEvent implements ActionListener {
	private ModeSelectionBoard msb;
	
	public ModeSelectionBoardEvent(ModeSelectionBoard msb) {
		this.msb = msb;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == msb.menuButton[0]) {
			String[] option = {"Ĭ�����ݼ�1", "Ĭ�����ݼ�2", "Ĭ�����ݼ�3" };
			int choice = JOptionPane.showOptionDialog(null, "��ѡ����Ҫʹ�õ����ݼ�", "", 
															JOptionPane.YES_NO_OPTION,
																JOptionPane.INFORMATION_MESSAGE,
																	null, option, option[0]);
			double[] dataSet = new double[30];
			if(choice == 0) {
				try {
					dataSet = Tool.getArrayFromFile(Tool.DATA_SET1_PATH);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "��ȡ�ļ�����", 
													"����", JOptionPane.ERROR_MESSAGE);
				}
				
			} else if(choice == 1) {
				try {
					dataSet = Tool.getArrayFromFile(Tool.DATA_SET2_PATH);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "��ȡ�ļ�����", 
													"����", JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				try {
					dataSet = Tool.getArrayFromFile(Tool.DATA_SET3_PATH);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "��ȡ�ļ�����", 
													"����", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			new DisplayingBoard(dataSet, msb.modelChoosen);
			msb.menu.setVisible(false);
			
		} else if(e.getSource() == msb.menuButton[1]) {
			JOptionPane.showMessageDialog(null, "��ʾ������ѡ��������ļ�ֻ�ܰ����ÿո�ֿ�������",
											"", JOptionPane.INFORMATION_MESSAGE);
			msb.fileChooser = new JFileChooser();
			int statu = msb.fileChooser.showOpenDialog(null);
			
			if(statu == JFileChooser.APPROVE_OPTION) {
				double[] dataSet = new double[10240];
				File file = msb.fileChooser.getSelectedFile();
				
				try {
					Tool.getArrayFromFile(file);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "��ȡ�ļ�����", 
													"����", JOptionPane.ERROR_MESSAGE);
				}
				
				new DisplayingBoard(dataSet, msb.modelChoosen);
				msb.menu.setVisible(false);
			}
									
		} else if(e.getSource() == msb.menuButton[2]) {
			new MainBoard();
			msb.menu.setVisible(false);
		}
	}
}
