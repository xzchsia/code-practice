package Entry;

import java.util.HashMap;

import ModelController.ModelChoosenController;
import ModelController.ModelCourseController;
import ModelController.ModelUserController;
import UIController.LoginController;

public class Main {

	public static HashMap<String, Object> SessionHold = new HashMap<String, Object>();
	public static ModelUserController ModelUser;
	public static ModelCourseController ModelCourse;
	public static ModelChoosenController ModelChoosen;

	public static void init() {
		ModelUser = new ModelUserController();
		ModelCourse = new ModelCourseController();
		ModelChoosen = new ModelChoosenController();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main.init();
		new LoginController();
	}

}
