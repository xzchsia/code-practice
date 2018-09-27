package Model;

import java.util.HashMap;

import Entry.Main;

public class Course {
	static int ID = 1;
	public int id;
	public String course_code;
	public String course_name;
	public String course_hour;
	public float which_year;
	public int course_nature;// 1 公选，2专业课
	public int course_type;// 1 必修，2选修
	public float course_grade;
	public String course_room;
	public String course_time;
	public String course_week;
	public int teacher_id;
	public int member_count;

	public Course(String course_code, String course_name, String course_hour, float which_year, int course_nature,
			int course_type, float course_grade, String course_room, String course_time, String course_week,
			int member_count, int teacher_id) {
		this.id = this.ID++;
		this.course_code = course_code;
		this.course_name = course_name;
		this.course_hour = course_hour;
		this.which_year = which_year;
		this.course_nature = course_nature;
		this.course_type = course_type;
		this.course_grade = course_grade;
		this.course_room = course_room;
		this.course_time = course_time;
		this.course_week = course_week;
		this.teacher_id = teacher_id;
		this.member_count = member_count;
	}

	public Course(int id, String course_code, String course_name, String course_hour, float which_year,
			int course_nature, int course_type, float course_grade, String course_room, String course_time,
			String course_week, int member_count, int teacher_id) {
		if (this.ID <= id) {
			this.ID = id + 1;
		}

		this.id = id;
		this.course_code = course_code;
		this.course_name = course_name;
		this.course_hour = course_hour;
		this.which_year = which_year;
		this.course_nature = course_nature;
		this.course_type = course_type;
		this.course_grade = course_grade;
		this.course_room = course_room;
		this.course_time = course_time;
		this.course_week = course_week;
		this.teacher_id = teacher_id;
		this.member_count = member_count;
	}

	public String toString() {
		return this.id + "|" + this.course_code + "|" + this.course_name + "|" + this.course_hour + "|"
				+ this.which_year + "|" + this.course_nature + "|" + this.course_type + "|" + this.course_grade + "|"
				+ this.course_room + "|" + this.course_time + "|" + this.course_week + "|" + this.member_count + "|"
				+ this.teacher_id;
	}

	public static Course fromString(String inputStr) {
		String inputArray[] = inputStr.split("\\|");
		if (inputArray.length == 13) {
			Course tempCourse = new Course(Integer.valueOf(inputArray[0]), inputArray[1], inputArray[2], inputArray[3],
					Float.valueOf(inputArray[4]), Integer.valueOf(inputArray[5]), Integer.valueOf(inputArray[6]),
					Float.valueOf(inputArray[7]), inputArray[8], inputArray[9], inputArray[10],
					Integer.valueOf(inputArray[11]), Integer.valueOf(inputArray[12]));
			return tempCourse;
		}

		return null;
	}

	public int getChoosenNumber() {
		HashMap<String, String> queryCondition = new HashMap<String, String>();
		queryCondition.put("course_id", String.valueOf(this.id));
		return Main.ModelChoosen.selectManyObjectsFromList(queryCondition).size();
	}

	public boolean hasChoosen() {
		HashMap<String, String> queryCondition = new HashMap<String, String>();
		queryCondition.put("course_id", String.valueOf(this.id));
		queryCondition.put("student_user_id", String.valueOf(((User) Main.SessionHold.get("CurrentLoginUser")).id));
		return Main.ModelChoosen.selectSingleObjectFromList(queryCondition) != null;
	}
}
