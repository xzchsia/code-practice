package UIController.Teacher;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Entry.Main;
import Model.Course;
import Model.User;
import UIController.LoginController;
import UIController.MasterController;
import UIController.TeacherMainController;

public class AddCourseController extends MasterController {
	JButton button_submit;
	JButton button_back;

	JTextField text_course_code;
	JTextField text_course_name;
	JTextField text_course_hour;
	JTextField text_which_year;
	JRadioButton button_course_nature_1;// 1 ��ѡ��2רҵ��
	JRadioButton button_course_nature_2;
	JRadioButton button_course_type_1;// 1 ���ޣ�2ѡ��
	JRadioButton button_course_type_2;
	JTextField text_course_grade;
	JTextField text_course_room;
	JTextField text_course_time;
	JTextField text_course_week;
	JTextField text_member_amount;

	public AddCourseController() {
		super(300, 400, "��ӿγ�");
		// TODO Auto-generated constructor stub
	}

	public void onBackButtonClick() {
		new TeacherMainController(1);
		this.dispose();
	}

	public void onSubmitButtonClick() {
		if (!this.isFill()) {
			this.showDialog("��Ϣ��д��ȫ����������ύ��");
			return;
		}

		try {
			Main.ModelCourse.addObjectToList(new Course(text_course_code.getText(), text_course_name.getText(),
					text_course_hour.getText(), Float.valueOf(text_which_year.getText()),
					(button_course_nature_1.isSelected() ? 1 : 2), (button_course_type_1.isSelected() ? 1 : 2),
					Float.valueOf(text_course_grade.getText()), text_course_room.getText(), text_course_time.getText(),
					text_course_week.getText(), Integer.valueOf(text_member_amount.getText()),
					((User) Main.SessionHold.get("CurrentLoginUser")).id));

			this.showDialog("��ӳɹ���");
			this.onBackButtonClick();
		} catch (Exception e) {
			this.showDialog("���������ƺ���������������룡");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button_submit) {
			this.onSubmitButtonClick();
		}

		if (e.getSource() == button_back) {
			this.onBackButtonClick();
		}
	}

	@Override
	public void addToPane() {
		// TODO Auto-generated method stub
		JPanel Line1 = new JPanel();
		Line1.setLayout(new FlowLayout());
		Line1.add(new JLabel("�γ̴��룺"));
		Line1.add(text_course_code = new JTextField(12));
		list_check_fill.add(text_course_code);
		pane.add(Line1);

		JPanel Line2 = new JPanel();
		Line2.setLayout(new FlowLayout());
		Line2.add(new JLabel("�γ����ƣ�"));
		Line2.add(text_course_name = new JTextField(12));
		list_check_fill.add(text_course_name);
		pane.add(Line2);

		JPanel Line3 = new JPanel();
		Line3.setLayout(new FlowLayout());
		Line3.add(new JLabel("��ʱ����"));
		Line3.add(text_course_hour = new JTextField(12));
		list_check_fill.add(text_course_hour);
		pane.add(Line3);

		JPanel Line4 = new JPanel();
		Line4.setLayout(new FlowLayout());
		Line4.add(new JLabel("����ѧ�꣺"));
		Line4.add(text_which_year = new JTextField(12));
		list_check_fill.add(text_which_year);
		pane.add(Line4);

		JPanel Line5 = new JPanel();
		Line5.setLayout(new FlowLayout());
		Line5.add(new JLabel("�γ����ͣ�"));
		Line5.add(button_course_nature_1 = new JRadioButton("��ѡ��"));
		Line5.add(button_course_nature_2 = new JRadioButton("רҵ��"));
		ButtonGroup button_group_nature = new ButtonGroup();
		button_group_nature.add(button_course_nature_1);
		button_group_nature.add(button_course_nature_2);
		pane.add(Line5);

		JPanel Line6 = new JPanel();
		Line6.setLayout(new FlowLayout());
		Line6.add(new JLabel("�γ����ͣ�"));
		Line6.add(button_course_type_1 = new JRadioButton("���޿�"));
		Line6.add(button_course_type_2 = new JRadioButton("ѡ�޿�"));
		ButtonGroup button_group_type = new ButtonGroup();
		button_group_type.add(button_course_type_1);
		button_group_type.add(button_course_type_2);
		pane.add(Line6);

		JPanel Line7 = new JPanel();
		Line7.setLayout(new FlowLayout());
		Line7.add(new JLabel("ѧ�֣�"));
		Line7.add(text_course_grade = new JTextField(12));
		list_check_fill.add(text_course_grade);
		pane.add(Line7);

		JPanel Line8 = new JPanel();
		Line8.setLayout(new FlowLayout());
		Line8.add(new JLabel("���ң�"));
		Line8.add(text_course_room = new JTextField(12));
		list_check_fill.add(text_course_room);
		pane.add(Line8);

		JPanel Line9 = new JPanel();
		Line9.setLayout(new FlowLayout());
		Line9.add(new JLabel("�Ͽ�ʱ�䣨���� - �Ͽ�ʱ�䣩��"));
		pane.add(Line9);

		JPanel Line91 = new JPanel();
		Line91.setLayout(new FlowLayout());
		Line91.add(text_course_time = new JTextField(12));
		list_check_fill.add(text_course_time);
		pane.add(Line91);

		JPanel Line10 = new JPanel();
		Line10.setLayout(new FlowLayout());
		Line10.add(new JLabel("�����ܣ�"));
		Line10.add(text_course_week = new JTextField(12));
		list_check_fill.add(text_course_week);
		pane.add(Line10);

		JPanel Line11 = new JPanel();
		Line11.setLayout(new FlowLayout());
		Line11.add(new JLabel("�Ͽ�������"));
		Line11.add(text_member_amount = new JTextField(12));
		list_check_fill.add(text_member_amount);
		pane.add(Line11);

		pane.add(button_submit = new JButton("�ύ"));
		button_submit.addActionListener(this);

		pane.add(button_back = new JButton("����"));
		button_back.addActionListener(this);

		pane.setLayout(new GridLayout(14, 1));
	}

}
