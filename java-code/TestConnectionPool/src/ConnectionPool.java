

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * 数据库连接池
 * 1. ConnectionPool() 构造方法约定了这个连接池一共有多少连接
 * 2. 在init() 初始化方法中，创建了size条连接。 注意，这里不能使用try-with-resource这种自动关闭连接的方式，
 * 		因为连接恰恰需要保持不关闭状态，供后续循环使用
 * 3. getConnection， 判断是否为空，如果是空的就wait等待，否则就借用一条连接出去
 * 4. returnConnection， 在使用完毕后，归还这个连接到连接池，并且在归还完毕后，调用notifyAll，
 * 		通知那些等待的线程，有新的连接可以借用了。
 */
public class ConnectionPool {

	List<Connection> cs = new ArrayList<Connection>();

	private int connsize;

	public ConnectionPool(int size) {
		this.connsize = size;
		init();
	}

	public void init() {

		// 这里恰恰不能使用try-with-resource的方式，因为这些连接都需要是"活"的，不要被自动关闭了
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
				// 等待资源返回
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
		// 资源返回之后，通知有资源可用
		this.notifyAll();
	}

}
