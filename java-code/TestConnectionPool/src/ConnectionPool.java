

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * ���ݿ����ӳ�
 * 1. ConnectionPool() ���췽��Լ����������ӳ�һ���ж�������
 * 2. ��init() ��ʼ�������У�������size�����ӡ� ע�⣬���ﲻ��ʹ��try-with-resource�����Զ��ر����ӵķ�ʽ��
 * 		��Ϊ����ǡǡ��Ҫ���ֲ��ر�״̬��������ѭ��ʹ��
 * 3. getConnection�� �ж��Ƿ�Ϊ�գ�����ǿյľ�wait�ȴ�������ͽ���һ�����ӳ�ȥ
 * 4. returnConnection�� ��ʹ����Ϻ󣬹黹������ӵ����ӳأ������ڹ黹��Ϻ󣬵���notifyAll��
 * 		֪ͨ��Щ�ȴ����̣߳����µ����ӿ��Խ����ˡ�
 */
public class ConnectionPool {

	List<Connection> cs = new ArrayList<Connection>();

	private int connsize;

	public ConnectionPool(int size) {
		this.connsize = size;
		init();
	}

	public void init() {

		// ����ǡǡ����ʹ��try-with-resource�ķ�ʽ����Ϊ��Щ���Ӷ���Ҫ��"��"�ģ���Ҫ���Զ��ر���
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for (int i = 0; i < this.connsize; i++) {
				Connection c = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/hutubill?characterEncoding=UTF-8&useSSL=false", "root", "abc123!");

				cs.add(c);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized Connection getConnection() {
		while (cs.isEmpty()) {
			try {
				// �ȴ���Դ����
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Connection c = cs.remove(0);
		return c;
	}

	public synchronized void returnConnection(Connection c) {
		cs.add(c);
		// ��Դ����֮��֪ͨ����Դ����
		this.notifyAll();
	}

}
