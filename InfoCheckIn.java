package practice8;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.util.Vector;

public class InfoCheckIn {
	JFrame j;
	
	JTextField name;
	JTextField id;
	JRadioButton male;	
	JRadioButton female;
	ButtonGroup sex;
	JPasswordField password;
	JPanel sexBoard;
	JPanel basicBoard;
	/////////////////////////
	
	JList<String> pro;	
	JCheckBox read;
	JCheckBox travel;
	JCheckBox sport;
	JLabel title2;
	JPanel hobby;
	JPanel proBoard;
	/////////////////////////
	
	JComboBox<String> year;
	JLabel title1;
	JButton stuff1;
	JButton stuff2;
	JPanel birthyearBoard;
	/////////////////////////
	
	JButton check;
	JButton flush;
	JPanel operateBoard;
	////////////////////////
	
	JPanel inputBoard;
	
	JScrollPane infoBoardScroller;
	JTextArea infoBoard;
	
	public InfoCheckIn() {
		j = new JFrame("ѧ����Ϣ�Ǽ����");
		j.setLayout(new GridLayout(1, 2, 5, 5));
		
		name = new JTextField();
		name.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "����"));
		id = new JTextField();
		id.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ѧ��"));
		
		sex = new ButtonGroup();
		sexBoard = new JPanel();
		sexBoard.setLayout(new FlowLayout());
		male = new JRadioButton("��");
		female = new JRadioButton("Ů");
		sex.add(male);
		sex.add(female);
		sexBoard.add(male);
		sexBoard.add(female);
		sexBoard.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "�Ա�"));
		
		
		password = new JPasswordField();
		password.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "����"));
		basicBoard = new JPanel();
		basicBoard.setLayout(new GridLayout(4, 1, 5, 5));
		basicBoard.add(name);
		basicBoard.add(id);
		basicBoard.add(sexBoard);
		basicBoard.add(password);
		basicBoard.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		Vector<String> proList = new Vector<>();
		proList.add("�����");
		proList.add("��ѧ");
		proList.add("����");
		pro = new JList<String>(proList);
		pro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "��ѡ��רҵ"));
		read = new JCheckBox("�Ķ�");
		travel = new JCheckBox("����");
		sport = new JCheckBox("�˶�");
		hobby = new JPanel();
		hobby.setLayout(new GridLayout(1, 3, 5, 5));
		hobby.add(read);
		hobby.add(travel);
		hobby.add(sport);
		hobby.setBorder(BorderFactory.createEtchedBorder());
		title2 = new JLabel("��ѡ��һ��������Ȥ����", JLabel.CENTER);
		proBoard = new JPanel();
		proBoard.setLayout(new GridLayout(3, 1, 5, 5));
		proBoard.add(pro);
		proBoard.add(title2);
		proBoard.add(hobby);
		proBoard.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		title1 = new JLabel("��ѡ��������", JLabel.CENTER);
		title1.setBorder(BorderFactory.createEtchedBorder());
		Vector<String> yearList = new Vector<>();	
		for(int i = 0; i < 10; i++) {
			yearList.add("199" + i);
		}
		for(int i = 0; i < 10; i++) {
			yearList.add("200" + i);
		}
		year = new JComboBox<String>(yearList);
		stuff1 = new JButton();
		stuff1.setEnabled(false);
		stuff1.setBackground(Color.CYAN);
		stuff1.setBorder(BorderFactory.createEmptyBorder());
		stuff2 = new JButton();
		stuff2.setEnabled(false);
		stuff2.setBackground(Color.CYAN);
		stuff2.setBorder(BorderFactory.createEmptyBorder());
		birthyearBoard = new JPanel();
		birthyearBoard.setLayout(new GridLayout(4, 1, 5, 5));
		birthyearBoard.add(stuff1);
		birthyearBoard.add(title1);
		birthyearBoard.add(year);
		birthyearBoard.add(stuff2);
		birthyearBoard.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		check = new JButton("ע��");
		check.addActionListener(new CheckIn(this));
		flush = new JButton("���");		
		flush.addActionListener(new FlushBoard(this));
		operateBoard = new JPanel();	
		operateBoard.setLayout(new GridLayout(2, 1, 5, 5));
		operateBoard.add(check);
		operateBoard.add(flush);
		operateBoard.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		inputBoard = new JPanel();
		inputBoard.setLayout(new GridLayout(2, 2, 5, 5));
		inputBoard.add(basicBoard);
		inputBoard.add(proBoard);
		inputBoard.add(birthyearBoard);
		inputBoard.add(operateBoard);
		
		infoBoard = new JTextArea();
		infoBoard.setEnabled(false);
		infoBoard.setFont(new Font("����", Font.BOLD, 20));
		infoBoard.setLineWrap(true);
		infoBoard.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
		infoBoardScroller = new JScrollPane(infoBoard);
		
		j.add(inputBoard);
		j.add(infoBoardScroller);
		j.setSize(1600, 800);
		j.setVisible(true);
		j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new InfoCheckIn();
	}
}

class CheckIn implements ActionListener {
	InfoCheckIn ici;
	public CheckIn(InfoCheckIn ici) {
		this.ici = ici;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ici.infoBoard.append("ѧ������Ϊ: " + ici.name.getText() + " ѧ��ѧ��Ϊ: " + ici.id.getText());
		if(ici.male.isSelected()) {
			ici.infoBoard.append(" ѧ���Ա�Ϊ: " + ici.male.getText());
		} else {
			ici.infoBoard.append(" ѧ���Ա�Ϊ: " + ici.female.getText());
		}
		ici.infoBoard.append(" ѧ��רҵ: " +ici.pro.getSelectedValue() + " ѧ������: ");
		if(ici.read.isSelected()) {
			ici.infoBoard.append(ici.read.getText());
		}
		if(ici.travel.isSelected()) {
			ici.infoBoard.append(ici.travel.getText());
		}
		if(ici.sport.isSelected()) {
			ici.infoBoard.append(ici.sport.getText());
		}
		ici.infoBoard.append(" �������: " + ici.year.getSelectedItem() + "\n");
	}
}

class FlushBoard implements ActionListener {
	InfoCheckIn ici;
	
	public FlushBoard(InfoCheckIn ici) {
		this.ici = ici;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ici.name.setText("");
		ici.id.setText("");
		ici.infoBoard.setText("");
	}
}