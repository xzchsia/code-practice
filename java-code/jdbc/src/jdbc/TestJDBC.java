package jdbc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class TestJDBC {
	
	public static void execute(String sql) {
		return;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HeroDAO hd = new HeroDAO();
		hd.get(6);
		
//		Hero ho = new Hero();
//		Hero ho1 = new Hero();
//		ho.setName("≤‚ ‘21");
//		ho.setHp(120.43f);
//		ho.setDamage(89);
//		hd.add(ho);
//		System.out.println("----------------");
//		
//		ho1.setName("≤‚ ‘22–ﬁ∏ƒ");
//		ho1.setHp(120.43f);
//		ho1.setDamage(91);
//		ho1.setId(ho.id);
//		hd.update(ho1);
//		System.out.println("----------------");
		
		hd.list(0, 10);
		System.out.println("----------------");
		hd.list(10, 10);

	}

}
