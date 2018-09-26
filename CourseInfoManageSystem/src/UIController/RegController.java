package UIController;

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
import Model.User;
import ModelController.ModelUserController;

public class RegController extends MasterController {
	JTextField text_user_number;
	JTextField text_user_name;
	JTextField text_user_password;
	JTextField text_user_repeat_password;
	JRadioButton button_role_teacher;
	JRadioButton button_role_student;
	JButton button_reg;
	JButton button_back;

	public RegController() {
		super(300, 400, "ע����");
		// TODO Auto-generated constructor stub
	}

	public void addToPane() {
		// need to be overwrited

		JPanel Line1 = new JPanel();
		Line1.setLayout(new FlowLayout());
		Line1.add(new JLabel("ѧ/���ţ�"));
		Line1.add(text_user_number = new JTextField(12));
		list_check_fill.add(text_user_number);
		pane.add(Line1);

		JPanel Line2 = new JPanel();
		Line2.setLayout(new FlowLayout());
		Line2.add(new JLabel("������"));
		Line2.add(text_user_name = new JTextField(12));
		list_check_fill.add(text_user_name);
		pane.add(Line2);

		JPanel Line3 = new JPanel();
		Line3.setLayout(new FlowLayout());
		Line3.add(new JLabel("���룺"));
		Line3.add(text_user_password = new JPasswordField(12));
		list_check_fill.add(text_user_password);
		pane.add(Line3);

		JPanel Line4 = new JPanel();
		Line4.setLayout(new FlowLayout());
		Line4.add(new JLabel("�ظ����룺"));
		Line4.add(text_user_repeat_password = new JPasswordField(12));
		list_check_fill.add(text_user_repeat_password);
		pane.add(Line4);

		JPanel Line5 = new JPanel();
		Line5.setLayout(new FlowLayout());
		Line5.add(new JLabel("��ɫ��"));
		Line5.add(button_role_student = new JRadioButton("ѧ��"));
		Line5.add(button_role_teacher = new JRadioButton("��ʦ"));
		ButtonGroup button_group_role = new ButtonGroup();
		button_group_role.add(button_role_student);
		button_group_role.add(button_role_teacher);
		pane.add(Line5);

		pane.add(button_reg = new JButton("ע��"));
		button_reg.addActionListener(this);

		pane.add(button_back = new JButton("����"));
		button_back.addActionListener(this);

		pane.setLayout(new GridLayout(7, 1));
	}

	public void onRegButtonClick() {
		if (!this.isFill()) {
			this.showDialog("��Ϣ��д��ȫ����������ύ��");
			return;
		}

		if (!text_user_password.getText().equals(text_user_repeat_password.getText())) {
			this.showDialog("�����������벻һ�£���������ύ��");
			return;
		}

		// ע���߼�
		HashMap<String, String> queryCondition = new HashMap<String, String>();
		queryCondition.put("user_number", text_user_number.getText());
		if (Main.ModelUser.selectSingleObjectFromList(queryCondition) != null) {
			this.showDialog("ѧ�����ѱ�ע�ᣬ��������ύ��");
			return;
		}

		Main.ModelUser.addObjectToList(new User(text_user_number.getText(), text_user_name.getText(),
				text_user_password.getText(), (button_role_teacher.isSelected() ? 1 : 2)));

		this.showDialog("ע��ɹ������¼��");
		this.onBackButtonClick();
	}

	public void onBackButtonClick() {
		new LoginController();
		this.dispose();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button_reg) {
			this.onRegButtonClick();
		}

		if (e.getSource() == button_back) {
			this.onBackButtonClick();
		}
	}
}
