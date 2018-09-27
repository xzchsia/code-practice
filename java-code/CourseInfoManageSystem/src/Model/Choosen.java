package Model;

import java.util.HashMap;

import Entry.Main;

public class Choosen {
	static int ID = 1;
	public int id;
	public int course_id;
	public int student_user_id;
	public float course_score;

	public Choosen(int course_id, int course_name, float course_score) {
		this.id = this.ID++;
		this.course_id = course_id;
		this.student_user_id = course_name;
		this.course_score = course_score;
	}

	public Choosen(int id, int course_id, int course_name, float course_score) {
		if (this.ID <= id) {
			this.ID = id + 1;
		}

		this.id = id;
		this.course_id = course_id;
		this.student_user_id = course_name;
		this.course_score = course_score;
	}

	public String toString() {
		return this.id + "|" + this.course_id + "|" + this.student_user_id + "|" + this.course_score;
	}

	public static Choosen fromString(String inputStr) {
		String inputArray[] = inputStr.split("\\|");
		if (inputArray.length == 4) {
			Choosen tempChoosen = new Choosen(Integer.valueOf(inputArray[0]), Integer.valueOf(inputArray[1]),
					Integer.valueOf(inputArray[2]), Float.valueOf(inputArray[3]));
			return tempChoosen;
		}

		return null;
	}

	public Course course() {
		HashMap<String, String> queryCondition = new HashMap<String, String>();
		queryCondition.put("id", String.valueOf(this.course_id));
		return (Course) Main.ModelCourse.selectSingleObjectFromList(queryCondition);
	}

	public User user() {
		HashMap<String, String> queryCondition = new HashMap<String, String>();
		queryCondition.put("id", String.valueOf(this.student_user_id));
		return (User) Main.ModelUser.selectSingleObjectFromList(queryCondition);
	}
}
