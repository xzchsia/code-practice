package UIController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Entry.Main;
import Model.Choosen;
import Model.Course;
import Model.User;
import UIController.Teacher.AddCourseController;

public class TeacherMainController extends MasterController{

	JButton button_student_view;
	JButton button_course_manager;
	JButton button_choosen_view;
	JButton button_time_table_view;
	JButton button_course_grade;
	JPanel panel_center;
	JPanel panel_east;

	JButton button_add_course;
	JButton button_delete_course;
	JButton button_course_choosen;
	JButton button_course_score;
	JTable table;

	public TeacherMainController() {
		super(1100, 600, "�γ̹���ϵͳ--��ʦ��");
		// TODO Auto-generated constructor stub
	}

	public TeacherMainController(int Action) {
		super(1100, 600, "�γ̹���ϵͳ--��ʦ��");
		// TODO Auto-generated constructor stub

		switch(Action) {
			case 1:
				this.on_course_manager_click();
				break;
		}
	}

	public void on_course_manager_click() {
		JPanel table_panel = new JPanel();
		table_panel.setLayout(new BorderLayout());

		HashMap<String, String> queryCondition = new HashMap<String, String>();
		queryCondition.put("teacher_id", String.valueOf(((User)Main.SessionHold.get("CurrentLoginUser")).id));

		Object[][] cellData = new Object[Main.ModelCourse.selectManyObjectsFromList(queryCondition).size()][10];
		Iterator<Object> iterator = Main.ModelCourse.selectManyObjectsFromList(queryCondition).iterator();
		int i = 0;
		while(iterator.hasNext()) {
			Course tempCourse = (Course)iterator.next();
			if(tempCourse == null) {
				continue;
			}

			cellData[i][0] = tempCourse.id;
			cellData[i][1] = tempCourse.course_code;
			cellData[i][2] = tempCourse.course_name;
			cellData[i][3] = tempCourse.course_hour;
			cellData[i][4] = tempCourse.course_grade;
			cellData[i][5] = tempCourse.course_time;
			cellData[i][6] = tempCourse.course_room;
			cellData[i][7] = tempCourse.member_count;
			cellData[i][8] = tempCourse.getChoosenNumber();
			cellData[i][9] = tempCourse.which_year;
			i++;
		}

		Object[][] newCellData = new Object[i][10];
		System.arraycopy(cellData, 0, newCellData, 0, i);

		String[] columnNames = {"id", "�γ̴���", "�γ�����", "��ʱ��", "ѧ��", "�Ͽ�ʱ��", "�Ͽν���", "�Ͽ�����", "��ѡ����" ,"����ѧ��"};
		table = new JTable(newCellData, columnNames);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane panel_scroll = new JScrollPane(table);
		panel_scroll.setPreferredSize(new Dimension(800, 500));
		panel_center.add(panel_scroll);
		panel_center.add(new JLabel("˫��������Ա༭��Ϣ�����س������棬���id����Խ���ɾ�����鿴��ѡѧ������ѧ����ֵȲ�����"));

		TeacherMainController ins_obj = this;
		//�༭�¼�
		DefaultTableModel model = new DefaultTableModel(newCellData, columnNames)
		{
		    @Override
		    public boolean isCellEditable(int row, int column)
		    {
		    	if(column == 0 || column == 8) {
		    		return false;
		    	}

		    	return true;
		        // add your code here
		    }
		};

		table.setModel(model);

		table.getModel().addTableModelListener(new TableModelListener() {

	      public void tableChanged(TableModelEvent e) {
	    	  try{
		    	  int row = e.getFirstRow();
		          int column = e.getColumn();
		          TableModel model = (TableModel)e.getSource();
		          String columnName = model.getColumnName(column);
		          Object data = model.getValueAt(row, column);
	
		          if(columnName.equals("id")) {
		        	  ins_obj.showDialog("id������༭Ŷ~���α༭������Ч��");
		          }
	
		          int id = (int) model.getValueAt(row, 0);
	
		      	  HashMap<String, String> queryCondition = new HashMap<String, String>();
	    		  queryCondition.put("id", String.valueOf(id));
		      	  Course tempCourse = (Course) Main.ModelCourse.selectSingleObjectFromList(queryCondition);
		      	  
		      	  if(((String)data).indexOf('|') != -1) {
		      		  ins_obj.showDialog("�Ƿ��ַ�������������룡");
		      		  return;
		      	  }
	
		          if(columnName.equals("�γ̴���")) {
		        	  int index = Main.ModelCourse.getIndex(tempCourse);
		        	  tempCourse.course_code = (String)data;
		        	  Main.ModelCourse.updateObjectToList(index, tempCourse);
		        	  ins_obj.showDialog("�γ̴���༭�ɹ�~");
		          }
	
		          if(columnName.equals("�γ�����")) {
		        	  int index = Main.ModelCourse.getIndex(tempCourse);
		        	  tempCourse.course_name = (String)data;
		        	  Main.ModelCourse.updateObjectToList(index, tempCourse);
		        	  ins_obj.showDialog("�γ����Ʊ༭�ɹ�~");
		          }
	
		          if(columnName.equals("��ʱ��")) {
		        	  int index = Main.ModelCourse.getIndex(tempCourse);
		        	  tempCourse.course_hour = (String)data;
		        	  Main.ModelCourse.updateObjectToList(index, tempCourse);
		        	  ins_obj.showDialog("��ʱ���༭�ɹ�~");
		          }
	
		          if(columnName.equals("ѧ��")) {
		        	  int index = Main.ModelCourse.getIndex(tempCourse);
		        	  tempCourse.course_grade = Float.valueOf((String)data);
		        	  Main.ModelCourse.updateObjectToList(index, tempCourse);
		        	  ins_obj.showDialog("ѧ�ֱ༭�ɹ�~");
		          }
	
		          if(columnName.equals("�Ͽ�ʱ��")) {
		        	  int index = Main.ModelCourse.getIndex(tempCourse);
		        	  tempCourse.course_time = (String)data;
		        	  Main.ModelCourse.updateObjectToList(index, tempCourse);
		        	  ins_obj.showDialog("�Ͽ�ʱ��༭�ɹ�~");
		          }
	
		          if(columnName.equals("�Ͽν���")) {
		        	  int index = Main.ModelCourse.getIndex(tempCourse);
		        	  tempCourse.course_room = (String)data;
		        	  Main.ModelCourse.updateObjectToList(index, tempCourse);
		        	  ins_obj.showDialog("�Ͽν��ұ༭�ɹ�~");
		          }
	
		          if(columnName.equals("�Ͽ�����")) {
		        	  int index = Main.ModelCourse.getIndex(tempCourse);
		        	  tempCourse.member_count = Integer.valueOf((String)data);
		        	  Main.ModelCourse.updateObjectToList(index, tempCourse);
		        	  ins_obj.showDialog("�Ͽ������༭�ɹ�~");
		          }
	
		          if(columnName.equals("����ѧ��")) {
		        	  int index = Main.ModelCourse.getIndex(tempCourse);
		        	  tempCourse.which_year = Float.valueOf((String)data);
		        	  Main.ModelCourse.updateObjectToList(index, tempCourse);
		        	  ins_obj.showDialog("������ݱ༭�ɹ�~");
		          }
	    	  } catch (Exception e1) {
	  			  ins_obj.showDialog("���������ƺ���������������룡");
	  		  }
	      }
	    });
		
		table.setSize(table.getWidth() + 200, table.getHeight());

		panel_east.add(button_add_course = new JButton("��ӿγ�"));
		button_add_course.addActionListener(this);

		panel_east.add(button_delete_course = new JButton("ɾ���γ�"));
		button_delete_course.addActionListener(this);

		//button_course_choosen
		panel_east.add(button_course_choosen = new JButton("�γ���ѡѧ��"));
		button_course_choosen.addActionListener(this);

		panel_east.add(button_course_score = new JButton("�γ̴��"));
		button_course_score.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		panel_center.removeAll();
		panel_east.removeAll();

		if(e.getSource() == button_add_course) {
			new AddCourseController();
			this.dispose();
		}

		if(e.getSource() == button_delete_course) {
			this.on_course_delete_click();
		}

		if(e.getSource() == button_course_manager || e.getSource() == button_add_course || e.getSource() == button_delete_course) {
			this.on_course_manager_click();
		}


		if(e.getSource() == button_student_view) {
			this.on_student_view_click();
		}

		if(e.getSource() == button_time_table_view) {
			this.on_time_table_click();
		}

		if(e.getSource() == button_course_choosen) {
			this.on_course_choosen_click();
		}

		if(e.getSource() == button_course_score) {
			this.on_course_score_click();
		}

		panel_east.repaint();
		panel_center.repaint();

		//���԰취����޷�ˢ�µ�����
		this.setSize(this.getWidth() + 1, this.getHeight() + 1);
		this.setSize(this.getWidth() - 1, this.getHeight() - 1);
	}

	public void on_time_table_click() {
		// TODO Auto-generated method stub
		JPanel table_panel = new JPanel();
		table_panel.setLayout(new BorderLayout());

		HashMap<String, String> queryCondition = new HashMap<String, String>();
		queryCondition.put("teacher_id", String.valueOf(((User)Main.SessionHold.get("CurrentLoginUser")).id));
		
		Object[][] cellData = new Object[Main.ModelCourse.selectManyObjectsFromList(queryCondition).size()][8];
		Iterator<Object> iterator = Main.ModelCourse.selectManyObjectsFromList(queryCondition).iterator();
		int i = 0;
		while(iterator.hasNext()) {
			Course tempCourse = (Course)iterator.next();
			if(tempCourse == null) {
				continue;
			}

			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			if(!(tempCourse.which_year >= year && year + 1 > tempCourse.which_year)) {
				continue;
			}

			cellData[i][0] = tempCourse.id;
			cellData[i][1] = tempCourse.course_code;
			cellData[i][2] = tempCourse.course_name;
			cellData[i][3] = tempCourse.course_hour;
			cellData[i][4] = tempCourse.course_grade;
			cellData[i][5] = tempCourse.course_time;
			cellData[i][6] = tempCourse.course_room;
			cellData[i][7] = tempCourse.member_count;
			i++;
		}

		Object[][] newCellData = new Object[i][8];
		System.arraycopy(cellData, 0, newCellData, 0, i);

		String[] columnNames = {"id", "�γ̴���", "�γ�����", "��ʱ��", "ѧ��", "�Ͽ�ʱ��", "�Ͽν���", "�Ͽ�����"};
		table = new JTable(newCellData, columnNames);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane panel_scroll = new JScrollPane(table);
		panel_scroll.setPreferredSize(new Dimension(800, 500));
		panel_center.add(panel_scroll);

		//�༭�¼�
		DefaultTableModel model = new DefaultTableModel(newCellData, columnNames)
		{
		    @Override
		    public boolean isCellEditable(int row, int column)
		    {
		    	return false;
		        // add your code here
		    }
		};

		table.setModel(model);
	}

	public void on_course_choosen_click() {
		if(table != null) {
			if(table.getSelectedRow() == -1) {
				this.showDialog("����ûѡ����Ҫ�鿴�ı����أ�����ѡ��~");
				this.on_course_manager_click();
				return;
			}

			int course_id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);

			JPanel table_panel = new JPanel();
			table_panel.setLayout(new BorderLayout());


			HashMap<String, String> queryCondition = new HashMap<String, String>();
			queryCondition.put("course_id", String.valueOf(course_id));

			Object[][] cellData = new Object[Main.ModelChoosen.selectManyObjectsFromList(queryCondition).size()][10];
			Iterator<Object> iterator = Main.ModelChoosen.selectManyObjectsFromList(queryCondition).iterator();

			int i = 0;
			while(iterator.hasNext()) {
				Choosen tempChoosen = (Choosen)iterator.next();

				if(tempChoosen == null) {
					continue;
				}

				Course tempCourse = (Course)tempChoosen.course();
				if(tempCourse == null) {
					continue;
				}

				User tempUser = (User)tempChoosen.user();
				if(tempUser == null) {
					continue;
				}

				cellData[i][0] = tempChoosen.id;
				cellData[i][1] = tempUser.user_number;
				cellData[i][2] = tempUser.user_name;
				cellData[i][3] = tempCourse.course_code;
				cellData[i][4] = tempCourse.course_name;
				cellData[i][5] = tempCourse.course_hour;
				cellData[i][6] = tempCourse.course_grade;
				cellData[i][7] = tempCourse.course_time;
				cellData[i][8] = tempCourse.course_room;
				cellData[i][9] = tempCourse.member_count;
				i++;
			}

			Object[][] newCellData = new Object[i][10];
			System.arraycopy(cellData, 0, newCellData, 0, i);

			String[] columnNames = {"ѡ�μ�¼id", "ѧ��ѧ��", "ѧ������", "�γ̴���", "�γ�����", "��ʱ��", "ѧ��", "�Ͽ�ʱ��", "�Ͽν���", "�Ͽ�����"};
			table = new JTable(newCellData, columnNames);
			table.setFillsViewportHeight(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			JScrollPane panel_scroll = new JScrollPane(table);
			panel_scroll.setPreferredSize(new Dimension(800, 500));
			panel_center.add(panel_scroll);

			//�༭�¼�
			DefaultTableModel model = new DefaultTableModel(newCellData, columnNames)
			{
			    @Override
			    public boolean isCellEditable(int row, int column)
			    {
			    	return false;
			        // add your code here
			    }
			};

			table.setModel(model);
		}

		// TODO Auto-generated method stub

	}

	public void on_course_score_click() {
		if(table != null) {
			if(table.getSelectedRow() == -1) {
				this.showDialog("����ûѡ����Ҫ�鿴�ı����أ�����ѡ��~");
				this.on_course_manager_click();
				return;
			}

			int course_id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);

			JPanel table_panel = new JPanel();
			table_panel.setLayout(new BorderLayout());


			HashMap<String, String> queryCondition = new HashMap<String, String>();
			queryCondition.put("course_id", String.valueOf(course_id));

			Object[][] cellData = new Object[Main.ModelChoosen.selectManyObjectsFromList(queryCondition).size()][11];
			Iterator<Object> iterator = Main.ModelChoosen.selectManyObjectsFromList(queryCondition).iterator();

			int i = 0;
			while(iterator.hasNext()) {
				Choosen tempChoosen = (Choosen)iterator.next();

				if(tempChoosen == null) {
					continue;
				}

				Course tempCourse = (Course)tempChoosen.course();
				if(tempCourse == null) {
					continue;
				}

				User tempUser = (User)tempChoosen.user();
				if(tempUser == null) {
					continue;
				}

				cellData[i][0] = tempChoosen.id;
				cellData[i][1] = tempUser.user_number;
				cellData[i][2] = tempUser.user_name;
				cellData[i][3] = tempCourse.course_code;
				cellData[i][4] = tempCourse.course_name;
				cellData[i][5] = tempCourse.course_hour;
				cellData[i][6] = tempCourse.course_grade;
				cellData[i][7] = tempCourse.course_time;
				cellData[i][8] = tempCourse.course_room;
				cellData[i][9] = tempCourse.member_count;
				cellData[i][10] = tempChoosen.course_score;
				i++;
			}

			Object[][] newCellData = new Object[i][11];
			System.arraycopy(cellData, 0, newCellData, 0, i);

			String[] columnNames = {"ѡ�μ�¼id", "ѧ��ѧ��", "ѧ������", "�γ̴���", "�γ�����", "��ʱ��", "ѧ��", "�Ͽ�ʱ��", "�Ͽν���", "�Ͽ�����", "����"};
			table = new JTable(newCellData, columnNames);
			table.setFillsViewportHeight(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			JScrollPane panel_scroll = new JScrollPane(table);
			panel_scroll.setPreferredSize(new Dimension(800, 500));
			panel_center.add(panel_scroll);
			panel_center.add(new JLabel("˫�������е�����Խ��д�֣�������ɺ󰴻س������ɱ��棡"));

			TeacherMainController ins_obj = this;
			//�༭�¼�
			DefaultTableModel model = new DefaultTableModel(newCellData, columnNames)
			{
			    @Override
			    public boolean isCellEditable(int row, int column)
			    {
			    	if(column == 10) {
			    		return true;
			    	}
			    	return false;
			        // add your code here
			    }
			};

			table.setModel(model);

			table.getModel().addTableModelListener(new TableModelListener() {

			      public void tableChanged(TableModelEvent e) {
			    	  try{
			    		  int row = e.getFirstRow();
				          int column = e.getColumn();
				          TableModel model = (TableModel)e.getSource();
				          String columnName = model.getColumnName(column);
				          Object data = model.getValueAt(row, column);
	
				          if(columnName.equals("id")) {
				        	  ins_obj.showDialog("id������༭Ŷ~���α༭������Ч��");
				          }
				          
				          if(((String)data).indexOf('|') != -1) {
				      		  ins_obj.showDialog("�Ƿ��ַ�������������룡");
				      		  return;
				      	  }
	
				          int id = (int) model.getValueAt(row, 0);
	
			        	  HashMap<String, String> queryCondition = new HashMap<String, String>();
			      		  queryCondition.put("id", String.valueOf(id));
			        	  Choosen tempChoosen = (Choosen) Main.ModelChoosen.selectSingleObjectFromList(queryCondition);
	
				          if(columnName.equals("����")) {
				        	  int index = Main.ModelChoosen.getIndex(tempChoosen);
				        	  tempChoosen.course_score = Float.valueOf((String)data);
				        	  Main.ModelChoosen.updateObjectToList(index, tempChoosen);
				        	  ins_obj.showDialog("��ֳɹ�~");
				          }
			    	  } catch (Exception e1) {
			  			  ins_obj.showDialog("���������ƺ���������������룡");
			  		  }
			      }
			    });
		}

		// TODO Auto-generated method stub

	}

	public void on_student_view_click() {
		// TODO Auto-generated method stub
		JPanel table_panel = new JPanel();
		table_panel.setLayout(new BorderLayout());

		HashMap<String, String> queryCondition = new HashMap<String, String>();
		queryCondition.put("role_type", "2");

		Object[][] cellData = new Object[Main.ModelUser.selectManyObjectsFromList(queryCondition).size()][3];
		Iterator<Object> iterator = Main.ModelUser.selectManyObjectsFromList(queryCondition).iterator();
		int i = 0;
		while(iterator.hasNext()) {
			User tempUser = (User)iterator.next();
			if(tempUser == null) {
				continue;
			}
			cellData[i][0] = tempUser.id;
			cellData[i][1] = tempUser.user_name;
			cellData[i][2] = tempUser.user_number;
			i++;
		}

		String[] columnNames = {"id", "�û���", "ѧ��"};
		table = new JTable(cellData, columnNames);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane panel_scroll = new JScrollPane(table);
		panel_scroll.setPreferredSize(new Dimension(800, 500));
		panel_center.add(panel_scroll);

		//�༭�¼�
		DefaultTableModel model = new DefaultTableModel(cellData, columnNames)
		{
		    @Override
		    public boolean isCellEditable(int row, int column)
		    {
		    	return false;
		        // add your code here
		    }
		};

		table.setModel(model);
	}

	public void on_course_delete_click() {
		// TODO Auto-generated method stub
		if(table != null) {
			if(table.getSelectedRow() == -1) {
				this.showDialog("����ûѡ����Ҫɾ���ı����أ�����ѡ��~");
				return;
			}

			int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);

			HashMap<String, String> queryCondition = new HashMap<String, String>();
    		queryCondition.put("id", String.valueOf(id));
      	    Course tempCourse = (Course) Main.ModelCourse.selectSingleObjectFromList(queryCondition);

			Main.ModelCourse.deleteObjectFromList(tempCourse);
		}
	}

	@Override
	public void addToPane() {
		// TODO Auto-generated method stub

		pane.setLayout(new BorderLayout());
		pane.add("North", new JLabel("��ӭ��¼����ʦ "+ ((User)Main.SessionHold.get("CurrentLoginUser")).user_name));


		JPanel Row1 = new JPanel();
		Row1.setLayout(new FlowLayout());
		Row1.add(button_course_manager = new JButton("�γ̹���"));
		Row1.add(button_student_view = new JButton("ѧ���鿴"));
		Row1.add(button_time_table_view = new JButton("��ʦ�α�"));

		button_course_manager.addActionListener(this);
		button_student_view.addActionListener(this);
		button_time_table_view.addActionListener(this);

		Row1.setLayout(new GridLayout(5, 1));
		pane.add("West", Row1);

		panel_center = new JPanel();
		panel_center.add(new JLabel("��ӭ����������ߵİ�ť������ز�����"));
		pane.add("Center", panel_center);

		panel_east = new JPanel();
		panel_east.setLayout(new GridLayout(5,1));
		pane.add("East", panel_east);
	}

}
