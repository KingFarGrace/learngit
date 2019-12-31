package com.KingFarGrace.board;

import java.awt.*;
import javax.swing.*;

public class HelpDialog {
	public JDialog helpDialog;
	public JTextArea area1;
	public JTextArea area2;
	JScrollPane scroller1;
	JScrollPane scroller2;
	
	private final String help1 = 	"���������Щ���ܣ�" + "\n" +
									"1.��ϰ���Ĵ����ٶȣ���������;���ƪĿ����ϰ��" + "\n" +
									"2.��ϰӢ�Ĵ����ٶȣ��������ʺ;���ƪĿ����ϰ��" + "\n" +
									"3.ͨ��Ȥζ��Ϸ��ϰ�����ٶȣ�" + "\n" +
									"4.��¼ÿһ�δ��ֵ���ʱ��ֱ�ۿ����Լ��Ľ�����" + "\n" +
									"5.�������������Ժ󲹳䡣"; 
	
	private final String help2 =	"���ʹ�ñ������" + "\n" + 
									"1.����ҳ��ѡ���Լ���Ҫ����ϰģʽ�����ݵ����Ի�������ݲ�����" + "\n" +
									"2.�ڴ���ҳ����ݸ������ı����֣�������ʱ��ͣ�������" + "\n" +
									"3.������Ϻ����ʱ������ȷ�ʵó��÷֣��������а�";
	Font f = new Font("����", Font.BOLD, 24);
	
	public HelpDialog() {
		helpDialog = new JDialog();
		helpDialog.setLayout(new GridLayout(2, 1, 10, 10));
		area1 = new JTextArea(help1);
		area2 = new JTextArea(help2);
		area1.setEnabled(false);
		area1.setFont(f);
		area2.setEnabled(false);
		area2.setFont(f);
		scroller1 = new JScrollPane(area1);
		scroller2 = new JScrollPane(area2);
		helpDialog.add(scroller1);
		helpDialog.add(scroller2);
		helpDialog.setVisible(true);
		helpDialog.setSize(600, 600);
	}
}
