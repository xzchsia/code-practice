package ModelController;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Model.Course;

public abstract class ModelMasterController {
	public ArrayList<Object> list_store;
	String ClassName;

	public ModelMasterController(String ClassName) {
		this.ClassName = "Model." + ClassName;
		list_store = this.getRecordsFromFile();

		ModelMasterController ins_obj = this;

		Runtime run = Runtime.getRuntime();// 当前 Java 应用程序相关的运行时对象。
		run.addShutdownHook(new Thread() { // 注册新的虚拟机来关闭钩子
			@Override
			public void run() {
				// 程序结束时进行的操作
				ins_obj.saveRecordsToFile();
				System.out.println(ins_obj.ClassName + "已存盘！");
			}
		});
	}

	public ArrayList<Object> getRecordsFromFile() {
		ArrayList<Object> arrayListObject = new ArrayList<Object>();
		try {
			FileInputStream fileInput = new FileInputStream(this.ClassName + ".dat");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileInput));
			String stringLine;
			while ((stringLine = reader.readLine()) != null) {
				if (stringLine.equals("")) {
					continue;
				}
				Method m = Class.forName(this.ClassName).getMethod("fromString", new Class[] { String.class });
				Object tempCourse = null;
				tempCourse = m.invoke(tempCourse, stringLine);
				arrayListObject.add(tempCourse);
			}
			fileInput.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("文件打开或者操作错误，洗洗睡吧。");
		}
		return arrayListObject;
	}

	public void saveRecordsToFile() {
		String str_output = "";

		// 记住处理 | 分割问题

		Iterator<Object> iterator = list_store.iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			str_output += object.toString() + "\n";
		}

		try {
			FileOutputStream fileOutput = new FileOutputStream(this.ClassName + ".dat");
			byte byteTemp[] = str_output.getBytes();
			fileOutput.write(byteTemp);
			fileOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("文件打开或者操作错误，洗洗睡吧。");
		}
	}

	public void addObjectToList(Object object) {
		list_store.add(object);
	}

	public void deleteObjectFromList(Object object) {
		list_store.remove(object);
	}

	public void updateObjectToList(int index, Object object) {
		list_store.set(index, object);
	}

	public int getIndex(Object object) {
		return list_store.indexOf(object);
	}

	public Object selectSingleObjectFromList(HashMap<String, String> key_value) {
		Object array_object[] = list_store.toArray();
		for (Object object : array_object) {
			Iterator<String> iterator_want_check_key_set = key_value.keySet().iterator();
			boolean isMatch = true;
			while (iterator_want_check_key_set.hasNext()) {
				String key = iterator_want_check_key_set.next();
				try {
					if (!object.getClass().getDeclaredField(key).get(object).toString().equals(key_value.get(key))) {
						isMatch = false;
						break;
					}
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}

			if (isMatch == true) {
				return object;
			}
		}

		return null;
	}

	public ArrayList<Object> selectManyObjectsFromList(HashMap<String, String> key_value) {
		Object array_object[] = list_store.toArray();
		ArrayList<Object> list_return = new ArrayList<Object>();
		for (Object object : array_object) {
			Iterator<String> iterator_want_check_key_set = key_value.keySet().iterator();
			boolean isMatch = true;
			while (iterator_want_check_key_set.hasNext()) {
				String key = iterator_want_check_key_set.next();
				try {
					if (!object.getClass().getDeclaredField(key).get(object).toString().equals(key_value.get(key))) {
						isMatch = false;
						break;
					}
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					return null;
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					return null;
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}

			if (isMatch == true) {
				list_return.add(object);
			}
		}

		return list_return;
	}

	public ArrayList<Object> getAllObjectsFromList() {
		return list_store;
	}

}
