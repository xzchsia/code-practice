package UIController;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Entry.Main;
import Model.User;
import ModelController.ModelUserController;

public class LoginController extends MasterController{

	JTextField text_user_number;
	JTextField text_user_password;
	JButton button_login;
	JButton button_redirect_to_reg;
	
	public LoginController() {
		super(300, 200, "欢迎登录课程管理系统！");
		// TODO Auto-generated constructor stub
	}
	
	public void addToPane() {
		//need to be overwrited
		
		JPanel Line1 = new JPanel();
		Line1.setLayout(new FlowLayout());
		Line1.add(new JLabel("欢迎使用课程信息管理系统"));
		pane.add(Line1);
		
		JPanel Line2 = new JPanel();
		Line2.setLayout(new FlowLayout());
		Line2.add(new JLabel("学/工号："));
		Line2.add(text_user_number = new JTextField(12));
		pane.add(Line2);
		
		
		JPanel Line3 = new JPanel();
		Line3.setLayout(new FlowLayout());
		Line3.add(new JLabel("密码："));
		Line3.add(text_user_password = new JPasswordField(12));
		pane.add(Line3);
		
		pane.add(button_login = new JButton("登录"));
		button_login.addActionListener(this);
		
		pane.add(button_redirect_to_reg = new JButton("没有账号,注册一个"));
		button_redirect_to_reg.addActionListener(this);
		
		pane.setLayout(new GridLayout(5, 1));
	}
	
	public void onRedirectToRegButtonClick() {
		new RegController();
		this.dispose();
	}
	
	public void onLoginButtonClick() {
		HashMap<String, String> queryCondition = new HashMap<String, String>();
		queryCondition.put("user_number", text_user_number.getText());
		queryCondition.put("user_password", text_user_password.getText());
		
		User tempUser = (User) Main.ModelUser.selectSingleObjectFromList(queryCondition);
		if(tempUser != null) {
			this.showDialog("登录成功");
			
			Main.SessionHold.put("CurrentLoginUser", (Object)tempUser);
			
			if(tempUser.role_type == 1) {
				//老师
				new TeacherMainController();
				this.dispose();
			} else {
				//学生
				new StudentMainController();
				this.dispose();
			}
		} else {
			this.showDialog("用户名或者密码不正确，请检查后再登录！");
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button_redirect_to_reg) {
			this.onRedirectToRegButtonClick();
		}
		
		if(e.getSource() == button_login) {
			this.onLoginButtonClick();
		}
	}

}
