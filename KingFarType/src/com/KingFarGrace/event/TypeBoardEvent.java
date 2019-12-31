package com.KingFarGrace.event;

import java.awt.event.*;

import javax.swing.JOptionPane;

import com.KingFarGrace.board.EndBoard;
import com.KingFarGrace.board.MainBoard;
import com.KingFarGrace.board.TypeBoard;
import com.KingFarGrace.util.SourceLoader;
import com.KingFarGrace.util.TimeTool;
import com.KingFarGrace.util.User;

public class TypeBoardEvent implements ActionListener {
	TypeBoard tb;
	MainBoard mb;
	
	public TypeBoardEvent(TypeBoard tb) {
		this.tb = tb;
	}
	
	public static int strCompare(String str1, String str2) {
		int count = 0;
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		for(int i = 0; i < str1.length(); i++) {
			if(c1[i] != c2[i]) {
				count++;
			}
		}
		return count;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SourceLoader sl = SourceLoader.getInstance();
		User user = User.getInstance();
		if(e.getSource() == tb.operateButton[0]) {
			
			if(tb.operateButton[0].getText().equals("开始")) {
				tb.operateButton[0].setText("结束");	
				tb.textArea.append(sl.getTextPart());
				TimeTool.addTimer();
			} else {
				int statu = JOptionPane.showConfirmDialog(null, "确定要结束吗？成绩将按照当前进度计算！",
															"",JOptionPane.OK_CANCEL_OPTION);
				if(statu == JOptionPane.OK_OPTION) {
					tb.operateButton[1].setText("开始");
					TimeTool.delTimer();
					int[] time = TimeTool.getIntervalTime();
					String timestr = time[0] + ":" + time[1] + ":" + time[2];
					user.setTypeTime(timestr);
					String typestr = tb.typeArea.getText();
					int typeLength = typestr.length();
					int falseType = 0;
					if(typeLength != SourceLoader.textLength) {
						falseType = typeLength < SourceLoader.textLength ? 
										SourceLoader.textLength - typeLength : 
											typeLength - SourceLoader.textLength ;
						double curracy = (typeLength - falseType) / typeLength;
						user.setCurracy(curracy);
					} else {
						falseType = strCompare(tb.textArea.getText(), typestr);
						double curracy = (typeLength - falseType) / typeLength;
						user.setCurracy(curracy);
					}
					double score = user.getScore();
					System.out.println(score);
					user.setScore(score);
					tb.typeBoard.setVisible(false);
					new EndBoard();
				}
			}
			
		} else if(e.getSource() == tb.operateButton[1]) {
			if(tb.operateButton[1].getText().equals("暂停") &&
					tb.operateButton[0].getText().equals("结束")) {
				tb.operateButton[1].setText("继续");
				TimeTool.pauseTimer();
			} else {
				tb.operateButton[1].setText("暂停");
				TimeTool.continueTimer();
			}
		}
	}
}
